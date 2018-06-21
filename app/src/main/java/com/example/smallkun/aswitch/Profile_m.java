package com.example.smallkun.aswitch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Profile_m extends AppCompatActivity implements OnClickListener{

    private Button btnDate,btnTime,confirmbtn,closebtn;
    private TextView tvDate;
    private TextView tvTime;
    private Spinner spinner1,spinner2;
    private RadioGroup mode, power;
    private boolean pd1 = true, pd2=false, pd3 =true;
    private String date1,time1;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        //btnDate = (Button) findViewById(R.id.btnDatePick);
        btnTime = (Button) findViewById(R.id.btnTimePick);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
        //btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        spinner1 = (Spinner) findViewById(R.id.wendu);
        spinner2 = (Spinner) findViewById(R.id.fengsu);
        confirmbtn = (Button) findViewById(R.id.confirmbtn);
        mode = (RadioGroup) findViewById(R.id.radioGroup);
        power = (RadioGroup) findViewById(R.id.radioPower);

        //final SharedPreferences.Editor editor =  getSharedPreferences("data", MODE_PRIVATE).edit();
        //final SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);

        power.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                if (checkId == R.id.radioOff){
                    pd3=false;
                    spinner1.setEnabled(false);
                    spinner2.setEnabled(false);
                    mode.setEnabled(false);
                } else {
                    pd3=true;
                    spinner1.setEnabled(true);
                    spinner2.setEnabled(true);
                    mode.setEnabled(true);
                }
            }
        });

        List<String> datas = new ArrayList<>();
        for (int i = 4; i < 15; i++) {
            datas.add(16 + i+"℃");
        }
        //适配器
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datas);
        spinner1.setAdapter(adapter);



        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add( i+"档");
        }
        //适配器
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);
        spinner2.setAdapter(adapter1);

        //加载存储的数据
        //loadData();
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        spinner1.setSelection(pref.getInt("WD",20)-20);
        spinner2.setSelection(pref.getInt("FS",0));
        power.check(pref.getInt("PW",0));
        mode.check(pref.getInt("MS",0));
        pd2 = pref.getBoolean("pdTime",false);
        if (pd2) {
            time1 = pref.getString("Time","");
            tvTime.setText(time1);}

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pd1 && pd2){
                    int selected = mode.getCheckedRadioButtonId();
                    RadioButton b = Profile_m.this.findViewById(selected);
                    if (pd3) {
                        String text = time1+" "+ spinner1.getSelectedItem().toString() + "  " + spinner2.getSelectedItem().toString()+"  "+b.getText().toString();
                        Toast.makeText(Profile_m.this, text, Toast.LENGTH_SHORT).show();

                        //存储数据
                        int pos =spinner1.getSelectedItemPosition()+20;
                        int pos1 = spinner2.getSelectedItemPosition();
                        int pos2 = mode.getCheckedRadioButtonId();
                        int pos3 = power.getCheckedRadioButtonId();
                        saveData(time1,pos,pos1,pos2,pos3);


                        //将数据传给上一个activity
                        Intent intent = new Intent();
                        intent.putExtra("data_return", text);
                        setResult(RESULT_OK,intent);
                        finish();
                    } else {
                        String text = time1+" "+"close";
                        Toast.makeText(Profile_m.this, text, Toast.LENGTH_SHORT).show();

                        //存储数据
                        int pos =spinner1.getSelectedItemPosition()+20;
                        int pos1 = spinner2.getSelectedItemPosition();
                        int pos2 = mode.getCheckedRadioButtonId();
                        int pos3 = power.getCheckedRadioButtonId();
                        saveData(time1,pos,pos1,pos2,pos3);

                        //将数据传给上一个activity
                        Intent intent = new Intent();
                        intent.putExtra("data_return", text);
                        setResult(RESULT_OK,intent);
                        finish();
                    }

                } else {
                    Toast.makeText(Profile_m.this, "Please input the Date and Time.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        closebtn = (Button) findViewById(R.id.closebtn);
        closebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.putExtra("data_return", "关闭该模式");
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });



    }

//    public void loadData(){
//        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
//        spinner1.setSelection(pref.getInt("WD",20)-20);
//        spinner2.setSelection(pref.getInt("FS",0));
//        power.check(pref.getInt("PW",0));
//        mode.check(pref.getInt("MS",0));
//    }


    public void saveData(String time1, int pos, int pos1, int pos2, int pos3){
        SharedPreferences.Editor editor =  getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString("Time",time1);
        editor.putBoolean("pdTime",pd2);
        editor.putInt("WD",pos);
        editor.putInt("FS",pos1);
        editor.putInt("MS",pos2);
        editor.putInt("PW",pos3);
        editor.apply();
    }

    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnDatePick:
//                DatePickerDialog datePicker = new DatePickerDialog(this, new OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int day) {
//                        tvDate.setText(year+" "+month+" "+day);
//                        date1 = year+" "+month+" "+day;
//                        pd1= true;
//                    }
//                }, 2018, 2, 12);
//                datePicker.show();
//                break;
            case R.id.btnTimePick:
                TimePickerDialog time = new TimePickerDialog(this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvTime.setText(hour+":"+minute);
                        time1 = hour+":"+minute;
                        pd2= true;
                    }
                }, 18, 25, true);
                time.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("data_return", "返回");
        setResult(RESULT_CANCELED,intent);
        finish();
    }

}
