package com.bw.mawenlong20190905;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.adapter.MyAdapter;
import com.bw.async.MyDataTask;
import com.bw.bean.News;
import com.bw.uti.HttpUtil;
import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView xList;
    private  int i = 1;
    private String url= "http://blog.zhaoliang5156.cn/api/news/news.json?page="+i+"&&count=10";

    private List<News.ResultBean.DataBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //创建适配器
        myAdapter = new MyAdapter(list, MainActivity.this);
        xList.setAdapter(myAdapter);
        //设置上拉下拉
        xList.setPullRefreshEnable(true);
        xList.setPullLoadEnable(true);
        xList.setXListViewListener(this);
        //判断网络
        HttpUtil util = HttpUtil.getInstance();
        if (util.getNetWork(MainActivity.this)){
            initData();
        }else {
            Toast.makeText(MainActivity.this,"请检查网络设置",Toast.LENGTH_SHORT).show();
        }
    }

    private void initData() {
        Log.i("aaa", "initData: "+url);
        //有网
        new MyDataTask(new MyDataTask.CallBack() {
            @Override
            public void getJson(String str) {
                Log.i("aaa", "getJson: "+str);
                //解析
                Gson gson = new Gson();
                News news = gson.fromJson(str, News.class);
                List<News.ResultBean.DataBean> data = news.getResult().getData();
                //添加到大集合数据
                list.addAll(data);
                myAdapter.notifyDataSetChanged();
                //刷新完成，关闭Ui效果
                onLoad();
            }
        }).execute(url);
    }

    private void onLoad() {
        xList.setRefreshTime("刚刚刷新");
        xList.stopLoadMore();
        xList.stopRefresh();
    }

    private void initView() {
        xList = findViewById(R.id.xListView);
    }
    //刷新
    @Override
    public void onRefresh() {
        list.clear();
        initData();
    }
    //加载
    @Override
    public void onLoadMore() {
        i++;
        initData();
    }
}
