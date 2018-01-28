package com.example.movcirculo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* OjO, ten en cuenta que se incorpora la vista definida en la clase MyView
        al layout activity_main.xml con esta etiqueta XML:
            <com.example.movcirculo.MyView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_centerInParent="true"
            android:layout_above="@+id/button1" />*/

        Button botonCerrar = (Button) findViewById(R.id.button1);

        //Evento para cerrar la actividad
        botonCerrar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                cerrarActividad();
            }
        });
    }

    public void cerrarActividad() {
        finish();
    }
}
