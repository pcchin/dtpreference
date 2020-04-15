package com.pcchin.dtpreference;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;

public class DatePreference extends Preference {
    public DatePreference(Context context) {
        super(context);
    }

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DatePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DatePreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
