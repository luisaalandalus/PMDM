package com.pmdm.ud5sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorBD {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_TIPO = "tipo";
    public static final String KEY_TELEFONO = "telefono";

    private static final String TAG = "AdaptadorBD";

    private static final String DATABASE_NAME = "dbagenda";
    private static final String DATABASE_TABLE = "contactos";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + DATABASE_TABLE +
                    "(" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_NOMBRE + " text not null, "
                    + KEY_TIPO + " text not null, "
                    + KEY_TELEFONO + " text not null);";

    private final Context context;
    private BaseDatosHelper BDHelper;
    private SQLiteDatabase bsSql;
    private String[] todasColumnas = new String[]{KEY_ROWID, KEY_NOMBRE, KEY_TIPO, KEY_TELEFONO};


    //---constructor---
    public AdaptadorBD(Context ctx) {
        // dar valor del contexto
        this.context = ctx;
        // dar valores iniciales a la BD antes de crearla
        BDHelper = new BaseDatosHelper(context);
    }

    //--- abre una conexión a la BD para lectura/escritura
    public AdaptadorBD open() throws SQLException {
        bsSql = BDHelper.getWritableDatabase();
        return this;
    }

    //---cierra la base de datos---
    public void close() {
        BDHelper.close();
    }

    //-- INSERTAR
    // inserta una fila en la BD a partir de los datos de un contacto
    // devuelve el número de la fila insertada o -1 si ha ocurrido un error
    public long insertarContacto(String nombre, String tipo, String telefono) {

        // Crea un objeto ContentValues
        // que contendrá los valores de la fila de la tabla que después se insertará
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOMBRE, nombre);
        initialValues.put(KEY_TIPO, tipo);
        initialValues.put(KEY_TELEFONO, telefono);

        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(DATABASE_TABLE, null, initialValues);
    }

    // inserta una fila en la BD a partir de un objeto Contactos
    // devuelve el número de la fila insertada o -1 si ha ocurrido un error
    public long insertarContacto(Contactos contacto) {

        // Crea un objeto ContentValues
        // que contendrá los valores de la fila de la tabla que después se insertará
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOMBRE, contacto.getNombre());
        initialValues.put(KEY_TIPO, contacto.getTipo());
        initialValues.put(KEY_TELEFONO, contacto.getTipo());

        //manda una sentencia INSERT a la BD para insertar una fila con los valores initialValues
        return bsSql.insert(DATABASE_TABLE, null, initialValues);
    }

    //-- ELIMINAR
    // elimina el contacto identificado por numero
    // devuelve true si ha conseguido borrar el contacto y false en caso contrario
    public boolean borrarContacto(long numero) {
        //manda una sentencia DELETE a la BD para eliminar la fila identificada por numero
        return bsSql.delete(DATABASE_TABLE, KEY_ROWID + "=" + numero, null) > 0;
    }

    //--CONSULTAR
    // consulta a la BD para obtener todos los contactos
    // devuelve el objeto de la interface Cursor con el resultado de la consulta
    public Cursor getTodosContactos() {
        return bsSql.query(DATABASE_TABLE, todasColumnas, null, null, null, null, null);
    }

    //consulta de un contacto por 'numero' (clave primaria)
    public Cursor getContacto(long numero) throws SQLException {

        // realizar la consulta para seleccionar
        // solo a la fila con de la clave primaria "_id" que tenga el valor de "numero"
        // que se almanena en el objeto mCursor
        Cursor mCursor = bsSql.query(true, DATABASE_TABLE, todasColumnas,
                KEY_ROWID + "=" + numero, null, null, null, null, null);

        // si hay datos devueltos, apunta al principio
        // para poder consultar los datos desde el principio
        if (mCursor != null)
            mCursor.moveToFirst();

        // devuelbe el resultado de la consulta
        return mCursor;
    }

    //-- MODIFICAR
    // actualiza los datos del contacto identificado por numero, con los nuevos valores
    // pasados como parámetros
    // devuelve true si se ha actualizado algún contacto o false en caso contrario
    public boolean actualizarContacto(int numero, String nombre, String tipo, String telefono) {

        // Crea un objeto ContentValues
        // que contendrá los valores de la fila de la tabla
        // que después se usarán para actualizar el contacto
        ContentValues args = new ContentValues();
        args.put(KEY_NOMBRE, nombre);
        args.put(KEY_TIPO, tipo);
        args.put(KEY_TELEFONO, telefono);

        //manda una sentencia UPDATE a la BD para modificar el contacto
        // identificado por numero
        return bsSql.update(DATABASE_TABLE, args, KEY_ROWID + "=" + numero, null) > 0;
    }

    // actualiza los datos de un contacto concreto
    // devuelve true si se ha actualizado algún contacto o false en caso contrario
    public boolean actualizarContacto(Contactos contacto) {

        // Crea un objeto ContentValues
        // que contendrá los valores de la fila de la tabla
        // que después se usarán para actualizar el contacto
        ContentValues args = new ContentValues();
        args.put(KEY_NOMBRE, contacto.getNombre());
        args.put(KEY_TIPO, contacto.getTipo());
        args.put(KEY_TELEFONO, contacto.getTelefono());

        //manda una sentencia UPDATE a la BD para modificar el contacto
        // identificado por getnumero()
        return bsSql.update(DATABASE_TABLE, args, KEY_ROWID + "=" + contacto.getNumero(), null) > 0;
    }

    //-- OTROS MÉTODOS

    // Devuelve cadena con los datos de un contacto
    public String mostrarContacto(long numero) {
        String cadena = null;

        //obtiene objeto Cursor con el contacto consultado
        Cursor c = getContacto(numero);

        //si existe, se posiciona al principio
        if (c.moveToFirst()) {
            //compone la cadena con todos los datos del contacto
            cadena = "NÚMERO: " + c.getString(0) + "\n" +
                    "NOMBRE: " + c.getString(1) + "\n" +
                    "TIPO: " + c.getString(2) + "\n" +
                    "TELEFONO: " + c.getString(3);
        }

        return cadena;
    }

    //Devuelve cadena con los datos de un contacto (la fila de un Cursor)
    public String mostrarContacto(Cursor c) {
        String cadena = null;

        cadena = "NÚMERO: " + c.getString(0) + "\n" +
                "NOMBRE: " + c.getString(1) + "\n" +
                "TIPO: " + c.getString(2) + "\n" +
                "TELEFONO: " + c.getString(3);

        return cadena;
    }

    //Obtiene una lista de contactos a través de un objeto Cursor
    public List<Contactos> getAllContactos() {

        //Lista de contactos
        List<Contactos> listaContactos = new ArrayList<Contactos>();

        //objeto cursor que se llena con el resultado de la consulta
        // que obtiene todos los contactos
        Cursor cursor = this.getTodosContactos();

        //se posiciona al principio del cursor
        cursor.moveToFirst();

        // mientras hay datos en el cursor
        // isAfterLast() Devuelve si el cursor está apuntando
        // a la posición después de la última fila.
        while (!cursor.isAfterLast()) {
            //genera un contacto
            Contactos contacto = cursorToContactos(cursor);
            //añade un contacto a la lista mediante su método add()
            listaContactos.add(contacto);
            //avanza al siguiente
            cursor.moveToNext();
        }

        cursor.close();

        return listaContactos;
    }

    //genera un contacto a partir de un objeto Cursor
    private Contactos cursorToContactos(Cursor cursor) {
        Contactos contacto = new Contactos();
        contacto.setNumero(cursor.getLong(0));
        contacto.setNombre(cursor.getString(1));
        contacto.setTipo(cursor.getString(2));
        contacto.setTelefono(cursor.getString(3));

        return contacto;
    }


    //**** CLASE PRIVADA subclase SQLiteOpenHelper ***/
    //clase para crear la base de datos SQLite
    private static class BaseDatosHelper extends SQLiteOpenHelper {

        // constructor => para dar valores iniciales a la BD
        BaseDatosHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // este método se lanza la primera vez que se ejecuta la aplicación
        // y permite crear la BD
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //ejecuta la sentencia SQL de creación de la tabla de la BD
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // método para actualizar la BD
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Actualizando base de datos de la versión " + oldVersion
                    + " a "
                    + newVersion + ", borraremos todos los datos");
            //elimina tabla de la BD
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            //crea la nueva BD
            onCreate(db);
        }

    } // fin clase BaseDatosHelper


} // fin clase AdaptadorBD
