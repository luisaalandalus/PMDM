package com.pmdm.ud5sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Ud5_SQLiteActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button botonAdd = (Button) findViewById(R.id.btnAdd);
        final Button botonConsulta = (Button) findViewById(R.id.btnConsulta);

        final Intent IntentConsulta = new Intent(Ud5_SQLiteActivity.this, Consulta.class);
        final Intent IntentAdd = new Intent(Ud5_SQLiteActivity.this, AddContactos.class);


        //evento onClick botón 'Añadir contactos'
        botonAdd.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                startActivity(IntentAdd);
            }
        });

        //evento onClick botón 'Consultar contactos'
        botonConsulta.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                startActivity(IntentConsulta);
            }
        });


    }


}