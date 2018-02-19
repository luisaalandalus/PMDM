package com.cipolat.surfaceview_tutorial_part1;

// Ejemplo sacado de:
// http://androideity.com/2013/02/12/usando-surfaceview-en-android-parte-1/

// Clase SurfaceView
// => http://developer.android.com/intl/es/reference/android/view/SurfaceView.html


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// Necesitamos una clase que herede de SurfaceView
// y además para acceder al SurfaceView es necesario hacerlo
// mediante la interfaz SurfaceHolder.Callback para poder manejar los metodos relacionados con esta:
// creación, modificación y destrucción, etc...
// El concepto de CallBack (devoluciones de llamada) es informar a una clase
// si algún trabajo en otra clase se hace => (eventos de creación, modificación, etc... en este caso)
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    //Referencia a un thread o hilo que usaremos para dibujar
    public MySurfaceThread thread;

    // constructor
    public MySurfaceView(Context context) {
        super(context);

        // El acceso al SurfaceView se proporciona a través de la interfaz SurfaceHolder
        // con el método getHolder() => que devuelve un SurfaceHolder
        // Y addCallback() Añade una interfaz de devolución de llamada SurfaceHolder
        getHolder().addCallback(this);
    }

    // evento que detecta cambios en el SurfaceView
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    // evento que detecta cuando es creado el SurfaceView
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // Creamos el hilo => objeto de la clase MySurfaceThread
        // La clase MySurfaceThread permite crear hilos para dibujar un SurfaceView sobre un Canvas
        thread = new MySurfaceThread(getHolder(), this);
        // Establecemos que el hilo pueda pintar
        thread.setRunning(true);
        // Iniciamos el hilo
        thread.start();
    }

    // evento que detecta cuando es destruido el SurfaceView
    // Cada vez que la app pase a segundo plano el SurfaceView se va a destruir junto
    // con la totalidad de su contenido ejecutandose SurfaceDestroyed(),
    // el hilo se va a detener y liberar el canvas
    // lo que implica que cuando la app vuelva a primer plano debera volver a dibujar todo
    // nuevamente deberán tener en cuenta esto a la hora de utilizar esto.
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {

        Log.e("surfaceDestroyed ", "Hilo detenido ");

        boolean retry = true;

        // establecer que el hilo que dibuja el Surfaview no pueda pintar
        // , ya que el SurfaceView o superfice ha sido destruida
        thread.setRunning(false);

        // el bucle se ejecute mientras que el hilo "thread" no haya acabado de pintar
        while (retry) {
            try {
                // el hilo actual (hilo principal de la aplicación) debe esperar
                // a que el hilo "thread" termine de ejecutarse
                thread.join();
                retry = false; // para acabar el bucle

            } catch (InterruptedException e) {
                Log.e("surfaceDestroyed", "Error join() => Esperando que el hilo 'thread' termine de ejecutarse ");
            }
        }
    }

    // evento para dibujar en la superficie del canvas
    @Override
    public void onDraw(Canvas canvas) {

        int ancho = canvas.getWidth();
        int alto = canvas.getHeight();

        // mitad de ancho
        int mitadW = ancho / 2;
        // mitad de largo
        int mitadH = alto / 2;

        // pinto fondo de blanco
        canvas.drawColor(Color.WHITE);

        // definimos pincel
        Paint pcirculo = new Paint();
        pcirculo.setColor(Color.BLACK);
        pcirculo.setStyle(Paint.Style.FILL);

        //dibujamos circulo usando ese pincel en la mitad de la pantalla
        canvas.drawCircle(mitadW, mitadH, 60, pcirculo);

    }

    // Evento que detecta cuando hacemos click sobre el dibujo pintado con onDraw()
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub

        // Lo veremos en la siguiente entrega
        // => http://androideity.com/2013/03/23/usando-surfaceview-en-android-parte-2/
        return true;

    }
}