package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.carpark.R;


public class EnterNameActivity extends AppCompatActivity {
    EditText firstName,lastName;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        firstName = findViewById(R.id.firstName);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        lastName = findViewById(R.id.lastName);
        btnContinue = findViewById(R.id.btnContinue);

        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContinue();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EnterNameActivity.this, EnterOTP.class);
                startActivity(i);
            }
        });

    }



    public void editContinue(){
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();


        if (firstName.isEmpty()) {
            this.firstName.setError("Please enter your first name");
        } else if (lastName.isEmpty()) {
            this.lastName.setError("Please enter your last name");
        }else{
         Intent intent = new Intent(EnterNameActivity.this, HomeActivity.class);
         startActivity(intent);
        }
    }
}
