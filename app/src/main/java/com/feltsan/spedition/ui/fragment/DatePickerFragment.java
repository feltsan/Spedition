package com.feltsan.spedition.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.feltsan.spedition.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by john on 05.10.15.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private View view;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {

        this.view = getActivity().findViewById(getArguments().getInt(App.DATE_VIEW, 0));
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        String formattedDate = sdf.format(c.getTime());
        ((TextView) this.view).setTextColor(getActivity().getResources().getColor(android.R.color.black));
        ((TextView) this.view).setText(formattedDate);
    }
}