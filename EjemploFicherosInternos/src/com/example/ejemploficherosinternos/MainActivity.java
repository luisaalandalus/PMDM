package com.example.ejemploficherosinternos;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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
		Button botonLeerRaw = (Button) findViewById(R.id.button3);
		
		// Si pulsamos en el botón escribir.
        botonEscribir.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		escribirFichero();		
        	}
        	});
       
        // Si pulsamos en el botón leer.
        botonLeer.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		leerFichero();
        	}
        	});    
        
		// Si pulsamos en el botón leer Raw.
        botonLeerRaw.setOnClickListener(new OnClickListener() {
        	public void onClick(View arg0) {
        		leerFicheroRaw();
        	}
        	});
	}

	private void escribirFichero(){
		try
		{
			EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
			String cadena = cuadroTxt.getText().toString();

			//Abre en modo privado y no sobreescribe
			OutputStreamWriter fout= new OutputStreamWriter(
							openFileOutput("fichero.txt", Context.MODE_PRIVATE | Context.MODE_APPEND));

			fout.write(cadena);
			fout.close();
		}
		catch (Exception ex)
		{
			Log.e("Ficheros", "Error al escribir fichero en la memoria interna");
		}
	}

	private void leerFichero(){
		try
		{
			String linea="", 
				   cadena="";
			BufferedReader fin = new BufferedReader(
		            			new InputStreamReader(openFileInput("fichero.txt")));
		 
		    while((linea = fin.readLine())!=null){
		    	cadena += linea + "\n";
		    }
		    fin.close();
		    
			EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
			cuadroTxt.setText(cadena);
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al leer fichero de la memoria interna");
		}
	}
	
	private void leerFicheroRaw(){
		try
		{
			String linea="", 
				   cadena="";
		    InputStream fraw =
		        getResources().openRawResource(R.raw.fich_raw);
		 
		    BufferedReader brin =
		        new BufferedReader(new InputStreamReader(fraw));
		 
		    while((linea = brin.readLine())!=null){
		    	cadena += linea + "\n";
		    }
		    fraw.close();
		    
			EditText cuadroTxt = (EditText)findViewById(R.id.editText1);
			cuadroTxt.setText(cadena);
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al leer fichero del recurso raw");
		}
		
	}

}
