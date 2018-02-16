package com.alandalus.luisaparragarcia.interfazusuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

public class ListSpinner extends AppCompatActivity {

    private TextView lblMensaje;
    private Spinner cmbOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        final String[] datos =
                new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};


        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);


        //Alternativa 1: Array java
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);

        //Alternativa 2: Recurso XML de tipo string-array
        //ArrayAdapter<CharSequence> adaptador =
        //	    ArrayAdapter.createFromResource(this,
        //	        R.array.valores_array, android.R.layout.simple_spinner_item);



        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        lblMensaje.setText("Seleccionado: " +
                                parent.getItemAtPosition(position));
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        lblMensaje.setText("");
                    }
                });

    }
}
