package com.example.smallkun.aswitch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by vbn on 18/6/14.
 */

public class Login extends AppCompatActivity {
    private String user_name;
    private String pass_word;
    public Toolbar login_toolbar;
    private String[] temp;//获取登录之后的信息
    private String result;//返回登录是否成功结果
    private int ok;
    private TextView lr;//显示登录结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lr = (TextView)findViewById(R.id.login_result);
        login_toolbar = (Toolbar)findViewById(R.id.login_toolbar);
        login_toolbar.setTitle("");
        setSupportActionBar(login_toolbar);
        //为标题栏设置颜色
        login_toolbar.setBackgroundColor(getResources().getColor(R.color.blue));
        //显示标题栏左边自带的按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }


        //项目配置时，已设置actionbar为空
        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setTitle("登录");

        final EditText et1=(EditText)findViewById(R.id.account);
        final EditText et2=(EditText)findViewById(R.id.apw);

        Button login=(Button)findViewById(R.id.log);
        TextView register=(TextView) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = et1.getText().toString();
                pass_word = et2.getText().toString();
                if(user_name.equals("")){
                    Toast.makeText(Login.this, "请输入账户！ " , Toast.LENGTH_SHORT).show();
                }else if(pass_word.equals("")) {
                    Toast.makeText(Login.this, "请输入密码！ " , Toast.LENGTH_SHORT).show();
                }else{
                    new Thread(runnable).start();
                    //返回处理结果

                }

            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
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


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            result = data.getString("response");

            //提取返回数据中的内容
            temp = result.split("=|,");
            result = temp[1];
            ok = Integer.parseInt(result.trim());//trim方法用于去除当前 String 对象移除所有前导空白字符和尾部空白字符
            if (ok == 100){
                lr.setText("");
                Intent intent = new Intent();
                intent.putExtra("b",user_name);
                setResult(RESULT_OK,intent);
                finish();
            } else {
                lr.setText("帐户名或登录密码不正确，请重新输入");

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
                url = new URL("http://47.106.181.0:8080/air/Login?account="+
                        URLEncoder.encode(user_name, "utf-8")+"&password="+URLEncoder.encode(pass_word, "utf-8"));
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