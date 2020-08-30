package com.example.root.renametest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by root on 9/1/18.
 */

public class dissite extends AppCompatActivity{
    String[] na=new String[100];
    String n[];
    sitehelper ob;
    Intent intent;
    site wl;
    String cate;
    LinearLayout wll;
    String ds;
    ArrayAdapter<String> st;
    ListView lt;
    Context context=this;
    public void deletesite()
    {
        wl=new site(this);
        wl.delete(ds);
        update();
        allsite as=new allsite(this);
        allsitehelper ah=new allsitehelper(this) {
        };
        SQLiteDatabase db=ah.getWritableDatabase();
        String [] whereArgs = { cate };
        String [] columns = { ah . UID , ah . NAME , ah . STATUS , ah . CAT};
        Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
        while(cursor.moveToNext()){
            as.deletecat(ds);
         }
        }
    public void callupdate(View v)
    {
        update();
    }
    public void update()
    {
        ob=new sitehelper(this) {
        };
        EditText sst=(EditText)findViewById(R.id.searchstr);
        String comsst=sst.getText().toString();
        SQLiteDatabase db=ob.getWritableDatabase();
        String [] columns = { ob . UID , ob . NAME};
        Cursor cursor=db.query(ob.TABLE_NAME, columns , null , null , null , null , null );
        int i=0;
        while(cursor.moveToNext()){
            String s = cursor.getString(cursor.getColumnIndex( ob.NAME ));
            na[i]=s;
            i++;
        }
        n=new String[i];
        int iwct=0;
        for(int j=0;j<i;j++)
        {
            if(comsst.length()==0 || na[j].toUpperCase().startsWith(comsst.toUpperCase())) {
                n[iwct] = na[j];
                iwct++;
            }
        }
        String finaln[]=new String[iwct];
        for(int j=0;j<iwct;j++)
        {
            finaln[j]=n[j];
        }
        st=new ArrayAdapter<String>(this,
                R.layout.stsite,
                R.id.tx,
                finaln);
        lt=(ListView) findViewById(R.id.site_list);
        lt.setAdapter(st);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.site);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.sitebackid);
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

        intent=new Intent(this,disallsite.class);
        update();
        lt. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("cate",n[i]);
                startActivity(intent);
            }
        });

        lt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ds=n[i];
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletesite();
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
