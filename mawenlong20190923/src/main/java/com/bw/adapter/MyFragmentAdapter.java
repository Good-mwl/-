package com.bw.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/23 20:20:26
 * 功能:
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<String> tlist;
    private List<Fragment> flist;

    public MyFragmentAdapter(FragmentManager fm, List<String> tlist, List<Fragment> flist) {
        super(fm);
        this.tlist = tlist;
        this.flist = flist;
    }

    @Override
    public Fragment getItem(int i) {
        return flist.get(i);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tlist.get(position);
    }
}

