package com.example.smallkun.aswitch;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smallkun cgnz on 2018/5.
 */

public class Start extends Fragment {

    private Button button;
    private GradientProgressBar gradientProgressBar;
    static public boolean onLogin =false;
    static public String user_name;
    static public boolean ond = false;
    static public int lgstate = 100;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.start,container,false);
        button = (Button)view.findViewById(R.id.button);
        gradientProgressBar = (GradientProgressBar)view.findViewById(R.id.progress_bar);

        //确认按钮字体加粗
        TextPaint paint = button.getPaint();
        paint.setFakeBoldText(true);

        if (getArguments()!=null) {
            lgstate = getArguments().getInt("LGState");
            onLogin = getArguments().getBoolean("ONLOGIN");
            user_name= getArguments().getString("USERNAME");
        }

        gradientProgressBar.setPercent(25);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (getArguments()!=null) {
                    lgstate = getArguments().getInt("LGState");
                    onLogin = getArguments().getBoolean("ONLOGIN");
                    user_name= getArguments().getString("USERNAME");
                }

                if (onLogin) {
                    if (lgstate == 100) {
                        Toast.makeText(getActivity(), "登陆成功，请切页刷新~", Toast.LENGTH_SHORT).show();
                        button.setEnabled(false);
//                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                        OneFragment fragment = new OneFragment();
//                        fragmentTransaction.replace(R.id.ll_content,fragment).commit();
                    } else if (lgstate == 101) {
                        Toast.makeText(getActivity(), "您尚未绑定空调，请绑定", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),"您尚未登录，请登录", Toast.LENGTH_SHORT).show();
                }



            }
        });




        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}

