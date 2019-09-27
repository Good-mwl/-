package com.bw.mawenlong20190916;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.adapter.MyFragmentAdapter;
import com.bw.base.BaseActivity;
import com.bw.fragment.CangFrafment;
import com.bw.fragment.DianFrafment;
import com.bw.fragment.FirstFrafment;
import com.bw.fragment.MuFrafment;
import com.bw.fragment.MyFrafment;
import com.bw.fragment.ShiFrafment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ViewPager view_pager;
    private TabLayout tab_layout;
    private List<String> tList = new ArrayList<>();
    private List<Fragment> fList = new ArrayList<>();
    private MyFragmentAdapter adapter;

    @Override
    protected void initData() {
        initTab();
        initFragment();
        //创建适配器
        adapter = new MyFragmentAdapter(getSupportFragmentManager(), tList, fList);
        view_pager.setAdapter(adapter);
        //关联
        tab_layout.setupWithViewPager(view_pager);
    }

    private void initFragment() {
        fList.add(new FirstFrafment());
        fList.add(new CangFrafment());
        fList.add(new MyFrafment());
        fList.add(new DianFrafment());
        fList.add(new MuFrafment());
        fList.add(new ShiFrafment());
    }

    private void initTab() {
        tList.add("首页");
        tList.add("百货");
        tList.add("男装");
        tList.add("电器");
        tList.add("母婴");
        tList.add("食品");
    }

    @Override
    protected void initView() {
        view_pager = findViewById(R.id.view_pager);
        tab_layout = findViewById(R.id.tab_layout);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}
