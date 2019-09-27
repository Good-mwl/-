package com.bw.mawenlong20190910;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.base.BaseActivity;
import com.bw.fragment.MyFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new MyFragment()).commit();

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }
}
