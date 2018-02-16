package com.herprogramacion.revistatempor;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Creado por Hermosa Programacion.
 */
public class SettingsActivity extends PreferenceActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        // Comprobar que el fragmento este relacionado con la actividad
        return SettingsFragment.class.getName().equals(fragmentName);
    }

    @Override
    public boolean onIsMultiPane() {
        // Obtener caracteristicas del dispositivo
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return ((float) metrics.densityDpi / (float) metrics.widthPixels) < 0.30;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class SettingsFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Reutilizacion del fragmento
            String settings = getArguments().getString("settings");
            if ("generales".equals(settings)) {
                addPreferencesFromResource(R.xml.settings_gen);
            } else if ("twitter".equals(settings)) {
                addPreferencesFromResource(R.xml.settings_twitter);
            }

        }

        @Override
        public void onResume() {
            super.onResume();
            // Registrar escucha
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            super.onPause();
            // Eliminar registro de la escucha
            getPreferenceScreen().getSharedPreferences()
                    .unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            // Actualizar el resumen de la preferencia
            if (key.equals("numArticulos")) {
                Preference preference = findPreference(key);
                preference.setSummary(sharedPreferences.getString(key, ""));
            }
        }
    }


}
