package com.example.webservices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<Mobile> mobj = null;

    private LayoutInflater thisInflater;

    public CustomAdapter(Context context, ArrayList<Mobile> mobj) {
        super();
        thisInflater = (LayoutInflater.from(context));
        this.mobj = mobj;



    }


    @Override
    public int getCount() {
        return mobj.size();
    }

    @Override
    public Object getItem(int position) {
        return mobj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = thisInflater.inflate(R.layout.customlayout, null);
        }
        TextView txtview = (TextView)convertView.findViewById(R.id.txtview);
        TextView txtview2 = (TextView)convertView.findViewById(R.id.txtview2);

        TextView txtview3 = (TextView)convertView.findViewById(R.id.txtview3);

        txtview.setText(mobj.get(position).getMake());
        txtview2.setText(mobj.get(position).getModel());
        txtview3.setText(mobj.get(position).getPrice());
        return convertView;
    }
}
