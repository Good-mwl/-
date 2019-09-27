package com.bw.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.adapter.MyAdapter;
import com.bw.async.MyDataTask;
import com.bw.base.BaseFragment;
import com.bw.bean.Goods;
import com.bw.mawenlong20190915.R;
import com.bw.util.HttpUtil;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 马文龙
 * data: 2019/9/15 16:16:07
 * 功能:
 */
public class FirstFrafment extends BaseFragment {
    private PullToRefreshListView p_listview;
    private View my_banner;
    private String dataUrl1 = "http://blog.zhaoliang5156.cn/api/shop/fulishe";
    private int i = 1;
    private String dataUrl2 = ".json";
    private List<String> bList = new ArrayList<>();
    private Banner banner;
    private List<Goods.DataBean> dList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        //判断网络
        if (HttpUtil.getInstance().getNetState(getActivity())){
            //创建适配器
            myAdapter = new MyAdapter(dList, getActivity());
            p_listview.setAdapter(myAdapter);
            //请求数据
            reqestData(i);
            //设置上拉下拉的监听
            p_listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    dList.clear();
                    reqestData(i);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    i++;
                    reqestData(i);
                }
            });
        }else {
            Toast.makeText(getActivity(),"请检查网络设置",Toast.LENGTH_SHORT).show();
        }

    }

    private void reqestData(final int page) {
        new MyDataTask(new MyDataTask.DataCallBack() {
            @Override
            public void getJson(String s) {
                Gson gson = new Gson();
                Goods goods = gson.fromJson(s, Goods.class);
                if (page==1){
                    //1.取出banner数据
                    List<Goods.BannerBean> banners = goods.getBanner();
                    for (int j = 0; j < banners.size(); j++) {
                        bList.add(banners.get(j).getImageUrl());
                    }
                    //设置轮播
                    initBanner();
                }
                //2.取出展示数据
                List<Goods.DataBean> datas = goods.getData();
                dList.addAll(datas);
                myAdapter.notifyDataSetChanged();
                //关闭刷新
                p_listview.onRefreshComplete();
            }
        }).execute(dataUrl1+page+dataUrl2);
    }

    private void initBanner() {
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(bList).setDelayTime(2000).start();
    }

    @Override
    protected void initView(View view) {
        p_listview = view.findViewById(R.id.p_listview);
        //设置支持上拉
        p_listview.setMode(PullToRefreshBase.Mode.BOTH);
        //把Banner布局转化成视图对象
        my_banner = View.inflate(getActivity(), R.layout.banner_layout, null);
        banner = my_banner.findViewById(R.id.my_banner);
        //获取PullToRefreshListView的头部视图对象
        ListView listView = p_listview.getRefreshableView();
        //添加头部视图
        listView.addHeaderView(my_banner);
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_first;
    }
}
