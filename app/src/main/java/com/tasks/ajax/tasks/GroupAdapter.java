package com.tasks.ajax.tasks;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class GroupAdapter extends ArrayAdapter {

    private List<Group> groups;
    private Activity c;
    public GroupAdapter(Context context, List<Group> groups,Activity c){
        super(context,R.layout.listviewitem,groups);
        this.groups = groups;
        this.c = c;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Group group = groups.get(position);
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewitem,parent,false);
            holder = new ViewHolder();
            View view = inflater.inflate(R.layout.listviewitem,parent,false);
            holder.groupName = (TextView) convertView.findViewById(R.id.catname);
            holder.workCount = (TextView) convertView.findViewById(R.id.workcount);
            holder.edit = (Button)convertView.findViewById(R.id.catlisteditbutton);
            holder.delete = (Button)convertView.findViewById(R.id.catlistdeletebutton);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.fill(group);

        return convertView;
    }

    private class ViewHolder implements View.OnClickListener{
        public TextView groupName;
        public TextView workCount;
        public Button edit;
        public Button delete;

        public void fill(Group group){
            groupName.setText(group.getName());
            workCount.setText(group.getWorkCount());
            edit.setTag(group.getName());
            delete.setTag(group.getName());
            edit.setOnClickListener(this);;
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String name = (String)v.getTag();
            if(v.equals(edit)){
                EditGroup editGroup = new EditGroup(c,name);
                editGroup.show();
            }else if(v.equals(delete)){
                Sure sure = new Sure(c,name);
                sure.show();
            }
        }
    }
}
