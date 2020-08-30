package com.example.root.renametest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
 * Created by root on 6/1/18.
 */

public class disallwish extends AppCompatActivity {
    String cate;
    LinearLayout hll;
    String[] na=new String[100];
    String[] st=new String[100];
    String n[];
    String s[];
    String ds;
    ListView lv;
    String ms;
    String mos;
    Context context=this;
    ArrayList<HashMap<String,String>> al;
    HashMap < String , String > hashMap;
    String [] from ={ "text1" , "text2" };
    SimpleAdapter simpleAdapter;
    int [] to ={ R . id . tx1 , R . id . tx2 };
    public void insertallwish(View v)
    {
        int matchallwish=0;
        String name=new String();
        EditText et=(EditText)findViewById(R.id.edit_allwish);
        name=et.getText().toString();
        allwish al=new allwish(this);
        allwishhelper ah=new allwishhelper(this) {
        };
        SQLiteDatabase db=ah.getWritableDatabase();
        String [] columns = { ah . UID , ah . NAME , ah . STATUS , ah . CAT};
        Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
        while(cursor.moveToNext()){
            String s1 = cursor.getString(cursor.getColumnIndex( ah.NAME ));
            if(s1.equals(name))
                matchallwish=1;
        }
         if(matchallwish==0) {
            if(name.length()==0)
             Message.message(this,"Fill Field");
            else {
                long id = al.insertData(name, cate);
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
        update();
    }
    public void deleteallwish()
    {
        allwish al=new allwish(this);
        al.delete(ds);
        update();
    }
    public void modallwish()
    {
        if(ms.equals("NOT DONE"))
            ms="DONE";
        else
            ms="NOT DONE";
        allwish aw=new allwish(this);
        aw.updateName(mos,ms);
       update();
    }
    public void update()
    {
        allwishhelper ah=new allwishhelper(this) {
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
        lv=(ListView)findViewById(R.id.allwish_view);

        al = new ArrayList<>();
        for ( i = 0 ; i < n . length ; i ++)
        {
            hashMap = new HashMap <>();
            hashMap . put ( "text1" , n [ i ]);
            hashMap . put ( "text2" , s [ i ]+ " " );
            al . add ( hashMap );
        }
        simpleAdapter = new
                SimpleAdapter ( this , al , R . layout . stallwish , from , to );
        lv. setAdapter ( simpleAdapter );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allwishl);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.hidekey);
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

        cate = getIntent().getStringExtra("cate");
        update();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ms=s[i];
                mos=n[i];
                modallwish();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ds=n[i];
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteallwish();
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
