/*
 * Copyright 2020 PC Chin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pcchin.dtpreference_sample;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.pcchin.dtpreference.DatePreference;
import com.pcchin.dtpreference.DateTimePreference;
import com.pcchin.dtpreference.TimePreference;
import com.pcchin.dtpreference.dialog.DatePreferenceDialog;
import com.pcchin.dtpreference.dialog.DateTimePreferenceDialog;
import com.pcchin.dtpreference.dialog.TimePreferenceDialog;

import java.util.Calendar;

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
        } else if (preference instanceof DatePreference) {
            // Only able to select a date before today
            dialogFragment = DatePreferenceDialog.newInstance(preference.getKey(), -1,
                    Calendar.getInstance().getTimeInMillis());
        } else if (preference instanceof DateTimePreference) {
            dialogFragment = DateTimePreferenceDialog.newInstance(preference.getKey());
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
