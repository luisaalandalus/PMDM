package com.pmdm.ud7_reproducirvideo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class Ud7ReproducirVideo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud7_reproducir_video);

        VideoView miVideoView = (VideoView) findViewById(R.id.videoView1);

		/*// fuente del vídeo	--- en raw		
        Uri pathVideo = Uri.parse("android.resource://" + getPackageName()
			+ "/" + R.raw.wildlife);
		// se asigna la fuente del fichero de vídeo
		miVideoView.setVideoURI(pathVideo);*/


        // fuente del vídeo -- Internet-- 
        String url = "http://dcomserver.gnd.upv.es/~jtomas/android/ficheros/video.3gp";
        //String url2="http://www.youcel.com/mp4_storage/106.mp4";
        // se asigna la fuente del fichero de vídeo
        miVideoView.setVideoURI(Uri.parse(url));

		
				
		/*// fuente del vídeo	--- en SD	de tablet
		String path = Environment.getExternalStorageDirectory()+"/DCIM/Camera/VID_20130504_111847.mp4";
		//String path = "/mnt/sdcard/DCIM/Camera/VID_20130504_111847.mp4";
		miVideoView.setVideoPath(path);	*/
		
		/*// fuente del vídeo	--- en SD grabado con programa GrabarVideo
		String path = Environment.getExternalStorageDirectory()+"/DCIM/Camera/videoOutput.3gp";
		//String path = "/mnt/sdcard/videoOutput.3gp";
		miVideoView.setVideoPath(path);	*/


        //miVideoView.start();
        // se le asigna el foco al vídeo
        miVideoView.requestFocus();

        // creación del objeto MediaControler --- asignación de botones de
        // control
        MediaController mController = new MediaController(this);
        // reacciona el objeto MediaControler con el objeto VideoView
        miVideoView.setMediaController(mController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ud7_reproducir_video, menu);
        return true;
    }

}
