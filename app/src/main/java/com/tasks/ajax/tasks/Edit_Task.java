package com.tasks.ajax.tasks;

import android.annotation.TargetApi;
import android.app.Activity;
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


import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;

/*
    Create
 */

public class Edit_Task extends AppCompatActivity  implements OnDateSetListener , OnTimeSetListener {

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
    String deadline = "n";
    String donetime = "";
    String isdone;
    String rates = "";
    Button donetimebtn;
    String starttime = "";
    String endtime = "";
    String remember = "";

    TimePickDialog timedialog;
    final Context to = this;
    static ImageView showSelectedIcon;
    public static int selectedIcon = 0 ;

    RatingBar rate;
    TextView set;
    TextView dead;
    TextView reme;
    TextView done;

    Spinner repmode;
    EditText getname;
    EditText getdetails;
    Spinner sp;
    static String getid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        forceRTLIfSupported();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        set = (TextView)findViewById(R.id.settimetv);
        dead = (TextView)findViewById(R.id.deadlinetv);
        reme = (TextView)findViewById(R.id.remembertv);

        rate = (RatingBar)findViewById(R.id.ratingBar);

        getname = (EditText)findViewById(R.id.getname);
        getdetails = (EditText)findViewById(R.id.getdetails);

        donetimebtn = (Button)findViewById(R.id.endtimebutton);
        repmode = (Spinner)findViewById(R.id.spinner4);
        showSelectedIcon = (ImageView)findViewById(R.id.icon);
        getname.requestFocus();
        Calendar calendar = Calendar.getInstance();

        final DatePickDialog datePickerDialog = new DatePickDialog(
                Edit_Task.this,
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
                    case "donetime":
                        donetime = "";
                        done.setText("Not set");
                        break;
                }
            }
        });
        done = (TextView)findViewById(R.id.endtimetv);
        Button settimebtn = (Button)findViewById(R.id.settime);
        Button deadlinebtn = (Button)findViewById(R.id.deadlinebtn);
        Button rememberbtn = (Button)findViewById(R.id.remeberbtn);
        timedialog = new TimePickDialog(
                Edit_Task.this,
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
                    case "donetime":
                        donetime = "";
                        done.setText("Not set");
                        break;
                }
            }
        });
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
        donetimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buffer = "donetime";
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
        icon = (Button)findViewById(R.id.button3);
        final Activity a = com.tasks.ajax.tasks.Edit_Task.this;
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IconDialog dialog = new IconDialog(a,"Edit");
                dialog.show();
            }
        });
        initial();
    }

    private void initial(){
        Db_helper dbhelper = new Db_helper(this);
        Cursor cursor = dbhelper.selectTaskById(getid);
        int i = 0;
        if(cursor.moveToFirst()){
            do{
                getname.setText(cursor.getString(1));
                getdetails.setText(cursor.getString(3));
                sp.setSelection(getGroupPosition(cursor.getString(2)));
                showSelectedIcon.setImageResource(Integer.parseInt(cursor.getString(6)));
                icono = cursor.getString(6);
                selectedIcon = Integer.parseInt(cursor.getString(6));
                rate.setRating(Float.parseFloat(cursor.getString(10)));
                set.setText(cursor.getString(4));
                isdone = cursor.getString(9);
                starttime = cursor.getString(4);
                repmode.setSelection(Integer.parseInt(cursor.getString(11)));
                if(cursor.getString(9).equals("yes")){
                    hide();
                    done.setText(cursor.getString(8));
                    donetime = cursor.getString(8);
                }
                if(cursor.getString(7).equals("n")){
                    dead.setText("Not set");
                    endtime = "n";
                }
                else{
                    dead.setText(cursor.getString(7));
                    endtime = cursor.getString(7);
                }
                if(cursor.getString(5).equals("")){
                    reme.setText("Not set");
                    remember = "";
                }
                else{
                    reme.setText(cursor.getString(5));
                    remember = cursor.getString(5);
                }
                i++;
            }while(cursor.moveToNext() && i < 1);
        }
    }

    private void hide() {
        LinearLayout hide1 = (LinearLayout)findViewById(R.id.hide1);
        LinearLayout hide2 = (LinearLayout)findViewById(R.id.hide2);
        LinearLayout hide3 = (LinearLayout)findViewById(R.id.hide3);
        LinearLayout hide4 = (LinearLayout)findViewById(R.id.hide4);
        TextView txt4 = (TextView)findViewById(R.id.textView4);
        TextView txtu = (TextView)findViewById(R.id.txtu);
        TextView txtv = (TextView)findViewById(R.id.txtv);
        TextView txti = (TextView)findViewById(R.id.txti);
        txtu.setVisibility(View.GONE);
        txti.setVisibility(View.GONE);
        txtv.setVisibility(View.GONE);
        hide1.setVisibility(View.GONE);
        hide2.setVisibility(View.GONE);
        hide3.setVisibility(View.GONE);
        repmode.setVisibility(View.GONE);
        txt4.setVisibility(View.VISIBLE);
        hide4.setVisibility(View.VISIBLE);
    }

    private int getGroupPosition(String cat){
        Db_helper dbhelper = new Db_helper(this);
        int position = 0;
        Cursor cursor = dbhelper.selectGroup();
        if(cursor.moveToFirst()){
            do{
                if(cat.equals(cursor.getString(0)))
                    break;
                position++;

            }while(cursor.moveToNext());
        }
        return position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buttons,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        update();
        return super.onOptionsItemSelected(item);
    }

    private void update() {
        Db_helper dbhelper = new Db_helper(this);
        if(isdone.equals("no")) {
            if (validation(this, getname, starttime, endtime, remember)) {
                name = getname.getText().toString().trim();
                group = sp.getSelectedItem().toString();
                details = getdetails.getText().toString().trim();
                if(selectedIcon == 0){
                    icono = String.valueOf(R.drawable.noicon);
                }else{
                    icono = String.valueOf(selectedIcon);
                }
                rates = String.valueOf(rate.getRating());
                dbhelper.updateTask(name, group, details, starttime, remember, icono, endtime
                        , rates,String.valueOf(repmode.getSelectedItemPosition()), getid);
                NotifAdapter notif = new NotifAdapter(getid + "1",name,details,icono,starttime,this);
                notif.addNot();
                if(!endtime.equals("n")) {
                    NotifAdapter notif1 = new NotifAdapter(getid + "3", name, details, icono, endtime, this);
                    notif1.addNot();
                }
                else{
                    NotifAdapter notif1 = new NotifAdapter(getid + "3", name, details, icono, endtime, this);
                    notif1.deleteNot(getid + "3");
                }
                if(!remember.equals("")) {
                    NotifAdapter notif2 = new NotifAdapter(getid + "2", name, details, icono, remember, this);
                    switch (repmode.getSelectedItemPosition()){
                        case 0:
                            notif2.addNot();
                            break;
                        case 1:
                            notif2.addrepeatday();
                            break;
                        case 2:
                            notif2.addrepeatweek();
                            Toast.makeText(to, "2", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            notif2.addrepeatmonth();
                            break;
                    }
                }
                else{
                    NotifAdapter notif2 = new NotifAdapter(getid + "2", name, details, icono, remember, this);
                    notif2.deleteNot(getid + "2");
                }
                ShowTask.setId(getid);
                Intent intent = new Intent(Edit_Task.this,ShowTask.class);
                startActivity(intent);
            }
        }else{
            if(validation(this,getname,donetime)){
                name = getname.getText().toString().trim();
                group = sp.getSelectedItem().toString();
                details = getdetails.getText().toString().trim();
                icono = String.valueOf(selectedIcon);
                rates = String.valueOf(rate.getRating());
                dbhelper.updateTask(name, group, details, icono, donetime, rates, getid);
                ShowTask.setId(getid);
                Intent intent = new Intent(Edit_Task.this,ShowTask.class);
                startActivity(intent);
            }
        }
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
                Toast.makeText(to, "Please select a time for start time!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(to, "The title must not be empty!", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

    boolean validation(Context to,EditText name,String donetime){
        boolean isValid = false;
        if (!name.getText().toString().trim().equals("")) {
            if (!donetime.equals("")) {
                isValid = true;
            } else {
                Toast.makeText(to, "Time done cannot be empty for a done task!", Toast.LENGTH_SHORT).show();
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
            case "donetime":
                donetime = DateTime.addzero(year) + "/" + DateTime.addzero((monthOfYear+1)) + "/" + DateTime.addzero(dayOfMonth);;
                done.setText(donetime);
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
            case "donetime":
                donetime += "-" + DateTime.addzero(hourOfDay) + ":" + DateTime.addzero(minute);
                done.setText(donetime);
                break;
        }
    }
}
