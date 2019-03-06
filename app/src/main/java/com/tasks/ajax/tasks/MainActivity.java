package com.tasks.ajax.tasks;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Context to;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void forceRTLIfSupported()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        forceRTLIfSupported();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Bundle bundle = getIntent().getExtras();
        boolean firstTime = false;
        if(bundle != null && Login.isLoggedIn){
            Intent intent1 = new Intent(MainActivity.this,ShowTask.class);
            intent1.putExtras(bundle);
            startActivity(intent1);
        }
        to = this;
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSansMobile.ttf");
        TextView txt = (TextView)findViewById(R.id.textView10);
        final EditText pass = (EditText)findViewById(R.id.password);
        Button login = (Button)findViewById(R.id.sabtpass);
        txt.setTypeface(typeface);
        try{
            String a = bundle.getString("ids");
            Log.i("kijax",a);
        }catch (Exception e){
            firstTime = true;
        }
        boolean firstTimetoapp = false;
        Db_helper dbHelper = new Db_helper(this);
        try{
            Cursor cursor = dbHelper.selectGroup();
            if(cursor.moveToFirst()){

            }
        }catch (Exception e){
            firstTimetoapp = true;
        }


        dbHelper.createTables();
        if(firstTimetoapp){
            dbHelper.insertToGroup("Group 1");
            dbHelper.insertToBG(String.valueOf(R.drawable.newback2));
        }
        if(through() && firstTime){
            Intent intent = new Intent(MainActivity.this,Tasks.class);
            startActivity(intent);
        }
        else if(through() && bundle != null){
            Intent intent = new Intent(MainActivity.this,ShowTask.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean firstTime1 = false;
                try{
                    String a = bundle.getString("ids");
                    Log.i("kijax",a);
                }catch (Exception e){
                    firstTime1 = true;
                }
                if(checkValid(pass.getText().toString()) && firstTime1){
                    Login.logIn();
                    Intent intent = new Intent(MainActivity.this,Tasks.class);
                    startActivity(intent);
                }else if(checkValid(pass.getText().toString()) && bundle != null && !firstTime1){
                    Login.logIn();
                    Intent intent = new Intent(MainActivity.this,ShowTask.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(to,"Invalid Password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkValid(String pass){
        boolean valid = true;
        Db_helper dbhelper = new Db_helper(this);
        Cursor c = dbhelper.selectUP();
        if(c.moveToFirst()){
            do{
                if(pass.equals(c.getString(0))){
                    valid = true;
                }else{
                    valid = false;
                }
            }while(c.moveToNext());
        }
        return valid;
    }
    private boolean through() {
        boolean go = true;
        Db_helper dbHelper = new Db_helper(this);
        Cursor cursor = dbHelper.selectUP();
        if(cursor.moveToFirst()){
            do{
                go = false;
            }while(cursor.moveToNext());
        }
        return go;
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String a = item.getTitle().toString();
        if(a.equals("addtask")){
            Intent intent = new Intent(MainActivity.this,AddingTask.class);
            startActivity(intent);
        }
        else if(a.equals("addcat")){
            Intent intent = new Intent(MainActivity.this,Stat.class);
            startActivity(intent);
        }
        else if(a.equals("setpass")){
            Intent intent = new Intent(MainActivity.this,Tasks.class);
            startActivity(intent);
        }
        else if(a.equals("changepass")){
            Intent intent = new Intent(MainActivity.this,Tasks.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
*/
}
