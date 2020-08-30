package com.example.root.renametest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 7/1/18.
 */

public class week {
    myhoHelper myhelper ;
    public week ( Context context )
    {
        myhelper = new myhoHelper ( context );
    }
    public long insertData ( String name ,Integer ho , Integer mi , Integer al)
    {
        SQLiteDatabase dbb = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper . NAME , name );
        contentValues . put ( myhoHelper . HOUR , ho );
        contentValues . put ( myhoHelper . MINUTE , mi );
        contentValues . put ( myhoHelper . SUN , 0 );
        contentValues . put ( myhoHelper . MON , 1 );
        contentValues . put ( myhoHelper . TUE , 1 );
        contentValues . put ( myhoHelper . WEN , 1 );
        contentValues . put ( myhoHelper . THU , 1 );
        contentValues . put ( myhoHelper . FRI, 1 );
        contentValues . put ( myhoHelper . SAT , 0 );
        contentValues . put ( myhoHelper . ALARM , al );
        long id = dbb . insert ( myhoHelper . TABLE_NAME , null , contentValues );
        return id ;
    }
    public void getData ()
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] columns = { myhoHelper . UID , myhoHelper . NAME };
        Cursor cursor = db . query ( myhoHelper . TABLE_NAME , columns , null , null , null , null , null );
        while ( cursor . moveToNext ())
        {
            int cid = cursor . getInt ( cursor . getColumnIndex ( myhoHelper . UID ));
            String name = cursor . getString ( cursor . getColumnIndex ( myhoHelper . NAME ));
        }
    }
    public int delete ( String uname )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        String [] whereArgs ={ uname };
        int count = db . delete ( myhoHelper . TABLE_NAME , myhoHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    public int updateName ( String oldName , Integer su ,Integer mo ,Integer tu ,Integer we ,Integer th ,Integer fr ,Integer sa )
    {
        SQLiteDatabase db = myhelper . getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();
        contentValues . put ( myhoHelper . SUN , su );
        contentValues . put ( myhoHelper . MON , mo );
        contentValues . put ( myhoHelper . TUE , tu );
        contentValues . put ( myhoHelper . WEN , we );
        contentValues . put ( myhoHelper . THU , th );
        contentValues . put ( myhoHelper . FRI , fr );
        contentValues . put ( myhoHelper . SAT , sa );
        String [] whereArgs = { oldName };
        int count = db . update ( myhoHelper . TABLE_NAME , contentValues , myhoHelper . NAME + " = ?" , whereArgs );
        return count ;
    }
    static class myhoHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "weekbase" ;
        // Database Name
        private static final String TABLE_NAME = "weektable" ;
        // Table Name
        private static final int DATABASE_Version = 1 ;
        // Database Version
        private static final String UID = "_id" ;
        // Column I (Primary Key)
        private static final String NAME = "Name" ;
        //Column II
        private static final String HOUR = "Hour" ;
        private static final String MINUTE= "Minute" ;
        private static final String SUN = "Sun" ;
        private static final String MON = "Mon" ;
        private static final String TUE = "Tue" ;
        private static final String WEN = "Wen" ;
        private static final String THU = "Thu" ;
        private static final String FRI = "Fri" ;
        private static final String SAT = "Sat" ;
        private static final String ALARM = "Alarm";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," +
                HOUR + " INTEGER, " + MINUTE + " INTEGER, " + SUN + " INTEGER, " + MON +
                " INTEGER, " + TUE + " INTEGER, " + WEN + " INTEGER, " + THU + " INTEGER, "
                + FRI + " INTEGER, " + SAT + " INTEGER, "+ ALARM + " INTEGER );";

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

