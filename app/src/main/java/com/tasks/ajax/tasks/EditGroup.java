package com.tasks.ajax.tasks;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by AsaRayan on 21/01/2019.
 */

public class EditGroup extends Dialog implements View.OnClickListener{


    public Activity c;
    Button ok;
    Button cancel;
    EditText newName;
    private String groupName;
    public EditGroup(Activity a,String groupName){
        super(a);
        this.c = a;
        this.groupName = groupName;
    }

    @Override
    protected void onCreate(Bundle newSaveInstanceStat){
        super.onCreate(newSaveInstanceStat);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.editdialog);
        ok = (Button)findViewById(R.id.button8);
        cancel = (Button)findViewById(R.id.button7);
        newName = (EditText)findViewById(R.id.editText2);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }
    private void editGroup(){
        Db_helper dbHelper = new Db_helper(c);
        try {
            if (!newName.getText().toString().trim().equals("")) {
                dbHelper.updateGroup(groupName, newName.getText().toString().trim());
                dbHelper.updateGroupOfTask(groupName,newName.getText().toString().trim());
                Intent intent = new Intent(c, Group_Management.class);
                c.startActivity(intent);
            }
            else{
                dismiss();
                Toast.makeText(c,"The title must not be empty!" ,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            dismiss();
            Toast.makeText(c,"A group with This title already created.Please enter another title!" ,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {
        if(v.equals(ok)){
            editGroup();
            dismiss();
        }else if(v.equals(cancel)){
            dismiss();
        }
    }
}
