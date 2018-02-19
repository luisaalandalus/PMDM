package com.example.ejemploficherosexternos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button botonEscribir = (Button) findViewById(R.id.button1);
		Button botonLeer = (Button) findViewById(R.id.button2);
		Button botonEscPublic = (Button) findViewById(R.id.button3);
		
		// Si pulsamos en el botón escribir en la tarjeta SD.
        botonEscribir.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		escribirFicheroSD();		
        	}
        	});
       
        // Si pulsamos en el botón leer en la tarjeta SD.
        botonLeer.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		leerFicheroSD();
        	}
        	});    
        
		// Si pulsamos en el botón escribir en la tarjeta SD público.
        botonEscPublic.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		escribirFicheroSDPublico();		
        	}
        	});
  	}

	private void escribirFicheroSD(){
		try
		{
			EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
			String cadena = cuadroTxt.getText().toString();
			String estado = Environment.getExternalStorageState();
		
			if (estado.equals(Environment.MEDIA_MOUNTED)){
				File file = new File(getExternalFilesDir(null), "fichero.txt");
			    OutputStreamWriter fout =
			            new OutputStreamWriter(
			                new FileOutputStream(file));
				fout.write(cadena);
				fout.close();				
			}
		}
		catch (Exception ex)
		{
			Log.e("Ficheros", "Error al escribir fichero en la memoria externa");
		}
	}

	private void leerFicheroSD(){
		try
		{
			String linea="", 
					cadena="";
			String estado = Environment.getExternalStorageState();
			
			if (estado.equals(Environment.MEDIA_MOUNTED)){
				File file = new File(getExternalFilesDir(null), "fichero.txt");

			    BufferedReader br =
			            new BufferedReader(
			                new InputStreamReader(
			                    new FileInputStream(file)));
			     
			    while((linea = br.readLine())!=null){
			    	cadena += linea + "\n";
			    }
			    br.close();
    
				EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
				cuadroTxt.setText(cadena);	
			}

		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al leer fichero de la memoria externa");
		}
	}
	
	private void escribirFicheroSDPublico(){
		try
		{
			EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
			String cadena = cuadroTxt.getText().toString();
			String estado = Environment.getExternalStorageState();
		
			if (estado.equals(Environment.MEDIA_MOUNTED)){
				File file = new File(
						Environment.getExternalStoragePublicDirectory(
								Environment.DIRECTORY_DOWNLOADS), "fichero.txt");
			    OutputStreamWriter fout =
			            new OutputStreamWriter(
			                new FileOutputStream(file));
				fout.write(cadena);
				fout.close();				
			}
		}
		catch (Exception ex)
		{
			Log.e("Ficheros", "Error al escribir fichero en el directorio Descargas" +
					" de la memoria externa ");
		}
	}
}
