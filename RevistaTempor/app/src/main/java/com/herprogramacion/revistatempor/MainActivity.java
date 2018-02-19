package com.herprogramacion.revistatempor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
/**
 * Creado por Hermosa Programacion.
 */

public class MainActivity extends ActionBarActivity {

    /*
    Etiqueta para depuración
     */
    private final String TAG = MainActivity.class.getSimpleName();

    /*
    Declarar instancias globales
    */
    private RecyclerView recycler;
    private ListaAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private int cantidadItems;

    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar valores por defecto
        PreferenceManager.setDefaultValues(this, R.xml.settings_gen, false);
        PreferenceManager.setDefaultValues(this, R.xml.settings_twitter, false);

        // Procesar valores actuales de las preferencias.
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean miniaturasPref = sharedPref.getBoolean("miniaturas", true);
        cantidadItems = Integer.parseInt(sharedPref.getString("numArticulos", "8"));

        // Obtener el Recycler
        recycler = (RecyclerView) findViewById(R.id.reciclador);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new ListaAdapter(ConjuntoListas.randomList(cantidadItems), miniaturasPref);
        recycler.setAdapter(adapter);

        // Obtener el refreshLayout
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        // Seteamos los colores que se usarán a lo largo de la animación
        refreshLayout.setColorSchemeResources(
                R.color.s1,
                R.color.s2,
                R.color.s3,
                R.color.s4
        );

        // Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new HackingBackgroundTask().execute();
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Actualizar visibilidad de miniaturas
        boolean miniaturasPref = sharedPref.getBoolean("miniaturas", true);
        adapter.setConMiniaturas(miniaturasPref);

        // Actualizar cantidad de items
        cantidadItems = Integer.parseInt(sharedPref.getString("numArticulos", "8"));
        updateAdapter(ConjuntoListas.randomList(cantidadItems));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            // Iniciar la actividad de preferencias
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class HackingBackgroundTask extends AsyncTask<Void, Void, List<Lista>> {

        static final int DURACION = 3 * 1000; // 3 segundos de carga

        @Override
        protected List<Lista> doInBackground(Void... params) {
            // Simulación de la carga de items
            try {
                Thread.sleep(DURACION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Retornar en nuevos elementos para el adaptador
            return ConjuntoListas.randomList(cantidadItems);
        }

        @Override
        protected void onPostExecute(List<Lista> result) {
            super.onPostExecute(result);

            // Actualizar los elementos
            updateAdapter(result);

            // Parar la animación del indicador
            refreshLayout.setRefreshing(false);
        }

    }

    private void updateAdapter(List<Lista> result) {
        // Limpiar elementos antiguos
        adapter.clear();

        // Añadir elementos nuevos
        adapter.addAll(result);
    }
}

