package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import static com.tasks.ajax.tasks.R.id.datePicker;

/**
 * Created by AsaRayan on 28/02/2019.
 */

public class TimePickDialog extends Dialog {

    TimePicker timePicker;
    int curyear;
    int curmonth;
    int curday;

    int selectedHour = 0;
    int selectedMinute = 0;
    OnTimeSetListener timesetlistener;
    Activity ac;
    Button ok;
    Button cancel;

    public TimePickDialog(Activity a,OnTimeSetListener listener) {
        super(a);
     /*   curyear = year;
        curmonth = month;
        curday = day;*/
        ac = a;
        timesetlistener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.timepick);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        ok = (Button)findViewById(R.id.button9);
        cancel = (Button)findViewById(R.id.button10);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    selectedHour = timePicker.getHour();
                    selectedMinute = timePicker.getMinute();
                }
                timesetlistener.onTimeSetListener(selectedHour,selectedMinute);
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
