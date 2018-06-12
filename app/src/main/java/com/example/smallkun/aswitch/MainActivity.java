package com.example.smallkun.aswitch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

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
    public boolean is_open=false;





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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.nav_view);

        //为了显示隐藏菜单nav_menu的item图标，传入一个null参数
        navigationView.setItemIconTintList(null);
        //设置隐藏菜单nav_menu默认选择的item
        navigationView.setCheckedItem(R.id.zero);
        //隐藏菜单nav_menu列表的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //给navigationview的nav_header的头像添加点击事件
        CircleImageView circleImageView = (CircleImageView)findViewById(R.id.icon_image);


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
        mBottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_one, R.string.设备控制).setActiveColorResource(R.color.green))
                .addItem(new BottomNavigationItem(R.drawable.icon_two, R.string.情景模式).setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.icon_three, R.string.设备添加).setActiveColorResource(R.color.lime))
                .addItem(new BottomNavigationItem(R.drawable.icon_four, R.string.用户信息))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(MainActivity.this);
        setDefaultFragment();
    }


    //设置活动切页默认打开页面，后续也要根据服务器返回数据来判断设备是否开启来选择不同碎片
    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mFragmentOne = new Start();
        transaction.replace(R.id.ll_content, mFragmentOne).commit();
    }

    //滑动切页的点击事件
    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                //后续需要根据服务器返回数据判断设备是否开启来选择不同碎片
                if (mFragmentOne == null) {
                    mFragmentOne = new TwoFragment();
                };
                transaction.replace(R.id.ll_content, mFragmentOne);
                break;
            case 1:
                if (mFragmentTwo == null) {
                    mFragmentTwo = new TwoFragment();
                }
                transaction.replace(R.id.ll_content, mFragmentTwo);
                break;
            case 2:
                if (mFragmentThree == null) {
                    mFragmentThree = new ThreeFragment();
                }
                transaction.replace(R.id.ll_content, mFragmentThree);
                break;
            case 3:
                if (mFragmentFour == null) {
                    mFragmentFour = new FourFragment();
                }
                transaction.replace(R.id.ll_content, mFragmentFour);
                break;
            default:
                if (mFragmentOne == null) {
                    mFragmentOne = new Start();
                }
                transaction.replace(R.id.ll_content, mFragmentOne);
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
}