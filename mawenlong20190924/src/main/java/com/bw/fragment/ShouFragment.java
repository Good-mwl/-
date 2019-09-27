package com.bw.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bw.adapter.MyAdapter;
import com.bw.async.MyAsync;
import com.bw.base.BaseFragment;
import com.bw.bean.News;
import com.bw.mawenlong20190924.Main3Activity;
import com.bw.mawenlong20190924.R;
import com.bw.util.HttpUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/24 15:15:08
 * 功能:
 */
public class ShouFragment extends BaseFragment {

    private PullToRefreshListView plist_view;
    private String url1 = "http://blog.zhaoliang5156.cn/api/news/news_month_a";
    private int i = 1;
    private String url2 = ".json";
    private List<News.ResultBean.NewsBean> list = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        //判断网络
        if (HttpUtil.getInstence().getConn(getActivity())){
            //有网
            //请求数据
            RequestData(i);
            //创建适配器
            myAdapter = new MyAdapter(list,getActivity());
            plist_view.setAdapter(myAdapter);
            plist_view.setMode(PullToRefreshBase.Mode.BOTH);
            //分页加载
            plist_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    list.clear();
                    RequestData(i);
                    plist_view.onRefreshComplete();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    i++;
                    RequestData(i);
                    plist_view.onRefreshComplete();
                }
            });
            //点击跳转详请页面
            plist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String url = list.get(position+2).getUrl();
                    Intent intent = new Intent(getActivity(), Main3Activity.class);
                    intent.putExtra("url",url);
                    startActivity(intent);
                }
            });


        }else{
            //无网
            Toast.makeText(getActivity(), "当前手机没有网，请检测手机网络", Toast.LENGTH_SHORT).show();
        }
    }

    private void RequestData(int page) {
        new MyAsync(new MyAsync.MyDataBack() {
            @Override
            public void dataBack(String s) {
                Log.i("bbb", "dataBack: "+s);
                Gson gson =new Gson();
                News news = gson.fromJson(s, News.class);
                List<News.ResultBean.NewsBean> newsList = news.getResult().getNews();
                list.addAll(newsList);
            }
        }).execute(url1+page+url2);
    }

    @Override
    protected void initView(View view) {
        //初始化控件
        plist_view = view.findViewById(R.id.plist_view);
    }

    @Override
    protected int initLayout() {
        return R.layout.shou_fragment;
    }
}
