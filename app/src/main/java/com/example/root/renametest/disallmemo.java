package com.example.root.renametest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by root on 8/1/18.
 */

public class disallmemo extends AppCompatActivity{
 TextView tv;
 EditText ev;
 LinearLayout lv;
 allmemo al;
 allmemohelper ah;
 String s;
 String c;
 String old;
 String cat;
 Integer toe;
 public void tvclick()
 {
     old=tv.getText().toString();
     tv.setVisibility(tv.GONE);
     ev.setVisibility(ev.VISIBLE);
     toe=1;
     ev.setText(old);
 }
 public void saveclick(View v)
 {
  if(toe==1) {
      String tot = new String();
      tot = ev.getText().toString();
      al = new allmemo(this);
      al.updateName(old, tot);
      ev.setVisibility(ev.GONE);
      tv.setVisibility(tv.VISIBLE);
      toe = 0;
      tv.setText(tot);
  }
 }


 protected void onPause(){
  super.onPause();
     if(toe==1) {
         String tot = new String();
         tot = ev.getText().toString();
         al = new allmemo(this);
         al.updateName(old, tot);
         ev.setVisibility(ev.GONE);
         tv.setVisibility(tv.VISIBLE);
         toe = 0;
         tv.setText(tot);
     }
 }
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmemol);
        LinearLayout lllbbb=(LinearLayout)findViewById(R.id.allmemo_view);
     starthelper bh = new starthelper(this) {
     };
     SQLiteDatabase dbh = bh . getWritableDatabase ();
     String [] columnss = { bh . UID,bh.DCS};
     Cursor cursors = dbh . query ( bh . TABLE_NAME , columnss , null , null , null , null , null );
     Integer dcs=0;
     while ( cursors . moveToNext ()) {
         dcs=cursors.getInt(cursors.getColumnIndex(bh.DCS));
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

     cat=getIntent().getStringExtra("cate");
        tv=(TextView)findViewById(R.id.tv_allmemo);
        ev=(EditText)findViewById(R.id.ev_allmemo);
        lv=(LinearLayout)findViewById(R.id.allmemo_view);
        al=new allmemo(this);
        ah=new allmemohelper(this) {
        };
        ev.setVisibility(ev.GONE);
        toe=0;
        SQLiteDatabase
              db=ah.getWritableDatabase();
     String [] columns = { ah . UID , ah . NAME , ah . CAT};
      Cursor cursor=db.query(ah.TABLE_NAME, columns, null, null , null , null, null );
     s=new String();
     while(cursor.moveToNext())
     {
         s=cursor.getString(cursor.getColumnIndex( ah.NAME));
         c=cursor.getString(cursor.getColumnIndex( ah.CAT));
         if(cat.equals(c))
         {
             break;
         }
     }
    tv.setText(s);
     lv.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             tvclick();
         }
     });
 }
}
