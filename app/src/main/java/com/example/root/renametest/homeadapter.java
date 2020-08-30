package com.example.root.renametest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by root on 4/1/18.
 */

public class homeadapter extends BaseAdapter {
    Context context;
    String na[];
    String da[];
    String no[];
    LayoutInflater inflater;
    public homeadapter( Context applicationContext , String [] na , String [] da ,String [] no) {
        this . context = context;
        this . na = na;
        this . da = da;
        this . no = no;
        inflater = ( LayoutInflater . from ( applicationContext ));
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
        view = inflater . inflate ( R . layout . sthome , null );
        TextView nat = ( TextView )
                view . findViewById ( R . id . tx1 );
        TextView dat = ( TextView )
                view . findViewById ( R . id . tx2 );
        TextView bt = (TextView)
                view.findViewById(R.id.tx3);
        nat . setText ( na [ i ]);
        dat . setText( da[ i ]);
        bt . setText( no[ i ]);
        return view;
    }
}
