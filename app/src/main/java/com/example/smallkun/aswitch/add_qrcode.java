package com.example.smallkun.aswitch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.smallkun.aswitch.util.Constant;
import com.google.zxing.activity.CaptureActivity;

/**
 * Created by vbn on 18/6/18.
 */

public class add_qrcode extends AppCompatActivity {
    private Button qrcode;
    private TextView result;
    private Toolbar qrcode_toolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_qrcode);
        qrcode = (Button)findViewById(R.id.btn_qrcode);
        result = (TextView)findViewById(R.id.txt_result);
        qrcode_toolbar = (Toolbar) findViewById(R.id.qrcode_toolbar);
        qrcode_toolbar.setTitle("");
        setSupportActionBar(qrcode_toolbar);

        //显示标题栏左边自带的按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_back);
        }




        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQrCode();
            }
        });

    }



    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(add_qrcode.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(add_qrcode.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);

            //将扫描出的信息显示出来
            result.setText(scanResult);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(add_qrcode.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
