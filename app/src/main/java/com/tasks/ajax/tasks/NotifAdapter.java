package com.tasks.ajax.tasks;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by AsaRayan on 28/01/2019.
 */

public class NotifAdapter {

    private String date;
    private String icon;
    private String title;
    private String content;
    private int mode = 0;
    private String id;
    private Context context;

    public NotifAdapter(){

    }

    public NotifAdapter(String id,String title,String content,String icon,String date,Context context){
        this.id = id;
        this.title = title;
        this.content = content;
        this.icon = icon;
        this.date = date;
        this.context = context;
    }
    public void addNot(){
        setMode();
        Log.i("ajax",date + ":" + mode + ":" + id);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        Bundle data = new Bundle();
        data.putString("id",id);
        Log.i("TIMEDATE:",icon + "");
        data.putString("icon",icon);
        data.putString("title",title);
        data.putString("content",content);
        data.putInt("mode",mode);
        alarmIntent.putExtras(data);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,Integer.parseInt(id), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Log.i("ajax","adapter:" + calDate());
        alarmManager.set(AlarmManager.RTC_WAKEUP,calDate(),pendingIntent);
    }
    public void addrepeatday(){
        setMode();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        Bundle data = new Bundle();
        data.putString("id",id);
        data.putString("icon",icon);
        data.putString("title",title);
        data.putString("content",content);
        data.putInt("mode",mode);
        alarmIntent.putExtras(data);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,Integer.parseInt(id), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Log.i("ajax","adapter:" + calDate());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calDate(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    public void addrepeatweek(){
        setMode();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        Bundle data = new Bundle();
        data.putString("id",id);
        data.putString("icon",icon);
        data.putString("title",title);
        data.putString("content",content);
        data.putInt("mode",mode);
        alarmIntent.putExtras(data);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,Integer.parseInt(id), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Log.i("ajax","adapter:" + calDate());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calDate(),AlarmManager.INTERVAL_DAY * 7,pendingIntent);
    }

    public void addrepeatmonth(){
        setMode();
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        Bundle data = new Bundle();
        data.putString("id",id);
        data.putString("icon",icon);
        data.putString("title",title);
        data.putString("content",content);
        data.putInt("mode",mode);
        alarmIntent.putExtras(data);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,Integer.parseInt(id), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Log.i("ajax","adapter:" + calDate());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calDate(),AlarmManager.INTERVAL_DAY * 30,pendingIntent);
    }

    public void newNot(){
        NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(context);

        mBuilder.setContentTitle(title);
        mBuilder.setContentText(content);
        mBuilder.setTicker("زمان شروع کار");
        mBuilder.setSmallIcon(Integer.parseInt(icon));
        mBuilder.setAutoCancel(true);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mBuilder.setWhen(calDate());
/*        Intent resultIntent = new Intent(context, ShowTask.class);

        TaskStackBuilder stackBuilder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder.addParentStack(ShowTask.class);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder.addNextIntent(resultIntent);
        }
        PendingIntent resultPendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        mBuilder.setContentIntent(resultPendingIntent);*/
        Intent intent = new Intent(context, ShowTask.class);
        intent.putExtra("id",id.substring(0,id.length()-1));
        PendingIntent pIntent = null;
        pIntent = PendingIntent.getActivity(context,(int) System.currentTimeMillis(), intent, 0);
        mBuilder.setContentIntent(pIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(Integer.parseInt(id), mBuilder.build());

    }

    public void deleteNot(String getid){
        setDate("3000/05/05-00:01");
        addNot();
    }

    public void editNot(String id,String newDate){
        setDate(newDate);
        setId(id);
        addNot();
    }

    private long calDate(){
        long a = 0;
        Bundle args = DateTime.getValues(date);
        int year = args.getInt("year");
        int month = args.getInt("month");
        int day = args.getInt("day");
        int hour = args.getInt("hour");
        int minute = args.getInt("minute");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year,month - 1,day,hour,minute,0);
        a = calendar.getTimeInMillis();
        Log.i("showmetime",calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DATE) +
                calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        return a;
    }

    private void setMode(){
        String d = id.substring(id.length()-1,id.length());
        switch(d){
            case "1":
                mode = 1;
                break;
            case "2":
                mode = 2;
                break;
            case "3":
                mode = 3;
                break;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContext(Context context){ this.context = context; }
}
