package com.example.root.renametest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 5/1/18.
 */

abstract class wishlisthelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "wishbase" ;
    // Database Name
    public static final String TABLE_NAME = "wishtable" ;
    // Table Name
    public static final int DATABASE_Version = 1 ;
    // Database Version
    public static final String UID = "_id" ;
    // Column I (Primary Key)
    public static final String NAME = "Name" ;
    //Column II
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255));" ;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
    private Context context ;
    public wishlisthelper ( Context context ) {
        super ( context , DATABASE_NAME , null , DATABASE_Version );
        this . context = context ;
    }
    public void onCreate ( SQLiteDatabase db ) {
        try {
            db . execSQL ( CREATE_TABLE );
        } catch ( Exception e ) {
            Message . message ( context , "" + e );
        }
    }
    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        try {
            Message . message ( context , "OnUpgrade" );
            db . execSQL ( DROP_TABLE );
            onCreate ( db );
        } catch ( Exception e ) {
            Message . message ( context , "" + e );
        }
    }
}
