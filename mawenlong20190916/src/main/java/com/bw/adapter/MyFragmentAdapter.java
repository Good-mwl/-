package com.bw.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/16 09:9:24
 * 功能:
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<String> list1;
    private List<Fragment> list2;

    public MyFragmentAdapter(FragmentManager fm, List<String> list1, List<Fragment> list2) {
        super(fm);
        this.list1 = list1;
        this.list2 = list2;
    }

    @Override
    public Fragment getItem(int i) {
        return list2.get(i);
    }

    @Override
    public int getCount() {
        return list2.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list1.get(position);
    }
}

