package com.example.root.renametest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by root on 7/1/18.
 */

public class distimer extends AppCompatActivity{
   String[] na=new String[100];
    String[] da=new String[100];
    String[] ti=new String[100];
    String[] n;
    String[] d;
    String[] t;
    int[] al=new int[1000];
    int[] a;
    String ds;
    int dela;
    Context cc=this;
    LinearLayout cl;
    int tep=0;
    View tepv;
    timeradapter customAdapter;
    ListView lv;
    public void deletetimer()
   {
      timer t=new timer(this);
      t.delete(ds);
       Intent intent = new Intent(this, timerbroadcast.class);
       PendingIntent pendingIntent = PendingIntent.getBroadcast(
               this.getApplicationContext(), dela, intent, 0);
       AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
       alarmManager.cancel(pendingIntent);
       update();
   }
  public void update()
  {
      lv=(ListView) findViewById(R.id.timer_view);
      timerhelper th=new timerhelper(this) {
      };
      SQLiteDatabase db=th.getWritableDatabase();
      String [] columns = { th . UID , th . NAME , th . YEAR , th . MONTH , th . DATE , th . HOUR , th . MINUTE , th . ALARM};
      Cursor cursor = db . query ( th . TABLE_NAME , columns , null , null , null , null , null );
      Integer i=0;
      String strdat="";
      while(cursor.moveToNext())
      {
          int cid = cursor . getInt ( cursor . getColumnIndex ( th . UID ));
          String name = cursor . getString ( cursor . getColumnIndex ( th . NAME ));
          int year = cursor . getInt ( cursor . getColumnIndex ( th . YEAR ));
          int month = cursor . getInt ( cursor . getColumnIndex ( th . MONTH ));
          int date = cursor . getInt ( cursor . getColumnIndex ( th . DATE ));
          int hour = cursor . getInt ( cursor . getColumnIndex ( th . HOUR ));
          int minute = cursor . getInt ( cursor . getColumnIndex ( th . MINUTE ));
          int alarm=cursor.getInt(cursor . getColumnIndex(th .ALARM));
          int h;
          if(hour<=12) {
              ti[i]=hour+":"+minute+" "+"AM";
          }
          else
          {
              h=hour-12;
              ti[i]=h+":"+minute+" "+"PM";
          }
          if(month==1)
              strdat="jan";
          else if(month==2)
              strdat="feb";
          else if(month==3)
              strdat="mar";
          else if(month==4)
              strdat="apr";
          else if(month==5)
              strdat="may";
          else if(month==6)
              strdat="jun";
          else if(month==7)
              strdat="jul";
          else if(month==8)
              strdat="aug";
          else if(month==9)
              strdat="sep";
          else if(month==10)
              strdat="oct";
          else if(month==11)
              strdat="nov";
          else if(month==12)
              strdat="dec";
          else;
          da[i]=date+"/"+strdat+" ";
          na[i]=name;
          al[i]=alarm;
          i++;
      }
      n=new String[i];
      for(int j=0;j<i;j++)
      {
          n[j]=na[j];
      }
      d=new String[i];
      for(int j=0;j<i;j++)
      {
          d[j]=da[j];
      }
      t=new String[i];
      for(int j=0;j<i;j++)
      {
          t[j]=ti[j];
      }
      a=new int[i];
      for(int j=0;j<i;j++)
      {
          a[j]=al[j];
      }
      customAdapter = new timeradapter ( getApplicationContext (), n , d , t );
      lv.setAdapter(customAdapter);
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timerl);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.timerbackid);
        starthelper bh = new starthelper(this) {
        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID,bh.DCS};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        Integer dcs=0;
        while ( cursor . moveToNext ()) {
            dcs=cursor.getInt(cursor.getColumnIndex(bh.DCS));
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

        update();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(tep==0) {
                    tepv = view;
                    ds = n[i];
                    dela=a[i];
                    view.setBackgroundColor(Color.GRAY);
                    Message.message(cc, "SWIPE RIGHT FOR DELETE");
                    tep=1;
                }
                else
                {
                    if(tepv.equals(view))
                    {
                        tepv.setBackgroundColor(Color.TRANSPARENT);
                        ds="";
                        dela=0;
                        tep=0;
                    }
                    else {
                        tepv.setBackgroundColor(Color.TRANSPARENT);
                        tepv = view;
                        ds = n[i];
                        dela=a[i];
                        view.setBackgroundColor(Color.GRAY);
                        Message.message(cc, "SWIPE RIGHT FOR DELETE");
                        tep = 1;
                    }
                }
            }
        });
        lv . setOnTouchListener(new OnSwipeTouchListener(this){

            public void onSwipeRight() {
                deletetimer();
            }

        });

    }
}
