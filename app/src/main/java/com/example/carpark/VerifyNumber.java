package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class VerifyNumber extends AppCompatActivity {

    Button nxt;
    EditText tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);
        nxt = findViewById(R.id.next1);
        tv = findViewById(R.id.editText2);

        nxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent intToMain = new Intent(VerifyNumber.this, EnterOTP.class);
                startActivity(intToMain);
            }

        });

        tv.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));
    }
}
