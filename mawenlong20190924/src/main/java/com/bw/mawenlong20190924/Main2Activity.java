package com.bw.mawenlong20190924;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.adapter.MyFragmentAdapter;
import com.bw.async.MyAsync;
import com.bw.base.BaseActivity;
import com.bw.base.BaseFragment;
import com.bw.bean.News;
import com.bw.fragment.FaFragment;
import com.bw.fragment.MyFragment;
import com.bw.fragment.ShouFragment;
import com.bw.util.HttpUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends BaseActivity {

    private Banner My_Banner;
    private TabLayout tab_layout;
    //创建tab的集合
    private List<String> tlist = new ArrayList<>();
    //创建fragment 的集合
    private List<Fragment> flist = new ArrayList<>();
    private ViewPager view_pager;
    private MyFragmentAdapter myFragmentAdapter;
    //声明接口
    private String url = "http://blog.zhaoliang5156.cn/api/news/news_month_a1.json";
    private List<String> Llist = new ArrayList<>();

    @Override
    protected void initData() {
        //添加文字
        tlist.add("推荐");
        tlist.add("学生");
        tlist.add("老师");
        //添加fragment
        flist.add(new ShouFragment());
        flist.add(new FaFragment());
        flist.add(new MyFragment());
        //创建适配器
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), tlist, flist);
        view_pager.setAdapter(myFragmentAdapter);
        tab_layout.setupWithViewPager(view_pager);
        if (HttpUtil.getInstence().getConn(Main2Activity.this)){
            //有网请求banner数据
            RequestBanner();
        }else{
            //没网
            Toast.makeText(this, "当前手机没有网，请检测手机网络", Toast.LENGTH_SHORT).show();
        }


    }

    private void RequestBanner() {
        new MyAsync(new MyAsync.MyDataBack() {
            @Override
            public void dataBack(String s) {
                //打印
                Log.i("aaa", "dataBack: "+s);
                // 取出banner数据
                Gson gson = new Gson();
                News news = gson.fromJson(s, News.class);
                List<News.ResultBean.BannerBean> bannerList = news.getResult().getBanner();
                for (int i = 0; i <bannerList.size() ; i++) {
                    Llist.add(bannerList.get(i).getImage());
                }
                //轮播
                initLunbo();
            }
        }).execute(url);
    }

    private void initLunbo() {
        My_Banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).setImages(Llist).setDelayTime(2000).start();
    }

    @Override
    protected void initView() {
        //初始化控件
        My_Banner = findViewById(R.id.My_Banner);
        tab_layout = findViewById(R.id.tab_layout);
        view_pager = findViewById(R.id.view_pager);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main2;
    }
}
