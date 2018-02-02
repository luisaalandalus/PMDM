package com.example.ip_d.viewflipperexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper simpleViewFlipper;
    private float initialX;
    int[] images = {R.drawable.apple, R.drawable.pineapple, R.drawable.litchi, R.drawable.mango, R.drawable.banana};     // array of images

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // get The references of ViewFlipper
        simpleViewFlipper = (ViewFlipper) findViewById(R.id.simpleViewFlipper); // get the reference of ViewFlipper

        // loop for creating ImageView's
        for (int i = 0; i < images.length; i++) {
            // create the object of ImageView
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]); // set image in ImageView
            simpleViewFlipper.addView(imageView); // add the created ImageView in ViewFlipper
        }
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type's to ViewFlipper
        simpleViewFlipper.setInAnimation(in);
        simpleViewFlipper.setOutAnimation(out);
        // set interval time for flipping between views
        simpleViewFlipper.setFlipInterval(3000);
        // set auto start for flipping between views
        simpleViewFlipper.setAutoStart(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialX > finalX) {
                    if (simpleViewFlipper.getDisplayedChild() == images.length)
                        break;

                    simpleViewFlipper.showNext();
                } else {
                    if (simpleViewFlipper.getDisplayedChild() == 0)
                        break;



                    simpleViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }

}


