package com.example.root.renametest;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by root on 8/1/18.
 */

public class disweek extends AppCompatActivity {
  Button c1;
    Button c2;
    Button c3;
    Button c4;
    Button c5;
    Button c6;
    Button c7;
    LinearLayout ll;
  LinearLayout lw;
  ListView lv;
  weekhelper wh;
  String[] na=new String[100];
  String[] ti=new String[100];
  String[] su=new String[100];
  String[] mo=new String[100];
  String[] tu=new String[100];
  String[] we=new String[100];
  String[] th=new String[100];
  String[] fr=new String[100];
  String[] sa=new String[100];
  Context context=this;
  String[] n;
  String[] t;
    String[] sn;
    String[] m;
    String[] te;
    String[] w;
    String[] tr;
    String[] f;
    String[] st;
    weekadapter wa;
    String ds;
    String ms;
    int[] al=new int[1000];
    int[] a;
    Integer isu,imo,itu,iwe,ith,ifr,isa;
    Integer dal;
    public void csu(View v)
    {
        if(isu==0) {isu=1;c1.setText("S");}
        else {isu=0;c1.setText(" ");}
    }
    public void cmo(View v)
    {
        if(imo==0) {imo=1;c2.setText("M");}
        else {imo=0;c2.setText(" ");}
    }
    public void ctu(View v)
    {
        if(itu==0) {itu=1;c3.setText("T");}
        else {itu=0;c3.setText(" ");}
    }
    public void cwe(View v)
    {
        if(iwe==0) {iwe=1;c4.setText("W");}
        else {iwe=0;c4.setText(" ");}
    }
    public void cth(View v)
    {
        if(ith==0) {ith=1;c5.setText("T");}
        else {ith=0;c5.setText(" ");}
    }
    public void cfr(View v)
    {
        if(ifr==0) {ifr=1;c6.setText("F");}
        else {ifr=0;c6.setText(" ");}
    }
    public void csa(View v)
    {
        if(isa==0) {isa=1;c7.setText("S");}
        else {isa=0;c7.setText(" ");}
    }

    public void modweek(View v)
    {
      week wk=new week(this);
      wk.updateName(ms,isu,imo,itu,iwe,ith,ifr,isa);
      lw.setVisibility(lw.GONE);
      update();
    }
    public void deleteweek()
    {
        week wk=new week(this);
        wk.delete(ds);
        Intent intent = new Intent(this, weekbroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), dal, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        update();
    }
    public void update()
    {
        wh=new weekhelper(this) {
        };
        SQLiteDatabase db=wh.getWritableDatabase();
        String [] columns = { wh . UID , wh . NAME , wh . HOUR , wh . MINUTE , wh . SUN , wh . MON , wh . TUE , wh . WEN , wh . THU , wh . FRI , wh . SAT , wh . ALARM};
        Cursor cursor = db . query ( wh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        while(cursor.moveToNext())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( wh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( wh . NAME ));
            Integer hour
                    = cursor . getInt ( cursor . getColumnIndex ( wh . HOUR ));
            Integer minute
                    = cursor . getInt ( cursor . getColumnIndex ( wh . MINUTE ));
            Integer sun
                    = cursor . getInt ( cursor . getColumnIndex ( wh . SUN ));
            Integer mon
                    = cursor . getInt ( cursor . getColumnIndex ( wh . MON ));
            Integer tue
                    = cursor . getInt ( cursor . getColumnIndex ( wh . TUE ));
            Integer wen
                    = cursor . getInt ( cursor . getColumnIndex ( wh . WEN ));
            Integer thu
                    = cursor . getInt ( cursor . getColumnIndex ( wh . THU ));
            Integer fri
                    = cursor . getInt ( cursor . getColumnIndex ( wh . FRI ));
            Integer sat
                    = cursor . getInt ( cursor . getColumnIndex ( wh . SAT ));
            Integer alarm
                    = cursor . getInt ( cursor . getColumnIndex( wh . ALARM ));
            al[i]=alarm;
            na[i]=name;
            int h;
            if(hour<=12) {
                ti[i]=hour+":"+minute+" "+"AM";
            }
            else
            {
                h=hour-12;
                ti[i]=h+":"+minute+" "+"PM";
            }
            if(sun==1)
                su[i]="S";
            else
                su[i]=" ";
            if(mon==1)
                mo[i]="M";
            else
                mo[i]=" ";
            if(tue==1)
                tu[i]="T";
            else
                tu[i]=" ";
            if(wen==1)
                we[i]="W";
            else
                we[i]=" ";
            if(thu==1)
                th[i]="T";
            else
                th[i]=" ";
            if(fri==1)
                fr[i]="F";
            else
                fr[i]=" ";
            if(sat==1)
                sa[i]="S";
            else
                sa[i]=" ";

            i++;
        }
        sn=new String[i];
        for(int j=0;j<i;j++)
        {
            sn[j]=su[j];
        }
        m=new String[i];
        for(int j=0;j<i;j++)
        {
            m[j]=mo[j];
        }
        te=new String[i];
        for(int j=0;j<i;j++)
        {
            te[j]=tu[j];
        }
        w=new String[i];
        for(int j=0;j<i;j++)
        {
            w[j]=we[j];
        }
        tr=new String[i];
        for(int j=0;j<i;j++)
        {
            tr[j]=th[j];
        }
        f=new String[i];
        for(int j=0;j<i;j++)
        {
            f[j]=fr[j];
        }
        st=new String[i];
        for(int j=0;j<i;j++)
        {
            st[j]=sa[j];
        }
        n=new String[i];
        for(int j=0;j<i;j++)
        {
            n[j]=na[j];
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
        wa=new weekadapter(this,n,t,sn,m,te,w,tr,f,st);
        lv.setAdapter(wa);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekl);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.weekbackid);
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

      lv=(ListView)findViewById(R.id.week_view);
      lw=(LinearLayout)findViewById(R.id.option_week);
      c1=(Button)findViewById(R.id.c1);
        c2=(Button)findViewById(R.id.c2);
        c3=(Button)findViewById(R.id.c3);
        c4=(Button)findViewById(R.id.c4);
        c5=(Button)findViewById(R.id.c5);
        c6=(Button)findViewById(R.id.c6);
        c7=(Button)findViewById(R.id.c7);
        lw.setVisibility(lw.GONE);
        update();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ms=n[i];
               lw.setVisibility(lw.VISIBLE);
               if(sn[i]=="S")
               { isu=1; c1.setText("S");}
               else
               {isu=0; c1.setText(" ");}
                if(m[i]=="M")
                {imo=1; c2.setText("M");}
                else
                {imo=0; c2.setText(" ");}
                if(te[i]=="T")
                { itu=1; c3.setText("T");}
                else
                {itu=0; c3.setText(" ");}
                if(w[i]=="W")
                {iwe=1; c4.setText("W");}
                else
                {iwe=0; c4.setText(" ");}
                if(tr[i]=="T")
                { ith=1; c5.setText("T");}
                else
                {ith=0; c5.setText(" ");}
                if(f[i]=="F")
                {ifr=1; c6.setText("F");}
                else
                {ifr=0; c6.setText(" ");}
                if(st[i]=="S")
                {isa=1; c7.setText("S");}
                else
                {isa=0; c7.setText(" ");}
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ds=n[i];
                dal=a[i];
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteweek();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();
                return true;
            }
        });
    }
}
