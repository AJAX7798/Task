package com.tasks.ajax.tasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class Db_helper extends SQLiteOpenHelper{

    private static final String dbname = "Taskdb";
    private static final int version = 1;

    private static final String gtbl = "Group";//group table name
    private static final String ttbl = "Task";//Tasks table name
    private static final String uptbl = "UP";//User_Pass table name
    Context to;

    public Db_helper(Context context) {
        super(context, dbname, null, version);
        to = context;
    }

    //Create Tables
    public void createTables(){
        String createGp = "Create Table IF Not exists 'Group'('name' TEXT UNIQUE)";
        String createtsk = "Create Table If not exists 'Task'('id' Integer,'name' TEXT,'cat' TEXT,'details' TEXT,'settime' TEX"+
                "T,'remember' TEXT,'icon' TEXT,'deadline' TEXT,'donetime' TEXT,'isdone' TEXT,'rate' TEXT,'repeat' TEXT)";
        String createup = "Create Table if not exists 'UP' ('pass' TEXT)";
        String createbg = "Create Table if not exists 'bg' ('src' TEXT)";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(createGp);
        db.execSQL(createtsk);
        db.execSQL(createup);
        db.execSQL(createbg);
        db.close();
        Log.i("sqlex","Tables Created");
    }

    //Insert
    public void insertToGroup(String name){
        String insert = "Insert Into '" + gtbl + "' Values('"+ name +"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
        db.close();
    }
    public void insertToBG(String src){
        String insert = "Insert Into 'bg' Values('"+ src +"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
        db.close();
    }

    public void insertToTask(String id,String name,String group,String details,String settime,String remember,String icon,String deadline,
                             String donetime,String isdone,String rate,String repeat){
        String insert = "Insert Into '"+ ttbl +"' Values('" + id + "','" + name + "','" + group + "','" + details + "','" + settime
                + "','" + remember + "','" + icon + "','" + deadline + "','" + donetime + "','" + isdone + "','" + rate + "','" + repeat +"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
        db.close();
    }

    public Cursor getBG(){
        SQLiteDatabase db = getReadableDatabase();
        String select = "select * from 'bg'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor sort(String cat,int mode){
        SQLiteDatabase db = getReadableDatabase();
        String select = "select * from '" +ttbl + "' where cat='" + cat + "' ";
        switch(mode){
            case 0:
                select += "order by rate desc ,deadline asc";
                break;
            case 1:
                select += "order by rate asc ,deadline asc";
                break;
            case 2:
                select += "order by isdone desc ,rate desc";
                break;
            case 3:
                select += "order by isdone asc ,rate desc";
                break;
            case 4:
                select += "order by deadline desc ,rate desc";
                break;
            case 5:
                select += "order by deadline asc ,rate desc";
                break;
            case 6:
                select += "order by settime desc ,rate desc";
                break;
            case 7:
                select += "order by settime asc ,rate desc";
                break;
        }
        Cursor cursor = db.rawQuery(select,null);
        Log.i("sql:","mode:"+mode);
        return cursor;
    }

    public void insertToUP(String pass){
        String insert = "Insert Into '" + uptbl + "' Values('"+ pass +"')";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(insert);
        db.close();
    }

    //Select Query
    public Cursor selectGroup(){
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select * From '" + gtbl +"'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public int getGroupCount(){
        int count = 0;
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select count(name) From '" + gtbl + "'";
        Cursor cursor = db.rawQuery(select,null);
        if(cursor.moveToFirst()){
            count = Integer.parseInt(cursor.getString(0));
        }
        return count;
    }

    public Cursor selectTask(){
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select * From '" + ttbl +"'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor selectTaskByGroup(String group){
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select * From '" + ttbl + "' where cat = '"+ group + "'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor selectUP(){
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select * From '" + uptbl +"'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    public Cursor selectTaskById(String id){
        SQLiteDatabase db = getReadableDatabase();
        String select = "Select * From '" + ttbl + "' where id = '"+ id + "'";
        Cursor cursor = db.rawQuery(select,null);
        return cursor;
    }

    //Delete
    public void deleteGroup(String name){
        SQLiteDatabase db = getWritableDatabase();
        String delete = "Delete From '" + gtbl +"' where name='"+ name + "'";
        db.execSQL(delete);
        db.close();
    }

    public void deleteTask(String id){
        SQLiteDatabase db = getWritableDatabase();
        String delete = "Delete From '" + ttbl +"' where id='"+ id + "'";
        db.execSQL(delete);
        db.close();
    }

    public void deleteUP(){
        SQLiteDatabase db = getWritableDatabase();
        String delete = "Delete From '" + uptbl +"'";
        db.execSQL(delete);
        db.close();
    }

    public void deleteTaskByGroup(String groupName){
        SQLiteDatabase db = getWritableDatabase();
        String delete = "Delete From '" + ttbl +"' where cat='"+ groupName + "'";
        db.execSQL(delete);
        db.close();
    }

    //Update

    public void updateGroup(String oldname,String newname){
        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ gtbl + "' Set name='"+ newname +"' where name='"+ oldname + "'";
        db.execSQL(update);
        Log.i("sqlex","Group Updated!");
        db.close();
    }

    public void updateBG(String newsrc,String oldsrc){

        SQLiteDatabase db = getWritableDatabase();
        String update = "Update 'bg' Set src='"+ newsrc +"' where src='"+ oldsrc + "'";
        db.execSQL(update);
        Log.i("sqlex","BG Updated!");
        db.close();
    }

    public void updateGroupOfTask(String oldcat,String newcat){
        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ ttbl + "' Set cat='"+ newcat +"' where cat='"+ oldcat + "'";
        db.execSQL(update);
        Log.i("sqlex","Task Updated!");
        db.close();
    }
    public void updateTask(String name,String group,String details,String settime,String remember,String icon,String deadline
                                 ,String rate,String repeat,String keyid){

        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ ttbl + "' Set name='" + name + "'" +" ,cat='"+ group +"' ,details='" + details
                + "' ,settime='"+ settime + "' ,remember='" + remember +"',icon='" + icon + "' ,deadline='"+ deadline
                + "' ,rate='" + rate + "' ,repeat='" + repeat  + "' where id='"+ keyid + "'";
        db.execSQL(update);
        Log.i("sqlex","Task Updated!");
        db.close();
    }
    public void updateTask(String name,String group,String details,String icon,
                           String donetime,String rate,String keyid){

        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ ttbl + "' Set name='" + name + "'" +" ,cat='"+ group +"' ,details='" + details
                + "' ,icon='" + icon + "',donetime='" + donetime + "' ,rate='" + rate + "' where id='"+ keyid + "'";
        db.execSQL(update);
        Log.i("sqlex","Task Updated!");
        db.close();
    }
    public void setDoneTask(String id,String time){
        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ ttbl + "' Set isdone='yes' , donetime='"+ time + "' where id='"+ id + "'";
        db.execSQL(update);
        Log.i("sqlex","Task done!");
        db.close();
    }

    public void updateUP(String oldpass,String newpass){
        SQLiteDatabase db = getWritableDatabase();
        String update = "Update '"+ uptbl + "' Set pass='"+ newpass +"' where pass='"+ oldpass + "'";
        db.execSQL(update);
        Log.i("sqlex","UP Updated!");
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
