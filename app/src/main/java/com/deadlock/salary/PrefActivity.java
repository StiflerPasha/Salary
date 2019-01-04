package com.deadlock.salary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import java.util.Arrays;

public class PrefActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        PreferenceManager.setDefaultValues(PrefActivity.this, R.xml.pref, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences = getPreferenceManager().getSharedPreferences();
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for(int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
            setSummary(getPreferenceScreen().getPreference(i));
        }
    }

    @Override
    public void onPause() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = getPreferenceScreen().findPreference(key);
        setSummary(pref);
        /*Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);*/
    }

    private void setSummary(Preference pref) {
        if (pref instanceof EditTextPreference) {
            updateSummary((EditTextPreference) pref);
        } else if (pref instanceof ListPreference) {
            updateSummary((ListPreference) pref);
        } else if (pref instanceof MultiSelectListPreference) {
            updateSummary((MultiSelectListPreference) pref);
        }
    }

    private void updateSummary(MultiSelectListPreference pref) {
        pref.setSummary(Arrays.toString(pref.getValues().toArray()) + " rub");
    }

    private void updateSummary(ListPreference pref) {
        pref.setSummary(pref.getValue() + " rub");
    }

    private void updateSummary(EditTextPreference preference) {
        preference.setSummary(preference.getText() + " rub");
    }
}
