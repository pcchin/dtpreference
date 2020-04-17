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

package com.pcchin.dtpreference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.preference.DialogPreference;

import java.util.Calendar;

/** A preference for selecting a specific date.
 * The time stored with the date is always midnight. **/
public class DatePreference extends DialogPreference {
    private boolean currentDateSet = false;
    private long currentDate;

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

    /** Gets the current date that is selected. **/
    public long getDate() {
        if (currentDateSet) {
            return currentDate;
        } else {
            return Calendar.getInstance().getTimeInMillis();
        }
    }

    /** Sets the date that is selected. **/
    public void setDate(long date) {
        currentDate = date;
        persistLong(date);
        currentDateSet = true;
        if (getOnPreferenceChangeListener() != null) {
            getOnPreferenceChangeListener().onPreferenceChange(this, date);
        }
    }

    /** Gets the default value of the DatePicker from the XML. **/
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // Default value from attribute
        String value = a.getString(index);
        return value == null ? null : Long.parseLong(value);
    }

    /** Sets the initial value of the DatePicker, otherwise defaults to defaultValue. **/
    @Override
    protected void onSetInitialValue(Object defaultValue) {
        // Read the value. Use the default value if it is not possible.
        long value = getPersistedLong(-1);
        if (value == -1) {
            setDate((long) defaultValue);
        } else {
            setDate(value);
        }
    }

    /** Returns the layout resource of the DatePicker. **/
    @Override
    public int getDialogLayoutResource() {
        return R.layout.com_pcchin_dtpreference_qz6q5864rndeem9u42ks;
    }
}
