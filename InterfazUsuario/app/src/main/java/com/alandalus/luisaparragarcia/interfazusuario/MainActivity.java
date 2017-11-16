package com.alandalus.luisaparragarcia.interfazusuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Controles Básicos 1
    public void abrirBotones(View v){
        Intent intent = new Intent(this,Botones.class);
        this.startActivity(intent);

    }
    public void abrirTextoImagenes(View v){
        Intent intent = new Intent(this,TextoImagenes.class);
        this.startActivity(intent);

    }
    public void abrirCheckRadio(View v){
        Intent intent = new Intent(this,CheckRadio.class);
        this.startActivity(intent);

    }
    public void abrirSpinner(View v){
        Intent intent = new Intent(this,ListSpinner.class);
        this.startActivity(intent);

    }
    public void abrirListaListView(View v){
        Intent intent = new Intent(this,ListaListView.class);
        this.startActivity(intent);

    }
    public void abrirSelectGridView(View v){
        Intent intent = new Intent(this,SelectGridView.class);
        this.startActivity(intent);

    }
    public void abrirCardRView(View v){
        Intent intent = new Intent(this,CardRView.class);
        this.startActivity(intent);

    }
}
