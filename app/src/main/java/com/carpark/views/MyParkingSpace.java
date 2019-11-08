package com.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.carpark.R;
import com.carpark.adapter.MySpaceAdapter;
import com.carpark.adapter.SectionsAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyParkingSpace extends AppCompatActivity {

    private MySpaceAdapter mSectionsAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_parking_space);

        getSupportActionBar().setTitle("My Parking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsAdapter = new MySpaceAdapter(getSupportFragmentManager());
        mViewpager = findViewById(R.id.viewPager1);
        mTabLayout = findViewById(R.id.current_history);
        mViewpager.setAdapter(mSectionsAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);

    }
}
