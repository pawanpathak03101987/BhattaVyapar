package com.pawan.bhattavyapar.utils;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickerClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView tv;
    int day = 0, month = 0, year = 0;

    public DatePickerClass() {

    }

    @SuppressLint("ValidFragment")
    public DatePickerClass(TextView tv) {
        this.tv = tv;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        String sDate = tv.getText().toString();

            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.DATE, 1);
//        }
        DatePickerDialog datepickerdialog = new DatePickerDialog(getActivity(), this, year, month, day);
      //  datepickerdialog.getDatePicker().setMinDate(new Date().getTime());
        datepickerdialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        return datepickerdialog;
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int selectedyear, int monthOfYear,
                          int dayOfMonth) {
        // TODO Auto-generated method stub

        Calendar calander2 = Calendar.getInstance();
        year = selectedyear;
        month = monthOfYear;
        day = dayOfMonth;
        calander2.setTimeInMillis(0);
        calander2.set(year, month, day, 0, 0, 0);
        calander2.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

        Date SelectedDate = calander2.getTime();

        String StringDateformat = new SimpleDateFormat("dd-MM-yyyy").format(SelectedDate);
        tv.setText(StringDateformat);

    }


}