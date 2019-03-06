package com.tasks.ajax.tasks;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetPass extends AppCompatActivity {

    Context to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass);
        boolean checkgo = false;
        checkgo = checktogo();
        if(checkgo){
            Intent intent = new Intent(SetPass.this,ChangePass.class);
            startActivity(intent);
        }
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile.ttf");
        TextView txt = (TextView)findViewById(R.id.textView11);
        TextView txt1 = (TextView)findViewById(R.id.textView12);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText agpassword = (EditText)findViewById(R.id.agpassword);
        Button sabt = (Button)findViewById(R.id.sabtpass);
/*        editText.setTypeface(typeface);
        editText1.setTypeface(typeface);*/
        password.requestFocus();
        sabt.setTypeface(typeface);
        txt.setTypeface(typeface);
        txt1.setTypeface(typeface);
        to = this;
        sabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_helper dbHelper = new Db_helper(to);
                if(validation(to,password,agpassword)){
                    dbHelper.insertToUP(password.getText().toString().trim());
                    Intent intent = new Intent(SetPass.this,Tasks.class);
                    startActivity(intent);

                /*    Cursor cursor = dbHelper.selectUP();
                    if(cursor.moveToFirst()){
                        do{
                            Toast.makeText(to,cursor.getString(0),Toast.LENGTH_SHORT).show();
                        }while(cursor.moveToNext());
                    }*/
                }
            }
        });

    }

    private boolean checktogo() {
        Db_helper dbhelper = new Db_helper(this);
        Cursor c = dbhelper.selectUP();
        boolean check = false;
        if(c.moveToFirst()){
            do{
                check = true;
            }while(c.moveToNext());
        }
        return check;
    }

    boolean validation(Context to,EditText password,EditText agpassword){
        boolean isvalid = false;
        if(!password.getText().toString().trim().equals("")&&!agpassword.getText().toString().trim().equals("")){
            if(password.getText().toString().trim().equals(agpassword.getText().toString().trim())){
                isvalid = true;
            }else{
                Toast.makeText(to,"Invalid Repeat Password!",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(to,"Password and repeat password field must not be empty!",Toast.LENGTH_SHORT).show();
        }

        return isvalid;
    }
}
