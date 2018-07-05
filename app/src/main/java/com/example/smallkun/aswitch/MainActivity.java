package com.example.smallkun.aswitch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;


import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import de.hdodenhof.circleimageview.CircleImageView;


public  class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    public BottomNavigationBar mBottomNavigationBar;
    private Fragment mFragmentOne;
    private TwoFragment mFragmentTwo;
    private ThreeFragment mFragmentThree;
    private FourFragment mFragmentFour;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView textView;
    private TextView username;//隐藏菜单内菜单头显示信息1
    private TextView mail;//隐藏菜单内菜单头显示信息2
    private static boolean enableNightMode = false;
    public String user_name;
    public int okres=200;
    public boolean onLogin=false;

    Bundle bundle = new Bundle();

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //设置左边菜单项的点击事件
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.add:
                Intent intent = new Intent(this, Main_weather.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String usern = data.getStringExtra("username");
                    okres = data.getIntExtra("rst",101);
                    mail = (TextView)findViewById(R.id.mail);
                    username = (TextView)findViewById(R.id.username);
                    mail.setText("已登录");
                    username.setText("用户名："+usern);
                    onLogin = true;
                    user_name = usern;

                    bundle.putInt("LGState",okres);
                    bundle.putBoolean("ONLOGIN",onLogin);
                    bundle.putString("USERNAME",user_name);

                    //这里的values就是我们要传的值
                    //mail.setText(mail_text);
                    //username.setText(username_text);
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        textView = (TextView)findViewById(R.id.title);
        mail = (TextView)findViewById(R.id.mail);


        //设置标题加粗
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);

        //给toolbar标题栏设置颜色
        toolbar.setBackgroundColor(getResources().getColor(R.color.blue));

        //为了显示隐藏菜单nav_menu的item图标，传入一个null参数
        navigationView.setItemIconTintList(null);
        //设置隐藏菜单nav_menu默认选择的item
        navigationView.setCheckedItem(R.id.account);

        //隐藏菜单nav_menu列表的点击事件

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mail = (TextView) findViewById(R.id.mail);
                username =(TextView)findViewById(R.id.username);
                switch (item.getItemId()) {
                    case R.id.account:
                        if (mail.getText().equals("尚未登录")) {
                            Intent intent = new Intent(MainActivity.this, Login.class);
                            startActivityForResult(intent, 1);

                        } else {
                            Toast.makeText(MainActivity.this, "已成功退出", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.exit:
                        if (mail.getText().equals("尚未登录")){
                            onLogin = false;
                            bundle.putBoolean("ONLOGIN",onLogin);
                            Toast.makeText(MainActivity.this, "尚未登录",Toast.LENGTH_LONG).show();

                        } else {
                            username.setText("");
                            mail.setText("尚未登录");
                            onLogin = false;
                            bundle.putBoolean("ONLOGIN",onLogin);
                            Toast.makeText(MainActivity.this, "已退出",Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.day:
                        modeDay(getWindow().getDecorView());
                        Toast.makeText(MainActivity.this, "已开启日间模式",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.night:
                        modeNight(getWindow().getDecorView());
                        Toast.makeText(MainActivity.this, "已开启夜间模式",Toast.LENGTH_SHORT).show();
                        break;

                }
                return true;
            }
        });




        //给navigationview的头部要添加点击事件，首先要获取头部控件
        View headerView = navigationView.getHeaderView(0);




        //左边的菜单项的显示
         ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }


      //  设置底部导航栏显示模式
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

      //设置底部导航栏颜色
        mBottomNavigationBar.setBarBackgroundColor(R.color.blue);//set background color for navigation bar
        mBottomNavigationBar.setInActiveColor(R.color.white);//unSelected icon color
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.设备控制).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.情景模式).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.设备添加).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.操作记录).setActiveColorResource(R.color.orange))
                .setFirstSelectedPosition(0)
                .initialise();
        mBottomNavigationBar.setTabSelectedListener(MainActivity.this);
        setDefaultFragment();

        if(!enableNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


    }

    public void modeDay(View v) {
        setEnableNightMode(false);
    }

    public void modeNight(View v) {
        setEnableNightMode(true);
    }



    public void setEnableNightMode(boolean enableNightMode) {
        this.enableNightMode = enableNightMode;
        if(enableNightMode) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }






    //设置活动切页默认打开页面，后续也要根据服务器返回数据来判断设备是否开启来选择不同碎片
    private void setDefaultFragment() {

        if (okres == 101){
            Toast.makeText(MainActivity.this, "您尚未绑定空调！请绑定空调", Toast.LENGTH_LONG).show();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragmentOne = new OneFragment();
            mFragmentOne.setArguments(bundle);
            transaction.replace(R.id.ll_content, mFragmentOne).commit();
        } else if (okres == 100){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragmentOne = new OneFragment();
            mFragmentOne.setArguments(bundle);
            transaction.replace(R.id.ll_content, mFragmentOne).commit();
        } else if (okres == 200) {
            Toast.makeText(MainActivity.this, "您尚未登录！请登录", Toast.LENGTH_LONG).show();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            mFragmentOne = new Start();
            mFragmentOne.setArguments(bundle);
            transaction.replace(R.id.ll_content, mFragmentOne).commit();
        }

    }

    //滑动切页的点击事件
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                //后续需要根据服务器返回数据判断设备是否开启来选择不同碎片
//                if (mFragmentOne == null) {
//                    mFragmentOne = new Start();
//                };
//                mFragmentOne.setArguments(bundle);
//                transaction.replace(R.id.ll_content, mFragmentOne);
                //mFragmentOne.setArguments(bundle);
                if (okres == 101){
                    Toast.makeText(MainActivity.this, "您尚未绑定空调！请绑定空调", Toast.LENGTH_LONG).show();
                    mFragmentOne = new OneFragment();
                    mFragmentOne.setArguments(bundle);
                    transaction.replace(R.id.ll_content, mFragmentOne);
                } else if (okres == 100){
                    mFragmentOne = new OneFragment();
                    mFragmentOne.setArguments(bundle);
                    transaction.replace(R.id.ll_content, mFragmentOne);
                } else if (okres == 200) {
                    Toast.makeText(MainActivity.this, "您尚未登录！请登录", Toast.LENGTH_LONG).show();
                    mFragmentOne = new Start();
                    mFragmentOne.setArguments(bundle);
                    transaction.replace(R.id.ll_content, mFragmentOne);
                }
                break;
            case 1:
                mFragmentTwo = new TwoFragment();
                mFragmentTwo.setArguments(bundle);
                transaction.replace(R.id.ll_content, mFragmentTwo);
                break;
            case 2:
                mFragmentThree = new ThreeFragment();
                mFragmentThree.setArguments(bundle);
                transaction.replace(R.id.ll_content, mFragmentThree);
                break;
            case 3:
                if (mFragmentFour == null) {
                    mFragmentFour = new FourFragment();
                }
                transaction.replace(R.id.ll_content, mFragmentFour);
                break;

        }
        transaction.commit();

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    public String getusername(){
        return this.user_name;
    }
}