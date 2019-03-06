package com.tasks.ajax.tasks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Group_Management extends AppCompatActivity {

    List<Group> groups;
    GroupAdapter adapter;
    ListView listView;
    Db_helper dbhelper;
    FloatingActionButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_management);
        listView = (ListView)findViewById(R.id.grouplistview);
        groups = new ArrayList<>();
        dbhelper = new Db_helper(this);
        button = (FloatingActionButton)findViewById(R.id.floatingActionButton1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Group_Management.this,addcat.class);
                startActivity(intent);
            }
        });
        prepareData();
        refresh();
    }
    private void prepareData(){
        dbhelper = new Db_helper(this);
        Cursor cursor = dbhelper.selectGroup();
        if(cursor.moveToFirst()){
            do{
                Group group = new Group();
                String groupName = cursor.getString(0);
                group.setName(groupName);
                group.setWorkCount(String.valueOf(getWorkCount(groupName)));
                groups.add(group);
            }while(cursor.moveToNext());
        }
    }

    private void refresh(){
        adapter = new GroupAdapter(this,groups,this);
        listView.setAdapter(adapter);
    }

    private int getWorkCount(String groupName){
        int workCount = 0;
        dbhelper = new Db_helper(this);
        Cursor cursor = dbhelper.selectTaskByGroup(groupName);
        if(cursor.moveToFirst()){
            do {
                workCount++;
            }while(cursor.moveToNext());
        }
        return workCount;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Group_Management.this,Tasks.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Group_Management.this,Tasks.class);
            this.startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
