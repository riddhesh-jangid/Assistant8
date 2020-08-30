package com.example.root.renametest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by root on 17/1/18.
 */

public class otherdatestop extends AppCompatActivity {
    Context cc=this;
    String s[]=new String[1000];
    String d[]=new String[1000];
    String[] fd;
    String[] fs;
    String ds=new String();
    LinearLayout cl;
    int tep=0;
    View tepv;
    Integer cd;
    Integer cm,cy;
    LocalDateTime now;
    Integer mm,md;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherdatel);
        otherdatebroadcast.ringtone.stop();
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.otherdatebackid);
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

        otherdatehelper bh = new otherdatehelper(this) {


        };
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( bh . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( bh . MONTH ));
            cy= Calendar.getInstance().get(Calendar.YEAR);
            cd=Calendar.getInstance().get(Calendar.DATE);
            cm=Calendar.getInstance().get(Calendar.MONTH)+1;
            if(date==1)
            {
                if(month==1)
                {
                    md=31;
                    mm=12;
                }
                else if(month==2)
                {
                    md=31;
                    mm=1;
                }
                else if(month==3)
                {
                    if(cy%4==0)
                        cd=29;
                    else
                        cd=28;
                    mm=2;
                }
                else if(month==4)
                {
                    md=31;
                    mm=3;
                }
                else if(month==5)
                {
                    md=30;
                    mm=4;
                }
                else if(month==6)
                {
                    md=31;
                    mm=5;
                }
                else if(month==7)
                {
                    md=30;
                    mm=6;
                }
                else if(month==8)
                {
                    md=31;
                    mm=7;
                }
                else if(month==9)
                {
                    md=31;
                    mm=8;
                }
                else if(month==10)
                {
                    md=30;
                    mm=9;
                }
                else if(month==11)
                {
                    md=31;
                    mm=10;
                }
                else if(month==12)
                {
                    md=30;
                    mm=11;
                }
                else;
            }
            else
            {
                md=date-1;
                mm=month;
            }

            if(md==cd && mm==cm) {
                s[i] = name;
                d[i] = " " + date + " / " + month + " ";
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
        ListView lv = (ListView)findViewById(R.id.otherdate_view);
        ArrayList<HashMap<String,String>> al = new ArrayList<>();
        for ( i = 0 ; i < fs . length ; i ++)
        {
            HashMap < String , String > hashMap = new HashMap <>();
            hashMap . put ( "text1" , fs [ i ]);
            hashMap . put ( "text2" , fd [ i ]+ " " );
            al . add ( hashMap );
        }
        String [] from ={ "text1" , "text2" };
        int [] to ={ R . id . tx1 , R . id . tx2 };
        SimpleAdapter simpleAdapter = new
                SimpleAdapter ( this , al , R . layout . stotherdate , from , to );
        lv. setAdapter ( simpleAdapter );
    }
}
