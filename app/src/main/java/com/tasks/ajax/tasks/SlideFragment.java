package com.tasks.ajax.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class SlideFragment extends Fragment implements  RecyclerViewClickListener{

    public String test_text = "Hello BAYBAY";
    public static int mode = 0;
    public static Context context1;
    public static Activity activity;
    public static SlideFragment newSlide(String group,int sort_mode){
        SlideFragment slideFragment = new SlideFragment();
        Bundle args = new Bundle();
        args.putString("Group_name",group);
        mode = sort_mode;
        slideFragment.setArguments(args);
        return slideFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args == null)
            return;
        test_text = args.getString("Group_name");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.slide,container, false);
//        ((TextView)view.findViewById(R.id.slide_test_text)).setText(test_text);
        RecyclerAdapter adapter = new RecyclerAdapter(getItemsFromDB(test_text,mode),this,activity);
        ((RecyclerView)view.findViewById(R.id.recyclerview)).setLayoutManager(new LinearLayoutManager(context1));
        ((RecyclerView)view.findViewById(R.id.recyclerview)).setItemAnimator(new DefaultItemAnimator());
        ((RecyclerView)view.findViewById(R.id.recyclerview)).setAdapter(adapter);
        return view;

    }

    @Override
    public void recyclerViewListClicked(View v, int position){
        TextView text = (TextView) v.findViewById(R.id.itemid);
        ShowTask.setId(text.getText().toString());
        Intent intent = new Intent(activity.getBaseContext(),ShowTask.class);
        activity.startActivity(intent);
    }
    List<Task> getItemsFromDB(String group,int mode){
        List<Task> list = new ArrayList<Task>();
        Db_helper dbHelper = new Db_helper(getContext());
        Cursor cursor = dbHelper.sort(group,mode);
        if(cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setId(cursor.getString(0));
                task.setName(cursor.getString(1));
                task.setRate(cursor.getString(10));
                task.setIcon(cursor.getString(6));
                task.setIsdone(cursor.getString(9));
                list.add(task);
            }while(cursor.moveToNext());
        }
        return list;
    }

}
