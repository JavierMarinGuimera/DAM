package com.javiermarin.javininja.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.javiermarin.javininja.MainActivity.sp;

public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new
                        PreferenciasFragment()).commit();

        sp.registerOnSharedPreferenceChangeListener((sharedPreferences, s) -> {
            try {
                Integer.parseInt(sp.getString("totalEnemies", "0"));
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "The number of enemies must to be a number!", Toast.LENGTH_SHORT).show();
                sp.edit().putString("totalEnemies", "0").commit();
            }
        });
    }
}
