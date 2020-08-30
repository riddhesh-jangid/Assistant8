package com.example.root.renametest;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by root on 20/1/18.
 */

public class weekservice extends IntentService {
    private NotificationManager alarmNotificationManager;
    Integer ho,mi;
    Integer sho,smi;
    Calendar calendar;
    String msg;
    public weekservice() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
       /* weekhelper wh=new weekhelper(this) {
        };
        SQLiteDatabase db=wh.getWritableDatabase();
        String [] columns = { wh . UID , wh . NAME , wh . HOUR , wh . MINUTE , wh . SUN , wh . MON , wh . TUE , wh . WEN , wh . THU , wh . FRI , wh . SAT , wh . ALARM};
        Cursor cursor = db . query ( wh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while(cursor.moveToNext()) {
            calendar=Calendar.getInstance();
            int cid = cursor.getInt(cursor.getColumnIndex(wh.UID));
            String name = cursor.getString(cursor.getColumnIndex(wh.NAME));
            sho
                    = cursor.getInt(cursor.getColumnIndex(wh.HOUR));
            smi
                    = cursor.getInt(cursor.getColumnIndex(wh.MINUTE));
            ho=calendar.get(Calendar.HOUR_OF_DAY);
            mi=calendar.get(Calendar.MINUTE);
            if(ho.equals(sho) && (mi.equals(smi) || mi.equals(smi+1)))
            {
                msg=cursor.getString(cursor.getColumnIndex(wh.NAME));
                break;
            }
            else
            {
                msg="Sorry some error occured";
            }
        }*/
        weekmsghelper whelp=new weekmsghelper(this) {
        };
        SQLiteDatabase db=whelp.getWritableDatabase();
        String columns [] ={whelp.NAME};
        Cursor cursor = db . query ( whelp . TABLE_NAME , columns , null , null , null , null , null );
        while (cursor.moveToNext())
        {
            msg=cursor.getString(cursor.getColumnIndex(whelp.NAME));
        }
        sendNotification(msg+"");
        weekmsg weeem=new weekmsg(this);
        weeem.delete(msg);
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        weekbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (weekbroadcast.alarmUri == null) {
            weekbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        weekbroadcast.ringtone = RingtoneManager.getRingtone(this, weekbroadcast.alarmUri);
        weekbroadcast.ringtone.play();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, weekstop.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }


}
