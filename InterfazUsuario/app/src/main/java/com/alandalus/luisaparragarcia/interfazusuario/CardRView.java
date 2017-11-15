package com.alandalus.luisaparragarcia.interfazusuario;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CardRView extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private ArrayList<Palette> datos;

    private Button btnInsertar;
    private Button btnEliminar;
    private Button btnMover;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_rview);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerList);
        //Definimos tamaño fijo
        mRecyclerView.setHasFixedSize(true);


        this.setDatos();
        final MyRecycledViewAdapter adaptador = new MyRecycledViewAdapter(datos);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + mRecyclerView.getChildAdapterPosition(v));
            }
        });

        mRecyclerView.setAdapter(adaptador);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mRecyclerView.setLayoutManager(linearLayoutManager);



        btnInsertar = (Button)findViewById(R.id.btnAnadir);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.add(1, new Palette("PINK", "#FF4081", Color.parseColor("#ff4081")));
                adaptador.notifyItemInserted(1);
            }
        });

        btnEliminar = (Button)findViewById(R.id.btnEliminar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos.remove(1);
                adaptador.notifyItemRemoved(1);
            }
        });

        btnMover = (Button)findViewById(R.id.btnMover);

        btnMover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Palette aux = datos.get(1);
                datos.set(1,datos.get(2));
                datos.set(2,aux);

                adaptador.notifyItemMoved(1, 2);
            }
        });


    }


    private void setDatos() {
         datos = new ArrayList<>();
        datos.add(new Palette("RED", "#D32F2F", Color.parseColor("#d32f2f")));
        datos.add(new Palette("PINK", "#FF4081", Color.parseColor("#ff4081")));
        datos.add(new Palette("INDIGO", "#7B1FA2", Color.parseColor("#7b1fa2")));
        datos.add(new Palette("BLUE", "#536DFE", Color.parseColor("#536dfe")));
        datos.add(new Palette("GREEN", "#388E3C", Color.parseColor("#388e3c")));
        datos.add(new Palette("ORANGE", "#FF5722", Color.parseColor("#ff5722")));
        datos.add(new Palette("AMBER", "#FFA000", Color.parseColor("#ffa000")));
        }

}
