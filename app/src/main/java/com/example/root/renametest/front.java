package com.example.root.renametest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Calendar;

/**
 * Created by root on 23/1/18.
 */

public class front extends AppCompatActivity{
    int checkforfirst=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);

    }
 public void go(View v)
 {
     Calendar calendar = Calendar.getInstance();
     Calendar cl=new Calendar() {
         @Override
         protected void computeTime() {

         }

         @Override
         protected void computeFields() {

         }

         @Override
         public void add(int i, int i1) {

         }

         @Override
         public void roll(int i, boolean b) {

         }

         @Override
         public int getMinimum(int i) {
             return 0;
         }

         @Override
         public int getMaximum(int i) {
             return 0;
         }

         @Override
         public int getGreatestMinimum(int i) {
             return 0;
         }

         @Override
         public int getLeastMaximum(int i) {
             return 0;
         }
     } ;
     cl=cl.getInstance();
     Integer firho=23;
     Integer firmi=0;
     calendar.set(calendar.HOUR_OF_DAY,firho);
     calendar.set(calendar.MINUTE,firmi);
     Integer pho=cl.get(Calendar.HOUR_OF_DAY);
     Integer pmo=cl.get(Calendar.MINUTE);
     if(firho.equals(pho))
     {
         if(firmi.equals(pmo) || firmi<pmo)
         {
             calendar.add(Calendar.HOUR,24);
         }
     }
     else if(firho<pho)
     {
         calendar.add(Calendar.HOUR,24);
     }
     else ;
     Intent intent = new Intent(this, birthbroadcast.class);
     PendingIntent pendingIntent = PendingIntent.getBroadcast(
             this.getApplicationContext(), 110299, intent, 0);
     AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
         alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
         alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else
         alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

     firho=17;
     firmi=0;
     calendar = Calendar.getInstance();
     cl=cl.getInstance();
     calendar.set(calendar.HOUR_OF_DAY,firho);
     calendar.set(calendar.MINUTE,firmi);
     pho=cl.get(Calendar.HOUR_OF_DAY);
     pmo=cl.get(Calendar.MINUTE);
     if(firho.equals(pho))
     {
         if(firmi.equals(pmo) || firmi<pmo)
         {
             calendar.add(Calendar.HOUR,24);
         }
     }
     else if(firho<pho)
     {
         calendar.add(Calendar.HOUR,24);
     }
     else ;
     intent = new Intent(this, homeworkbroadcast.class);
     pendingIntent = PendingIntent.getBroadcast(
             this.getApplicationContext(), 130498, intent, 0);
     alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
         alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
         alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else
         alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

     firho=17;
     firmi=0;
     calendar = Calendar.getInstance();
     cl=cl.getInstance();
     calendar.set(calendar.HOUR_OF_DAY,firho);
     calendar.set(calendar.MINUTE,firmi);
     pho=cl.get(Calendar.HOUR_OF_DAY);
     pmo=cl.get(Calendar.MINUTE);
     if(firho.equals(pho))
     {
         if(firmi.equals(pmo) || firmi<pmo)
         {
             calendar.add(Calendar.HOUR,24);
         }
     }
     else if(firho<pho)
     {
         calendar.add(Calendar.HOUR,24);
     }
     else ;
     intent = new Intent(this, otherdatebroadcast.class);
     pendingIntent = PendingIntent.getBroadcast(
             this.getApplicationContext(), 250493, intent, 0);
     alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
         alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
         alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     else
         alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
     starthelper bh = new starthelper(this) {
     };
     SQLiteDatabase db = bh . getWritableDatabase ();
     String [] columns = { bh . UID,bh.FVT};
     Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
     checkforfirst=0;
     while ( cursor . moveToNext ())
     {
         checkforfirst=1;
     }
     if(checkforfirst==0) {
         start startapp=new start(this);
         startapp.insertData();
     }
      Intent iii=new Intent(this,MainActivity.class);
     iii.putExtra("five",567);
     startActivity(iii);
 }
}
