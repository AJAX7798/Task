package com.tasks.ajax.tasks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BackgroundSetting extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RadioGroup group;
    String oldsrc = "";
    RadioButton r1,r2,r3,r4;
    int preId = 0;
    int getSrc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_setting);
        r1 = (RadioButton)findViewById(R.id.aval);
        r2 = (RadioButton)findViewById(R.id.dovom);
        r3 = (RadioButton)findViewById(R.id.sevom);
        r4 = (RadioButton)findViewById(R.id.charom);
        r1.setOnCheckedChangeListener(this);
        r2.setOnCheckedChangeListener(this);
        r3.setOnCheckedChangeListener(this);
        r4.setOnCheckedChangeListener(this);

        Db_helper dbHelper = new Db_helper(this);
        Cursor cursor = dbHelper.getBG();
        if(cursor.moveToFirst()){
            oldsrc = cursor.getString(0);
        }
        group = (RadioGroup)findViewById(R.id.ra1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buttons,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean checked = true;
    /*    switch(id){
            case R.id.aval:
                getSrc = R.drawable.newback;
                break;
            case R.id.dovom:
                getSrc = R.drawable.newback3;
                break;

            case R.id.sevom:
                getSrc = R.drawable.newback2;
                break;

            case R.id.charom:
                getSrc = R.drawable.newback1;
                break;
            default:
                Toast.makeText(this,"لطفا یک پس زمینه را انتخاب کنید",Toast.LENGTH_SHORT).show();
                checked = false;
        }*/
        if(getSrc == 0){
            checked = false;
        }
        if(checked) {
            Db_helper dbHelper = new Db_helper(this);
            dbHelper.updateBG(String.valueOf(getSrc),oldsrc);
            Intent intent = new Intent(BackgroundSetting.this,Tasks.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int getId = buttonView.getId();
        if(preId == 0){
            preId = getId;
        }else if(preId != getId){
            RadioButton r = (RadioButton)findViewById(preId);
            Toast.makeText(this, "ss", Toast.LENGTH_SHORT).show();
            r.setChecked(false);
        }
        switch(getId) {
            case R.id.aval:
                getSrc = R.drawable.newback;
                break;
            case R.id.dovom:
                getSrc = R.drawable.newback3;
                break;

            case R.id.sevom:
                getSrc = R.drawable.newback2;
                break;

            case R.id.charom:
                getSrc = R.drawable.newback1;
                break;
        }
        preId = getId;
    }
}
