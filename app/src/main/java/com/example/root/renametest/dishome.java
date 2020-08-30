package com.example.root.renametest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by root on 3/1/18.
 */

public class dishome extends AppCompatActivity {
    Context cc=this;
    homeadapter customAdapter;
    ListView lv;
    homework bd=new homework(this);
    String s[]=new String[100];
    String d[]=new String[100];
    String h[]=new String[100];
    String[] hw;
    String[] fd;
    String[] fs;
    String ds=new String();
    String dh=new String();
    int tep=0;
    View tepv;
    Context context=this;
    public void homedel()
    {
        bd.delete(ds);
        ds="";
        tep=0;
      update();
    }
    public void homemod()
    {
       if(dh=="");
       else {
           if (dh.equals("on")) {
               bd.updateName(ds, "off");
           } else {
               bd.updateName(ds, "on");
           }
         update();
       }
    }
    public void callupdate(View v)
    {
        update();
    }
    public void update()
    {
        homehelper bh = new homehelper(this) {
        };
        EditText sst=(EditText)findViewById(R.id.searchstr);
        String comsst=sst.getText().toString();
        ds="";
        dh="";
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH , bh . HWNOT};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        String strdat="";
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
            String hwnot= cursor . getString ( cursor . getColumnIndex ( bh . HWNOT ));

            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( bh . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( bh . MONTH ));
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
            s[i]=name;
            d[i]=" "+date+"/"+strdat+" ";
            h[i]=hwnot;
            i++;
        }
        int iwct=0;
        fs=new String[i];
        fd=new String[i];
        hw=new String[i];
        for(int j=0;j<i;j++)
        {
            if(comsst.length()==0 || s[j].toUpperCase().startsWith(comsst.toUpperCase())) {
                fs[iwct] = s[j];
                fd[iwct]=d[j];
                hw[iwct]=h[j];
                iwct++;
            }
        }
        String finalfs[]=new String[iwct];
        String finalfd[]=new String[iwct];
        String finalhw[]=new String[iwct];
        for (int j = 0; j < iwct; j++) {
            finalfs[j]=fs[j];
            finalfd[j]=fd[j];
            finalhw[j]=hw[j];
        }
        lv = (ListView)findViewById(R.id.home_view);

        customAdapter = new homeadapter ( getApplicationContext (), finalfs , finalfd , finalhw );
        lv.setAdapter(customAdapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homel);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.homebackid);
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
        lv . setOnItemClickListener ( new AdapterView. OnItemClickListener () {
            @Override
            public void onItemClick (AdapterView <?> adapterView , View view , int i , long l ) {

                if(tep==0) {
                    tepv = view;
                    ds = fs[i];
                    dh=hw[i];
                    view.setBackgroundColor(Color.GRAY);
                    tep=1;
                }
                else
                {
                   if(tepv.equals(view))
                   {
                       tepv.setBackgroundColor(Color.TRANSPARENT);
                       ds="";
                       dh="";
                       tep=0;
                   }
                   else {
                       tepv.setBackgroundColor(Color.TRANSPARENT);
                       tepv = view;
                       ds = fs[i];
                       dh = hw[i];
                       view.setBackgroundColor(Color.GRAY);
                       tep = 1;
                     }
                   }
            homemod();
            }
        });


        lv . setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(tep==0) {
                    tepv = view;
                    ds = fs[i];
                    dh=hw[i];
                    view.setBackgroundColor(Color.GRAY);
                    tep=1;
                }
                else
                {
                    if(tepv.equals(view))
                    {
                        tepv.setBackgroundColor(Color.TRANSPARENT);
                        ds="";
                        dh="";
                        tep=0;
                    }
                    else {
                        tepv.setBackgroundColor(Color.TRANSPARENT);
                        tepv = view;
                        ds = fs[i];
                        dh = hw[i];
                        view.setBackgroundColor(Color.GRAY);
                        tep = 1;
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        homedel();
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
