package com.pmdm.ud7_grabaraudio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.File;

public class Ud7GrabarAudio extends Activity implements OnClickListener {
    private MediaPlayer mediaPlayer; // para reproducir
    private MediaRecorder recorder; // para grabar

    // fichero grabación
    private static final String OUTPUT_FILE = Environment.getExternalStorageDirectory() + "/record.3gpp";
    Button iniGrabar, paraGrabar, play, paraPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud7_grabar_audio);

        iniGrabar = (Button) findViewById(R.id.btnIniGrabar);
        paraGrabar = (Button) findViewById(R.id.btnPararGrabar);
        play = (Button) findViewById(R.id.btnPlay);
        paraPlay = (Button) findViewById(R.id.btnPararPlay);

        iniGrabar.setOnClickListener(this);
        paraGrabar.setOnClickListener(this);
        play.setOnClickListener(this);
        paraPlay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == iniGrabar) {
            try {
                comienzaGrabar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (v == paraGrabar) {
            try {
                terminaGrabar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (v == play) {
            try {
                reproduceGrabado();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (v == paraPlay) {
            try {
                detenPlay();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void comienzaGrabar() throws Exception {
        // libera el objeto de la grabación
        killMediaRecorder();

        // crear fichero donde se grabará el sonido
        File ficSalida = new File(OUTPUT_FILE);

        //si existe el fichero de salida se borra
        if (ficSalida.exists())
            ficSalida.delete();

        // Crea objeto y configura grabación
        recorder = new MediaRecorder();

        // Establecer el origen del audio => micrófono
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // Establecer formato de grabación mutimedia => 3GP
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        // Establecer formato de grabación audio=> AMR
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        // Establecer donde se grabará el fichero de audio
        recorder.setOutputFile(OUTPUT_FILE);

        // se prepara la grabación
        recorder.prepare();
        // se inicia la grabación
        recorder.start();

    }

    private void terminaGrabar() throws Exception {

        // comprueba si se ha iniciado la grabación
        if (recorder != null) {
            // termina la grabación
            recorder.stop();
        }

    }

    private void killMediaRecorder() throws Exception {

        if (recorder != null) {
            // libera el objeto de la grabación
            recorder.release();
        }

    }

    private void reproduceGrabado() throws Exception {
        // libera el objeto MediaPlayer para la reproducción
        killMediaPlayer();

        // crea un nuevo objeto MediaPlayer
        mediaPlayer = new MediaPlayer();
        // establece el origen (ruta del fichero a reproducir)
        mediaPlayer.setDataSource(OUTPUT_FILE);
        // prepara la reproducción
        mediaPlayer.prepare();
        // inicia la reproducción
        mediaPlayer.start();

    }

    private void killMediaPlayer() throws Exception {
        if (mediaPlayer != null) {
            try {
                // libera el objeto MediaPlayer para la reproducción
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void detenPlay() throws Exception {

        // comprueba si se está reproduciendo la grabación
        if (mediaPlayer != null) {
            // para de reproducir la grabación
            mediaPlayer.stop();
        }

    }

    @Override
    // llamado al finalizar la aplicación
    protected void onDestroy() {
        super.onDestroy();
        try {
            // libera el grabador
            killMediaRecorder();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // libera el reproductor
            killMediaPlayer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}







