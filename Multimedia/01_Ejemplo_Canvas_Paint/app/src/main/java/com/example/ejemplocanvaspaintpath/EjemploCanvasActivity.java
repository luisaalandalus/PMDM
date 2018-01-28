package com.example.ejemplocanvaspaintpath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;

// Vista dibujada por código mediante un Canvas

public class EjemploCanvasActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Asociamos el objeto View a la actividad, pero sin layout
        // lo hacemos a través de una instancia de EjemploView
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {

        public EjemploView(Context context) {
            super(context);
        }

        // este método define lo que se va a dibujar en el Canvas
        @Override
        protected void onDraw(Canvas canvas) {

            // Crear un pincel de color verde
            Paint pincel = new Paint();
            pincel.setColor(Color.GREEN);
            pincel.setStrokeWidth(7); // grosor del pincel
            //pincel.setStyle(Style.STROKE); // solo pinta contorno
            pincel.setStyle(Style.FILL_AND_STROKE); // contorno e interior

            // Dibujar rectángulo
            canvas.drawRect(100, 120, 200, 220, pincel);

            // Color para dibujar un círculo
            pincel.setColor(getResources().getColor(R.color.color_circulo));
            // Dibujar círculo
            canvas.drawCircle(150, 250, 100, pincel);
        }

    }

}
