package com.alandalus.luisaparragarcia.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by luisaparragarcia on 24/1/18.
 */

public class ServicioMusica extends Service {
    MediaPlayer reproductor;
    private NotificationManager nm;
    Notification myNotication;
    private static final int ID_NOTIFICACION_CREAR = 1;


    @Override

    public void onCreate() {
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Toast.makeText(this,"Servicio creado",
                Toast.LENGTH_SHORT).show();

        reproductor = MediaPlayer.create(this, R.raw.audio);


    }



    @Override

    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        Toast.makeText(this,"Servicio arrancado "+ idArranque,
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent intencionPendiente =PendingIntent.getActivity(this, 1, intent, 0);

        Notification.Builder builder=new Notification.Builder(this);


        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("WhatsApp Notification");
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentIntent(intencionPendiente);
        builder.setOngoing(true);
        builder.setNumber(100);
        //builder.build();
        myNotication = builder.getNotification();
        nm.notify(ID_NOTIFICACION_CREAR, myNotication);

        reproductor.start();

        return START_STICKY;

    }



    @Override

    public void onDestroy() {

        Toast.makeText(this,"Servicio detenido",
                Toast.LENGTH_SHORT).show();
        nm.cancel(ID_NOTIFICACION_CREAR);
        reproductor.stop();

    }



    @Override

    public IBinder onBind(Intent intencion) {

        return null;

    }


}
