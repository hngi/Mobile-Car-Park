package com.carpark.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.carpark.views.homefragments.ListFragment;
import com.carpark.views.homefragments.MapFragment;
import com.carpark.views.homefragments.MyVehicleFragment;

public class SectionsAdapter extends FragmentPagerAdapter {

    public SectionsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MapFragment map = new MapFragment();
                return map;
            case 1:
                ListFragment list = new ListFragment();
                return list;
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
                return "MAP";
            case 1:
                return "LIST";
            default:
                return null;
        }
    }

}
