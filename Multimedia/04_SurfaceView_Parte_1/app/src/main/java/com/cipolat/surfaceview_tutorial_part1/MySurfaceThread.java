package com.cipolat.surfaceview_tutorial_part1;

// Ejemplo sacado de:
// http://androideity.com/2013/02/12/usando-surfaceview-en-android-parte-1/

// Clase SurfaceView
// => http://developer.android.com/intl/es/reference/android/view/SurfaceView.html

import android.graphics.Canvas;
import android.view.SurfaceHolder;

// Esta clase permite crear hilos para dibujar un SurfaceView sobre un Canvas
public class MySurfaceThread extends Thread {

    // Definir un objeto de SurfaceHolder => Interfaz abstracta necesaria para manejar
    // un SurfaceView. Permite controlar el tamaño y formato de superficie, editar los píxeles
    // en la superficie, y monitorear los cambios en la superficie
    private SurfaceHolder sh;

    // definir un objeto de la clase MySurfaceView (hereda de SurfaceView e implementa SurfaceHolder)
    private MySurfaceView view;

    // para determinar si el hilo pinta o no
    private boolean pinta;

    // El Constructor recibe como parámetros la referencia a SurfaceHolder y nuestro SurfaceView
    public MySurfaceThread(SurfaceHolder sh, MySurfaceView view) {
        this.sh = sh;
        this.view = view;
        pinta = false; // por defecto el hilo no pintará
    }

    // Lo utilizaremos para establecer cuando el hilo pinta o no.
    public void setRunning(boolean pintar) {
        this.pinta = pintar;
    }

    // método que se ejecuta cuando se inicia el hilo
    public void run() {
        //Instancia a canvas
        Canvas canvas;

        //Mientras la variable "pinta" sea true va a pintar contínuamente.
        while (pinta) {
            canvas = null;
            try {
                // lockCanvas() Comienza la edición de los píxeles en la superficie (SurfaceView)
                // definimos nulo el area en donde pintar
                canvas = sh.lockCanvas(null);

                //usamos synchronized para asegurarnos que no haya ningún otro hilo usando ese objeto
                synchronized (sh) {
                    // El SurfaceView ejecuta el metodo onDraw() para dibujar sobre el canvas
                    view.onDraw(canvas);
                }
            } finally {
                /* En caso de que halla algún error liberamos el canvas
                 * para no dejar el surfaceview en un estado inconsistente
				 */
                if (canvas != null)
                    //liberamos el canvas
                    sh.unlockCanvasAndPost(canvas);
            }
        }
    }
}

