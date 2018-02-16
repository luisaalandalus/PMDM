package com.vogella.android.drawable.lightswitch;

// basado en uno de los ejemplos de:
// http://www.vogella.com/tutorials/AndroidDrawables/article.html

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class LightSwitchActivity extends Activity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carga el layout que está formado por una imagen
        // con dos posibles estados => bombilla apagada o encendida
        // y por un ToggleButton
        setContentView(R.layout.main);

        if (BuildConfig.DEBUG) {
            Log.i("CATEGORY", "AHHHHH!!!");
        }

        // acceder a la imagen del layout (bombilla)
        final ImageView image = (ImageView) findViewById(R.id.image);
        // acceder al ToggleButton del layout
        final ToggleButton button = (ToggleButton) findViewById(R.id.button);

        // definir que hacer al pulsar el ToggleButton
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                // definir el objeto para la animación a partir de la imagen del layout
                TransitionDrawable drawable = (TransitionDrawable) image.getDrawable();

                if (button.isChecked()) {
                    // si el botón está activado iniciar la transición
                    // de una imagen a otra (bombilla apagada/encendida) durante un segundo
                    drawable.startTransition(1000);
                } else {
                    // si el botón está desactivado iniciar la transición a la inversa
                    // de una imagen a otra durante un segundo ((bombilla encendida/apagada))
                    drawable.reverseTransition(1000);
                }
            }
        });
    }
}