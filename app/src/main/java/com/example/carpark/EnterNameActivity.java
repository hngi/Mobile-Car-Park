package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.carpark.views.Home;


public class EnterNameActivity extends AppCompatActivity {
    EditText firstName,lastName;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Enter Name");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_enter_name);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        btnContinue = findViewById(R.id.btnContinue);



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContinue();
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
         Intent intent = new Intent(EnterNameActivity.this, Home.class);
         startActivity(intent);
        }
    }
}
