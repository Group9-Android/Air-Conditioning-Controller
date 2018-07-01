package com.example.smallkun.aswitch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLEncoder;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by smallkun on 2018/5/5.
 */

public class ThreeFragment extends Fragment {

    private Button number;
    private Button qrcode;
    private TextView cstate;
    public String user_name;
    public int lgstate=200;
    public boolean onLogin=false;
    public String device_id;
    private String result;//返回登录是否成功结果
    public int ok;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        number = (Button) view.findViewById(R.id.number);
        qrcode = (Button) view.findViewById(R.id.qrcode);
        cstate = (TextView) view.findViewById(R.id.currentstate);

        //获取登录状态
        if (getArguments()!=null) {
            onLogin = getArguments().getBoolean("ONLOGIN");
            user_name= getArguments().getString("USERNAME");
            lgstate = getArguments().getInt("LGState");
        }

        //设置按钮点击事件
        number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (onLogin) {
                    Intent intent = new Intent(getActivity(),add_number.class);
                    startActivityForResult(intent,1);
                } else {
                    Toast.makeText(getActivity(), "请先登录再进行绑定！", Toast.LENGTH_SHORT).show();
                }

            }
        });

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLogin) {
                    Intent intent2 = new Intent(getActivity(), add_qrcode.class);
                    startActivityForResult(intent2,2);
                } else {
                    Toast.makeText(getActivity(), "请先登录再进行绑定！", Toast.LENGTH_SHORT).show();
                }

            }
        });



        if (onLogin) {
            if (lgstate == 101) {
                    cstate.setText(user_name+"您好 您尚未绑定空调！");
            } else {
                if (lgstate == 100) {
                   cstate.setText(user_name+"您好 您已绑定空调！");
                }
            }
        } else {
            cstate.setText("尚未登录！");
        }

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK) {
                    device_id = data.getStringExtra("data_return");
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        String returnedData = data.getStringExtra("data_return");
                        Toast.makeText(getActivity(),"wu", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    device_id = data.getStringExtra("data_return");
                    //接口
                    new Thread(runnable).start();
                } else {
                    if (resultCode == RESULT_CANCELED) {
                        String returnedData = data.getStringExtra("data_return");
                        Toast.makeText(getActivity(), "wu2", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
        }
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
            result = data.getString("response");

            //提取返回数据中的内容
            String[] temp = result.split(",");
            result = temp[0];
            ok = Integer.parseInt(result.trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符
            if (ok == 100){
                Toast.makeText(getActivity(), "绑定成功！", Toast.LENGTH_SHORT).show();
                cstate.setText(user_name+"您好 您已绑定空调！");
            } else if (ok == 201){
                Toast.makeText(getActivity(), "空调不存在！", Toast.LENGTH_SHORT).show();
            }  else if (ok == 200){
                Toast.makeText(getActivity(), "操作失败", Toast.LENGTH_SHORT).show();
            }
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
                url = new URL("http://47.106.181.0:8080/air/Binding?account="+URLEncoder.encode(user_name, "utf-8")+"&airId="+URLEncoder.encode(device_id, "utf-8"));

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
