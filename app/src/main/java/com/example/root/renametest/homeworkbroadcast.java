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
 * Created by root on 16/1/18.
 */

public class homeworkbroadcast extends WakefulBroadcastReceiver {
    MediaPlayer mp;
    static Uri alarmUri;
    static Ringtone ringtone;
    Integer cd,cm;
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,24);
        Intent in = new Intent(context, homeworkbroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 130498, in, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        homehelper bh=new homehelper(context) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . HWNOT , bh . DATE , bh . MONTH};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . HWNOT ));
            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( bh . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( bh . MONTH ));
            if(name.equals("on")) {
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
                    homeworkservice.class.getName());
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);
        }
    }
}
