package com.example.smallkun.aswitch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by smallkun on 2018/5/5.
 */

public class TwoFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_two,container,false);

        final Button btntest1 = (Button) view.findViewById(R.id.btntest);
        btntest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "2333", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Profile_m.class);
                startActivity(intent);
            }
        });

        return inflater.inflate(R.layout.fragment_two,container,false);
    }

    private String[] data = {
            "回家",
            "睡眠",
            "item",
            "item",
            "item"
    };



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Activity activity = (MainActivity)getActivity();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,data);
        ListView listview = (ListView)view.findViewById(R.id.list_view);
        View footerView = ((LayoutInflater)activity.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.list_footer, null, false);
        listview.addFooterView(footerView);
        listview.setAdapter(adapter);

        final Button btntest1 = (Button) view.findViewById(R.id.btntest);
        btntest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "2333", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), Profile_m.class);
                startActivity(intent);
            }
        });
    }
}