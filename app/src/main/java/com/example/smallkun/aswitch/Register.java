package com.example.smallkun.aswitch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    private String confirm_password;
    public Toolbar register_toolbar;
    private String[] temp;//获取登录之后的信息
    private String result;//返回登录是否成功结果
    private int ok;
    private TextView lr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_toolbar = (Toolbar)findViewById(R.id.register_toolbar);
        lr = (TextView)findViewById(R.id.register_result);
        register_toolbar.setTitle("");
        setSupportActionBar(register_toolbar);
        //为标题栏设置颜色
        register_toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        //显示标题栏左边自带的按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }
        //设置注册界面的返回按钮用于返回到登录业



        //项目设置时，已设置actionbar为空
        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("注册");

        final EditText et1=(EditText)findViewById(R.id.r_account);
        final EditText et2=(EditText)findViewById(R.id.r_apw);
        final EditText et3=(EditText)findViewById(R.id.confirm_password);
        Button button=(Button)findViewById(R.id.r_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = et1.getText().toString();
                pass_word = et2.getText().toString();
                confirm_password = et3.getText().toString();

                if(user_name.equals("")){
                    Toast.makeText(Register.this, "请输入账户！ " , Toast.LENGTH_SHORT).show();
                }else if(pass_word.equals("")) {
                    Toast.makeText(Register.this, "请输入密码！ " , Toast.LENGTH_SHORT).show();
                }else if (confirm_password.equals("")){
                    Toast.makeText(Register.this, "请输入确认密码！ " , Toast.LENGTH_SHORT).show();
                }else if (!confirm_password.equals(pass_word)){
                    Toast.makeText(Register.this, "两次密码请保持一致！ " , Toast.LENGTH_SHORT).show();
                }
                else{
                    new Thread(runnable).start();
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //设置左边菜单项的点击事件
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }



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


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            result = data.getString("response");
            temp = result.split("=|,");
            result = temp[1];
            ok = Integer.parseInt(result.trim());
            if (ok == 201){
                lr.setText("该账号已注册，请重新输入");
            } else {
                Toast.makeText(Register.this, "注册成功",2).show();
                finish();
            }


        }
    };
}

