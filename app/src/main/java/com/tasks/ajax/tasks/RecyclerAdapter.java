package com.tasks.ajax.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Task> tasks;
    private static RecyclerViewClickListener itemListener;
    Activity activity;

    public RecyclerAdapter(List<Task> task,RecyclerViewClickListener itemListener,Activity ac){
        this.itemListener = itemListener;
        activity = ac;
        if(task == null){
            tasks = new ArrayList<Task>();
        }else{
            tasks = task;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.maintask,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(tasks.get(position),activity);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView name,deadline,id,start;
        RatingBar care;
        ImageView icon;
        CheckedTextView done;
        ImageView btn;
        Activity activity;
        boolean spClicked = false;
        PopupMenu pop;

        MyViewHolder(View itemview){
            super(itemview);
            name = (TextView)itemview.findViewById(R.id.itemname);
            id = (TextView)itemview.findViewById(R.id.itemid);
            care = (RatingBar)itemview.findViewById(R.id.itemcare);
            icon = (ImageView) itemview.findViewById(R.id.itemicon);
            btn = (ImageView) itemview.findViewById(R.id.itemmore);
            done = (CheckedTextView) itemview.findViewById(R.id.itemisdone);
            itemview.setOnClickListener(this);
        }

        public void bind(Task task,Activity ac){
            final Activity activity = ac;
            name.setText(task.getName());
            id.setText(task.getId());
            care.setRating(Float.valueOf(task.getRate()));
            icon.setImageResource(Integer.valueOf(task.getIcon()));
            btn.setTag(task.getId());
            final String s = task.getId();
            pop = new PopupMenu(btn.getContext(),btn);
            pop.inflate(R.menu.popupmenu);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.show();
                }
            });
            pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().toString().equals("Edit")){
                        Edit_Task.getid = s;
                        Intent intent = new Intent(activity,Edit_Task.class);
                        activity.startActivity(intent);
                    }else if(item.getTitle().toString().equals("Delete")){
                        SureDeleteTask sure = new SureDeleteTask(activity,s);
                        sure.show();
                    }

                    return false;
                }
            });
            if(task.getIsdone().equals("no")){
                done.setVisibility(View.INVISIBLE);
            }else{
                done.setVisibility(View.VISIBLE);
            }
        }
        @Override
        public void onClick(View v) {
            /*boolean torokhodainjorynaia = false;
            try{
                String a = v.getTag().toString();
                Log.i("sddf:",a);
            }catch (Exception e) {*/
            itemListener.recyclerViewListClicked(v, this.getPosition());
                /*torokhodainjorynaia = true;
            }
            if(!torokhodainjorynaia){
                String getId = v.getTag().toString();
                pop.show();
            }*/

        }
    }
}
