package com.example.root.renametest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by root on 25/1/18.
 */

public class weekstop extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timerl);
        weekbroadcast.ringtone.stop();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
