package com.pdm.surfaceview;

// ejemplo basado en la página web:
// http://android-er.blogspot.com.es/2014/03/simple-surfaceview-example.html

/**
 * Created by ramon on 6/01/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

// Necesitamos una clase que herede de SurfaceView
// fíjate que en esta clase no se implementa la interfaz  SurfaceHolder.Callback como en otros ejemplos
// pero más adelante es usada en el método init()
public class MySurfaceView extends SurfaceView {

    // Definir un objeto de SurfaceHolder => Interfaz abstracta necesaria para manejar
    // un SurfaceView. Permite controlar el tamaño y formato de superficie, editar los píxeles
    // en la superficie, y monitorear los cambios en la superficie
    private SurfaceHolder surfaceHolder;

    // para poner imágenes de Mapas de Bits o BitMap
    private Bitmap bmpIcon;

    // Constructor
    public MySurfaceView(Context context) {
        super(context);
        init();
    }

    // Constructor 2
    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // Constructor 3
    public MySurfaceView(Context context,
                         AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // El acceso al SurfaceView se proporciona a través de la interfaz SurfaceHolder
        // con el método getHolder()
        surfaceHolder = getHolder();

        // asociar al bitmap la imagen ic_launcher.png que obtiene como un recurso
        bmpIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        // addCallback() Añade una interfaz de devolución de llamada para este Surface View
        // y la interfaz SurfaceHolder.Callback es para poder manejar los metodos relacionados con esta:
        // creación, modificación y destrucción, etc...
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            // evento que detecta cuando es creado el SurfaceView
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // lockCanvas() Comienza la edición de los píxeles en la superficie (SurfaceView)
                // definimos nulo el area en donde pintar
                Canvas canvas = holder.lockCanvas(null);

                // dibujamos en el canvas
                drawSomething(canvas);

                //liberamos el canvas
                holder.unlockCanvasAndPost(canvas);
            }

            // evento que detecta cambios en el SurfaceView
            @Override
            public void surfaceChanged(SurfaceHolder holder,
                                       int format, int width, int height) {
                // TODO Auto-generated method stub

            }

            // evento que detecta cuando es destruido el SurfaceView
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // TODO Auto-generated method stub

            }
        });
    }

    protected void drawSomething(Canvas canvas) {
        // pinto fondo de negro
        canvas.drawColor(Color.BLACK);

        // Dibujar el bitmap en la mitad del ancho y alto de la pantalla
        canvas.drawBitmap(bmpIcon, getWidth()/2, getHeight()/2, null);
    }

}


