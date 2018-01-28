package com.exercise.AndroidAnimButtons;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AndroidAnimButtonsActivity extends Activity {
	
	LinearLayout layerImage;
    LinearLayout layerButtons;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
        
        animTranslate.setAnimationListener(animationListener);
        animAlpha.setAnimationListener(animationListener);
        animScale.setAnimationListener(animationListener);
        animRotate.setAnimationListener(animationListener);
        
        Button btnTranslate = (Button)findViewById(R.id.translate);
        Button btnAlpha = (Button)findViewById(R.id.alpha);
        Button btnScale = (Button)findViewById(R.id.scale);
        Button btnRotate = (Button)findViewById(R.id.rotate);
        final ImageView image = (ImageView)findViewById(R.id.image);
        
        layerImage = (LinearLayout)findViewById(R.id.layer_image);
        layerButtons = (LinearLayout)findViewById(R.id.layer_buttons);
        layerImage.setVisibility(View.INVISIBLE);
		layerButtons.setVisibility(View.VISIBLE);
        
        btnTranslate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				layerImage.setVisibility(View.VISIBLE);
				layerButtons.setVisibility(View.INVISIBLE);
				image.startAnimation(animTranslate);
			}});
        
        btnAlpha.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				layerImage.setVisibility(View.VISIBLE);
				layerButtons.setVisibility(View.INVISIBLE);
				image.startAnimation(animAlpha);
			}});
        
        btnScale.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				layerImage.setVisibility(View.VISIBLE);
				layerButtons.setVisibility(View.INVISIBLE);
				image.startAnimation(animScale);
			}});
        
        btnRotate.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				layerImage.setVisibility(View.VISIBLE);
				layerButtons.setVisibility(View.INVISIBLE);
				image.startAnimation(animRotate);
			}});
    }
    
    AnimationListener animationListener
    = new AnimationListener(){

		@Override
		public void onAnimationEnd(Animation arg0) {
			// TODO Auto-generated method stub
			layerImage.setVisibility(View.INVISIBLE);
			layerButtons.setVisibility(View.VISIBLE);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
    	
    };
}