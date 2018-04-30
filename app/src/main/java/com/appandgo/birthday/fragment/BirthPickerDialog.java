package com.appandgo.birthday.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.appandgo.birthday.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by liran on 27/4/2018.
 */

public class BirthPickerDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener  mlistener;
//    private TextView mTextView;

    public BirthPickerDialog()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public BirthPickerDialog(DatePickerDialog.OnDateSetListener listener)
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//        mTextView.setText(getDateFormatToShow(year, month, day));
        if (mlistener != null) {
            mlistener.onDateSet(datePicker, year, month + 1, day);
        }
    }
}
