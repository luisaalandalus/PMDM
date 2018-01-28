package com.example.movcirculo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

// Esta clase define una vista (View) nueva que se incorpora al layout de la actividad MainActivity
public class MyView extends View {

    // objeto de tipo Paint usado para dibujar el circulo
    public Paint p;

    private static final int RADIO = 30;

    // coordenadas X e Y del círculo
    private int centroX;
    private int centroY;

    // velocidad a la que cambian las coordenadas X e Y del circulo
    private int velocidadX = 55;
    private int velocidadY = 55;


    // Constructor
    public MyView(Context context, AttributeSet attr) {
        super(context, attr);
        p = new Paint(); // crea el objeto Paint para dibujar el círculo
        p.setColor(Color.RED); // establece el color a rojo
    }

    // Llamado cuando el tamaño de la vista ha cambiado
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        centroX = w / 2;
        centroY = h / 2;
    }

    // Dibuja sobre el Canvas el círculo
    @Override
    protected void onDraw(Canvas c) {
        // Anchura del canvas
        int w = getWidth(); // width
        // Altura del canvas
        int h = getHeight(); // height

        // modificamos la ubicación del círculo con la velocidad
        centroX += velocidadX;
        centroY += velocidadY;

        // Límites de pantalla (ancho y alto menos el radio del círculo)
        int limiteDerecha = w - RADIO;
        int limiteInferior = h - RADIO;

        // Comprobar si llegamos al límite => invertir
        if (centroX >= limiteDerecha) {
            // se ha llegado al límite de la derecha

            // poner la coordenada X del centro del círculo en el límite de la derecha
            centroX = limiteDerecha;
            // ahora la velocidadX es la contraria de la de antes
            // para retroceder y no sobrepasarse del límite
            velocidadX *= -1;
        }

        if (centroX <= RADIO) {
            // // se ha llegado al límite de la izquierda

            // poner la coordenada X del centro del círculo en el límite de la izquierda,
            // que es el valor del radio
            centroX = RADIO;
            // ahora la velocidadX es la contraria de la de antes
            // para retroceder y no sobrepasarse del límite
            velocidadX *= -1;
        }

        if (centroY >= limiteInferior) {
            // se ha llegado al límite inferior

            // poner la coordenada Y del centro del círculo en el límite inferior
            centroY = limiteInferior;
            // ahora la velocidadY es la contraria de la de antes
            // para retroceder y no sobrepasarse del límite
            velocidadY *= -1;
        }

        if (centroY <= RADIO) {
            // se ha llegado al límite superior

            // poner la coordenada Y del centro del círculo en el límite superior
            // que es el valor del radio
            centroY = RADIO;

            // ahora la velocidadY es la contraria de la de antes
            // para retroceder y no sobrepasarse del límite
            velocidadY *= -1;
        }

        // Dibujar el círculo en el canvas c
        c.drawCircle(centroX, centroY, RADIO, p);

        // define una animación con un retraso de 100 milisegundos
        postInvalidateDelayed(100);
    }
}
