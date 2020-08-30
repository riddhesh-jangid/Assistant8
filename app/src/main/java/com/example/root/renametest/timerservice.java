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

/**
 * Created by root on 19/1/18.
 */

public class timerservice extends IntentService {
    private NotificationManager alarmNotificationManager;
    Integer ye,mo,da,ho,mi;
    Integer sye,smo,sda,sho,smi;
    String msg;
    public timerservice() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
      /*  timerhelper th=new timerhelper(this) {
        };
        SQLiteDatabase db=th.getWritableDatabase();
        String [] columns = { th . UID , th . NAME , th . YEAR , th . MONTH , th . DATE , th . HOUR , th . MINUTE , th . ALARM };
        Cursor cursor = db . query ( th . TABLE_NAME , columns , null , null , null , null , null );
        while(cursor.moveToNext()) {
            Calendar calendar=Calendar.getInstance();
            ye=calendar.get(Calendar.YEAR);
            mo=calendar.get(Calendar.MONTH)+1;
            da=calendar.get(Calendar.DAY_OF_MONTH);
            ho=calendar.get(Calendar.HOUR_OF_DAY);
            mi=calendar.get(Calendar.MINUTE);
            sye=cursor.getInt(cursor.getColumnIndex(th.YEAR));
            smo=cursor.getInt(cursor.getColumnIndex(th.MONTH));
            sda=cursor.getInt(cursor.getColumnIndex(th.DATE));
            sho=cursor.getInt(cursor.getColumnIndex(th.HOUR));
            smi=cursor.getInt(cursor.getColumnIndex(th.MINUTE));
           if(ye.equals(sye) && mo.equals(smo) && da.equals(sda) && ho.equals(sho) && (mi.equals(smi) || mi.equals(smi+1)))
           {
               msg=cursor.getString(cursor.getColumnIndex(th.NAME));
               break;
           }
           else
           {
               msg="sorry some error occured";
           }
        }*/
        timermsghelper timh=new timermsghelper(this) {
        };
        SQLiteDatabase db=timh.getWritableDatabase();
        String columns [] ={timh.NAME};
        Cursor cursor = db . query ( timh . TABLE_NAME , columns , null , null , null , null , null );
        while (cursor.moveToNext())
        {
            msg=cursor.getString(cursor.getColumnIndex(timh.NAME));
        }
        sendNotification(msg+"");
        timermsg timm=new timermsg(this);
        timm.delete(msg);
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        timerbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (timerbroadcast.alarmUri == null) {
            timerbroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        timerbroadcast.ringtone = RingtoneManager.getRingtone(this, timerbroadcast.alarmUri);
        timerbroadcast.ringtone.play();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, timerstop.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }

}
