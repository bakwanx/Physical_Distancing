package com.example.physicaldistancing.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.physicaldistancing.fragment.NewsFragment;
import com.example.physicaldistancing.fragment.ScannerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int number_tabs;
    private List<String> lstTitle = new ArrayList<>();
    private List<String> lstFragment = new ArrayList<>();

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.number_tabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ScannerFragment();
            case 1:
                return new NewsFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return number_tabs;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return lstTitle.get(position);
    }

    public void AddFragment (String title){
        lstTitle.add(title);
    }
}
