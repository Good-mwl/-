package com.bw.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.base.BaseFragment;
import com.bw.mawenlong20190923.R;

/**
 * author: 马文龙
 * data: 2019/9/23 19:19:15
 * 功能:
 */
public class MyFragment extends BaseFragment {

    private TextView textView;

    @Override
    protected void initData(Bundle savedInstanceState) {
        String my = getArguments().getString("my");
        textView.setText(my);

    }

    @Override
    protected void initView(View view) {
        textView = view.findViewById(R.id.text_1);
    }

    @Override
    protected int initLayout() {
        return R.layout.my_fragment;
    }
}
