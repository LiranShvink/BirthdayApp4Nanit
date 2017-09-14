package com.appandgo.birthday.components;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.appandgo.birthday.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by naor on 14/09/2017.
 */

public class NDDatePickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener  mlistener;
    private TextView mTextView;

    public NDDatePickerDialog()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public NDDatePickerDialog(DatePickerDialog.OnDateSetListener listener)
    {
        this.mlistener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.DialogTheme, this, year, month, day);

        dialog.getDatePicker().setMaxDate( new Date().getTime() );

        return dialog;
    }

    @NonNull
    private String getDateFormatToShow(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG);
        formatter.setTimeZone(calendar.getTimeZone());

        return formatter.format(calendar.getTime());
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mTextView.setText(getDateFormatToShow(year, month, day));
        if (mlistener != null) {
            mlistener.onDateSet(datePicker, year, month, day);
        }
    }
}
