package com.example.root.renametest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 26/1/18.
 */

public class timermsg {
    myhoHelper myhelper ;
    public timermsg ( Context context )
    {
        myhelper = new myhoHelper( context );
    }
    public long insertData ( String name )
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper. NAME , name );
        long id = dbb . insert ( myhoHelper. TABLE_NAME , null , contentValues );
        return id ;
    }
    public void getData ()
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] columns = { myhoHelper. UID , myhoHelper. NAME };
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
        int count = db . delete (myhoHelper. TABLE_NAME , myhoHelper. NAME + " = ?" , whereArgs );
        return count ;
    }
    public int updateName ( String oldName , String newName )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper. NAME , newName );
        String [] whereArgs = { oldName };
        int count = db . update ( myhoHelper. TABLE_NAME , contentValues , myhoHelper. NAME + " = ?" , whereArgs );
        return count ;
    }
    static class myhoHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "timermsgbase" ;
        // Database Name
        private static final String TABLE_NAME = "timermsgtable" ;
        // Table Name
        private static final int DATABASE_Version = 1 ;
        // Database Version
        private static final String UID = "_id" ;
        // Column I (Primary Key)
        private static final String NAME = "Name" ;
        //Column II
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255));" ;
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
