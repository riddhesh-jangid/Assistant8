package com.example.root.renametest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 24/1/18.
 */

abstract public class starthelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "startbase" ;
    // Database Name
    public static final String TABLE_NAME = "starttable" ;
    // Table Name
    private static final int DATABASE_Version = 1 ;
    // Database Version
    public static final String UID = "_id" ;
    // Column I (Primary Key)
    public static final String NAME = "Name" ;
    //Column II
    public static final String BHOUR="Bhour";
    public static final String BMINUTE="Bminute";
    public static final String BOF="Bof";
    public static final String HHOUR="Hhour";
    public static final String HMINUTE="Hminute";
    public static final String HOF="Hof";
    public static final String OHOUR="Ohour";
    public static final String OMINUTE="Ominute";
    public static final String OOF="Oof";
    public static final String SS="Ss";
    public static final String HS="Hs";
    public static final String DCS="Dcs";
    public static final String URI="Uri";
    public static final String FVT="Fvt";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), " + BHOUR + " INTEGER, " + BMINUTE
            + " INTEGER, " + BOF + " INTEGER, " + HHOUR + " INTEGER, "
            + HMINUTE + " INTEGER, " + HOF + " INTEGER, " + OHOUR + " INTEGER, "
            + OMINUTE + " INTEGER, " + OOF + " INTEGER, " + SS + " INTEGER, "
            + HS + " INTEGER , " + DCS + " INTEGER, " + FVT + " INTEGER);" ;

    //INTEGER);" ;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
    private Context context ;
    public starthelper ( Context context ) {
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


