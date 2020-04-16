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

package com.pcchin.dtpreference.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.pcchin.dtpreference.DatePreference;
import com.pcchin.dtpreference.R;

import java.util.Calendar;

/** The Fragment used in
 * @see DatePreference to display the time. This should not be used outside of DatePreference. **/
public final class DatePreferenceDialog extends PreferenceDialogFragmentCompat {
    private boolean dateOverwritten = false;
    private DatePicker datePicker;
    private long minDate = -1, maxDate = -1, iniDate;

    /** Creates a new instance of this dialog.
     * @param key The key of the preference selected. **/
    public static DatePreferenceDialog newInstance(String key) {
        final DatePreferenceDialog fragment = new DatePreferenceDialog();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);

        return fragment;
    }

    /** Creates a new instance of this dialog with a minimum date and maximum date respectively.
     * @param key The key of the preference selected.
     * @param min The minimum date for the DatePicker. Set to -1 for it to be ignored.
     * @param max The maximum date for the DatePicker. Set to -1 for it to be ignored. **/
    public static DatePreferenceDialog newInstance(String key, long min, long max) {
        final DatePreferenceDialog fragment = new DatePreferenceDialog();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);
        fragment.minDate = min;
        fragment.maxDate = max;
        return fragment;
    }

    /** Sets the initial date shown, which overwrites the value within the SharedPreference. **/
    public DatePreferenceDialog setInitialDate(long date) {
        iniDate = date;
        dateOverwritten = true;
        return this;
    }

    /** Initializes the view and sets up the DatePicker. **/
    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        datePicker = view.findViewById(R.id.edit);
        // Exception when there is no TimePicker
        if (datePicker == null) {
            throw new IllegalStateException("Dialog view must contain" +
                    " a DatePicker with id 'edit'");
        }

        // Get the date from the related Preference (If not overwritten)
        long initialTime;
        DialogPreference preference = getPreference();
        if (dateOverwritten) {
            initialTime = iniDate;
        } else if (preference instanceof DatePreference) {
            initialTime = ((DatePreference) preference).getDate();
        } else {
            initialTime = Calendar.getInstance().getTimeInMillis();
        }

        // Set the date to the DatePicker
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(initialTime);

        datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        if (minDate != -1) datePicker.setMinDate(minDate);
        if (maxDate != -1) datePicker.setMaxDate(maxDate);
    }

    /** Saves the value as a long SharedPreference. **/
    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // Get the related Preference and save the value
            DialogPreference preference = getPreference();
            if (preference instanceof DatePreference) {
                Calendar newTime = Calendar.getInstance();
                newTime.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                        0, 0, 0);
                ((DatePreference) preference).setDate(newTime.getTimeInMillis());
            }
        }
    }
}
