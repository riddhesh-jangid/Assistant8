package com.example.root.renametest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by root on 7/1/18.
 */

public class timeradapter extends BaseAdapter {
    Context context;
    String na[];
    String da[];
    String ti[];
    LayoutInflater inflater;
    public timeradapter( Context applicationContext , String [] na , String [] da ,String [] ti) {
        this . context = context;
        this . na = na;
        this . da = da;
        this . ti = ti;
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
        view = inflater . inflate ( R . layout . sttimer , null );
        TextView nat = ( TextView )
                view . findViewById ( R . id . tx1 );
        TextView bt = (TextView)
                view.findViewById(R.id.tx3);
        TextView dat=(TextView)
                view.findViewById(R.id.tx2);
        nat . setText ( na [ i ]);
        bt . setText( ti[ i ]);
        dat.setText(da[i]);
        return view;
    }
}

