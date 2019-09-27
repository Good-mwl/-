package com.bw.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bw.base.BaseFragment;
import com.bw.mawenlong20190919.R;

/**
 * author: 马文龙
 * data: 2019/9/21 09:9:51
 * 功能:
 */
public class OneFragment extends BaseFragment {

    private TextView textview;

    @Override
    protected void initData(Bundle savedInstanceState) {
        BroadcastReceiver receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String key = intent.getStringExtra("key");
                textview.setText(key);
            }
        };

        IntentFilter why = new IntentFilter("why");
        getActivity().registerReceiver(receiver,why);

    }

    @Override
    protected void initView(View view) {
        textview = view.findViewById(R.id.text_view);
    }

    @Override
    protected int initLayout() {
        return R.layout.gong_layout;
    }
}
