package com.example.root.renametest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 6/1/18.
 */

abstract class allwishhelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "alwishbase" ;
    // Database Name
    public static final String TABLE_NAME = "alwishtable" ;
    // Table Name
    public static final int DATABASE_Version = 1 ;
    // Database Version
    public static final String UID = "_id" ;
    // Column I (Primary Key)
    public static final String NAME = "Name" ;
    //Column II
    public static final String CAT = "Cat" ;
    // Column III
    public static final String STATUS = "Status" ;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
            CAT + " VARCHAR(255) ," +  STATUS + " VARCHAR(10) );" ;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
    private Context context ;
    public allwishhelper ( Context context ) {
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