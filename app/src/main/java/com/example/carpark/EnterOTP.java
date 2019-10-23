package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterOTP extends AppCompatActivity {

    EditText otp1,otp2,otp3,otp4;
    TextView receiveNumber;
    ImageView backToVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        otp1 = findViewById(R.id.edit_otp1);
        otp2 = findViewById(R.id.edit_otp2);
        otp3 = findViewById(R.id.edit_otp3);
        otp4 = findViewById(R.id.edit_otp4);
        receiveNumber = findViewById(R.id.display_number);
        backToVerify = findViewById(R.id.back_verify_num);

        //arrow back click to return to previous activity
        backToVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backVerify = new Intent(EnterOTP.this, VerifyNumber.class);
                startActivity(backVerify);
            }
        });

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp2.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp1.clearFocus();
                }
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp3.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp1.requestFocus();
                }
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp4.requestFocus();
                }
                else if(s.length()==0)
                {
                    otp2.requestFocus();
                }
            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    otp4.clearFocus();
                }
                else if(s.length()==0)
                {
                    otp3.requestFocus();
                }

            }
        });
    }
}
