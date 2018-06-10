package com.example.smallkun.aswitch;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public  class MainActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;



    MenuItem M1;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        M1=menu.findItem(R.id.add);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
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
        initViews();


    }

    private void initViews() {

        mTablayout= (TabLayout) findViewById(R.id.tabLayout);
        mViewPager= (ViewPager) findViewById(R.id.viewPager);

        mViewPager.setAdapter(
                new FragmentPagerAdapter(getSupportFragmentManager()) {

                    private String[] mTitles = new String[]{"设备控制", "情景设置","设备添加","用户信息"};

                    @Override
                    public Fragment getItem(int position) {
                        if (position == 1) {
                            return new TwoFragment();
                        }


                        if(position == 2){
                            return new ThreeFragment();
                        }
                        if(position == 3){
                            return new FourFragment();
                        }
                        return new OneFragment();
                    }

                    @Override
                    public int getCount() {
                        return mTitles.length;
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return mTitles[position];
                    }

                });

        mTablayout.setupWithViewPager(mViewPager);

        one = mTablayout.getTabAt(0);
        two = mTablayout.getTabAt(1);
        three = mTablayout.getTabAt(2);
        four = mTablayout.getTabAt( 3);

    }
}
