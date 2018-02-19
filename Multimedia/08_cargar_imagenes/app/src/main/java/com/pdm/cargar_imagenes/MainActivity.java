package com.pdm.cargar_imagenes;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout miLay = new LinearLayout(this);
        miLay.setOrientation(LinearLayout.VERTICAL);

        // Crear objeto ImageView => imagen del robot de Android
        ImageView miImagen = new ImageView(this);
        miImagen.setImageResource(R.drawable.android);

        // Crear objeto Drawable => imagen de Almería
        Drawable miObjetoDrawable = this.getResources().getDrawable(R.drawable.almeria);

        // Crear objeto ImageView
        ImageView miSegundaimg = new ImageView(this);
        miSegundaimg.setImageDrawable(miObjetoDrawable);

        // Añadir al layout las imágenes
        miLay.addView(miImagen);
        miLay.addView(miSegundaimg);

        // Establecer el layout de la aplicación definido en el objeto miLay
        setContentView(miLay);
    }

}