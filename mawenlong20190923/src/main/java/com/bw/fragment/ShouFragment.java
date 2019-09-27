package com.bw.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bw.adapter.MyAdapter;
import com.bw.base.BaseFragment;
import com.bw.bean.News;
import com.bw.mawenlong20190923.Main2Activity;
import com.bw.mawenlong20190923.R;
import com.bw.task.JsonTast;
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
 * data: 2019/9/23 19:19:14
 * 功能:
 */
public class ShouFragment extends BaseFragment {

    private PullToRefreshListView plist_view;
    private Banner my_banner;
    private String url1="http://blog.zhaoliang5156.cn/api/month/monthapi";
    private int i=1;
    private String url2=".json";
    //banner集合
    private List<String> blist=new ArrayList<>();
    //list集合
    private List<News.ResultBean.NewsBean> nlist=new ArrayList<>();
    private MyAdapter myAdapter;
    private List<News.ResultBean.NewsBean> newsList;

    @Override
    protected void initData(Bundle savedInstanceState) {
        //请求数据
        if (HttpUtil.getInstence().getNetType(getActivity())){
            ResData(i);
            //设置适配器
            myAdapter = new MyAdapter(nlist, getActivity());
            plist_view.setAdapter(myAdapter);
            //上拉加载下拉树新
            plist_view.setMode(PullToRefreshBase.Mode.BOTH);
            plist_view.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    nlist.clear();
                    ResData(i);
                    plist_view.onRefreshComplete();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    i++;
                    ResData(i);
                    plist_view.onRefreshComplete();
                }
            });
        }
    }

    private void ResData(final int page) {
        new JsonTast(new JsonTast.getDataBack() {
            @Override
            public void dataBack(String s) {
                Log.i("aaa", "dataBack: "+s);
                //解析
                Gson gson = new Gson();
                final News news = gson.fromJson(s, News.class);
                //取出banner数据
                if (page == 1){
                    List<News.ResultBean.BannerBean> bannerList = news.getResult().getBanner();
                    for (int j = 0; j <bannerList.size() ; j++) {
                        //存到新集合
                        blist.add(bannerList.get(j).getImage());
                    }
                    //轮播方法
                    initLunbo();
                }
                //2.取出list集合数据
                newsList = news.getResult().getNews();
                nlist.addAll(newsList);
                //3.跳转详情页面
                plist_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //传值的方法
                        String url = news.getResult().getNews().get(position-2).getUrl();
                        Log.i("bbb", "onItemClick: "+url);
                        Intent intent = new Intent(getActivity(), Main2Activity.class);
                        intent.putExtra("url",url);
                        startActivity(intent);
                    }
                });
            }
        }).execute(url1+page+url2);
    }

    private void initLunbo() {
        my_banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(blist).setDelayTime(2000).start();
    }

    @Override
    protected void initView(View view) {
        plist_view = view.findViewById(R.id.plist_view);
        //获取视图
        ListView listView = plist_view.getRefreshableView();
        View inflate = View.inflate(getActivity(), R.layout.mybanner, null);
        my_banner = inflate.findViewById(R.id.My_banner);
        listView.addHeaderView(inflate);

    }

    @Override
    protected int initLayout() {
        return R.layout.shou_fragment;
    }
}

