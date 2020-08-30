package com.example.root.renametest;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;

/**
 * Created by root on 15/1/18.
 */

public class birthbroadcast extends WakefulBroadcastReceiver {
    MediaPlayer mp;
    static Uri alarmUri;
    static Ringtone ringtone;
    Integer cd,cm,cy;
    Integer md;
    Integer mm;
    @Override
    public void onReceive(Context context, Intent intent) {
        //
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,24);
        Intent in = new Intent(context, birthbroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 110299, in, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        md=0;
        mm=0;
        birthhelper bh=new birthhelper(context) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( bh . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( bh . MONTH ));
            cd= Calendar.getInstance().get(Calendar.DATE);
            cm=Calendar.getInstance().get(Calendar.MONTH)+1;
            cy=Calendar.getInstance().get(Calendar.YEAR);
            if(date==1)
            {
               if(month==1)
               {
                   md=31;
                   mm=12;
               }
               else if(month==2)
               {
                   md=31;
                   mm=1;
               }
               else if(month==3)
               {
                   if(cy%4==0)
                      cd=29;
                   else
                       cd=28;
                   mm=2;
               }
               else if(month==4)
               {
                   md=31;
                   mm=3;
               }
               else if(month==5)
               {
                   md=30;
                   mm=4;
               }
               else if(month==6)
               {
                   md=31;
                   mm=5;
               }
               else if(month==7)
               {
                   md=30;
                   mm=6;
               }
               else if(month==8)
               {
                   md=31;
                   mm=7;
               }
               else if(month==9)
               {
                   md=31;
                   mm=8;
               }
               else if(month==10)
               {
                   md=30;
                   mm=9;
               }
               else if(month==11)
               {
                   md=31;
                   mm=10;
               }
               else if(month==12)
               {
                   md=30;
                   mm=11;
               }
               else;
            }
            else
            {
                md=date-1;
                mm=month;
            }
            if(md==cd && mm==cm) {
                i++;
            }
        }
        if(i>0) {
            if (Build.VERSION.SDK_INT >= 26) {
                ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(R.dimen.vibratortime);

            }

            ComponentName comp = new ComponentName(context.getPackageName(),
                       birthdayservice.class.getName());
               startWakefulService(context, (intent.setComponent(comp)));
               setResultCode(Activity.RESULT_OK);
           }
    }
}
