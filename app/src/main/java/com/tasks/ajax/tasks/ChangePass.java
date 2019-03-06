package com.tasks.ajax.tasks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePass extends AppCompatActivity {

    Context to;
    boolean remove = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile.ttf");
        TextView txt = (TextView)findViewById(R.id.textView11);
        TextView txt1 = (TextView)findViewById(R.id.textView12);
        Button sabt =  (Button)findViewById(R.id.sabtpass);
        final EditText oldpassword = (EditText)findViewById(R.id.oldpassword);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText agpassword = (EditText)findViewById(R.id.agpassword);
/*        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);*/
        oldpassword.requestFocus();
        sabt.setTypeface(typeface);
        txt.setTypeface(typeface);
        txt1.setTypeface(typeface);
        to = this;
        sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_helper dbHelper = new Db_helper(to);
                if(validation(to,dbHelper,oldpassword,password,agpassword)){
                    if(remove)
                        dbHelper.deleteUP();
                    else
                    dbHelper.updateUP(oldpassword.getText().toString().trim(),password.getText().toString().trim());
                    Intent intent = new Intent(ChangePass.this,Tasks.class);
                    startActivity(intent);
                /*    Cursor cursor = dbHelper.selectUP();
                    if(cursor.moveToFirst()){
                        do{
                            Toast.makeText(to,cursor.getString(0),Toast.LENGTH_SHORT).show();
                        }while (cursor.moveToNext());
                    }*/
                }
            }
        });
    }

    boolean validation(Context to,Db_helper dbHelper,EditText oldpassword, EditText password, EditText agpassword){
        boolean isvalid = false;
        boolean isoldonevalid = false;
        String oldpass = "";
        Cursor cursor = dbHelper.selectUP();
        if(cursor.moveToFirst()){
            oldpass = cursor.getString(0);
            cursor.close();
        }
        if(!oldpassword.getText().toString().trim().equals(oldpass)){
            Toast.makeText(to,"Invalid OldPassword!",Toast.LENGTH_SHORT).show();
        }else{
            isoldonevalid = true;
        }
            if(password.getText().toString().trim().equals(agpassword.getText().toString().trim())){
                isvalid = true;
                if(password.getText().toString().trim().equals(""))
                    remove = true;
            }else{
                Toast.makeText(to,"Invalid Password Repeat!",Toast.LENGTH_SHORT).show();
            }

        return isvalid & isoldonevalid;
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ChangePass.this,Tasks.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ChangePass.this,Tasks.class);
            this.startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
