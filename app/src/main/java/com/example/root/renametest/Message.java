package com.example.root.renametest;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by root on 2/1/18.
 */

public class Message {
    public static void message (Context context , String message ) {
        Toast. makeText ( context , message , Toast . LENGTH_SHORT ). show ();
    }
}
