package com.bw.mawenlong20190923;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.adapter.MyFragmentAdapter;
import com.bw.base.BaseActivity;
import com.bw.fragment.FaFragment;
import com.bw.fragment.MyFragment;
import com.bw.fragment.ShouFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private ViewPager view_pager;
    private TabLayout tab_layouut;
    private List<String> tlist = new ArrayList<>();
    private List<Fragment> flist = new ArrayList<>();

    @Override
    protected void initData() {
        tlist.add("首页");
        tlist.add("发现");
        tlist.add("我的");
        //传值
        ShouFragment shouFragment = new ShouFragment();
        FaFragment faFragment = new FaFragment();
        MyFragment myFragment = new MyFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("my","我的");
        myFragment.setArguments(bundle2);
        flist.add(shouFragment);
        flist.add(faFragment);
        flist.add(myFragment);
        //创建适配器
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), tlist, flist);
        view_pager.setAdapter(myFragmentAdapter);
        tab_layouut.setupWithViewPager(view_pager);

    }

    @Override
    protected void initView() {
        view_pager = findViewById(R.id.view_pager);
        tab_layouut = findViewById(R.id.tab_layout);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}

