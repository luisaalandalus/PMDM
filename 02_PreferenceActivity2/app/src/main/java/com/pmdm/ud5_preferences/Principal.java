package com.pmdm.ud5_preferences;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // cargar el layout de la aplicación
        setContentView(R.layout.principal);
    }

    //botón, cargar pantalla de preferencias
    public void onClickLoad(View view) {
        Intent i = new Intent("com.pmdm.ud5_preferences.Preferencias");
        startActivity(i);
    }

    //botón, mostrar valores de preferencias
    public void onClickDisplay(View view) {
        // obtenemos una instancia SharedPreferences,
        // utilizando el formato <NombrePaquete>_preferences
        // OJO MUY IMPORTANTE => el nombre del paquete es en este ejemplo: com.pmdm.ud5_preferences
        // y a continuación se pone _preferences
        // por eso el nombre completo a cargar es: "com.pmdm.ud5_preferences_preferences"
        // MODE_PRIVATE: el archivo de preferencia solo lo puede abrir la aplicación que lo creó
        SharedPreferences appPrefs =
                getSharedPreferences("com.pmdm.ud5_preferences_preferences", MODE_PRIVATE);

        //recupera una preferencia de cadena pasándole la clave y valor por defecto si no existe la preferencia
        tostada(appPrefs.getString("editTextPref", ""));
    }

    //botón, modificar valores de preferencias
    public void onClickModify(View view) {
        // obtenemos una instancia SharedPreferences
        SharedPreferences appPrefs =
                getSharedPreferences("com.pmdm.ud5_preferences_preferences", MODE_PRIVATE);

        //obtiene un objeto Editor mediante edit() => preparado para cambiar las preferencias
        SharedPreferences.Editor prefsEditor = appPrefs.edit();

        // cambia el valor de la preferencia "editTextPref" mediante putString()
        // coge el valor del cuadro de texto txtString y se lo pone a esta preferencia
        prefsEditor.putString("editTextPref", ((EditText) findViewById(R.id.txtString)).getText().toString());

        //confirma los cambios
        prefsEditor.commit();

    }

    // muestra una tostada o mensaje de duración larga
    private void tostada(String str) {
        Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }


}
