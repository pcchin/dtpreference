package com.pcchin.dtpreference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.preference.DialogPreference;

import com.pcchin.dtpreference.dialog.TimePreferenceDialog;

/** A preference for selecting a specific time. This is made based on a tutorial from
 * https://medium.com/@JakobUlbrich **/
public class TimePreference extends DialogPreference {
    private TimePreferenceDialog dialogFragment;
    private long currentTime;

    public TimePreference(Context context) {
        super(context);
    }

    public TimePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public long getTime() {
        return currentTime;
    }

    public void setTime(long time) {
        currentTime = time;
        persistLong(time);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute
        String value = a.getString(index);
        return value == null ? null : Long.parseLong(value);
    }

    @Override
    protected void onSetInitialValue(Object defaultValue) {
        // Read the value. Use the default value if it is not possible.
        long value = getPersistedLong(-1);
        if (value == -1) {
            setTime((long) defaultValue);
        } else {
            setTime(value);
        }
    }

    @Override
    public int getDialogLayoutResource() {
        return R.layout.com_pcchin_dtpreference_25f3t27tr7jswszj2eeg;
    }
}
