package com.example.smallkun.aswitch;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by smallkun cgnz on 2018/5.
 */

public class OneFragment extends Fragment {

    public int wd=25;
    public int fs=0;
    public String user_name;
    public int lgstate=200;
    public boolean onLogin=false;

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

        if (getArguments()!=null) {
            onLogin = getArguments().getBoolean("ONLOGIN");
            user_name= getArguments().getString("USERNAME");
            lgstate = getArguments().getInt("LGState");
        }


        gradientProgressBar.setPercent(25);

        sbwendu.setMax(10);
        sbwendu.setProgress(5);
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
                tempwendu.setText((seekBar.getProgress()+20)+"℃");
                wd = seekBar.getProgress()+20;

                //confirmbtn.setEnabled(true);
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

                //confirmbtn.setEnabled(true);

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

                if (getArguments()!=null) {
                    onLogin = getArguments().getBoolean("ONLOGIN");
                    user_name= getArguments().getString("USERNAME");
                    lgstate = getArguments().getInt("LGState");
                }

                if (onLogin) {
                    if (lgstate == 100) {
                        Toast.makeText(getActivity(), user_name, Toast.LENGTH_SHORT).show();
                    } else if (lgstate == 101){
                        Toast.makeText(getActivity(), "您尚未绑定空调，请绑定", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(getActivity(),"您尚未登录，请登录", Toast.LENGTH_SHORT).show();
                }
                //调用接口！！
                //new Thread(runnable).start();

                //String text = wd +"℃ "+ fs + "档 "+ spinnermode.getSelectedItem().toString();
                //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("response");
            String[] resultArray = result.split(",");//数据用,分割存在resultArray数组里面
            //这里写要对数据进行的操作

            Toast.makeText(getActivity(), resultArray[0], Toast.LENGTH_SHORT).show();
           /*TextView lr = (TextView)findViewById(R.id.login_result);
           if(resultArray[0].equals("100")){
               String res = "status: "+resultArray[1]+"\n"+"temperature: "+resultArray[2]+"\n"+"pattern: "+resultArray[3]+"\n"+"speed: "+resultArray[4];
               lr.setText(res);
           }else{
               String res = resultArray[1];
               lr.setText(res);
           }*/

        }
    };

    //新线程进行网络请求
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            URL url=null;
            try{
                url = new URL("http://47.106.181.0:8080/air/Login?account="+URLEncoder.encode("112", "utf-8")+"&password="+URLEncoder.encode("111", "utf-8"));

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            httpNet httpConnection=new httpNet(url);

            String result=httpConnection.loginOfPost();

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("response",result);

            //data.putString("value", "请求结果");
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
