package com.pcchin.dtpreference;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

public class DateTimePreference extends Preference {
    public DateTimePreference(Context context) {
        super(context);
    }

    public DateTimePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DateTimePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DateTimePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
