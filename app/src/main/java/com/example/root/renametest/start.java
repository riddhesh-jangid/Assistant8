package com.example.root.renametest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 24/1/18.
 */

public class start {
    myhoHelper myhelper ;
    public start ( Context context )
    {
        myhelper = new myhoHelper( context );
    }
    public long insertData ()
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper. NAME , "default" );
        contentValues . put ( myhoHelper. HHOUR , 17 );
        contentValues . put ( myhoHelper. HMINUTE , 0 );
        contentValues . put ( myhoHelper. BHOUR , 23 );
        contentValues . put ( myhoHelper. BMINUTE , 0 );
        contentValues . put ( myhoHelper. BOF , 1 );
        contentValues . put ( myhoHelper. HOF , 1 );
        contentValues . put ( myhoHelper. OHOUR , 22 );
        contentValues . put ( myhoHelper. OMINUTE , 0 );
        contentValues . put ( myhoHelper. OOF , 1 );
        contentValues . put ( myhoHelper. SS , 0 );
        contentValues . put ( myhoHelper. HS , 1 );
        contentValues . put ( myhoHelper. DCS , 0 );
        contentValues . put ( myhoHelper. FVT , 0 );
        long id = dbb . insert ( myhoHelper. TABLE_NAME , null , contentValues );
        return id ;
    }
    public void getData ()
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] columns = { myhoHelper. UID,myhoHelper. BHOUR , myhoHelper. BMINUTE,myhoHelper.BOF  , myhoHelper. HHOUR,myhoHelper. HMINUTE , myhoHelper. HOF,myhoHelper. OHOUR , myhoHelper. OMINUTE,myhoHelper. OOF , myhoHelper. SS,myhoHelper. HS };
        Cursor cursor = db . query ( myhoHelper. TABLE_NAME , columns , null , null , null , null , null );
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( myhoHelper. UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( myhoHelper. NAME ));
        }
    }
    public int delete ( String uname )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] whereArgs ={ uname };
        int count = db . delete ( myhoHelper. TABLE_NAME , myhoHelper. NAME + " = ?" , whereArgs );
        return count ;
    }
    public int updateName ( String newName )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper. NAME , newName );
        String [] whereArgs = { "default" };
        int count = db . update ( myhoHelper. TABLE_NAME , contentValues , myhoHelper. NAME + " = ?" , whereArgs );
        return count ;
    }
    static class myhoHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "startbase" ;
        // Database Name
        private static final String TABLE_NAME = "starttable" ;
        // Table Name
        private static final int DATABASE_Version = 1 ;
        // Database Version
        private static final String UID = "_id" ;
        // Column I (Primary Key)
        private static final String NAME = "Name" ;
        //Column II
        private static final String BHOUR="Bhour";
        private static final String BMINUTE="Bminute";
        private static final String BOF="Bof";
        private static final String HHOUR="Hhour";
        private static final String HMINUTE="Hminute";
        private static final String HOF="Hof";
        private static final String OHOUR="Ohour";
        private static final String OMINUTE="Ominute";
        private static final String OOF="Oof";
        private static final String SS="Ss";
        private static final String HS="Hs";
        private static final String DCS="Dcs";
        private static final String URI="Uri";
        private static final String FVT="Fvt";

        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255), " + BHOUR + " INTEGER, " + BMINUTE
                + " INTEGER, " + BOF + " INTEGER, " + HHOUR + " INTEGER, "
                + HMINUTE + " INTEGER, " + HOF + " INTEGER, " + OHOUR + " INTEGER, "
                + OMINUTE + " INTEGER, " + OOF + " INTEGER, " + SS + " INTEGER, "
                + HS + " INTEGER , " + DCS + " INTEGER, " + FVT + " INTEGER);" ;
        //INTEGER);" ;
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        private Context context ;
        public myhoHelper ( Context context ) {
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

}
