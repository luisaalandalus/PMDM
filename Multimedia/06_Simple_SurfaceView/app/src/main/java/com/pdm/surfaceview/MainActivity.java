package com.pdm.surfaceview;

import android.app.Activity;
import android.os.Bundle;

//http://android-er.blogspot.com.es/2014/03/simple-surfaceview-example.html

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*<!-- Incluir en el layout el SurfaceView de la clase MySurfaceView -->
        <com.pdm.surfaceview.MySurfaceView
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
        * */
    }

}
