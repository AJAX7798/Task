package com.tasks.ajax.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowTask extends AppCompatActivity {

    TextView name;
    TextView cat;
    TextView details;
    TextView settime;
    TextView deadline;
    TextView isdone;
    TextView remember;
    TextView donetime ;
    ImageView icon ;
    RatingBar care;
    Button edit;
    Button delete;
    Button done;
    Context context;
    Activity activity;
    private static String id;
    boolean taskisdone = false;
    boolean isNotify = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
/*        Bundle bundle = new Bundle();
        Bundle args = savedInstanceState;*/
        Bundle data = getIntent().getExtras();
        if(data != null){
            id = data.getString("ids");
            Log.i("ajax","ShowTask:" + id);
        }

        edit = (Button)findViewById(R.id.edittaskbutton);
        delete = (Button)findViewById(R.id.deletetaskbutton);
        done = (Button)findViewById(R.id.donetaskbutton);
        context = this;
        activity = this;
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Task.getid = id;
                Intent intent = new Intent(ShowTask.this,Edit_Task.class);
                startActivity(intent);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Db_helper dbhelper = new Db_helper(context);
                String time = DateTime.getNow();
                dbhelper.setDoneTask(id,time);
                NotifAdapter notifAdapter = new NotifAdapter();
                notifAdapter.setId(id + "1");
                notifAdapter.setContext(context);
                notifAdapter.setDate("3000/06/07-12:45");
                notifAdapter.deleteNot(id + "1");

                Cursor cursor = dbhelper.selectTaskById(id);
                if(cursor.moveToFirst()){
                    do{
                        if(!cursor.getString(5).equals("")){
                            NotifAdapter notifAdapter1 = new NotifAdapter();
                            notifAdapter1.setId(id + "2");
                            notifAdapter1.setContext(context);
                            notifAdapter1.setDate("3000/06/07-12:45");
                            notifAdapter1.deleteNot(id + "2");
                        }

                        if(!cursor.getString(7).equals("n")){
                            NotifAdapter notifAdapter2 = new NotifAdapter();
                            notifAdapter2.setId(id + "3");
                            notifAdapter2.setContext(context);
                            notifAdapter2.setDate("3000/06/07-12:45");
                            notifAdapter2.deleteNot(id + "3");
                        }

                    }while(cursor.moveToNext());
                }

                Toast.makeText(context,"Congratulations! The " + name.getText().toString()+ " has done"
                        ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShowTask.this,ShowTask.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SureDeleteTask sure = new SureDeleteTask(activity,id);
                sure.show();
            }
        });
        initial();
        if(taskisdone){
            done.setVisibility(View.GONE);
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ShowTask.this,Tasks.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(ShowTask.this,Tasks.class);
            this.startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private void initial() {
        name = (TextView)findViewById(R.id.showtaskname);
        cat = (TextView)findViewById(R.id.showcatname);
        details = (TextView)findViewById(R.id.showdetails);
        settime = (TextView)findViewById(R.id.showsettime);
        deadline = (TextView)findViewById(R.id.showdeadline);
        isdone = (TextView)findViewById(R.id.textView36);
        remember = (TextView)findViewById(R.id.showremember);
        donetime = (TextView)findViewById(R.id.showdonetime);
        icon = (ImageView)findViewById(R.id.imageView4);
        care = (RatingBar)findViewById(R.id.ratingBar2);
        getFullItem();
    }

    private void getFullItem(){
        Db_helper dbhelper = new Db_helper(this);
        Cursor cursor = dbhelper.selectTaskById(id);
        int i = 0;
        if(cursor.moveToFirst()){
            do{
                name.setText(cursor.getString(1));
                cat.setText(cursor.getString(2));
                details.setText(cursor.getString(3));
                settime.setText(cursor.getString(4));
                remember.setText(cursor.getString(5));
                if(cursor.getString(7).equals("n")){
                    deadline.setText("Not set");
                }else{
                    deadline.setText(cursor.getString(7));
                }
                donetime.setText(cursor.getString(8));
                if(cursor.getString(9).equals("no")){
                    isdone.setText("undone");
                }else{
                    isdone.setText("done");
                    taskisdone = true;
                }
                icon.setImageResource(Integer.parseInt(cursor.getString(6)));
                care.setRating(Float.parseFloat(cursor.getString(10)));
                i++;
            }while(cursor.moveToNext() && i < 1);
        }
    }
    public static void setId(String getid) {
        id = getid;
    }

}
