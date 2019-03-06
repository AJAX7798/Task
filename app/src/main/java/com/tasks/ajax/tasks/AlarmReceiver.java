package com.tasks.ajax.tasks;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;


public class AlarmReceiver extends BroadcastReceiver {
    String title;
    String content;
    int mode;
    String id;
    String icon;
    String ticker;
    Bundle args;

    @Override
    public void onReceive(Context context, Intent intent) {

        args = intent.getExtras();
        if(args != null) {
            title = args.getString("title");
            content = args.getString("title");
            mode = args.getInt("mode");
            id = args.getString("id");
            Log.i("ajax","receiver:" + id);
            icon = args.getString("icon");
            setAspect();
        }
        Builder  mBuilder = new Builder(context);

        mBuilder.setContentTitle(title);
        mBuilder.setContentText(content);
        mBuilder.setTicker(ticker);
        if(icon.equals(String.valueOf(R.drawable.noicon))) {
            mBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
        }else{
            mBuilder.setSmallIcon(Integer.parseInt(icon));
        }
        mBuilder.setAutoCancel(true);
        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Intent intent1 = new Intent(context, MainActivity.class);
    //    intent1.putExtra("id",id.substring(0,id.length()-1));
        Bundle bi = new Bundle();
        bi.putString("ids",id.substring(0,id.length()-1));
    //    bi.putString("")
        intent1.putExtras(bi);
        Log.i("Notification_log:",id.substring(0,id.length()-1));
        PendingIntent pIntent = null;
        pIntent = PendingIntent.getActivity(context,(int)System.currentTimeMillis(), intent1, 0);
        mBuilder.setContentIntent(pIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(Integer.parseInt(id), mBuilder.build());

    }

    private void setAspect(){
        Log.i("ajax",id + ":" + mode);
        switch(mode){
            case 1:
                title = "Start Task";
                break;
            case 2:
                title = "Reminder";
                break;
            case 3:
                title = "Deadline Reached";
                break;
        }
    }

}

