package com.example.smallkun.aswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smallkun cgnz on 2018/5.
 */

public class OneFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_one,container,false);

        final Button openbtn = (Button)view.findViewById(R.id.buttonopen);
        final Spinner spinner1 = (Spinner)view.findViewById(R.id.spinnerwendu);
        final Spinner spinner2 = (Spinner)view.findViewById(R.id.spinnerfengsu);
        Button confirmbtn = (Button)view.findViewById(R.id.buttonconfirm);
        final RadioGroup mode = (RadioGroup)view.findViewById(R.id.radioGroup);
        final TextView wendu = (TextView)view.findViewById(R.id.textViewwendu);
        final TextView moshi = (TextView)view.findViewById(R.id.textViewmode);

        //温度设置
        final List<String> datas = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            datas.add(16 + i+"C");
        }
        //适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,datas);
        spinner1.setAdapter(adapter);

        //风力
        final List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add( i+"档");
        }
        //适配器
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        spinner2.setAdapter(adapter1);

        //设置确认按钮点击事件
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selected = mode.getCheckedRadioButtonId();
                RadioButton b = getView().findViewById(selected);

                wendu.setText(spinner1.getSelectedItem().toString());
                moshi.setText(b.getText());

                String text = spinner1.getSelectedItem().toString() + "  " + spinner2.getSelectedItem().toString()+"  ";
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });



        //设置电源按钮点击事件
        openbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (openbtn.getText()=="on")
                    openbtn.setText("off");
                else openbtn.setText("on");
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}