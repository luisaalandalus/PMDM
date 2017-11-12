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
}
