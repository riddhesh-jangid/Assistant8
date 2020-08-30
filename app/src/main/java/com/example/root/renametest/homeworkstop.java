package com.example.root.renametest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by root on 16/1/18.
 */

public class homeworkstop extends AppCompatActivity {
    String s[]=new String[100];
    String d[]=new String[100];
    String h[]=new String[100];
    String[] hw;
    String[] fd;
    String[] fs;
    String ds=new String();
    String dh=new String();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homel);
        homeworkbroadcast.ringtone.stop();
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.homebackid);
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

        homehelper bh = new homehelper(this) {


        };
        ds="";
        dh="";
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH , bh . HWNOT};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
            String hwnot= cursor . getString ( cursor . getColumnIndex ( bh . HWNOT ));

            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( bh . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( bh . MONTH ));
          if(hwnot.equals("on")) {
              s[i] = name;
              d[i] = " " + date + " / " + month + " ";
              h[i] = hwnot;
              i++;
          }
        }
        fs=new String[i];
        for(int j=0;j<i;j++)
        {
            fs[j]=s[j];
        }
        fd=new String[i];
        for(int j=0;j<i;j++)
        {
            fd[j]=d[j];
        }
        hw=new String[i];
        for(int j=0;j<i;j++)
        {
            hw[j]=h[j];
        }
        ListView lv = (ListView)findViewById(R.id.home_view);

        homeadapter customAdapter = new homeadapter ( getApplicationContext (), fs , fd , hw );
        lv.setAdapter(customAdapter);

    }
    public void homemod(View v)
    {}

}