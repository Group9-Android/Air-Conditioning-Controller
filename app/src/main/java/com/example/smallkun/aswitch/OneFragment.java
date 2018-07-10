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
    public String user_name,pattern;
    public int lgstate=200;
    public boolean onLogin=false;
    private String result;//返回登录是否成功结果
    public int ok=100,ok1;
    public int onpd=1;
    public String[] ftemp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_one,container,false);

        final ImageButton refreshbtn = (ImageButton)view.findViewById(R.id.buttonrefresh);
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

        //获取登录状态
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
        dat.add("自动");
        dat.add("制热");
        dat.add("抽湿");
        dat.add("制冷");
        dat.add("送风");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dat);
        spinnermode.setAdapter(adapter2);


        //设置确认按钮点击事件
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pattern = Long.toString(spinnermode.getSelectedItemId());

                if (getArguments()!=null) {
                    onLogin = getArguments().getBoolean("ONLOGIN");
                    user_name= getArguments().getString("USERNAME");
                    lgstate = getArguments().getInt("LGState");
                }

                if (onLogin) {
                    if (lgstate == 100) {
                        //Toast.makeText(getActivity(), user_name, Toast.LENGTH_SHORT).show();
                        onpd = 1;
                        //调用接口！！
                        new Thread(runnable).start();

                        if (ok == 100){
                            //Toast.makeText(getActivity(), "绑定成功！", Toast.LENGTH_SHORT).show();
                            //显示设置
                            gradientProgressBar.setPercent(wd);
                            fengsu.setText(fs+"档");
                            moshi.setText(spinnermode.getSelectedItem().toString());
                            Toast.makeText(getActivity(), "操作成功", Toast.LENGTH_SHORT).show();
                        } else if (ok == 201){
                            Toast.makeText(getActivity(), "设置失败，请稍后重试", Toast.LENGTH_SHORT).show();
                        }  else if (ok == 202){
                            Toast.makeText(getActivity(), "您尚未绑定空调，请绑定", Toast.LENGTH_SHORT).show();
                        }
                    } else if (lgstate == 101){
                        Toast.makeText(getActivity(), "您尚未绑定空调，请绑定", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(),Long.toString(spinnermode.getSelectedItemId()), Toast.LENGTH_SHORT).show();
                    }
                } else {
                       Toast.makeText(getActivity(),"您尚未登录，请登录", Toast.LENGTH_SHORT).show();
                }


                //String text = wd +"℃ "+ fs + "档 "+ spinnermode.getSelectedItem().toString();
                //Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
            }
        });

        //设置刷新按钮点击事件
        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getArguments()!=null) {
                    onLogin = getArguments().getBoolean("ONLOGIN");
                    user_name= getArguments().getString("USERNAME");
                    lgstate = getArguments().getInt("LGState");
                }

                new Thread(runnable1).start();

                //Toast.makeText(getActivity(), Integer.toString(ok1), Toast.LENGTH_SHORT).show();

                if (ok1==200) {
                    Toast.makeText(getActivity(), "您尚未绑定空调！", Toast.LENGTH_SHORT).show();
                } else if (ok1==100) {
                    if (Integer.parseInt(ftemp[1].trim())==1){
                        Toast.makeText(getActivity(), "刷新成功！", Toast.LENGTH_SHORT).show();
                        gradientProgressBar.setPercent(Integer.parseInt(ftemp[2].trim()));
                        fengsu.setText(ftemp[4].trim()+"档");
                        switch (ftemp[3].trim()){
                            case "0":
                                moshi.setText("自动"); break;
                            case "1":
                                moshi.setText("制热"); break;
                            case "2":
                                moshi.setText("抽湿"); break;
                            case "3":
                                moshi.setText("制冷"); break;
                            case "4":
                                moshi.setText("送风"); break;
                            default:
                        }
                        onpd = 1;
                        confirmbtn.setEnabled(true);
                        sbfengsu.setEnabled(true);
                        sbwendu.setEnabled(true);
                    } else {
                        Toast.makeText(getActivity(), "当前空调已关闭", Toast.LENGTH_SHORT).show();
                        gradientProgressBar.setPercent(Integer.parseInt(ftemp[2].trim()));
                        fengsu.setText(ftemp[4].trim()+"档");
                        switch (ftemp[3].trim()){
                            case "0":
                                moshi.setText("自动"); break;
                            case "1":
                                moshi.setText("制热"); break;
                            case "2":
                                moshi.setText("抽湿"); break;
                            case "3":
                                moshi.setText("制冷"); break;
                            case "4":
                                moshi.setText("送风"); break;
                            default:
                        }
                        onpd = 0;
                        confirmbtn.setEnabled(false);
                        sbfengsu.setEnabled(false);
                        sbwendu.setEnabled(false);
                    }


                }
            }
        });


        //设置电源按钮点击事件
        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pattern = Long.toString(spinnermode.getSelectedItemId());
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                Start fragment = new Start();
//                fragmentTransaction.replace(R.id.ll_content, fragment);
//                fragmentTransaction.commit();
                if (onpd==1) onpd = 0; else onpd = 1;

                //调用接口！！
                new Thread(runnable).start();

                if (ok==100) {
                    Toast.makeText(getActivity(), "设备操作成功", Toast.LENGTH_SHORT).show();

                    if (onpd==0) {
                        confirmbtn.setEnabled(false);
                        sbfengsu.setEnabled(false);
                        sbwendu.setEnabled(false);
                    } else {
                        confirmbtn.setEnabled(true);
                        sbfengsu.setEnabled(true);
                        sbwendu.setEnabled(true);
                    }
                } else {
                    if (ok == 201){
                        Toast.makeText(getActivity(), "您尚未绑定空调", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "操作失败", Toast.LENGTH_SHORT).show();
                    }

                }
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

            //Toast.makeText(getActivity(), resultArray[0], Toast.LENGTH_SHORT).show();
            //提取返回数据中的内容
            String[] temp = result.split(",");
            result = temp[0];
            ok = Integer.parseInt(result.trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符


        }
    };

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("response");
            String[] resultArray = result.split(",");//数据用,分割存在resultArray数组里面
            //这里写要对数据进行的操作

            //Toast.makeText(getActivity(), result.trim(), Toast.LENGTH_SHORT).show();
            //提取返回数据中的内容
            ftemp = result.split(",");
            result = ftemp[0];
            ok1 = Integer.parseInt(result.trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符


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
                url = new URL("http://47.106.181.0:8080/air/SetAir?account="+URLEncoder.encode(user_name, "utf-8")+"&status="+URLEncoder.encode(Integer.toString(onpd), "utf-8")+"&temperature="+URLEncoder.encode(Integer.toString(wd), "utf-8")+"&pattern="+URLEncoder.encode(pattern, "utf-8")+"&speed="+URLEncoder.encode(Integer.toString(fs), "utf-8"));

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

    //新线程进行网络请求
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            //
            // TODO: http request.
            //
            URL url=null;
            try{
                url = new URL("http://47.106.181.0:8080/air/CheckAir?account="+URLEncoder.encode(user_name, "utf-8"));

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
            handler1.sendMessage(msg);
        }
    };
}
