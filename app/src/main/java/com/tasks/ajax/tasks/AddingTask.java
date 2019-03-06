package com.tasks.ajax.tasks;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

public class AddingTask extends AppCompatActivity implements OnTimeSetListener, OnDateSetListener{
    TextView txt;
    Button icon;
    String buffer = "";
    int id = 0;
    String name = "";
    String group = "";
    String details = "";
    String settime = "";
    String icono = "";
    int iconi = 0;
    String deadline = "";
    String donetime = "";
    String isdone = "no";
    String rates = "";

    String starttime = "";
    String endtime = "n";
    String remember = "";

    final Context to = this;
    static ImageView showSelectedIcon;
    public static int selectedIcon = 0 ;
    public static int selectedCat = 0;
    RatingBar rate;
    TextView set;
    TextView dead;
    TextView reme;
    Spinner repmode;
    EditText getname;
    EditText getdetails;
    Spinner sp;
    TimePickDialog timedialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_task);
        set = (TextView)findViewById(R.id.settimetv);
        dead = (TextView)findViewById(R.id.deadlinetv);
        reme = (TextView)findViewById(R.id.remembertv);

        rate = (RatingBar)findViewById(R.id.ratingBar);
    //    selectedIcon = R.drawable.noicon;
        getname = (EditText)findViewById(R.id.getname);
        getdetails = (EditText)findViewById(R.id.getdetails);
        repmode = (Spinner)findViewById(R.id.spinner4);

        rate.setRating(1);
        getname.requestFocus();
        showSelectedIcon = (ImageView)findViewById(R.id.icon);
        Calendar calendar = Calendar.getInstance();

        timedialog = new TimePickDialog(
                AddingTask.this,
                this
        );
        timedialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                switch(buffer){
                    case "settime":
                        starttime = "";
                        set.setText("Not set");
                        break;
                    case "deadline":
                        endtime = "n";
                        dead.setText("Not set");
                        break;
                    case "remembertime":
                        remember = "";
                        reme.setText("Not set");
                        break;
                }
            }
        });

        final DatePickDialog datePickerDialog = new DatePickDialog(
                AddingTask.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE),
                this
        );
        datePickerDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                switch(buffer){
                    case "settime":
                        starttime = "";
                        set.setText("Not set");
                        break;
                    case "deadline":
                        endtime = "n";
                        dead.setText("Not set");
                        break;
                    case "remembertime":
                        remember = "";
                        reme.setText("Not set");
                        break;
                }
            }
        });

        Button settimebtn = (Button)findViewById(R.id.settime);
        Button deadlinebtn = (Button)findViewById(R.id.deadlinebtn);
        Button rememberbtn = (Button)findViewById(R.id.remeberbtn);
        txt = (TextView)findViewById(R.id.textView);
        settimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = "settime";
                datePickerDialog.show();
            }
        });
        deadlinebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = "deadline";
                datePickerDialog.show();
            }
        });
        rememberbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = "remembertime";
                datePickerDialog.show();
            }
        });

        String[] items = new String[]{"Once","EveryDay","Weekly","Monthly"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.spinner_items,items);
        repmode.setAdapter(adapter1);
        sp = (Spinner)findViewById(R.id.spinner);
        ArrayList item = new ArrayList<String>();
        getGroups(item,this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_items,item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp.setSelection(selectedCat);
        icon = (Button)findViewById(R.id.button3);
        final Activity a = AddingTask.this;
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IconDialog dialog = new IconDialog(a,"Add");
                dialog.show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buttons,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        insert();
        return super.onOptionsItemSelected(item);
    }

    private void insert() {
        Db_helper dbhelper = new Db_helper(this);
        if(validation(this,getname,starttime,endtime,remember)){
            name = getname.getText().toString().trim();
            group = sp.getSelectedItem().toString();
            details = getdetails.getText().toString().trim();
            if(selectedIcon == 0){
                Log.i("TIMEDATE:","f0");
                icono = String.valueOf(R.drawable.noicon);
            }else{
                icono = String.valueOf(selectedIcon);
            }
            rates = String.valueOf(rate.getRating());
            id = getid(this);
            id++;
            dbhelper.insertToTask(String.valueOf(id),name,group,details,starttime,remember,icono,endtime,donetime,isdone,rates,String.valueOf(repmode.getSelectedItemPosition()));
            NotifAdapter notifAdapter = new NotifAdapter(encode(id,1),name,details,icono,starttime,this);
            notifAdapter.addNot();
            if(!remember.equals("")){
                NotifAdapter notifAdapter1 = new NotifAdapter(encode(id,2),name,details,icono,remember,this);
                switch (repmode.getSelectedItemPosition()){
                    case 0:
                        notifAdapter1.addNot();
                        break;
                    case 1:
                        notifAdapter1.addrepeatday();
                        break;
                    case 2:
                        notifAdapter1.addrepeatweek();
                        break;
                    case 3:
                        notifAdapter1.addrepeatmonth();
                        break;
                }
                Log.i("ajax:",encode(id,2) + "rem");
            }
            if(!endtime.equals("n")){
                NotifAdapter notifAdapter1 = new NotifAdapter(encode(id,3),name,details,icono,endtime,this);
                notifAdapter1.addNot();
                Log.i("ajax:",encode(id,3) + "dead");
            }
            Toast.makeText(this,"Task added successfully!",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddingTask.this,Tasks.class);
            startActivity(intent);
        }
    }

    private String encode(int id,int mode){
        String d = "";
        d = id + "" + mode;
        return d;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    void getGroups(ArrayList<String> items, Context to){
        Db_helper dbHelper = new Db_helper(to);
        items.clear();
        Cursor cursor = dbHelper.selectGroup();
        if(cursor.moveToFirst()){
            do{
                items.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
    }

    int getid(Context to){
        int id = 0;
        Db_helper dbHelper = new Db_helper(to);
        Cursor cursor = dbHelper.selectTask();
        if(cursor.moveToFirst()){
            do{
               id = Integer.parseInt(cursor.getString(0));
            } while(cursor.moveToNext());
        }
        return id;
    }

    boolean validation(Context to,EditText name,String settime,String deadline,String remember){
        boolean isValid = false;
        DateTime dateTime = new DateTime();
        if (!name.getText().toString().trim().equals("")) {
            if (!settime.equals("")) {
                if (!deadline.equals("n") && !remember.equals("")) {
                    if (dateTime.compare(deadline) == -1) {
                        Toast.makeText(to, "The deadline expired!", Toast.LENGTH_SHORT).show();
                    }
                    if (dateTime.compare(remember) == -1) {
                        Toast.makeText(to, "The reminderTime expired!", Toast.LENGTH_SHORT).show();
                    }
                    if (dateTime.compare(deadline) != -1 && dateTime.compare(remember) != -1) {
                        isValid = true;
                    }
                } else if (deadline.equals("n") && !remember.equals("")) {
                    if (dateTime.compare(remember) == -1) {
                        Toast.makeText(to, "The reminderTime expired!", Toast.LENGTH_SHORT).show();
                    } else {
                        isValid = true;
                    }
                } else if (!deadline.equals("n") && remember.equals("")) {
                    if (dateTime.compare(deadline) == -1) {
                        Toast.makeText(to, "The deadline expired!", Toast.LENGTH_SHORT).show();
                    } else {
                        isValid = true;
                    }
                } else if (deadline.equals("n") && remember.equals("")) {
                    isValid = true;
                }
            } else {
                Toast.makeText(to, "Please enter a starttime!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(to, "The title must not be empty!", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    public static void setIcon(){
        if(selectedIcon != 0) {
            showSelectedIcon.setImageResource(selectedIcon);
        }else{
            showSelectedIcon.setImageResource(R.drawable.imageback);
        }
    }

    @Override
    public void onDateSetListener(int year, int monthOfYear, int dayOfMonth) {
        switch(buffer){
        case "settime":
            starttime = DateTime.addzero(year) + "/" + DateTime.addzero((monthOfYear+1)) + "/" + DateTime.addzero(dayOfMonth);
            break;
        case "deadline":
            endtime = DateTime.addzero(year) + "/" + DateTime.addzero((monthOfYear+1)) + "/" + DateTime.addzero(dayOfMonth);
            break;
        case "remembertime":
            remember = DateTime.addzero(year) + "/" + DateTime.addzero((monthOfYear+1)) + "/" + DateTime.addzero(dayOfMonth);
            break;
    }
        //    String time = year + "/" + (monthOfYear+1) + "/" + dayOfMonth;
        timedialog.show();
    }

    @Override
    public void onTimeSetListener(int hourOfDay, int minute) {
        switch(buffer){
            case "settime":
                starttime += "-"+ DateTime.addzero(hourOfDay) + ":" + DateTime.addzero(minute);
                set.setText(starttime);
                break;
            case "deadline":
                endtime += "-"+ DateTime.addzero(hourOfDay) + ":" + DateTime.addzero(minute);
                dead.setText(endtime);
                break;
            case "remembertime":
                remember += "-"+ DateTime.addzero(hourOfDay) + ":" + DateTime.addzero(minute);
                reme.setText(remember);
                break;
        }
    }
}
