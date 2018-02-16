package com.pdm.path_canvas_ejemplo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {

        public EjemploView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // Crear un trazo
            Path trazo = new Path();

            // Definir un círculo con dirección contraria a las agujas del reloj CCW
            // la dirección de las agujas del reloj es CW
            /*/**
             * Add a closed circle contour to the path
             *
             * @param x   The x-coordinate of the center of a circle to add to the path
             * @param y   The y-coordinate of the center of a circle to add to the path
             * @param radius The radius of a circle to add to the path
             * @param dir    The direction to wind the circle's contour (CW o CCW)
             */
            trazo.addCircle(160, 160, 120, Path.Direction.CW);
            canvas.drawColor(Color.GRAY); //definir el color del lienzo o Canvas => gris

            // Crear un pincel y definirle las características
            Paint pincel = new Paint();
            pincel.setColor(Color.RED);
            pincel.setStrokeWidth(5);// grosor del trazado
            //rellena la figura por dentro y en el exterior (el círculo en este caso)
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);

            // Dibujar el trazo sobre el canvas con los datos
            // definidos para el pincel
            canvas.drawPath(trazo, pincel);

            // Características del pincel para escribir texto
            pincel.setStrokeWidth(1); // grosor del trazado
            pincel.setColor(Color.GREEN);
            pincel.setStyle(Paint.Style.STROKE); //rellena el texto solo por el exterior
            pincel.setTextSize(20); // tamaño del texto
            // para conseguir que el texto sea más o menos ancho
            // => no confundir con el grosor del trazado setStrokeWidth()
            // ancho o grosor del texto por defecto es 1.0
            // si ponemos 2 es el ancho o grosor doble del texto
            pincel.setTextScaleX((float) 1.25);
            // tipo de letra
            pincel.setTypeface(Typeface.SANS_SERIF);

            // Escribir el texto
            canvas.drawTextOnPath("DAM y ASIR. en IES. Al-Andalus", trazo, 0, 0, pincel);
            //canvas.drawTextOnPath("DAM y ASIR. en IES. Al-Andalus", trazo, 10, 40, pincel);
        }

    }
}
