package com.carpark.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.carpark.views.homefragments.CurrentSpaceFragment;
import com.carpark.views.homefragments.ListFragment;
import com.carpark.views.homefragments.MapFragment;
import com.carpark.views.homefragments.ParkingHistoryFragment;

public class MySpaceAdapter extends FragmentPagerAdapter {

    public MySpaceAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CurrentSpaceFragment current = new CurrentSpaceFragment();
                return current;
            case 1:
                ParkingHistoryFragment history = new ParkingHistoryFragment();
                return history;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Current";
            case 1:
                return "History";
            default:
                return null;
        }
    }

}
