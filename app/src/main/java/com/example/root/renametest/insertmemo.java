package com.example.root.renametest;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by root on 7/2/18.
 */

public class insertmemo extends AppCompatActivity {
    TextView txt1,txt2,txt3,txt4,intro;
    LinearLayout cover;
    Button extbtn;
    Animation animation,animation1,animation2,animation3;
    int son;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertmemo);
        cover=(LinearLayout)findViewById(R.id.coverlinear);
        cover.setVisibility(cover.GONE);
        intro=(TextView)findViewById(R.id.intro);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.moveless);
        animation2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movedn);
        animation3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.movednless);
        txt1=(TextView)findViewById(R.id.txt1);
        txt2=(TextView)findViewById(R.id.txt2);
        txt3=(TextView)findViewById(R.id.txt3);
        txt4=(TextView)findViewById(R.id.txt4);
        extbtn=(Button)findViewById(R.id.extbtn);
        extbtn.setText("<>");
        son=0;
        txt1.setVisibility(txt1.GONE);
        txt2.setVisibility(txt2.GONE);
        txt3.setVisibility(txt3.GONE);
        txt4.setVisibility(txt4.GONE);
    }
    public void extend(View v)
    {
        if(son==0)
        {
            intro.setVisibility(intro.GONE);
            cover.setVisibility(cover.VISIBLE);
            extbtn.setText("><");
            txt1.setVisibility(txt1.VISIBLE);
            txt2.setVisibility(txt2.VISIBLE);
            txt3.setVisibility(txt3.VISIBLE);
            txt4.setVisibility(txt4.VISIBLE);
            txt1.startAnimation(animation);
            txt2.startAnimation(animation1);
            txt3.startAnimation(animation);
            txt4.startAnimation(animation1);
            son=1;
        }
        else if(son==1)
        {
            cover.setVisibility(cover.GONE);
            extbtn.setText("<>");
            txt1.startAnimation(animation2);
            txt2.startAnimation(animation3);
            txt3.startAnimation(animation2);
            txt4.startAnimation(animation3);
            txt1.setVisibility(txt1.GONE);
            txt2.setVisibility(txt2.GONE);
            txt3.setVisibility(txt3.GONE);
            txt4.setVisibility(txt4.GONE);
            son=0;
            intro.setVisibility(intro.VISIBLE);
        }
    }
    public void insert(View v)
    {
        int match=0;
        EditText et=(EditText)findViewById(R.id.takest);
        String na = et.getText().toString();
        memo sl=new memo(this);
        allmemo am=new allmemo(this);
        if(na.length()>0) {
            int matchmemo=0;
            String s=new String();
            s=et.getText().toString();
            memohelper ob=new memohelper(this) {
            };
            SQLiteDatabase db=ob.getWritableDatabase();
            String [] columns = { ob . UID , ob . NAME};
            Cursor cursor=db.query(ob.TABLE_NAME, columns , null , null , null , null , null );
            int i=0;
            while(cursor.moveToNext()){
                String sa = cursor.getString(cursor.getColumnIndex( ob.NAME ));
                if(s.equals(sa))
                    matchmemo=1;
            }
            if(matchmemo==0) {
                am.insertfirstData(s);
                long id = sl.insertData(s);
                if (id <= 0) {
                    Message.message(this, "Unsuccessfull");
                } else {
                    Message.message(this, "Successfull");
                }
            }
            else
            {
                Message.message(this,"Already Exist");
            }
        }
    }
    public void show(View v)
    {
        Intent intent=new Intent(insertmemo.this,dismemo.class);
        startActivity(intent);
    }
    public void home(View v)
    {
        Intent intent=new Intent(insertmemo.this,MainActivity.class);
        startActivity(intent);
    }
    public void setting(View v)
    {
        Intent intent=new Intent(insertmemo.this,menu.class);
        startActivity(intent);
    }
    public void exit(View v)
    {
        finish();
        System.exit(0);
    }


}
