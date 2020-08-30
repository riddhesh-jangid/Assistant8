package com.example.root.renametest;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by root on 10/1/18.
 */

public class disallsite extends AppCompatActivity{
    AlertDialog.Builder builder;
    String cate;
    LinearLayout hll;
    String[] na=new String[100];
    String[] st=new String[100];
    String n[];
    String s[];
    String ds;
    ListView lv;
    LinearLayout hfw;
    LinearLayout cover;
    String ms;
    String mos;
    WebView wv;
    String os;
    Integer x;
    SimpleAdapter simpleAdapter;
    Context context=this;
    public void insertallsite(View v) {
        int matchallsite = 0;
        String name = new String();
        String des = new String();
        EditText et = (EditText) findViewById(R.id.sitena);
        EditText de = (EditText) findViewById(R.id.sitede);
        name = et.getText().toString();
        des = de.getText().toString();
        allsite al = new allsite(this);
        allsitehelper ah = new allsitehelper(this) {
        };
        SQLiteDatabase db = ah.getWritableDatabase();
        String[] columns = {ah.UID, ah.NAME, ah.STATUS, ah.CAT};
        Cursor cursor = db.query(ah.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String s1 = cursor.getString(cursor.getColumnIndex(ah.NAME));
            if (s1.equals(name))
                matchallsite = 1;
        }
        if (matchallsite == 0)
          {
            if(name.length()==0)
                Message.message(this,"Fill Field");
            else {
                long id = al.insertData(name, cate, des);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
          }
         else
        {
            Message.message(this,"Already Exist");
        }
        et.setText("");
        de.setText("");
        update();
    }
    public void deleteallsite()
    {
        allsite al=new allsite(this);
        al.delete(ds);
        update();
    }
    public void opensiteapp()
    {
       // wv . getSettings (). setJavaScriptEnabled ( true );
       if(os.startsWith("http"))
       {
           wv.loadUrl(os);
       }
       else {
           wv.loadUrl("http://" + os);
       }
    }
    public void update()
    {
        allsitehelper ah=new allsitehelper(this) {
        };
        SQLiteDatabase db=ah.getWritableDatabase();
        String [] whereArgs = { cate };
        String [] columns = { ah . UID , ah . NAME , ah . STATUS , ah . CAT};
        Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
        int i=0;
        while(cursor.moveToNext()){
            String s1 = cursor.getString(cursor.getColumnIndex( ah.NAME ));
            String s2 = cursor.getString(cursor.getColumnIndex( ah.STATUS ));
            String s3 = cursor.getString(cursor.getColumnIndex( ah.CAT ));
            if(cate.equals(s3)) {
                na[i] = s1;
                st[i] = s2;
                i++;
            }
        }
        n=new String[i];
        for(int j=0;j<i;j++)
        {
            n[j]=na[j];
        }
        s=new String[i];
        for (int j=0;j<i;j++)
        {
            s[j]=st[j];
        }
        lv=(ListView)findViewById(R.id.allsite_view);

        ArrayList<HashMap<String,String>> al = new ArrayList<>();
        for ( i = 0 ; i < n . length ; i ++)
        {
            HashMap < String , String > hashMap = new HashMap <>();
            hashMap . put ( "text1" , n [ i ]);
            hashMap . put ( "text2" , s [ i ]+ " " );
            al . add ( hashMap );
        }
        String [] from ={ "text1" , "text2" };
        int [] to ={ R . id . tx1 , R . id . tx2 };
        simpleAdapter = new
                SimpleAdapter ( this , al , R . layout . stallsite , from , to );
        lv. setAdapter ( simpleAdapter );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allsite);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.hideforweb);
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

        cover=(LinearLayout)findViewById(R.id.cover);
        cover.setVisibility(cover.GONE);
        wv=(WebView)findViewById(R.id.webview);
        //wv.setWebViewClient(new WebViewClient());
        hfw=(LinearLayout)findViewById(R.id.hideforweb);
        cate = getIntent().getStringExtra("cate");
        update();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                hfw.setVisibility(hfw.GONE);
                os=n[i];
                    cover.setVisibility(wv.VISIBLE);
                    opensiteapp();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ds=n[i];
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteallsite();
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
