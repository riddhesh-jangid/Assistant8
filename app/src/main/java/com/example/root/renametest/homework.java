package com.example.root.renametest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 3/1/18.
 */

public class homework {
    myDbHelper myhelper ;
    public homework ( Context context )
    {
        myhelper = new myDbHelper ( context );
    }
    public long insertData ( String name , Integer da , Integer mo )
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myDbHelper . NAME , name );
        contentValues . put ( myDbHelper . DATE , da );
        contentValues . put ( myDbHelper . MONTH , mo );
        String in= new String();
        in="on";
        contentValues . put ( myDbHelper . HWNOT , in );
        long id = dbb . insert ( myDbHelper . TABLE_NAME , null , contentValues );
        return id ;
    }
    public void getData ()
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] columns = { myDbHelper . UID , myDbHelper . NAME , myDbHelper . DATE , myDbHelper . MONTH};
        Cursor cursor = db . query ( myDbHelper . TABLE_NAME , columns , null , null , null , null , null );
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( myDbHelper . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( myDbHelper . NAME ));
            Integer date
                    = cursor . getInt ( cursor . getColumnIndex ( myDbHelper . DATE ));
            Integer month
                    = cursor . getInt ( cursor . getColumnIndex ( myDbHelper . MONTH ));

        }
    }
    public int delete ( String uname )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] whereArgs ={ uname };
        int count = db . delete ( myDbHelper . TABLE_NAME , myDbHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    public int updateName ( String oldName , String newName )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myDbHelper . HWNOT , newName );
        String [] whereArgs = { oldName };
        int count = db . update ( myDbHelper . TABLE_NAME , contentValues , myDbHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "homebase" ;
        // Database Name
        private static final String TABLE_NAME = "hometable" ;
        // Table Name
        private static final int DATABASE_Version = 1 ;
        // Database Version
        private static final String UID = "_id" ;
        // Column I (Primary Key)
        private static final String NAME = "Name" ;
        //Column II
        private static final String DATE = "Date" ;
        // Column III
        private static final String MONTH = "Month" ;
        private static final String HWNOT = "hwnot" ;
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
                DATE + " INTEGER, " + MONTH +" INTEGER, "+ HWNOT + " VARCHAR(5) );" ;
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
