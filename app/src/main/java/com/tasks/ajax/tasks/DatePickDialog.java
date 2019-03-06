package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

/**
 * Created by AsaRayan on 28/02/2019.
 */

public class DatePickDialog extends Dialog {

    DatePicker datePicker;
    int curyear;
    int curmonth;
    int curday;

    int selectedYear = 0;
    int selectedMonth = 0;
    int selectedDay = 0;
    OnDateSetListener datesetlistener;
    Activity ac;
    Button ok;
    Button cancel;

    public DatePickDialog(Activity a,int year,int month,int day,OnDateSetListener listener) {
        super(a);
        curyear = year;
        curmonth = month;
        curday = day;
        ac = a;
        datesetlistener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.datepick);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.init(curyear,curmonth,curday,null);
        ok = (Button)findViewById(R.id.button);
        cancel = (Button)findViewById(R.id.button5);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedYear = datePicker.getYear();
                selectedMonth = datePicker.getMonth();
                selectedDay = datePicker.getDayOfMonth();
                datesetlistener.onDateSetListener(selectedYear,selectedMonth,selectedDay);
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }
}
