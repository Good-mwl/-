package com.bw.mawenlong20190919;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.adapter.MyFragmentAdapter;
import com.bw.base.BaseActivity;
import com.bw.fragment.FiveFragment;
import com.bw.fragment.FourFragment;
import com.bw.fragment.OneFragment;
import com.bw.fragment.ShowFragment;
import com.bw.fragment.ThreeFragment;
import com.bw.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> flist = new ArrayList<>();
    private List<String> slist = new ArrayList<>();

    @Override
    protected void initData() {
        initFragment();
        initTab();
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), flist, slist));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                Intent intent = new Intent();
                intent.setAction("why");
                intent.putExtra("key",slist.get(i));
                //发送广播
                sendBroadcast(intent);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initTab() {
        slist.add("推荐");
        slist.add("热点");
        slist.add("最新");
        slist.add("北京");
        slist.add("动漫");
        slist.add("音乐");
    }

    private void initFragment() {
        flist.add(new ShowFragment());
        flist.add(new OneFragment());
        flist.add(new TwoFragment());
        flist.add(new ThreeFragment());
        flist.add(new FourFragment());
        flist.add(new FiveFragment());
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}
