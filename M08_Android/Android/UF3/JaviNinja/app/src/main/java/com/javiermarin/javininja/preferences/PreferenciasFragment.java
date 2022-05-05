package com.javiermarin.javininja.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.javiermarin.javininja.R;

public class PreferenciasFragment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
