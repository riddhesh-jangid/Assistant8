package com.example.root.renametest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 7/1/18.
 */

abstract class timerhelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "timerbase" ;
    // Database Name
    public static final String TABLE_NAME = "timertable" ;
    // Table Name
    public static final int DATABASE_Version = 1 ;
    // Database Version
    public static final String UID = "_id" ;
    // Column I (Primary Key)
    public static final String NAME = "Name" ;
    //Column II
    public static final String YEAR = "Year" ;
    public static final String DATE = "Date";
    public static final String MONTH = "Month" ;
    public static final String ALARM = "Alarm";
    public static final String HOUR = "Hour" ;
    public static final String  MINUTE= "Minute" ;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
            YEAR + " INTEGER, " +
            DATE + " INTEGER, " + MONTH +" INTEGER, " +
            HOUR + " INTEGER, " + MINUTE + " INTEGER, " + ALARM + " INTEGER );";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
    private Context context ;
    public timerhelper ( Context context ) {
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