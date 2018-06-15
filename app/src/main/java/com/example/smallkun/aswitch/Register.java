package com.example.smallkun.aswitch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vbn on 18/6/14.
 */

public class Register extends AppCompatActivity {
    private String user_name;
    private String pass_word;
    public Toolbar register_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_toolbar = (Toolbar)findViewById(R.id.register_toolbar);
        register_toolbar.setTitle("");
        setSupportActionBar(register_toolbar);
        //为标题栏设置颜色
        register_toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        //显示标题栏左边自带的按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //项目设置时，已设置actionbar为空
        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("注册");

        final EditText et1=(EditText)findViewById(R.id.r_account);
        final EditText et2=(EditText)findViewById(R.id.r_apw);
        Button button=(Button)findViewById(R.id.r_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = et1.getText().toString();
                pass_word = et2.getText().toString();
                if(user_name.equals("")){
                    Toast.makeText(Register.this, "请输入账户！ " , Toast.LENGTH_SHORT).show();
                }else if(pass_word.equals("")) {
                    Toast.makeText(Register.this, "请输入密码！ " , Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(runnable).start();
                }

            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("response");
            TextView rs = (TextView)findViewById(R.id.register_result);
            rs.setText(result);

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
                url = new URL("http://47.106.181.0:8080/air/Register?account="+ URLEncoder.encode(user_name, "utf-8")+"&password="+URLEncoder.encode(pass_word, "utf-8"));
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

