package com.bw.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/21 09:9:58
 * 功能:
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fList;
    private List<String> sList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fList, List<String> sList) {
        super(fm);
        this.fList = fList;
        this.sList = sList;
    }

    @Override
    public Fragment getItem(int i) {
        return fList.get(i);
    }

    @Override
    public int getCount() {
        return fList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return sList.get(position);
    }
}
