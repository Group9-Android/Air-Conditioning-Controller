package com.example.smallkun.aswitch;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Profile_o extends Activity implements View.OnClickListener{

    private Button btnDate,btnTime,confirmbtn,closebtn;
    private TextView tvDate,tvTime;
    private Spinner spinner1,spinner2;
    private RadioGroup mode, power;
    private boolean pd1 = false, pd2=false, pd3=true;
    private String date1,time1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_o);
        btnDate = (Button) findViewById(R.id.btnDatePick);
        btnTime = (Button) findViewById(R.id.btnTimePick);
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvTime = (TextView) findViewById(R.id.tv_time);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        spinner1 = (Spinner) findViewById(R.id.wendu);
        spinner2 = (Spinner) findViewById(R.id.fengsu);
        confirmbtn = (Button) findViewById(R.id.confirmbtn);
        mode = (RadioGroup) findViewById(R.id.radioGroup);
        power = (RadioGroup) findViewById(R.id.radioPower);
        power.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                if (checkId == R.id.radioOff){
                    spinner1.setEnabled(false);
                    spinner2.setEnabled(false);
                    mode.setEnabled(false);
                } else {
                    spinner1.setEnabled(true);
                    spinner2.setEnabled(true);
                    mode.setEnabled(true);
                }
            }
        });

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            datas.add(16 + i+"℃");
        }
        //适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,datas);
        spinner1.setAdapter(adapter);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add( i+"档");
        }
        //适配器
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);
        spinner2.setAdapter(adapter1);

        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pd1 && pd2){
                    int selected = mode.getCheckedRadioButtonId();
                    RadioButton b = Profile_o.this.findViewById(selected);

                    if (pd3) {
                        String text = date1+" "+time1+" "+ spinner1.getSelectedItem().toString() + "  " + spinner2.getSelectedItem().toString()+"  "+b.getText().toString();
                        Toast.makeText(Profile_o.this, text, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.putExtra("data_return", text);
                        setResult(RESULT_OK,intent);
                        finish();
                    } else {
                        String text = date1+" "+time1+" "+"close";
                        Toast.makeText(Profile_o.this, text, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.putExtra("data_return", text);
                        setResult(RESULT_OK,intent);
                        finish();
                    }

                } else {
                    Toast.makeText(Profile_o.this, "Please input the Date and Time.", Toast.LENGTH_SHORT).show();
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

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDatePick:
                DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        tvDate.setText(year+" "+month+" "+day);
                        date1 = year+" "+month+" "+day;
                        pd1= true;
                    }
                }, 2018, 2, 14);
                datePicker.show();
                break;
            case R.id.btnTimePick:
                TimePickerDialog time = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
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
}
