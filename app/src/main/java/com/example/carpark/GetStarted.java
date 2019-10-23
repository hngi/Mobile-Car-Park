package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetStarted extends AppCompatActivity {

    EditText number;
    Button fbbtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        final Button fbbtn = (Button) findViewById(R.id.fb_btn);
        final EditText number = (EditText) findViewById(R.id.number);


        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                intent = new Intent(getApplicationContext(),VerifyNumber.class);
                intent.putExtra("EdiTtEXTvALUE", number.getText().toString());
                startActivity(intent);
                return false;


            }



        });


    }}

