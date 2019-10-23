package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ParkingHistoryActivity extends AppCompatActivity {

    Button btnRebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_parking_history );

        btnRebook = findViewById ( R.id.btn_rebook );

        btnRebook.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Toast.makeText ( ParkingHistoryActivity.this , "Thanks for booking" , Toast.LENGTH_SHORT ).show ( );
            }
        } );
    }
}
