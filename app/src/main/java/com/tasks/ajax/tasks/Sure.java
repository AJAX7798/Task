package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class Sure extends Dialog implements View.OnClickListener {

    public Activity c;
    private String groupName;
    Button ok;
    Button cancel;
    public Sure(Activity a,String groupName) {
        super(a);
        this.c = a;
        this.groupName = groupName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sure);
        ok = (Button)findViewById(R.id.button4);
        cancel = (Button)findViewById(R.id.button6);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }
    private void deleteGroup(){
        Db_helper dbHelper = new Db_helper(c.getBaseContext());
        Cursor cursor = dbHelper.selectTaskByGroup(groupName);
        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(0);

                NotifAdapter notifAdapter = new NotifAdapter();
                notifAdapter.setId(id + "1");
                notifAdapter.setContext(c.getBaseContext());
                notifAdapter.setDate("3000/06/07-12:45");
                notifAdapter.setIcon(String.valueOf(R.drawable.noicon));
                notifAdapter.deleteNot(id + "1");

                if(!cursor.getString(5).equals("")){
                    NotifAdapter notifAdapter1 = new NotifAdapter();
                    notifAdapter1.setId(id + "2");
                    notifAdapter1.setContext(c.getBaseContext());
                    notifAdapter1.setDate("3000/06/07-12:45");
                    notifAdapter1.setIcon(String.valueOf(R.drawable.noicon));
                    notifAdapter1.deleteNot(id + "2");
                }

                if(!cursor.getString(7).equals("n")){
                    NotifAdapter notifAdapter2 = new NotifAdapter();
                    notifAdapter2.setId(id + "3");
                    notifAdapter2.setContext(c.getBaseContext());
                    notifAdapter2.setDate("3000/06/07-12:45");
                    notifAdapter2.setIcon(String.valueOf(R.drawable.noicon));
                    notifAdapter2.deleteNot(id + "3");
                }
            }while (cursor.moveToNext());
        }
        dbHelper.deleteGroup(groupName);
        dbHelper.deleteTaskByGroup(groupName);
        Intent intent = new Intent(c,Group_Management.class);
        c.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(ok)){
            deleteGroup();
            dismiss();
            return;
        }else if(v.equals(cancel)){
            dismiss();
        }
    }
}
