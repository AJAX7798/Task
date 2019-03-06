package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

/**
 * Created by AsaRayan on 22/01/2019.
 */

public class SureDeleteTask extends Dialog {

    public Activity c;
    String id;
    public SureDeleteTask(Activity a,String id) {
        super(a);
        this.c = a;
        this.id = id;
    }
    Button yes;
    Button no;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suredeletetask);
        yes = (Button)findViewById(R.id.button4);
        no = (Button)findViewById(R.id.button6);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_helper dbhelper = new Db_helper(c);
                Cursor cursor = dbhelper.selectTaskById(id);
                if(cursor.moveToFirst()){
                    do{
                        if(!cursor.getString(7).equals("n")){
                            NotifAdapter notifAdapter = new NotifAdapter();
                            notifAdapter.setContext(c.getBaseContext());
                            notifAdapter.setId(id + "3");
                            notifAdapter.setIcon(String.valueOf(R.drawable.noicon));
                            notifAdapter.setDate("3000/06/07-12:45");
                            notifAdapter.deleteNot(id + "3");
                        }
                        if(!cursor.getString(5).equals("")){
                            NotifAdapter notifAdapter = new NotifAdapter();
                            notifAdapter.setContext(c.getBaseContext());
                            notifAdapter.setId(id + "2");
                            notifAdapter.setIcon(String.valueOf(R.drawable.noicon));
                            notifAdapter.setDate("3000/06/07-12:45");
                            notifAdapter.deleteNot(id + "2");
                        }
                        NotifAdapter notifAdapter1 = new NotifAdapter();
                        notifAdapter1.setContext(c.getBaseContext());
                        notifAdapter1.setId(id + "1");
                        notifAdapter1.setIcon(String.valueOf(R.drawable.noicon));
                        notifAdapter1.setDate("3000/06/07-12:45");
                        notifAdapter1.deleteNot(id + "1");
                    }while(cursor.moveToNext());
                }
                dbhelper.deleteTask(id);
                Intent intent = new Intent(c,Tasks.class);
                c.startActivity(intent);
                dismiss();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
