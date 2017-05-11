package com.example.mstarc.lovemoon.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.mstarc.lovemoon.adapter.NewTopAdapter;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.fragment.NewTopFragment;

import java.util.ArrayList;
import java.util.List;

public class NewTopActivity extends BaseActivity {
    private String[] titles = new String[]{"推荐", "社会", "国内", "国际","娱乐", "体育", "军事", "科技","财经", "时尚"};


    @Override
    public int contentView() {
        return R.layout.activity_new_top;
    }

    @Override
    public void initView() {

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Fragment fragment = new NewTopFragment();
            Bundle bundle = new Bundle();
            bundle.putString("text",titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new NewTopAdapter(fragments, titles, getSupportFragmentManager(), this));
        TabLayout tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.setupWithViewPager(viewPager);
        tablayout.setTabTextColors(getResources().getColor(R.color.dark_white), Color.WHITE);

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }
}
