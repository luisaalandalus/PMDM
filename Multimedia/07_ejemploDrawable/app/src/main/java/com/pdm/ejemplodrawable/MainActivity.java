package com.pdm.ejemplodrawable;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new FormasDibujables(this));
    }

    public class FormasDibujables extends View {

        ShapeDrawable sdRectan;

        public FormasDibujables (Context context, AttributeSet attrs) {

            super(context, attrs);

            // ShapeDrawable => permite crear gráficos a partir de primitivas vectoriales
            // RectShape() => crea un rectángulo de forma vectorial
            sdRectan = new ShapeDrawable(new RectShape());
            // Especifica el area donde se extrae el Drawable y lo grande que debe ser.
            // Todos los Elementos de dibujo debe respetar el tamaño solicitado,
            // a menudo simplemente escalando sus imágenes.
            sdRectan.setBounds(200, 5, 300, 100);

            // De color verde
            sdRectan.getPaint().setColor(Color.GREEN);
        }

        public FormasDibujables (Context context) {

            super(context);

            // ShapeDrawable => permite crear gráficos a partir de primitivas vectoriales
            // RectShape() => crea un rectángulo de forma vectorial
            sdRectan = new ShapeDrawable(new RectShape());
            // Especifica el area donde se extrae el Drawable y lo grande que debe ser.
            // Todos los Elementos de dibujo debe respetar el tamaño solicitado,
            // a menudo simplemente escalando sus imágenes.
            sdRectan.setBounds(200, 5, 300, 100);

            // De color verde
            sdRectan.getPaint().setColor(Color.GREEN);
        }

        @Override

        protected void onDraw(Canvas canvas) {
            // Pintar el rectángulo
            sdRectan.draw(canvas);
        }

    }


}
