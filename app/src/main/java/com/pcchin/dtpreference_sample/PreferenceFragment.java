package com.pcchin.dtpreference_sample;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.pcchin.dtpreference.TimePreference;
import com.pcchin.dtpreference.dialog.TimePreferenceDialog;

public class PreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_list);
    }

    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Check for any custom DialogPreferences
        DialogFragment dialogFragment = null;
        if (preference instanceof TimePreference) {
            dialogFragment = TimePreferenceDialog.newInstance(preference.getKey());
        }

        if (dialogFragment != null && getFragmentManager() != null) {
            // If it was one of our preferences
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getFragmentManager(),
                    "android.support.v7.preference.PreferenceFragment.DIALOG");
        } else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}
