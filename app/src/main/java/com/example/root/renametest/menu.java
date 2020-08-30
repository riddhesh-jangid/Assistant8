package com.example.root.renametest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

/**
 * Created by root on 15/1/18.
 */

public class menu extends AppCompatActivity {
  TimePicker ti;
    Calendar cl;
    PendingIntent pendingIntent;
    String bd,hd,od;
    Integer sb,sh,so,fvt;
    public void setvoice(View v)
    {
       ToggleButton voice=(ToggleButton)findViewById(R.id.voice);
       if(voice.isChecked())
       {
           starthelper sttt=new starthelper(this) {
           };
           SQLiteDatabase db = sttt . getWritableDatabase ();
           ContentValues contentValues = new ContentValues ();
           contentValues.put(sttt.FVT,1);
           String [] whereArgs = { "default" };
           db . update ( sttt. TABLE_NAME , contentValues , sttt. NAME + " = ?" , whereArgs );
           voice.setChecked(true);
           update();
       }
       else
       {
           starthelper sttt=new starthelper(this) {
           };
           SQLiteDatabase db = sttt . getWritableDatabase ();
           ContentValues contentValues = new ContentValues ();
           contentValues.put(sttt.FVT,0);
           String [] whereArgs = { "default" };
           db . update ( sttt. TABLE_NAME , contentValues , sttt. NAME + " = ?" , whereArgs );
           voice.setChecked(false);
           update();
       }
    }
    public void setback(View v)
    {
        Intent intent=new Intent(this,back.class);
        startActivity(intent);
    }
    public void goforhelp(View v)
    {
        Intent intent=new Intent(this,dishelp.class);
        startActivity(intent);
    }
    public void update()
    {
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.backmenu);
        starthelper bhhh = new starthelper(this) {
        };
        SQLiteDatabase dbhh = bhhh . getWritableDatabase ();
        String [] columnshh = { bhhh . UID,bhhh.DCS};
        Cursor cursorhh = dbhh . query ( bhhh . TABLE_NAME , columnshh , null , null , null , null , null );
        Integer dcs=0;
        while ( cursorhh . moveToNext ()) {
            dcs=cursorhh.getInt(cursorhh.getColumnIndex(bhhh.DCS));
        }
        if(dcs==0)
            lllbbb.setBackgroundResource(R.drawable.background);
        else if(dcs==1)
            lllbbb.setBackgroundResource(R.drawable.background1);
        else if(dcs==2)
            lllbbb.setBackgroundResource(R.drawable.background2);
        else if(dcs==3)
            lllbbb.setBackgroundResource(R.drawable.background3);
        else if(dcs==4)
            lllbbb.setBackgroundResource(R.drawable.background4);
        else if(dcs==5)
            lllbbb.setBackgroundResource(R.drawable.background5);
        else if(dcs==6)
            lllbbb.setBackgroundResource(R.drawable.background6);
        else if(dcs==7)
            lllbbb.setBackgroundResource(R.drawable.background7);
        else if(dcs==8)
            lllbbb.setBackgroundResource(R.drawable.background8);
        else;
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID,bh . NAME,bh.BHOUR,bh.BMINUTE,bh.BOF,bh.HHOUR,bh.HMINUTE,bh.HOF,bh.OHOUR,bh.OMINUTE,bh.OOF,bh.SS,bh.HS,bh.DCS,bh.FVT};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        while ( cursor . moveToNext ())
        {
            fvt=cursor.getInt(cursor.getColumnIndex(bh.FVT));
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            Integer bhour
                    = cursor . getInt ( cursor . getColumnIndex ( bh . BHOUR ));
            Integer bminute
                    = cursor . getInt ( cursor . getColumnIndex ( bh . BMINUTE ));
            Integer h;
            if(bhour<=12) {
                bd=bhour+":"+bminute+" "+"AM";
            }
            else
            {
                h=bhour-12;
                bd=h+":"+bminute+" "+"PM";
            }
            bhour
                    = cursor . getInt ( cursor . getColumnIndex ( bh . HHOUR ));
            bminute
                    = cursor . getInt ( cursor . getColumnIndex ( bh . HMINUTE ));
            if(bhour<=12) {
                hd=bhour+":"+bminute+" "+"AM";
            }
            else
            {
                h=bhour-12;
                hd=h+":"+bminute+" "+"PM";
            }
            bhour
                    = cursor . getInt ( cursor . getColumnIndex ( bh . OHOUR ));
            bminute
                    = cursor . getInt ( cursor . getColumnIndex ( bh . OMINUTE ));
            if(bhour<=12) {
                od=bhour+":"+bminute+" "+"AM";
            }
            else
            {
                h=bhour-12;
                od=h+":"+bminute+" "+"PM";
            }
            Integer ss=cursor.getInt(cursor.getColumnIndex(bh.SS));
            Integer hs=cursor.getInt(cursor.getColumnIndex(bh.HS));
            sb=cursor.getInt(cursor.getColumnIndex(bh.BOF));
            sh=cursor.getInt(cursor.getColumnIndex(bh.HOF));
            so=cursor.getInt(cursor.getColumnIndex(bh.OOF));
        }
        ToggleButton birthtog=(ToggleButton)findViewById(R.id.birthtog);
        ToggleButton hometog=(ToggleButton)findViewById(R.id.hometog);
        ToggleButton othertog=(ToggleButton)findViewById(R.id.othertog);
        ToggleButton voice=(ToggleButton)findViewById(R.id.voice);
        TextView birthtxt=(TextView)findViewById(R.id.birthid);
        TextView hometxt=(TextView)findViewById(R.id.homeid);
        TextView othertxt=(TextView)findViewById(R.id.otherid);
        birthtxt.setText(bd);
        hometxt.setText(hd);
        othertxt.setText(od);
        Log.d("Update", "Value: fvt = "+fvt);
        if(fvt==0)
            voice.setChecked(false);
        else
            voice.setChecked(true);
        if(sb==0)
            birthtog.setChecked(false);
        else
            birthtog.setChecked(true);
        if(sh==0)
            hometog.setChecked(false);
        else
            hometog.setChecked(true);
        if(so==0)
            othertog.setChecked(false);
        else
            othertog.setChecked(true);
        Log.d("Update", "status: Second if else ledder end");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menul);
        update();
    };
    public void addbirth(View v)
    {
        ti=(TimePicker)findViewById(R.id.tipi);
        int cchh=0,ccmm=0;
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            cchh = ti.getHour();
            ccmm = ti.getMinute();
        } else {
            cchh = ti.getCurrentHour();
            ccmm = ti.getCurrentMinute();
        }
        Calendar calendar = Calendar.getInstance();
        cl=cl.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,cchh);
        calendar.set(calendar.MINUTE,ccmm);
        int pho=cl.get(Calendar.HOUR_OF_DAY);
        int pmo=cl.get(Calendar.MINUTE);
        if(cchh==pho)
        {
            if(ccmm==pmo || ccmm<pmo)
            {
                calendar.add(Calendar.HOUR,24);
            }
        }
        else if(cchh<pho)
        {
            calendar.add(Calendar.HOUR,24);
        }
        else ;
        Intent intent = new Intent(this, birthbroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 110299, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( starthelper. BHOUR , cchh );
        contentValues . put ( starthelper. BMINUTE , ccmm );
        contentValues . put ( starthelper. BOF , 1 );
        String [] whereArgs = { "default" };
        db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        update();
    }
    public void addhomework(View v)
    {
        int cchh=0,ccmm=0;
        ti=(TimePicker)findViewById(R.id.tipi);
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            cchh = ti.getHour();
            ccmm = ti.getMinute();
        } else {
            cchh = ti.getCurrentHour();
            ccmm = ti.getCurrentMinute();
        }
        Calendar calendar = Calendar.getInstance();
        cl=cl.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,cchh);
        calendar.set(calendar.MINUTE,ccmm);
        Integer pho=cl.get(Calendar.HOUR_OF_DAY);
        Integer pmo=cl.get(Calendar.MINUTE);
        if(cchh==pho)
        {
            if(ccmm==pmo || ccmm<pmo)
            {
                calendar.add(Calendar.HOUR,24);
            }
        }
        else if(cchh<pho)
        {
            calendar.add(Calendar.HOUR,24);
        }
        else ;
        Intent intent = new Intent(this, homeworkbroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 130498, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( starthelper. HHOUR , cchh );
        contentValues . put ( starthelper. HMINUTE , ccmm );
        contentValues . put ( starthelper. HOF , 1 );
        String [] whereArgs = { "default" };
        db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        update();
    }
    public void addotherdate(View v)
    {
        ti=(TimePicker)findViewById(R.id.tipi);
        int cchh=0,ccmm=0;
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            cchh = ti.getHour();
            ccmm = ti.getMinute();
        } else {
            cchh = ti.getCurrentHour();
            ccmm = ti.getCurrentMinute();
        }
        Calendar calendar = Calendar.getInstance();
        cl=cl.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,cchh);
        calendar.set(calendar.MINUTE,ccmm);
        Integer pho=cl.get(Calendar.HOUR_OF_DAY);
        Integer pmo=cl.get(Calendar.MINUTE);
        if(cchh==pho)
        {
            if(ccmm==pmo || ccmm<pmo)
            {
                calendar.add(Calendar.HOUR,24);
            }
        }
        else if(cchh<pho)
        {
            calendar.add(Calendar.HOUR,24);
        }
        else ;
        Intent intent = new Intent(this, otherdatebroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 250493, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        else
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( starthelper. OHOUR , cchh );
        contentValues . put ( starthelper. OMINUTE , ccmm );
        contentValues . put ( starthelper. OOF , 1 );
        String [] whereArgs = { "default" };
        db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        update();
    }
    public void cancelbirth(View v)
    {
        ToggleButton birthtog=(ToggleButton)findViewById(R.id.birthtog);
        if(birthtog.isChecked())
        {
            starthelper bh = new starthelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            String [] columns = { bh . UID,bh . NAME,bh.BHOUR,bh.BMINUTE,bh.BOF,bh.HHOUR,bh.HMINUTE,bh.HOF,bh.OHOUR,bh.OMINUTE,bh.OOF,bh.SS,bh.HS};
            Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
            while ( cursor . moveToNext ()) {
                Integer bhour
                        = cursor.getInt(cursor.getColumnIndex(bh.BHOUR));
                Integer bminute
                        = cursor.getInt(cursor.getColumnIndex(bh.BMINUTE));
                Calendar calendar = Calendar.getInstance();
                cl=cl.getInstance();
                calendar.set(calendar.HOUR_OF_DAY,bhour);
                calendar.set(calendar.MINUTE,bminute);
                Integer pho=cl.get(Calendar.HOUR_OF_DAY);
                Integer pmo=cl.get(Calendar.MINUTE);
                if(bhour.equals(pho))
                {
                    if(bminute.equals(pmo) || bminute<pmo)
                    {
                        calendar.add(Calendar.HOUR,24);
                    }
                }
                else if(bhour<pho)
                {
                    calendar.add(Calendar.HOUR,24);
                }
                else ;
                Intent intent = new Intent(this, birthbroadcast.class);
                pendingIntent = PendingIntent.getBroadcast(
                        this.getApplicationContext(), 110299, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                else
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
            ContentValues contentValues = new ContentValues ();
            contentValues . put ( starthelper. BOF , 1 );
            String [] whereArgs = { "default" };
            db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        }
        else
        {
            Intent intent = new Intent(this, birthbroadcast.class);
            pendingIntent = PendingIntent.getBroadcast(
                    this.getApplicationContext(), 110299, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            starthelper bh = new starthelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            ContentValues contentValues = new ContentValues ();
            contentValues . put ( starthelper. BOF , 0 );
            String [] whereArgs = { "default" };
            db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        }
        update();
    }
    public void cancelhomework(View v)
    {
       ToggleButton hometog=(ToggleButton)findViewById(R.id.hometog);
       if(hometog.isChecked()) {
           starthelper bh = new starthelper(this) {
           };
           SQLiteDatabase db = bh . getWritableDatabase ();
           String [] columns = { bh . UID,bh . NAME,bh.BHOUR,bh.BMINUTE,bh.BOF,bh.HHOUR,bh.HMINUTE,bh.HOF,bh.OHOUR,bh.OMINUTE,bh.OOF,bh.SS,bh.HS};
           Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
           while ( cursor . moveToNext ()) {
               Integer bhour
                       = cursor.getInt(cursor.getColumnIndex(bh.HHOUR));
               Integer bminute
                       = cursor.getInt(cursor.getColumnIndex(bh.HMINUTE));
               Calendar calendar = Calendar.getInstance();
               cl=cl.getInstance();
               calendar.set(calendar.HOUR_OF_DAY,bhour);
               calendar.set(calendar.MINUTE,bminute);
               Integer pho=cl.get(Calendar.HOUR_OF_DAY);
               Integer pmo=cl.get(Calendar.MINUTE);
               if(bhour.equals(pho))
               {
                   if(bminute.equals(pmo) || bminute<pmo)
                   {
                       calendar.add(Calendar.HOUR,24);
                   }
               }
               else if(bhour<pho)
               {
                   calendar.add(Calendar.HOUR,24);
               }
               else ;
               Intent intent = new Intent(this, homeworkbroadcast.class);
               pendingIntent = PendingIntent.getBroadcast(
                       this.getApplicationContext(), 130498, intent, 0);
               AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                   alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
               else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                   alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
               else
                   alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
           }
           ContentValues contentValues = new ContentValues ();
           contentValues . put ( starthelper. HOF , 1 );
           String [] whereArgs = { "default" };
           db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
       }
       else
       {
           Intent intent = new Intent(this, homeworkbroadcast.class);
           pendingIntent = PendingIntent.getBroadcast(
                   this.getApplicationContext(), 130498, intent, 0);
           AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
           alarmManager.cancel(pendingIntent);
           starthelper bh = new starthelper(this) {
           };
           SQLiteDatabase db = bh . getWritableDatabase ();
           ContentValues contentValues = new ContentValues ();
           contentValues . put ( starthelper. HOF , 0 );
           String [] whereArgs = { "default" };
           db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
       }
        update();
    }
    public void cancelotherdate(View v)
    {
        ToggleButton othertog=(ToggleButton)findViewById(R.id.othertog);
        if(othertog.isChecked()) {
            starthelper bh = new starthelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            String [] columns = { bh . UID,bh . NAME,bh.BHOUR,bh.BMINUTE,bh.BOF,bh.HHOUR,bh.HMINUTE,bh.HOF,bh.OHOUR,bh.OMINUTE,bh.OOF,bh.SS,bh.HS};
            Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
            while ( cursor . moveToNext ()) {
                Integer bhour
                        = cursor.getInt(cursor.getColumnIndex(bh.OHOUR));
                Integer bminute
                        = cursor.getInt(cursor.getColumnIndex(bh.OMINUTE));
                Calendar calendar = Calendar.getInstance();
                cl=cl.getInstance();
                calendar.set(calendar.HOUR_OF_DAY,bhour);
                calendar.set(calendar.MINUTE,bminute);
                Integer pho=cl.get(Calendar.HOUR_OF_DAY);
                Integer pmo=cl.get(Calendar.MINUTE);
                if(bhour.equals(pho))
                {
                    if(bminute.equals(pmo) || bminute<pmo)
                    {
                        calendar.add(Calendar.HOUR,24);
                    }
                }
                else if(bhour<pho)
                {
                    calendar.add(Calendar.HOUR,24);
                }
                else ;
                Intent intent = new Intent(this, otherdatebroadcast.class);
                pendingIntent = PendingIntent.getBroadcast(
                        this.getApplicationContext(), 250493, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                else
                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
            ContentValues contentValues = new ContentValues ();
            contentValues . put ( starthelper. OOF , 1 );
            String [] whereArgs = { "default" };
            db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        }
        else
        {
            Intent intent = new Intent(this, otherdatebroadcast.class);
            pendingIntent = PendingIntent.getBroadcast(
                    this.getApplicationContext(), 250493, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
            starthelper bh = new starthelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            ContentValues contentValues = new ContentValues ();
            contentValues . put ( starthelper. OOF , 0 );
            String [] whereArgs = { "default" };
            db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        }
        update();
    }
    public void recycle(View v)
    {
        Intent intent=new Intent(this,disrecycle.class);
        startActivity(intent);
    }
    public void def(View v)
    {
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( starthelper. DCS , 0 );
        String [] whereArgs = { "default" };
        db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
        update();
    }
}

