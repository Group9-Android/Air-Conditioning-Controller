package com.example.smallkun.aswitch;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.net.URL;
        import java.net.URLEncoder;
        import java.util.ArrayList;
        import java.util.List;

        import static android.content.Context.MODE_PRIVATE;

/**
 * Created by smallkun on 2018/5/5.
 */

public class FourFragment extends Fragment {

    private Button btnget;
    private Button qrcode;
    private TextView historytext;
    public String user_name;
    public int lgstate=200;
    public boolean onLogin=false,pdhis= false;
    public String device_id;
    private String result;//返回登录是否成功结果
    public int ok;
    public String[] temp;
    public ListView listview;


    public List<historydt> history = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_four,container,false);

        btnget = (Button) view.findViewById(R.id.btnget);
        historytext = (TextView)view.findViewById(R.id.texthistory);
        pdhis = false;

        //初始化ListView适配器
        final historyadapter adapter = new historyadapter(getActivity(),R.layout.historylayout,history);
        listview = (ListView) view.findViewById(R.id.hisview);
        listview.setAdapter(adapter);

        //获取登录状态
        if (getArguments()!=null) {
            onLogin = getArguments().getBoolean("ONLOGIN");
            user_name= getArguments().getString("USERNAME");
            lgstate = getArguments().getInt("LGState");
            result = getArguments().getString("TXT","");
        }
        //初始化历史记录
            //historytext.setText(result);
        SharedPreferences pref = getActivity().getSharedPreferences("data5",MODE_PRIVATE);
        //historytext.setText(pref.getString("TXT",""));
        historytext.setText("");

        btnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onLogin) {
                    if (lgstate == 100) {

                        //调用接口
                        new Thread(runnable).start();

                        //列表重新显示
                        listview.setAdapter(adapter);

                    } else {
                        Toast.makeText(getActivity(), "您尚未绑定空调，请绑定！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "您尚未登录，请登录！", Toast.LENGTH_SHORT).show();
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
            result = data.getString("response");

            //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            //提取返回数据中的内容
            temp = result.split(",");
            ok = Integer.parseInt(temp[0].trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符
            if (ok == 100){
                Toast.makeText(getActivity(), "操作成功，获取到"+temp.length+"条记录！", Toast.LENGTH_SHORT).show();

                //显示数据
                for (int i=1;i<temp.length;i+=2){
                    if (i%2==1){
                        if (pdhis == false) {
                            historydt hist = new historydt(temp[i]+"  ",temp[i+1].trim());
                            history.add(hist);
                        }
                        //historytext.setText(historytext.getText()+temp[i]);
                        //historytext.setText(historytext.getText()+temp[i+1]+"\n");
                    }
                }

                pdhis=true;

                //
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("data5", MODE_PRIVATE).edit();
                editor.putString("TXT",historytext.getText().toString());
                editor.apply();

            } else if (ok == 200){
                Toast.makeText(getActivity(), "该账户无设备操作记录！", Toast.LENGTH_SHORT).show();
                historytext.setText("");
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
                url = new URL("http://47.106.181.0:8080/air/History?account="+URLEncoder.encode(user_name, "utf-8"));

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


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Bundle bd = new Bundle();
        bd.putString("TXT",historytext.getText().toString());
    }

    //存储操作记录
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle bd = new Bundle();
        bd.putString("TXT",historytext.getText().toString());
    }
}
