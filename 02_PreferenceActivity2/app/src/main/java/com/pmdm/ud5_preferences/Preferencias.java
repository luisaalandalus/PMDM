package com.pmdm.ud5_preferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Preferencias extends PreferenceActivity {
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//asignar otro nombre al archivo de preferencias
		/*PreferenceManager prefMgr = getPreferenceManager();
		prefMgr.setSharedPreferencesName("appPreferences");*/
		
		// cargar las preferencias desde un archivo XML
		// el que está en /res/xml/myapppreferences.xml
		// a través del método addPreferencesFromResource
		addPreferencesFromResource(R.xml.myapppreferences);
	}
}
