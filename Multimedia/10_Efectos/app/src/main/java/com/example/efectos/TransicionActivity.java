package com.example.efectos;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.view.View;


public class TransicionActivity extends Activity {

    private TransitionDrawable transition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ImageView image = new ImageView(this);

        // Establecer el layout a traves del objeto ImageView
        setContentView(image);

        // Instanciar un objeto, accediendo a los recursos para
        // conseguir las imágenes sobre las que hacer la transición
        transition = (TransitionDrawable) getResources().getDrawable(R.drawable.transition);

        // Establece un objeto drawable como contenido del ImageView
        image.setImageDrawable(transition);

        // Iniciar la transición de una imagen a otra durante 3,3 segundos
        transition.startTransition(3300);

        // Añadir un escuchador para que al hacer click, el método
        // reverseTransition () se encargue de la transición en ambos sentidos.
        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageDrawable(transition);
                // Iniciar la transición contraria de una imagen a otra durante 1,2 segundos
                transition.reverseTransition(1200);
            }
        });

    }

}
