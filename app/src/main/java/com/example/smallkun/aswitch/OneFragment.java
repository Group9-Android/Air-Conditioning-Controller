package com.example.smallkun.aswitch;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smallkun cgnz on 2018/5.
 */

public class OneFragment extends Fragment {

    public int wd=0;
    public int fs=0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_one,container,false);

        final ImageButton closebtn = (ImageButton)view.findViewById(R.id.buttonclose);
        final Spinner spinnermode = (Spinner)view.findViewById(R.id.spinnermode);
        final Button confirmbtn = (Button)view.findViewById(R.id.buttonconfirm);
        final SeekBar sbwendu = (SeekBar)view.findViewById(R.id.seekbarwendu);
        final SeekBar sbfengsu = (SeekBar)view.findViewById(R.id.seekbarfengsu);
        final TextView tempwendu = (TextView)view.findViewById(R.id.tmpwendutext);
        final TextView tempfengsu = (TextView)view.findViewById(R.id.tmpfengsutext);

        //final TextView wendu = (TextView)view.findViewById(R.id.textViewwendu);

        final TextView moshi = (TextView)view.findViewById(R.id.textViewmode);
        final TextView fengsu = (TextView)view.findViewById(R.id.textViewspeed);

        final GradientProgressBar gradientProgressBar = (GradientProgressBar)view.findViewById(R.id.progress_bar);



        confirmbtn.setEnabled(true);
        gradientProgressBar.setPercent(16);

        sbwendu.setMax(25);
        sbwendu.setProgress(16);
        //调节温度
        sbwendu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempwendu.setText((seekBar.getProgress()+3)+"℃");
                wd = seekBar.getProgress()+3;


            }
        });

        //调节风速
        sbfengsu.setMax(3);
        sbfengsu.setProgress(0);
        sbfengsu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tempfengsu.setText(seekBar.getProgress()+"档");
                fs=seekBar.getProgress();

            }
        });


        //模式
        final List<String> dat =new ArrayList<>();
        dat.add("制冷");
        dat.add("送风");
        dat.add("抽湿");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dat);
        spinnermode.setAdapter(adapter2);


        //设置确认按钮点击事件（温度获取问题？）
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gradientProgressBar.setPercent(wd);
                fengsu.setText(fs+"档");

                moshi.setText(spinnermode.getSelectedItem().toString());

                String text = wd +"℃ "+ fs + "档 "+ spinnermode.getSelectedItem().toString();
                Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });




        //设置电源按钮点击事件
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                Start fragment = new Start();
                fragmentTransaction.replace(R.id.ll_content, fragment);
                fragmentTransaction.commit();


            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
