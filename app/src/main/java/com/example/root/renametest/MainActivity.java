package com.example.root.renametest;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    int front,fvt;
    RelativeLayout lay,cho_lay;
    Button btn,upbtn,dnbtn;
    int xdelta,ydelta,fixx,uxx,mxx,dxx,my,fy,fpox,fpoy,fi=0;
    TextView hinttext;
    String main_s;
    Calendar calendar;
    PendingIntent pendingIntent;

    // Voice start
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public int returnmonth(String ss)
    {   int x=1;
        DatePicker dt=(DatePicker)findViewById(R.id.date_picker);
        if(ss.equalsIgnoreCase("january"))
        {
            dt.updateDate(dt.getYear(),0,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("february"))
        {
            dt.updateDate(dt.getYear(),1,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("march"))
        {
            dt.updateDate(dt.getYear(),2,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("april"))
        {
            dt.updateDate(dt.getYear(),3,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("may"))
        {
            dt.updateDate(dt.getYear(),4,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("june"))
        {
            dt.updateDate(dt.getYear(),5,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("july"))
        {
            dt.updateDate(dt.getYear(),6,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("august"))
        {
            dt.updateDate(dt.getYear(),7,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("september"))
        {
            dt.updateDate(dt.getYear(),8,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("october"))
        {
            dt.updateDate(dt.getYear(),9,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("november"))
        {
            dt.updateDate(dt.getYear(),10,dt.getDayOfMonth());
        }
        else if(ss.equalsIgnoreCase("december"))
        {
            dt.updateDate(dt.getYear(),11,dt.getDayOfMonth());
        }
        else
        {
            x=0;
        }
        return x;
    }
    public int addlistcomwords(String ss)
    {
        int x=0;
        if(ss.equalsIgnoreCase(getResources().getString(R.string.save)) ) x=1;
        return x;
    }
    public int checkwish(String ss)
    {
        wishlisthelper ob=new wishlisthelper(this) {
        };
        SQLiteDatabase db=ob.getWritableDatabase();
        String [] columns = { ob . UID , ob . NAME};
        Cursor cursor=db.query(ob.TABLE_NAME, columns , null , null , null , null , null );
        String s="";
        int x=0;
        while(cursor.moveToNext()){
            s = cursor.getString(cursor.getColumnIndex( ob.NAME ));
            if(s.equalsIgnoreCase(ss))
            {
                x=1;
                break;
            }
        }
        return x;
    }
    public void promptSpeechInput(View v) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "say something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Sorry your device doesnt support",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //AI start from here
                    EditText tv=(EditText) findViewById(R.id.content);
                    DatePicker dt=(DatePicker)findViewById(R.id.date_picker);
                    TimePicker tt=(TimePicker)findViewById(R.id.tipicker);
                    RelativeLayout lll=(RelativeLayout)findViewById(R.id.main_id);
                    Integer occurcat=0,addinlist=0,closeapp=0,restartvoice=0;
                    String container=new String();
                    container=result.get(0).toString();
                    String[] words=container.split(" ");
                    String addlistna=new String();
                    for(String ss : words)
                    {
                        if(ss.equalsIgnoreCase("birthday"))
                        {
                            occurcat=1;
                        }
                        else if (ss.equalsIgnoreCase("homework"))
                        {
                            occurcat=2;
                        }
                        else if(ss.equalsIgnoreCase(getResources().getString(R.string.save)))
                        {
                            addinlist=1;
                        }
                        else if(ss.equalsIgnoreCase("memo"))
                        {
                            occurcat=3;
                        }
                        else if(ss.equalsIgnoreCase("dates"))
                        {
                            occurcat=4;
                        }
                        else if(ss.equalsIgnoreCase("week"))
                        {
                            occurcat=5;
                        }
                        else if(ss.equalsIgnoreCase("timer") || ss.equalsIgnoreCase("remind"))
                        {
                            occurcat=6;
                        }
                        else if(ss.equalsIgnoreCase("sites"))
                        {
                            occurcat=7;
                        }
                        else if(ss.equalsIgnoreCase("list"))
                        {
                            occurcat=8;
                        }
                        else if(ss.equalsIgnoreCase(getResources().getString(R.string.exit)))
                        {
                            closeapp=1;
                        }
                        else if(ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                        {
                            restartvoice=1;
                        }
                        else if(checkwish(ss)==1)
                        {
                            if(addinlist==1) {
                                addlistna=ss;
                                occurcat = 9;
                            }
                        }
                        else
                        {
                            //  occurcat=0;
                        }
                    }
                    if(occurcat==1)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            try {
                                numc=0;
                                date = Integer.parseInt(ss.toString());
                            }
                            catch (Exception e)
                            {
                                numc=1;
                            }
                            if(date>=1 && date<=31 && numc==0)
                            {
                                dt.updateDate(dt.getYear(),dt.getMonth(),date);
                            }
                            else {
                                chfm = returnmonth(ss);
                                if (chfm == 0) {
                                    if(!ss.equalsIgnoreCase("birthday") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                                    {if(constr.length()==0)
                                        constr=ss;
                                    else
                                        constr = constr + " " + ss;}
                                    else ;
                                }
                            }
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertbirth(lll);
                        }
                    }
                    else if(occurcat==2)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            try {
                                numc=0;
                                date = Integer.parseInt(ss.toString());
                            }
                            catch (Exception e)
                            {
                                numc=1;
                            }
                            if(date>=1 && date<=31 && numc==0)
                            {
                                dt.updateDate(dt.getYear(),dt.getMonth(),date);
                            }
                            else {
                                chfm = returnmonth(ss);
                                if (chfm == 0) {
                                    if(!ss.equalsIgnoreCase("homework") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                                    {if(constr.length()==0)
                                        constr=ss;
                                    else
                                        constr = constr + " " + ss;}
                                    else ;
                                }
                                else;
                            }
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            inserthome(lll);
                        }
                    }
                    else if(occurcat==3)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            if(!ss.equalsIgnoreCase("memo") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                            {if(constr.length()==0)
                                constr=ss;
                            else
                                constr = constr + " " + ss;}
                            else ;
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertmemo(lll);
                        }
                    }
                    else if(occurcat==4)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            try {
                                numc=0;
                                date = Integer.parseInt(ss.toString());
                            }
                            catch (Exception e)
                            {
                                numc=1;
                            }
                            if(date>=1 && date<=31 && numc==0)
                            {
                                dt.updateDate(dt.getYear(),dt.getMonth(),date);
                            }
                            else {
                                chfm = returnmonth(ss);
                                if (chfm == 0) {
                                    if(!ss.equalsIgnoreCase("dates") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                                        if(constr.length()==0)
                                            constr=ss;
                                        else
                                            constr = constr + " " + ss;
                                    else ;
                                }
                            }
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertotherdate(lll);
                        }
                    }
                    else if(occurcat==5)
                    {
                        String constr=new String();
                        constr="";
                        int date=0,numc=0,chfm=0,opn=0,chewor=0,lastnu=0;
                        for (String ss : words)
                        {
                            try {
                                numc=0;
                                date = Integer.parseInt(ss.toString());
                            }
                            catch (Exception e)
                            {
                                numc=1;
                            }
                            if(date>=1 && date<=60 && numc==0)
                            {
                                //dt.updateDate(dt.getYear(),dt.getMonth(),date);
                                lastnu=date;
                            }
                            else {
                                chfm = returnmonth(ss);
                                if(chfm==1)
                                {
                                    dt.updateDate(dt.getYear(),dt.getMonth(),lastnu);
                                }
                                else if(ss.equalsIgnoreCase("hour") || ss.equalsIgnoreCase("hours"))
                                {
                                    Calendar calendar=Calendar.getInstance();
                                    Integer hour=calendar.get(Calendar.HOUR_OF_DAY);
                                    int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                                    if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                                        tt.setHour(hour+lastnu);
                                    } else {
                                        tt.setCurrentHour(hour+lastnu);
                                    }
                                }
                                else if(ss.equalsIgnoreCase("minute") || ss.equalsIgnoreCase("minutes"))
                                {
                                    Calendar calendar=Calendar.getInstance();
                                    Integer minute=calendar.get(Calendar.MINUTE);
                                    Integer hour=calendar.get(Calendar.HOUR_OF_DAY);
                                    int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                                    for(int i=1;i<=lastnu;i++)
                                    {
                                        if(minute+1>59)
                                        {
                                            //tt.setCurrentHour(hour+1);
                                            // tt.setCurrentMinute(0);
                                            hour=hour+1;
                                            minute=0;
                                        }
                                        else {
                                            minute=minute+1;
                                            //tt.setCurrentMinute(minute + 1);
                                        }
                                    }
                                    if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                                        tt.setHour(hour);
                                        tt.setMinute(minute);
                                    } else {
                                        tt.setCurrentHour(hour);
                                        tt.setCurrentMinute(minute);
                                    }
                                }
                                else if (chfm == 0) {
                                    // if(!ss.equalsIgnoreCase("week"))
                                    if(!ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)) && !!ss.equalsIgnoreCase("hour") && !ss.equalsIgnoreCase("hours") && !ss.equalsIgnoreCase("minutes") && !ss.equalsIgnoreCase("minute"))
                                    {if(constr.length()==0)
                                        constr=ss;
                                    else
                                        constr = constr + " " + ss;}
                                    // else ;
                                }
                                else;
                            }
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertweek(lll);
                        }
                    }
                    else if(occurcat==6)
                    {
                        String constr=new String();
                        constr="T";
                        int date=0,numc=0,chfm=0,opn=0,chewor=0,lastnu=0;
                        for (String ss : words)
                        {
                            try {
                                numc=0;
                                date = Integer.parseInt(ss.toString());
                            }
                            catch (Exception e)
                            {
                                numc=1;
                            }
                            if(date>=1 && date<=60 && numc==0)
                            {
                                //dt.updateDate(dt.getYear(),dt.getMonth(),date);
                                lastnu=date;
                            }
                            else {
                                chfm = returnmonth(ss);
                                if(chfm==1)
                                {
                                    dt.updateDate(dt.getYear(),dt.getMonth(),lastnu);
                                }
                                else if(ss.equalsIgnoreCase("hour") || ss.equalsIgnoreCase("hours"))
                                {
                                    Calendar calendar=Calendar.getInstance();
                                    Integer hour=calendar.get(Calendar.HOUR_OF_DAY);
                                    int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                                    if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                                        tt.setHour(hour+lastnu);
                                    } else {
                                        tt.setCurrentHour(hour+lastnu);
                                    }
                                }
                                else if(ss.equalsIgnoreCase("minute") || ss.equalsIgnoreCase("minutes"))
                                {
                                    Calendar calendar=Calendar.getInstance();
                                    Integer minute=calendar.get(Calendar.MINUTE);
                                    Integer hour=calendar.get(Calendar.HOUR_OF_DAY);
                                    int currentApiVersion = android.os.Build.VERSION.SDK_INT;
                                    for(int i=1;i<=lastnu;i++)
                                    {
                                        if(minute+1>59)
                                        {
                                            //tt.setCurrentHour(hour+1);
                                            // tt.setCurrentMinute(0);
                                            hour=hour+1;
                                            minute=0;
                                        }
                                        else {
                                            minute=minute+1;
                                            //tt.setCurrentMinute(minute + 1);
                                        }
                                    }
                                    if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                                        tt.setHour(hour);
                                        tt.setMinute(minute);
                                    } else {
                                        tt.setCurrentHour(hour);
                                        tt.setCurrentMinute(minute);
                                    }
                                }
                                else if (chfm == 0) {
                                    //if(!ss.equalsIgnoreCase("timer"))
                                    if(!ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)) && !!ss.equalsIgnoreCase("hour") && !ss.equalsIgnoreCase("hours") && !ss.equalsIgnoreCase("minutes") && !ss.equalsIgnoreCase("minute"))
                                    {if(constr.length()==0)
                                        constr=ss;
                                    else
                                        constr = constr + " " + ss;}
                                    //else ;
                                }
                                else;
                            }
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            inserttimer(lll);
                        }
                    }
                    else if(occurcat==7)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            if(!ss.equalsIgnoreCase("sites") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                            {if(constr.length()==0)
                                constr=ss;
                            else
                                constr = constr + " " + ss;}
                            else ;
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertsite(lll);
                        }
                    }
                    else if(occurcat==8)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            if(!ss.equalsIgnoreCase("list") && !ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)))
                            {if(constr.length()==0)
                                constr=ss;
                            else
                                constr = constr + " " + ss;}
                            else ;
                        }
                        if(opn==0) {
                            tv.setText(constr);
                            insertwishlist(lll);
                        }
                    }
                    else if(occurcat==9)
                    {
                        String constr=new String();
                        int date=0,numc=0,chfm=0,opn=0,chewor=0;
                        for (String ss : words)
                        {
                            chewor=addlistcomwords(ss);
                            if(ss.equalsIgnoreCase(addlistna))
                                chewor=1;
                            else;
                            if(chewor==0) {
                                if(!ss.equalsIgnoreCase(getResources().getString(R.string.exit)) && !ss.equalsIgnoreCase(getResources().getString(R.string.repeat)) )
                                {if(constr.length()==0)
                                    constr=ss;
                                else
                                    constr = constr + " " + ss;}
                            }
                            else ;
                        }
                        allwish awh=new allwish(this) {
                        };
                        awh.insertData(constr,addlistna);
                    }
                    else
                    {
                        tv.setText(result.get(0));
                    }
                    if(closeapp==1)
                    {
                        finish();
                        System.exit(0);
                    }
                    else if(restartvoice==1)
                    {
                        promptSpeechInput(lll);
                    }
                    //AI end from here
                }
                break;
            }

        }
    }
    public void insertweek(View v)
    {
        EditText et=(EditText)findViewById(R.id.content);

        if(et.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,disweek.class);
            startActivity(intent);
        }
        else
        {
            int weekx=0,matchweek=0;
            int alarmweek=0;
            TimePicker ti=(TimePicker)findViewById(R.id.tipicker);
            int cchh=0,ccmm=0;
            int currentApiVersion = android.os.Build.VERSION.SDK_INT;
            if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
                cchh = ti.getHour();
                ccmm = ti.getMinute();
            } else {
                cchh = ti.getCurrentHour();
                ccmm = ti.getCurrentMinute();
            }
            Integer ho=cchh;
            Integer mi=ccmm;
            String na=et.getText().toString();
            weekhelper wh=new weekhelper(this) {
            };
            SQLiteDatabase db=wh.getWritableDatabase();
            String [] columns = { wh . UID , wh . NAME , wh . HOUR , wh . MINUTE , wh . SUN , wh . MON , wh . TUE , wh . WEN , wh . THU , wh . FRI , wh . SAT , wh . ALARM};
            Cursor cursor = db . query ( wh . TABLE_NAME , columns , null , null , null , null , null );
            int i=0;
            while(cursor.moveToNext())
            {
                alarmweek=cursor.getInt(cursor.getColumnIndex(wh . ALARM));
                weekx++;
                String name=cursor.getString(cursor.getColumnIndex(wh.NAME));
                if(na.equals(name))
                    matchweek=1;
            }
            if(weekx==0)
                alarmweek=73000;
            else
                alarmweek++;
            week wk=new week(this);
            if(weekx==10)
            {
                Message.message(this,"Can't add more than 10");
            }
            else
            {
                if(matchweek==0) {
                    long id = wk.insertData(na, ho, mi, alarmweek);
                    if (id <= 0) {
                        Message.message(this, "Unsuccessfull");
                    } else {
                        Message.message(this, "Successfull");
                    }
                    Calendar calendar = Calendar.getInstance();
                    Calendar cl=Calendar.getInstance();
                    calendar.set(calendar.HOUR_OF_DAY, cchh);
                    calendar.set(calendar.MINUTE, ccmm);
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
                    Intent intent = new Intent(this, weekbroadcast.class);
                    intent.putExtra("notmsg",et.getText().toString());
                    pendingIntent = PendingIntent.getBroadcast(
                            this.getApplicationContext(), alarmweek, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    else
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                }
                else
                {
                    Message.message(this,"Already Exist");
                }
            }

        }
        et.setText("");
    }
    public void insertbirth(View v)
    {
        EditText et = (EditText)findViewById(R.id.content);
        if(et.getText().toString().length()==0)
        {
            Intent in = new Intent(this,disbirth.class);
            startActivity(in);
        }
        else
        {
            int match=0;
            String na = et.getText().toString();
            birthhelper bh = new birthhelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH};
            Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
            while ( cursor . moveToNext ())
            {
                String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
                if(na.equals(name))
                    match=1;
            }

            birthday help = new birthday(this);
            DatePicker dt = (DatePicker) findViewById(R.id.date_picker);
            Integer date = dt.getDayOfMonth();
            Integer month = dt.getMonth();
            month++;
            if(match==0) {
                long id = help.insertData(na,date,month);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
                et.setText("");
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
    }

    public void insertotherdate(View v)
    {
        EditText et = (EditText)findViewById(R.id.content);
        if(et.getText().toString().length()==0)
        {
            Intent in = new Intent(this,disotherdate.class);
            startActivity(in);
        }
        else
        {
            int matchhelper=0;
            otherdate help = new otherdate(this);
            DatePicker dt = (DatePicker) findViewById(R.id.date_picker);
            String na = et.getText().toString();
            Integer date = dt.getDayOfMonth();
            Integer month = dt.getMonth();
            month++;
            otherdatehelper bh = new otherdatehelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH};
            Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
            while ( cursor . moveToNext ())
            {
                String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
                if(na.equals(name))
                    matchhelper=1;
            }
            if(matchhelper==0) {
                long id = help.insertData(na, date, month);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Alreay Exist");
            }
        }
        et.setText("");

    }


    public void inserttimer(View v)
    {
        TimePicker t=(TimePicker)findViewById(R.id.tipicker);
        int cchh=0,ccmm=0;
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion > android.os.Build.VERSION_CODES.LOLLIPOP_MR1){
            cchh = t.getHour();
            ccmm = t.getMinute();
        } else {
            cchh = t.getCurrentHour();
            ccmm = t.getCurrentMinute();
        }
        DatePicker d=(DatePicker)findViewById(R.id.date_picker);
        EditText et=(EditText)findViewById(R.id.content);
        timer ti=new timer(this);
        if(et.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,distimer.class);
            startActivity(intent);
        }
        else
        {
            int matchtimer=0;
            int timerx=0;
            Integer alarm=0;
            timerhelper th=new timerhelper(this) {
            };
            SQLiteDatabase db=th.getWritableDatabase();
            String [] columns = { th . UID , th . NAME , th . YEAR , th . MONTH , th . DATE , th . HOUR , th . MINUTE , th . ALARM };
            Cursor cursor = db . query ( th . TABLE_NAME , columns , null , null , null , null , null );
            while(cursor.moveToNext()) {
                timerx++;
                alarm=cursor . getInt ( cursor . getColumnIndex ( th . ALARM ));
                String na=cursor.getString(cursor.getColumnIndex(th.NAME));
                if(na.equals(et.getText().toString()))
                    matchtimer=1;
            }
            Integer year=d.getYear();
            Integer month=d.getMonth();
            Integer date=d.getDayOfMonth();
            Integer hour=cchh;
            Integer min=ccmm;
            if(timerx==0)
                alarm=43000;
            else
                alarm++;
            month++;
            if(timerx==10)
            {
                Message.message(this,"Can't add more than 10");
            }
            else {
                if(matchtimer==0) {
                    long id = ti.insertData(et.getText().toString(), year, month, date, hour, min, alarm);
                    if (id <= 0) {
                        Message.message(this, "Unsuccessfull");
                    } else {
                        Message.message(this, "Successfull");
                    }
                    calendar = Calendar.getInstance();
                    calendar.set(calendar.YEAR, d.getYear());
                    calendar.set(calendar.MONTH, d.getMonth());
                    calendar.set(calendar.DAY_OF_MONTH, d.getDayOfMonth());
                    calendar.set(calendar.HOUR_OF_DAY, cchh);
                    calendar.set(calendar.MINUTE, ccmm);
                    Intent intent = new Intent(this, timerbroadcast.class);
                    intent.putExtra("notmsg",et.getText().toString());
                    pendingIntent = PendingIntent.getBroadcast(
                            this.getApplicationContext(), alarm, intent, 0);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    else
                        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                }
                else
                {
                    Message.message(this,"Already Exist");
                }
            }
        }
        et.setText("");
    }

    public void inserthome(View v)
    {

        EditText et = (EditText)findViewById(R.id.content);
        if(et.getText().toString().length()==0)
        {
            Intent in = new Intent(this,dishome.class);
            startActivity(in);
        }
        else
        {
            int matchhome=0;
            homework help = new homework(this);
            DatePicker dt = (DatePicker) findViewById(R.id.date_picker);
            String na = et.getText().toString();
            Integer date = dt.getDayOfMonth();
            Integer month = dt.getMonth();
            month++;
            homehelper bh = new homehelper(this) {
            };
            SQLiteDatabase db = bh . getWritableDatabase ();
            String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH , bh . HWNOT};
            Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
            int i=0;
            while ( cursor . moveToNext ())
            {
                String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
                if(na.equals(name))
                    matchhome=1;
            }
            if(matchhome==0) {
                long id = help.insertData(na, date, month);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
        et.setText("");
    }

    public void insertwishlist(View v)
    {
        String s=new String();
        EditText st=(EditText)findViewById(R.id.content);
        wishlist sl=new wishlist(this);
        if(st.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,diswishlist.class);
            startActivity(intent);
        }
        else
        {
            s=st.getText().toString();
            int matchwish=0;
            wishlisthelper ah=new wishlisthelper(this) {
            };
            SQLiteDatabase db=ah.getWritableDatabase();
            String [] columns = { ah . UID , ah . NAME};
            Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
            int i=0;
            while(cursor.moveToNext()){
                String s1 = cursor.getString(cursor.getColumnIndex( ah.NAME ));
                if(s.equals(s1))
                    matchwish=1;
            }

            if(matchwish==0) {
                long id = sl.insertData(s);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
        st.setText("");
    }

    public void insertmemo(View v)
    {
        EditText st=(EditText)findViewById(R.id.content);
        memo sl=new memo(this);
        allmemo am=new allmemo(this);

        if(st.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,dismemo.class);
            startActivity(intent);
        }
        else
        {
            int matchmemo=0;
            String s=new String();
            s=st.getText().toString();
            memohelper ob=new memohelper(this) {
            };
            SQLiteDatabase db=ob.getWritableDatabase();
            String [] columns = { ob . UID , ob . NAME};
            Cursor cursor=db.query(ob.TABLE_NAME, columns , null , null , null , null , null );
            int i=0;
            while(cursor.moveToNext()){
                String sa = cursor.getString(cursor.getColumnIndex( ob.NAME ));
                if(s.equals(sa))
                    matchmemo=1;
            }
            if(matchmemo==0) {
                am.insertfirstData(s);
                long id = sl.insertData(s);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
        st.setText("");
    }
    public void insertsite(View v)
    {
        EditText st=(EditText)findViewById(R.id.content);
        site sl=new site(this);
        if(st.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,dissite.class);
            startActivity(intent);
        }
        else
        {
            int matchsite=0;
            String s=new String();
            s=st.getText().toString();
            sitehelper ob=new sitehelper(this) {
            };
            SQLiteDatabase db=ob.getWritableDatabase();
            String [] columns = { ob . UID , ob . NAME};
            Cursor cursor=db.query(ob.TABLE_NAME, columns , null , null , null , null , null );
            while(cursor.moveToNext()){
                String sa = cursor.getString(cursor.getColumnIndex( ob.NAME ));
                if(s.equals(sa))
                    matchsite=1;
            }
            if(matchsite==0) {
                long id = sl.insertData(s);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
        st.setText("");
    }

    // Voice end

    @Override
    public void onRestart()
    {
        super.onRestart();
//        main_s="Memo";
        cho_lay=(RelativeLayout)findViewById(R.id.iner_lay);
        lay=(RelativeLayout)findViewById(R.id.main_id);
        btn=(Button)findViewById(R.id.main_btn);
        btn.setText(main_s);
        hinttext=(TextView)findViewById(R.id.hinttext);
        upbtn=(Button)findViewById(R.id.upone);
        dnbtn=(Button)findViewById(R.id.downone);
        RelativeLayout.LayoutParams mpara = (RelativeLayout.LayoutParams)
                btn.getLayoutParams();
        RelativeLayout.LayoutParams upara = (RelativeLayout.LayoutParams)
                upbtn.getLayoutParams();
        RelativeLayout.LayoutParams dpara = (RelativeLayout.LayoutParams)
                dnbtn.getLayoutParams();
        fpox=mpara.leftMargin;
        fpoy=mpara.topMargin;
        fixx=mpara.leftMargin;
        uxx=upara.topMargin;
        mxx=mpara.topMargin;
        dxx=dpara.topMargin;
        upbtn.setText("INSERT");
        dnbtn.setText("DISPLAY");
        upbtn.setVisibility(upbtn.GONE);
        dnbtn.setVisibility(dnbtn.GONE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Checking for first time start
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID,bh.FVT};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int checkforfirst=0;
        while ( cursor . moveToNext ())
        {
            checkforfirst=1;
            fvt=cursor.getInt(cursor.getColumnIndex(bh.FVT));
            break;
        }
        if(checkforfirst==0) {
            front = getIntent().getIntExtra("five", 0);
            if (front != 567) {
                Intent iii = new Intent(this, front.class);
                startActivity(iii);
                front = 567;
            }
        }
        front = getIntent().getIntExtra("five", 0);
        // First time check

        main_s="Memo";
        cho_lay=(RelativeLayout)findViewById(R.id.iner_lay);
        lay=(RelativeLayout)findViewById(R.id.main_id);
        btn=(Button)findViewById(R.id.main_btn);
        btn.setText(main_s);
        hinttext=(TextView)findViewById(R.id.hinttext);
        upbtn=(Button)findViewById(R.id.upone);
        dnbtn=(Button)findViewById(R.id.downone);
        RelativeLayout.LayoutParams mpara = (RelativeLayout.LayoutParams)
                btn.getLayoutParams();
        RelativeLayout.LayoutParams upara = (RelativeLayout.LayoutParams)
                upbtn.getLayoutParams();
        RelativeLayout.LayoutParams dpara = (RelativeLayout.LayoutParams)
                dnbtn.getLayoutParams();
        fpox=mpara.leftMargin;
        fpoy=mpara.topMargin;
        fixx=mpara.leftMargin;
        uxx=upara.topMargin;
        mxx=mpara.topMargin;
        dxx=dpara.topMargin;
        upbtn.setVisibility(upbtn.GONE);
        dnbtn.setVisibility(dnbtn.GONE);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x=(int) motionEvent.getRawX();
                int y=(int) motionEvent.getRawY();
                switch (motionEvent.getAction() & motionEvent.ACTION_MASK)
                {
                    case MotionEvent.ACTION_DOWN:
                        upbtn.setVisibility(upbtn.VISIBLE);
                        dnbtn.setVisibility(dnbtn.VISIBLE);
                        hinttext.setVisibility(hinttext.GONE);
                        cho_lay.setBackgroundResource(R.drawable.layout_color);
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        xdelta = x - lParams.leftMargin;
                        ydelta = y - lParams.topMargin;
                        fy=(int) motionEvent.getRawY();
                        break;

                    case MotionEvent.ACTION_UP:
                        my=(int) motionEvent.getRawY();
                        RelativeLayout.LayoutParams llParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();
                        if(my<fy){
                            llParams.topMargin=uxx;
                            upbtn.setText(main_s);
                            dnbtn.setText("DISPLAY");
                            if(main_s.equalsIgnoreCase("Birthday"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertbirth.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Work"))
                            {
                                Intent intent=new Intent(MainActivity.this,inserthome.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Dates"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertdate.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Memo"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertmemo.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Timer"))
                            {
                                Intent intent=new Intent(MainActivity.this,inserttimer.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Week"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertweek.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Sites"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertsites.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("List"))
                            {
                                Intent intent=new Intent(MainActivity.this,insertlist.class);
                                startActivity(intent);
                            }
                            else ;
                        }
                        else if(my>fy) {
                            llParams.topMargin = dxx;
                            dnbtn.setText(main_s);
                            upbtn.setText("INSERT");
                            if(main_s.equalsIgnoreCase("Birthday"))
                            {
                                Intent intent=new Intent(MainActivity.this,disbirth.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Memo"))
                            {
                                Intent intent=new Intent(MainActivity.this,dismemo.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Dates"))
                            {
                                Intent intent=new Intent(MainActivity.this,disotherdate.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Work"))
                            {
                                Intent intent=new Intent(MainActivity.this,dishome.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Timer"))
                            {
                                Intent intent=new Intent(MainActivity.this,distimer.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Week"))
                            {
                                Intent intent=new Intent(MainActivity.this,disweek.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("Sites"))
                            {
                                Intent intent=new Intent(MainActivity.this,dissite.class);
                                startActivity(intent);
                            }
                            else if(main_s.equalsIgnoreCase("List"))
                            {
                                Intent intent=new Intent(MainActivity.this,diswishlist.class);
                                startActivity(intent);
                            }
                            else ;
                        }
                        upbtn.setVisibility(upbtn.GONE);
                        dnbtn.setVisibility(dnbtn.GONE);
                        hinttext.setVisibility(hinttext.VISIBLE);
                        RelativeLayout.LayoutParams mpara = (RelativeLayout.LayoutParams)
                                btn.getLayoutParams();
                        mpara.leftMargin=fpox;
                        mpara.topMargin=fpoy;
                        cho_lay.setBackgroundResource(R.drawable.layout_round);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = fixx;
                        if((y-ydelta)>uxx && (y-ydelta)<dxx)
                            layoutParams.topMargin = y - ydelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                return true;
            }
        });
    }
    public void txtf1(View v)
    {
        main_s="Birthday";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt1);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);

    }
    public void txtf2(View v)
    {
        main_s="Memo";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt2);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf3(View v)
    {
        main_s="Dates";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt3);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf4(View v)
    {
        main_s="Work";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt4);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf5(View v)
    {
        main_s="Timer";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt5);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf6(View v)
    {
        main_s="Week";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt6);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf7(View v)
    {
        main_s="Sites";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt7);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }
    public void txtf8(View v)
    {
        main_s="List";
        btn.setText(main_s);
        TextView tx=(TextView)findViewById(R.id.txt8);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.myanimation);
        tx.startAnimation(animation1);
    }

    public void goToSetting(View v)
    {
        Intent intent=new Intent(MainActivity.this,menu.class);
        startActivity(intent);
    }
}
