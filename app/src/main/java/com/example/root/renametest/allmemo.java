package com.example.root.renametest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 8/1/18.
 */

public class allmemo {
    myDbHelper myhelper ;
    public allmemo ( Context context )
    {
        myhelper = new myDbHelper ( context );
    }
    public long insertData ( String name,String cat)
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myDbHelper . NAME , name );
        contentValues . put ( myDbHelper . CAT , cat );
        long id = dbb . insert ( myDbHelper . TABLE_NAME , null , contentValues );
        return id ;
    }
    public long insertfirstData ( String cat)
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        String name=cat+" ";
        contentValues . put ( myDbHelper . NAME , name );
        contentValues . put ( myDbHelper . CAT , cat );
        long id = dbb . insert ( myDbHelper . TABLE_NAME , null , contentValues );
        return id ;
    }
    public void getData ()
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] columns = { myDbHelper . UID , myDbHelper . NAME , myDbHelper . CAT};
        Cursor cursor = db . query ( myDbHelper . TABLE_NAME , columns , null , null , null , null , null );
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( myDbHelper . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( myDbHelper . NAME ));
            String cat = cursor . getString ( cursor . getColumnIndex ( myDbHelper . CAT ));
        }
    }
    public int delete ( String uname )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] whereArgs ={ uname };
        int count = db . delete ( myDbHelper . TABLE_NAME , myDbHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    public int deletecat ( String uname )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] whereArgs ={ uname };
        int count = db . delete ( myDbHelper . TABLE_NAME , myDbHelper . CAT + " = ?" , whereArgs );
        return count ;
    }
    public int updateName ( String oldName , String newName )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myDbHelper . NAME , newName );
        String [] whereArgs = { oldName };
        int count = db . update ( myDbHelper . TABLE_NAME , contentValues , myDbHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "almemobase" ;
        // Database Name
        private static final String TABLE_NAME = "almemotable" ;
        // Table Name
        private static final int DATABASE_Version = 1 ;
        // Database Version
        private static final String UID = "_id" ;
        // Column I (Primary Key)
        private static final String NAME = "Name" ;
        //Column II
        private static final String CAT = "Cat" ;
        // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
                CAT + " VARCHAR(255) );" ;
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        private Context context ;
        public myDbHelper ( Context context ) {
            super ( context , DATABASE_NAME , null , DATABASE_Version );
            this . context = context ;
        }
        public void onCreate ( SQLiteDatabase db ) {
            try {
                db . execSQL ( CREATE_TABLE );
            } catch ( Exception e ) {
                Message. message ( context , "" + e );
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
