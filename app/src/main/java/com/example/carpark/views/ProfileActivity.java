package com.example.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carpark.R;
import com.example.carpark.views.homefragments.MyVehicleFragment;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Created by Utibe Etim
 * etim.utibe@gmail.com
 * 23 Oct, 2019
 */
public class ProfileActivity extends BaseActivity {

    private TextInputLayout fname_edt, lname_edt, phone_edt, email_edt;
    private Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView() {
        fname_edt = findViewById(R.id.profile_fname_edtv);
        lname_edt = findViewById(R.id.profile_lname_edtv);
        phone_edt = findViewById(R.id.profile_phone_edtv);
        email_edt = findViewById(R.id.profile_email_edtv);
        save_btn = findViewById(R.id.profile_save_btn);

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, VehicleActivity.class);
                showToast("Your information has been saved successfully");
                startActivity(intent);
            }
        });
    }
}
