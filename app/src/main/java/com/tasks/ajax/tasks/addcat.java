package com.tasks.ajax.tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addcat extends AppCompatActivity {

    Db_helper db_helper;
    Button sabt;
    Context to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        forceRTLIfSupported();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcat);
        final EditText gpname = (EditText)findViewById(R.id.gpname);
        sabt = (Button)findViewById(R.id.sabtgp);
        to = this;
        sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db_helper = new Db_helper(to);
                try {
                    if(!gpname.getText().toString().trim().equals("")) {
                        db_helper.insertToGroup(gpname.getText().toString().trim());
                        Intent intent = new Intent(addcat.this,Tasks.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(to,"The title must not be empty!",Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(to,"A group with this name already created.Please Enter Another name!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}
