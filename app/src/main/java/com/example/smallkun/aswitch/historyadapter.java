package com.example.smallkun.aswitch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class historyadapter extends ArrayAdapter<historydt> {

    private int resourceId;

    public historyadapter(Context context, int textViewSourcesId, List<historydt> objects){
        super(context, textViewSourcesId, objects);
        resourceId = textViewSourcesId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        historydt his = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView historytime = (TextView) view.findViewById(R.id.htime);
        TextView historyop = (TextView) view.findViewById(R.id.hop);

        historytime.setText(his.gettime());
        historyop.setText(his.getOp());

        return view;
    }


}
