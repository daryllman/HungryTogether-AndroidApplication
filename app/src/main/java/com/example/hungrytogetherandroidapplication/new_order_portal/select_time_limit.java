package com.example.hungrytogetherandroidapplication.new_order_portal;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class select_time_limit extends DialogFragment {// implements AdapterView.OnItemSelectedListener


    Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    int hours;
    int minutes;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        /**FINISH THIS PART**/
        hours = hour;
        minutes = minute;

        return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener)getActivity(),
                hour, minute, DateFormat.is24HourFormat(getActivity()));
    }




}
