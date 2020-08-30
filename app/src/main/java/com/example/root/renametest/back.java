package com.example.root.renametest;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by root on 25/1/18.
 */

public class back extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.back);
    }
    public void def(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 0 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig3(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 3 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig2(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 2 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig6(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 6 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig4(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 4 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig5(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 5 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig1(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 1 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig7(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 7 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    public void ig8(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Set Background").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starthelper bh = new starthelper(back.this) {
                };
                SQLiteDatabase db = bh . getWritableDatabase ();
                ContentValues contentValues = new ContentValues ();
                contentValues . put ( starthelper. DCS , 8 );
                String [] whereArgs = { "default" };
                db . update ( starthelper. TABLE_NAME , contentValues , starthelper. NAME + " = ?" , whereArgs );
                Intent iii=new Intent(back.this,MainActivity.class);
                startActivity(iii);

            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }

}
