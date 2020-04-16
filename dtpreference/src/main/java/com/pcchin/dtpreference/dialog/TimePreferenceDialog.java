package com.pcchin.dtpreference.dialog;

import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TimePicker;

import androidx.preference.DialogPreference;
import androidx.preference.PreferenceDialogFragmentCompat;

import com.pcchin.dtpreference.R;
import com.pcchin.dtpreference.TimePreference;

import java.util.Calendar;

/** The Fragment used in TimePreference to display the time. This should not be used outside of
 * @see com.pcchin.dtpreference.TimePreference **/
public final class TimePreferenceDialog extends PreferenceDialogFragmentCompat {
    private TimePicker timePicker;

    public static TimePreferenceDialog newInstance(
            String key) {
        final TimePreferenceDialog
                fragment = new TimePreferenceDialog();
        final Bundle bundle = new Bundle(1);
        bundle.putString(ARG_KEY, key);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        timePicker = view.findViewById(R.id.edit);
        // Exception when there is no TimePicker
        if (timePicker == null) {
            throw new IllegalStateException("Dialog view must contain" +
                    " a TimePicker with id 'edit'");
        }

        // Get the time from the related Preference
        long initialTime = -1;
        DialogPreference preference = getPreference();
        if (preference instanceof TimePreference) {
            initialTime = ((TimePreference) preference).getTime();
        }

        // Set the time to the TimePicker
        if (initialTime != -1) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(initialTime);
            boolean is24hour = DateFormat.is24HourFormat(getContext());

            timePicker.setIs24HourView(is24hour);
            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
        }
    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // Get the related Preference and save the value
            DialogPreference preference = getPreference();
            if (preference instanceof TimePreference) {
                Calendar newTime = Calendar.getInstance();
                // getCurrentHour and getCurrentMinute are deprecated in API 23
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    newTime.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                    newTime.set(Calendar.MINUTE, timePicker.getMinute());
                } else {
                    newTime.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    newTime.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                }
                ((TimePreference) preference).setTime(newTime.getTimeInMillis());
            }
        }
    }
}
