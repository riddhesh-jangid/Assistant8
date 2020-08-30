package com.example.root.renametest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by root on 8/1/18.
 */

public class weekadapter extends BaseAdapter {
    Context context;
    String na[];
    String ti[];
    String su[];
    String mo[];
    String tu[];
    String we[];
    String th[];
    String fr[];
    String sa[];
    LayoutInflater inflater;
    public weekadapter(Context applicationContext , String [] na , String [] ti ,String [] su ,String [] mo ,String [] tu ,String [] we ,String [] th ,String [] fr ,String [] sa ) {
        this . context = context;
        this . na = na;
        this . ti = ti;
        this . su = su;
        this . mo = mo;
        this . tu = tu;
        this . we = we;
        this . th = th;
        this . fr = fr;
        this . sa= sa;
        inflater = ( LayoutInflater. from ( applicationContext ));
    }

    @Override
    public int getCount () {
        return na . length;
    }
    @Override
    public Object getItem ( int i ) {
        return null;
    }
    @Override
    public long getItemId ( int i ) {
        return 0;
    }

    @Override
    public View getView (int i , View view , ViewGroup viewGroup ) {
        view = inflater . inflate ( R . layout . stweek , null );
        TextView vn = ( TextView )
                view . findViewById ( R . id . tx1 );
        TextView vt = (TextView)
                view.findViewById(R.id.tx2);
        TextView vsu = (TextView)
                view.findViewById(R.id.tx3);
        TextView vmo = (TextView)
                view.findViewById(R.id.tx4);
        TextView vtu = (TextView)
                view.findViewById(R.id.tx5);
        TextView vwe = (TextView)
                view.findViewById(R.id.tx6);
        TextView vth = (TextView)
                view.findViewById(R.id.tx7);
        TextView vfr = (TextView)
                view.findViewById(R.id.tx8);
        TextView vsa = (TextView)
                view.findViewById(R.id.tx9);

        vn . setText ( na [ i ]);
        vt . setText( ti[ i ]);
        vsu . setText(su [i]);
        vmo . setText(mo [i]);
        vtu . setText(tu [i]);
        vwe . setText(we [i]);
        vth . setText(th [i]);
        vfr . setText(fr [i]);
        vsa . setText(sa [i]);
        return view;
    }
}
