package com.example.ejemplocontent;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	
	private Button boton;
	private TextView resultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			
		resultado = (TextView)findViewById(R.id.textView1);
		boton = (Button)findViewById(R.id.button1);
			
		boton.setOnClickListener(new OnClickListener(){				
			public void onClick(View view){
				String sURL = "";
				String sVisitado = "";
				resultado.setText("");
					 
				Uri miUri =  Browser.BOOKMARKS_URI;
					 
				ContentResolver contRes = getContentResolver();
				
				String[] columnas = new String[] {
					    Browser.BookmarkColumns.URL,
					    Browser.BookmarkColumns.VISITS };
					 
				Cursor cur = contRes.query(miUri,
					        columnas, 	//Columnas a devolver
					        null,       //Condición de la consulta.
					        null,       //Argumentos de la condición.
					        null);      //Orden de los registros devueltos.
					
				if (cur.moveToFirst())
				{					
					int columnaURL = cur.getColumnIndex(Browser.BookmarkColumns.URL);
					int columnaVisits = cur.getColumnIndex(Browser.BookmarkColumns.VISITS);
				    	
					do
					{    
						sURL = cur.getString(columnaURL);
					    sVisitado = cur.getString(columnaVisits);
					    	
					    resultado.append("La URL: " + sURL + " \nNúm. veces visitada: " + sVisitado + "\n");
					 
					} while (cur.moveToNext());					
				}	
			}
		});
	}
}
