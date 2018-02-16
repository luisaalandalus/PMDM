package com.alandalus.luisaparragarcia.interfazusuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class SelectGridView extends AppCompatActivity {
    private TextView lblMensaje;
    private GridView grdOpciones;

    private String[] datos = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_grid_view);

        for(int i=1; i<=50; i++)
            datos[i-1] = "Dato " + i;

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        lblMensaje = (TextView)findViewById(R.id.LblMensaje);
        grdOpciones = (GridView)findViewById(R.id.GridOpciones);

        grdOpciones.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent,
                                            android.view.View v, int position, long id) {
                        lblMensaje.setText("Opción seleccionada: "
                                + parent.getItemAtPosition(position));
                    }
                });

        grdOpciones.setAdapter(adaptador);
    }
}
