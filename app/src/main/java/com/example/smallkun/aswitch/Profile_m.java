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
import android.os.Bundle;
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
import android.support.v7.app.AppCompatActivity;
import android.widget.TimePicker;
import android.widget.Toast;

public class Profile_m extends Activity implements OnClickListener{

    private Button btnDate,btnTime,confirmbtn;
    private TextView tvDate,tvTime;
    private Spinner spinner1,spinner2;
    private RadioGroup mode, power;
    private boolean pd1 = false, pd2=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
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
                    RadioButton b = Profile_m.this.findViewById(selected);

                    String text = spinner1.getSelectedItem().toString() + "  " + spinner2.getSelectedItem().toString()+"  "+b.getText().toString();
                    Toast.makeText(Profile_m.this, text, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Profile_m.this, "Please input the Date and Time.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDatePick:
                DatePickerDialog datePicker = new DatePickerDialog(this, new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        tvDate.setText(year+" "+month+" "+day);
                        pd1= true;
                    }
                }, 2018, 2, 12);
                datePicker.show();
                break;
            case R.id.btnTimePick:
                TimePickerDialog time = new TimePickerDialog(this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvTime.setText(hour+":"+minute);
                        pd2= true;
                    }
                }, 18, 25, true);
                time.show();
                break;
        }
    }

}
