package com.example.mstarc.lovemoon.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-21.
 */

public class NewTopAdapter extends FragmentPagerAdapter {

    private final String[] titles;
    private Context context;
    private List<Fragment> fragments;
    public NewTopAdapter(List<Fragment> fragments,String[] titles, FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
