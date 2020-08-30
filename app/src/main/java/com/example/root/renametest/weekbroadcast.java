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
 * Created by root on 20/1/18.
 */

public class weekbroadcast  extends WakefulBroadcastReceiver {
    MediaPlayer mp;
    static Uri alarmUri;
    static Ringtone ringtone;
    Integer cd,cm,sho,smi,ho,mi,dw,fr=0;
    Calendar calendar;
    Integer alarmweek;
    @Override
    public void onReceive(Context context, Intent intent) {
        weekhelper wh=new weekhelper(context) {
        };
        SQLiteDatabase db=wh.getWritableDatabase();
        String [] columns = { wh . UID , wh . NAME , wh . HOUR , wh . MINUTE , wh . SUN , wh . MON , wh . TUE , wh . WEN , wh . THU , wh . FRI , wh . SAT , wh . ALARM};
        Cursor cursor = db . query ( wh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while(cursor.moveToNext()) {
            Calendar cal=Calendar.getInstance();
            int cid = cursor.getInt(cursor.getColumnIndex(wh.UID));
            String name = cursor.getString(cursor.getColumnIndex(wh.NAME));
            sho
                    = cursor.getInt(cursor.getColumnIndex(wh.HOUR));
            smi
                    = cursor.getInt(cursor.getColumnIndex(wh.MINUTE));
            ho=cal.get(Calendar.HOUR_OF_DAY);
            mi=cal.get(Calendar.MINUTE);
            if(ho.equals(sho) && (mi.equals(smi) || mi.equals(smi+1)))
            {
                alarmweek=cursor.getInt(cursor.getColumnIndex(wh.ALARM));
                break;
            }
            else
            {
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,24);
        Intent in = new Intent(context, weekbroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarmweek, in, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        wh=new weekhelper(context) {
        };

        db=wh.getWritableDatabase();
        String [] column = { wh . UID , wh . NAME , wh . HOUR , wh . MINUTE , wh . SUN , wh . MON , wh . TUE , wh . WEN , wh . THU , wh . FRI , wh . SAT , wh . ALARM};
        cursor = db . query ( wh . TABLE_NAME , column , null , null , null , null , null );
        i=0;
        while(cursor.moveToNext()) {
            calendar= Calendar.getInstance();
            int cid = cursor.getInt(cursor.getColumnIndex(wh.UID));
            String name = cursor.getString(cursor.getColumnIndex(wh.NAME));
            Integer sun
                    = cursor . getInt ( cursor . getColumnIndex ( wh . SUN ));
            Integer mon
                    = cursor . getInt ( cursor . getColumnIndex ( wh . MON ));
            Integer tue
                    = cursor . getInt ( cursor . getColumnIndex ( wh . TUE ));
            Integer wen
                    = cursor . getInt ( cursor . getColumnIndex ( wh . WEN ));
            Integer thu
                    = cursor . getInt ( cursor . getColumnIndex ( wh . THU ));
            Integer fri
                    = cursor . getInt ( cursor . getColumnIndex ( wh . FRI ));
            Integer sat
                    = cursor . getInt ( cursor . getColumnIndex ( wh . SAT ));

            sho
                    = cursor.getInt(cursor.getColumnIndex(wh.HOUR));
            smi
                    = cursor.getInt(cursor.getColumnIndex(wh.MINUTE));
            ho=calendar.get(Calendar.HOUR_OF_DAY);
            mi=calendar.get(Calendar.MINUTE);
            dw=calendar.get(Calendar.DAY_OF_WEEK);
            if(ho.equals(sho) && (mi.equals(smi) || mi.equals(smi+1)))
            {
                if(dw==1)
                {
                    if(sun==1)
                    {fr++;break;}
                }
                else if(dw==2)
                {
                    if(mon==1)
                    {fr++;break;}
                }
                else if(dw==3)
                {
                    if(tue==1)
                    {fr++;break;}
                }
                else if(dw==4)
                {
                    if(wen==1)
                    {fr++;break;}
                }
                else if(dw==5)
                {
                    if(thu==1)
                    {fr++;break;}
                }
                else if(dw==6)
                {
                    if(fri==1)
                    {fr++;break;}
                }
                else if(dw==7)
                {
                    if(sat==1)
                    {fr++;break;}
                }
                break;
            }
            else
            {
            }
        }

      if(fr>0) {
          if (Build.VERSION.SDK_INT >= 26) {
              ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
          } else {
              ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(R.dimen.vibratortime);

          }
          String notmsg=intent.getStringExtra("notmsg");
          weekmsg wm=new weekmsg(context);
          wm.insertData(notmsg);
          ComponentName comp = new ComponentName(context.getPackageName(),
                  weekservice.class.getName());
          startWakefulService(context, (intent.setComponent(comp)));
          setResultCode(Activity.RESULT_OK);
      }
    }
}
