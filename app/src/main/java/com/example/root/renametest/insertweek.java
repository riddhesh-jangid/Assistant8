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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class insertweek extends AppCompatActivity {
    TimePicker timePicker;
    PendingIntent pendingIntent;
    TextView txt1,txt2,txt3,txt4,intro;
    LinearLayout cover;
    Button extbtn;
    Animation animation,animation1,animation2,animation3;
    int son;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertweek);
        cover=(LinearLayout)findViewById(R.id.coverlinear);
        cover.setVisibility(cover.GONE);
        intro=(TextView)findViewById(R.id.intro);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.moveless);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movedn);
        animation3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movednless);
        timePicker=(TimePicker) findViewById(R.id.timepick);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt3=(TextView)findViewById(R.id.txt3);
        txt4=(TextView)findViewById(R.id.txt4);
        extbtn=(Button)findViewById(R.id.extbtn);
        extbtn.setText("<>");
        son=0;
        txt1.setVisibility(txt1.GONE);
        txt2.setVisibility(txt2.GONE);
        txt3.setVisibility(txt3.GONE);
        txt4.setVisibility(txt4.GONE);
    }
    public void extend(View v)
    {
        if(son==0)
        {
            intro.setVisibility(intro.GONE);
            cover.setVisibility(cover.VISIBLE);
            extbtn.setText("><");
            txt1.setVisibility(txt1.VISIBLE);
            txt2.setVisibility(txt2.VISIBLE);
            txt3.setVisibility(txt3.VISIBLE);
            txt4.setVisibility(txt4.VISIBLE);
            txt1.startAnimation(animation);
            txt2.startAnimation(animation1);
            txt3.startAnimation(animation);
            txt4.startAnimation(animation1);
            son=1;
        }
        else if(son==1)
        {
            cover.setVisibility(cover.GONE);
            extbtn.setText("<>");
            txt1.startAnimation(animation2);
            txt2.startAnimation(animation3);
            txt3.startAnimation(animation2);
            txt4.startAnimation(animation3);
            txt1.setVisibility(txt1.GONE);
            txt2.setVisibility(txt2.GONE);
            txt3.setVisibility(txt3.GONE);
            txt4.setVisibility(txt4.GONE);
            son=0;
            intro.setVisibility(intro.VISIBLE);
        }
    }
    public void insert(View v)
    {
        EditText et=(EditText)findViewById(R.id.name);

        if(et.getText().toString().length()==0)
        {
            Intent intent=new Intent(this,disweek.class);
            startActivity(intent);
        }
        else
        {
            int weekx=0,matchweek=0;
            int alarmweek=0;
            TimePicker ti=(TimePicker)findViewById(R.id.timepick);
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
    public void show(View v)
    {
        Intent intent=new Intent(insertweek.this,disweek.class);
        startActivity(intent);
    }
    public void home(View v)
    {
        Intent intent=new Intent(insertweek.this,MainActivity.class);
        startActivity(intent);
    }
    public void setting(View v)
    {
        Intent intent=new Intent(insertweek.this,menu.class);
        startActivity(intent);
    }
    public void exit(View v)
    {
        finish();
        System.exit(0);
    }
}
