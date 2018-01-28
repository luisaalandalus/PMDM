package com.cipolat.surfaceview_tutorial_part1;

// Ejemplo sacado de:
// http://androideity.com/2013/02/12/usando-surfaceview-en-android-parte-1/

// Clase SurfaceView
// => http://developer.android.com/intl/es/reference/android/view/SurfaceView.html

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // oculto el título
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // ocultamos barras de notificaciones
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // defino que en lugar de usar una UI (Interfaz de Usuario) de /res/layout
        // utilizo la vista que brinda la clase MySurfaceView
        setContentView(new MySurfaceView(this));

    }

}
