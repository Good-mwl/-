package com.bw.fragment;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.bw.base.BaseFragment;
import com.bw.mawenlong20190910.R;

/**
 * author: 马文龙
 * data: 2019/9/10 17:17:20
 * 功能:
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private Button btn_open,btn_close;
    private ListView list_view;
    private DrawerLayout drawer;

    @Override
    protected void initView(View view) {
        btn_open = view.findViewById(R.id.btn_open);
        btn_close = view.findViewById(R.id.btn_close);
        list_view = view.findViewById(R.id.list_view);
        drawer = view.findViewById(R.id.drawer);



    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        btn_close.setOnClickListener(this);
        btn_open.setOnClickListener(this);
        String data[] = {"马文龙","马文龙","马文龙","马文龙"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        list_view.setAdapter(arrayAdapter);

    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_mian;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_open:
                drawer.openDrawer(Gravity.LEFT);
                break;
            case  R.id.btn_close:
                drawer.closeDrawer(Gravity.LEFT);
                break;
        }

    }
}
