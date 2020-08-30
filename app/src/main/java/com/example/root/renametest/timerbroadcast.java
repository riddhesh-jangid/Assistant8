package com.example.root.renametest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by root on 19/1/18.
 */

public class timerbroadcast extends WakefulBroadcastReceiver {
    MediaPlayer mp;
    static Uri alarmUri;
    static Ringtone ringtone;
    Integer cd, cm;
    static String notmsg;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) context.getSystemService(context.VIBRATOR_SERVICE)).vibrate(R.dimen.vibratortime);

        }
        notmsg=intent.getStringExtra("notmsg");
        timer delti=new timer(context);
        delti.delete(notmsg);
        timermsg tm=new timermsg(context);
        tm.insertData(notmsg);
        ComponentName comp = new ComponentName(context.getPackageName(),
                timerservice.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}