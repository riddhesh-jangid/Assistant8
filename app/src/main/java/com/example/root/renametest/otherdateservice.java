package com.example.root.renametest;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by root on 17/1/18.
 */

public class otherdateservice extends IntentService {
    private NotificationManager alarmNotificationManager;

    public otherdateservice() {
        super("AlarmService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Importance Dates");
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        otherdatebroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (otherdatebroadcast.alarmUri == null) {
            otherdatebroadcast.alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        otherdatebroadcast.ringtone = RingtoneManager.getRingtone(this, otherdatebroadcast.alarmUri);
        otherdatebroadcast.ringtone.play();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, otherdatestop.class), 0);

        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);


        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }

}
