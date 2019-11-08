package com.carpark.views.homefragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carpark.R;
import com.carpark.adapter.SectionsAdapter;
import com.google.android.material.tabs.TabLayout;

public class MapListFragment extends Fragment {

    private SectionsAdapter mSectionsAdapter;
    private TabLayout mTabLayout;
    private ViewPager mViewpager;
    View root;

    public MapListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_map_list, container, false);
        // Inflate the layout for this fragment
        mSectionsAdapter = new SectionsAdapter(getFragmentManager());
        mViewpager = root.findViewById(R.id.viewPager);
        mTabLayout = root.findViewById(R.id.map_list);
        mViewpager.setAdapter(mSectionsAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        mViewpager.setCurrentItem(0);
        return root;
    }

}
