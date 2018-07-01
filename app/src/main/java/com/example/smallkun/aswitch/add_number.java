package com.example.smallkun.aswitch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by vbn on 18/6/18.
 */

public class add_number extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private Toolbar number_toolbar;
    public String text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_number);
        button = (Button)findViewById(R.id.sure);
        editText = (EditText)findViewById(R.id.ID);
        number_toolbar = (Toolbar)findViewById(R.id.number_toolbar);
        number_toolbar.setTitle("");
        setSupportActionBar(number_toolbar);

        //显示标题栏左侧按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }


        //确认按钮点击事件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                text = editText.getText().toString();
                //Toast.makeText(add_number.this,"wu", Toast.LENGTH_SHORT).show();

                //将数据传给上一个activity
                Intent intent = new Intent();
                intent.putExtra("data_return", text);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    //实现标题栏左侧按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
