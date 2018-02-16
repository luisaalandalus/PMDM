package com.alandalus.luisaparragarcia.serviciomusica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * PRUEBA GIT HUB JSTALIN 
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button arrancar = (Button) findViewById(R.id.boton_arrancar);

        arrancar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                startService(new Intent(MainActivity.this,

                        ServicioMusica.class));

            }

        });

        Button detener = (Button) findViewById(R.id.boton_detener);

        detener.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                stopService(new Intent(MainActivity.this,

                        ServicioMusica.class));

            }

        });
    }
}
