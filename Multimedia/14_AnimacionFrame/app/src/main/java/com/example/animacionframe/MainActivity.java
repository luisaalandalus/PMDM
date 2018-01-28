package com.example.animacionframe;


import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

// Puedes consultar más información sobre las animaciones en:
// http://developer.android.com/intl/es/reference/android/graphics/drawable/AnimationDrawable.html

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = (Button) findViewById(R.id.btn_start);

        // escuchador del botón para cuando se hace click sobre él
        btn_start.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // iniciar la animación
                animate();
            }
        });
    }

    // animación de la imagen
    private void animate() {

        // acceder a la imagen
        ImageView imgView = (ImageView) findViewById(R.id.imagen);
        // hacer que sea visible
        imgView.setVisibility(ImageView.VISIBLE);
        // cargar la imagen desde el fichero frame_animation.xml
        imgView.setBackgroundResource(R.drawable.frame_animation);

        // Obtener el fondo, que ha sido compilado a un objeto AnimationDrawable
        AnimationDrawable frame = (AnimationDrawable) imgView.getBackground();

        if (frame.isRunning()) {
            // Si la animación está iniciada la para
            frame.stop();
        } else {
            // Si no está iniciada la animación, la para y la inicia
            frame.stop();
            frame.start();
        }
    }

}
