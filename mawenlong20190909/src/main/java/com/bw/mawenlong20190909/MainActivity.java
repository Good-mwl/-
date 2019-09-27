package com.bw.mawenlong20190909;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.adapter.MyAdapter;
import com.bw.async.MyTask;
import com.bw.bean.Shop;
import com.bw.util.NetUtil;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    private int p = 1;
    private String urlText = "http://blog.zhaoliang5156.cn/api/shop/fulishe"+p+".json";
    private List<Shop.DataBean> list = new ArrayList<>();
    private XListView xListView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xListView = findViewById(R.id.X_List_View);
        myAdapter = new MyAdapter(MainActivity.this, list);
        //设置适配器
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                list.clear();
                p=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                p++;
                initData();
            }
        });
        xListView.setAdapter(myAdapter);
        listSet();
        if (NetUtil.getInstance().getNet(MainActivity.this)){
            initData();
        }else {
            Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
        }
    }

    private void listSet() {
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
    }

    private void initData() {
        new MyTask(new MyTask.MyCallBack() {
            @Override
            public void getJson(String json) {
                Log.i("aaa", "getJson: "+json);
                Gson gson = new Gson();
                Shop shop = gson.fromJson(json, Shop.class);
                List<Shop.DataBean> data = shop.getData();
                list.addAll(data);
                myAdapter.notifyDataSetChanged();
                //刷新适配器
                laod();
            }
        }).execute(urlText);
    }

    private void laod() {
        xListView.setRefreshTime("刚刚");
        xListView.stopRefresh();
        xListView.stopLoadMore();
    }}
