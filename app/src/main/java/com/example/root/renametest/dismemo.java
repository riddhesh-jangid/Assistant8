package com.example.root.renametest;

import android.app.Dialog;
import android.content.Context;
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
 * Created by root on 8/1/18.
 */

public class dismemo extends AppCompatActivity {
    Dialog dialog,othdialog,andialog;
    String[] na=new String[100];
    String n[];
    memohelper ob;
    Intent intent;
    memo wl;
    String cate;
    LinearLayout wll;
    ArrayAdapter<String> st;
    ListView lt;
    Context context=this;
    public void deletememo()
    {
        recycle re=new recycle(this);
        re.insertData(cate);
        wl=new memo(this);
        wl.delete(cate);
        update();
      allmemo  al=new allmemo(this);
      allrecycle ar=new allrecycle(this);
      allmemohelper  ah=new allmemohelper(this) {
        };
        SQLiteDatabase
                db=ah.getWritableDatabase();
        String [] columns = { ah . UID , ah . NAME , ah . CAT};
        Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
        while(cursor.moveToNext())
        {
          al.deletecat(cate);
          String c=cursor.getString(cursor.getColumnIndex(ah.CAT));
          String na=cursor.getString(cursor.getColumnIndex(ah.NAME));
          if(cate.equals(c))
          {
             ar.insertData(na,c);
          }
        }
    }
    public void callupdate(View v)
    {
        update();
    }
    public void update()
    {
        ob=new memohelper(this) {
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
                R.layout.stmemo,
                R.id.tx,
                finaln);
        lt=(ListView) findViewById(R.id.memo_list);
        lt.setAdapter(st);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(this);
        othdialog = new Dialog(this);
        andialog = new Dialog(this);
        setContentView(R.layout.memol);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.memobackid);
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

        intent=new Intent(this,disallmemo.class);
        update();
        lt. setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cate=n[i];
                memohelper  ah=new memohelper(context) {
                };
                SQLiteDatabase
                        db=ah.getWritableDatabase();
                String [] columns = { ah . UID , ah . NAME , ah . PAS};
                Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
                while(cursor.moveToNext())
                {
                    String na=cursor.getString(cursor.getColumnIndex(ah.NAME));
                    String pa=cursor.getString(cursor.getColumnIndex(ah.PAS));
                    if(na.equals(cate))
                    {
                       if(pa.length()==0)
                       {
                           intent.putExtra("cate",cate);
                           startActivity(intent);
                       }
                       else
                       {
                           othdialog.setContentView(R.layout.checkpassword);
                           othdialog.show();
                       }
                    }
                }
            }
        });

        lt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                cate=n[i];
                memohelper  ah=new memohelper(context) {
                };
                SQLiteDatabase
                        db=ah.getWritableDatabase();
                String [] columns = { ah . UID , ah . NAME , ah . PAS};
                Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
                while(cursor.moveToNext())
                {
                    String na=cursor.getString(cursor.getColumnIndex(ah.NAME));
                    String pa=cursor.getString(cursor.getColumnIndex(ah.PAS));
                    if(na.equals(cate))
                    {
                        if(pa.length()==0)
                        {
                            dialog.setContentView(R.layout.memoalert);
                            dialog.show();
                        }
                        else
                        {
                            andialog.setContentView(R.layout.grantper);
                            andialog.show();                        }
                    }
                }

                /*AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deletememo();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                builder.show();*/
                return true;
            }
        });
    }
   public void del(View v)
   {
      deletememo();
      dialog.dismiss();
      Message.message(this,"Deleted");
   }
   public void pas(View v)
   {
       int xxii=0;
      EditText ettt=(EditText)dialog.findViewById(R.id.getpas);
      String pax=new String();
      pax=ettt.getText().toString();
      memo m=new memo(this);
      xxii=m.updatepass(cate,pax);
      if(xxii<=0)
      {
          Message.message(this,"Unsuccessfull");
      }
      else
      {
          Message.message(this,"Successfull");
      }
      dialog.dismiss();
   }
   public void chepas(View v)
   {
       int xxii=0;
       EditText ettt=(EditText)othdialog.findViewById(R.id.checkpas);
       String pax=ettt.getText().toString();
       othdialog.dismiss();
       memohelper  ah=new memohelper(this) {
       };
       SQLiteDatabase
               db=ah.getWritableDatabase();
       String [] columns = { ah . UID , ah . NAME , ah . PAS};
       Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
       while(cursor.moveToNext())
       {
           String na=cursor.getString(cursor.getColumnIndex(ah.NAME));
           String pa=cursor.getString(cursor.getColumnIndex(ah.PAS));
           if(na.equals(cate))
           {
               if(pax.equals(pa))
               {
                   intent.putExtra("cate",cate);
                   startActivity(intent);
               }
           }
       }
   }
   public void perdel(View v)
   {
       int xxii=0;
       EditText ettt=(EditText)andialog.findViewById(R.id.chppp);
       String pax=ettt.getText().toString();
       memohelper  ah=new memohelper(this) {
       };
       SQLiteDatabase
               db=ah.getWritableDatabase();
       String [] columns = { ah . UID , ah . NAME , ah . PAS};
       Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
       while(cursor.moveToNext())
       {
           String na=cursor.getString(cursor.getColumnIndex(ah.NAME));
           String pa=cursor.getString(cursor.getColumnIndex(ah.PAS));
           if(na.equals(cate))
           {
               if(pax.equals(pa))
               {
                   andialog.dismiss();
                   dialog.setContentView(R.layout.memoalert);
                   dialog.show();
               }
           }
       }
   }
}
