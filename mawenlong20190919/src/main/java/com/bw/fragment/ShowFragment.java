package com.bw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.adapter.MyAdapter;
import com.bw.base.BaseFragment;
import com.bw.bean.User;
import com.bw.mawenlong20190919.R;
import com.bw.mawenlong20190919.ShowActivity;
import com.bw.task.JsonTask;
import com.bw.util.Util;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/21 09:9:51
 * 功能:
 */
public class ShowFragment extends BaseFragment {

    private PullToRefreshListView pListView;
    private String url1 = "http://blog.zhaoliang5156.cn/api/month/monthapi";
    private int p = 1;
    private String url2 = ".json";
    private List<User.ResultBean.NewsBean> list = new ArrayList<>();
    private MyAdapter adapter;
    private Banner banner;

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (Util.getHttp().getNetState(getActivity())) {
            //判断是否有网
            adapter = new MyAdapter(getActivity(), list);
            pListView.setAdapter(adapter);
            requestData(1);
            setListener();
        } else {
            Toast.makeText(getActivity(), "请检查网络~", Toast.LENGTH_LONG).show();
        }
    }

    private void setListener() {
        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                intent.putExtra("url", list.get(Integer.parseInt(id+"")).getUrl());
                startActivity(intent);
            }
        });
    }

    private void requestData(int p) {
        new JsonTask(new JsonTask.MyCallback() {
            @Override
            public void getJson(String json) {
                Gson gson = new Gson();
                User user = gson.fromJson(json, User.class);
                initBanner(user.getResult().getBanner());
                List<User.ResultBean.NewsBean> news = user.getResult().getNews();
                list.addAll(news);
                adapter.notifyDataSetChanged();
                pListView.onRefreshComplete();
            }
        }).execute(url1 + p + url2);
    }

    private void initBanner(List<User.ResultBean.BannerBean> b) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < b.size(); i++) {
            list.add(b.get(i).getImage());
        }

        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(list).setDelayTime(1000).start();

    }

    @Override
    protected void initView(View view) {
        pListView = view.findViewById(R.id.pListView);
        pListView.setMode(PullToRefreshBase.Mode.BOTH);
        pListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                list.clear();
                requestData(1);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                p++;
                if (p > 4) {
                    Toast.makeText(getActivity(), "底线~", Toast.LENGTH_LONG).show();
                    onPullDownToRefresh(pListView);
                } else {
                    requestData(p);
                }
            }
        });
        //banner——banner布局文件不能命名为banner
        View inflate = View.inflate(getActivity(), R.layout.mybanner, null);
        //添加到头部视图
        ListView listView = pListView.getRefreshableView();
        listView.addHeaderView(inflate);
        banner = inflate.findViewById(R.id.banner);
    }

    @Override
    protected int initLayout() {
        return R.layout.shou_layout;
    }
}
