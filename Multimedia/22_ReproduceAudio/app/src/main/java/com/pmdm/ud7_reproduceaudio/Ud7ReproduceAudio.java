package com.pmdm.ud7_reproduceaudio;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.IOException;

public class Ud7ReproduceAudio extends Activity implements OnClickListener {
    // objeto MediaPlayer para reproducir audio
    private MediaPlayer mediaPlayer;
    private int playPosicion = 0; // posición por donde va reproduciéndose el audio
    private Button playButton, pausaButton, continuarButton, pararButton;
    static final String AUDIO_PATH = "http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3";
    //static final String AUDIO_PATH = "http://www.leemp3.com/leemp3/Avecilla_alas.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ud7_reproduce_audio);

        playButton = (Button) findViewById(R.id.play);
        pausaButton = (Button) findViewById(R.id.pausa);
        continuarButton = (Button) findViewById(R.id.continuar);
        pararButton = (Button) findViewById(R.id.parar);

        playButton.setOnClickListener(this);
        pausaButton.setOnClickListener(this);
        continuarButton.setOnClickListener(this);
        pararButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // si se pulsa REPRODUCIR
        if (playButton == v) {
            try {
                // llama al método playLocal()
                playLocal();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        // si se pulsa PAUSAR
        if (pausaButton == v) {
            // si hay reproducción en marcha
            if (mediaPlayer != null) {
                // se obtiene y guarda la posición actual(el segundo)
                playPosicion = mediaPlayer.getCurrentPosition();
                // se pausa la reproducción
                mediaPlayer.pause();
            }
        }
        // si se pulsa CONTINUAR
        if (continuarButton == v) {
            // si hay reproducción en marcha y no está sonando
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                // se pone en marcha reproducción
                mediaPlayer.start();
                // se obtiene instante (segundo) desde el que seguir la
                // reproducción
                mediaPlayer.seekTo(playPosicion);
            }
        }
        // si se pulsa PARAR
        if (pararButton == v) {
            // si está sonando
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                // se asigna posición de inicio al principio
                playPosicion = 0;
                // se pausa reproducción
                mediaPlayer.pause();

            }
        }

    }

    // crea el objeto mediaPlayer dejándolo preparado para reproducir el sonido
    public void playLocal() throws IOException {
        // al objeto mediaPlayer se le asocia el fichero de sonido
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {

            //fuente de audio en Internet
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(AUDIO_PATH);
            mediaPlayer.prepare();
            mediaPlayer.start();

            //Fuente de audio en res/raw
            /*mediaPlayer = MediaPlayer.create(this, R.raw.audio);
            mediaPlayer.start();*/

        }

    }

    // llamado al destruirse actividad
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    // finalizar ciclo de vida de objeto MediaPlayer
    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                // el ciclo de vida del objeto MediaPlayer finaliza
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ud7_reproduce_audio, menu);
        return true;
    }

}
