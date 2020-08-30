package com.example.root.renametest;

import android.content.Context;
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
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 11/1/18.
 */

public class disotherdate extends AppCompatActivity{
    otherdate bd=new otherdate(this);
    String s[]=new String[100];
    String d[]=new String[100];
    String[] fd;
    String[] fs;
    String ds=new String();
    LinearLayout cl;
    int tep=0;
    View tepv;
    ArrayList<HashMap<String,String>> al;
    ListView lv;
    Context cc=this;
    public void deleteotherdate()
    {
        bd.delete(ds);
        update();
    }
    public void callupdate(View v)
    {
        update();
    }
    public void update()
    {
        otherdatehelper bh = new otherdatehelper(this) {
        };
        EditText sst=(EditText)findViewById(R.id.searchstr);
        String comsst=sst.getText().toString();
        SQLiteDatabase db = bh . getWritableDatabase ();
        String [] columns = { bh . UID , bh . NAME , bh . DATE , bh . MONTH};
        Cursor cursor = db . query ( bh . TABLE_NAME , columns , null , null , null , null , null );
        int i=0;
        String strdat="";
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( bh . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( bh . NAME ));
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
            i++;
        }
        fs=new String[i];
        int iwct=0;
        fd=new String[i];
        for(int j=0;j<i;j++)
        {
            if(comsst.length()==0 || s[j].toUpperCase().startsWith(comsst.toUpperCase())) {
                fs[iwct] = s[j];
                fd[iwct]=d[j];
                iwct++;
            }
        }
        String finalfs[]=new String[iwct];
        String finalfd[]=new String[iwct];
        for(int j=0;j<iwct;j++)
        {
            finalfs[j]=fs[j];
            finalfd[j]=fd[j];
        }
        lv = (ListView)findViewById(R.id.otherdate_view);
        al = new ArrayList<>();
        for ( i = 0 ; i < finalfs . length ; i ++)
        {
            HashMap < String , String > hashMap = new HashMap <>();
            hashMap . put ( "text1" , finalfs [ i ]);
            hashMap . put ( "text2" , finalfd [ i ]+ " " );
            al . add ( hashMap );
        }
        String [] from ={ "text1" , "text2" };
        int [] to ={ R . id . tx1 , R . id . tx2 };
        SimpleAdapter simpleAdapter = new
                SimpleAdapter ( this , al , R . layout . stotherdate , from , to );
        lv. setAdapter ( simpleAdapter );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otherdatel);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.otherdatebackid);
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
                        tep=0;
                    }
                    else {
                        tepv.setBackgroundColor(Color.TRANSPARENT);
                        tepv = view;
                        ds = fs[i];
                        view.setBackgroundColor(Color.GRAY);
                        Message.message(cc, "SWIPE RIGHT FOR DELETE");
                        tep = 1;
                    }
                }
            }
        });

        lv . setOnTouchListener(new OnSwipeTouchListener(this){

            public void onSwipeRight() {
                deleteotherdate();
            }

        });


    }

}
