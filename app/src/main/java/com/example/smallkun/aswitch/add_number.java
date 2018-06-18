package com.example.smallkun.aswitch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vbn on 18/6/18.
 */

public class add_number extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_number);
        Button button = (Button)findViewById(R.id.sure);
        final EditText editText = (EditText)findViewById(R.id.ID);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("good job");
            }
        });
    }
}
