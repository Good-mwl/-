package com.bw.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bw.base.BaseFragment;
import com.bw.mawenlong20190923.R;

/**
 * author: 马文龙
 * data: 2019/9/23 19:19:15
 * 功能:
 */
public class FaFragment extends BaseFragment {
    String url = "https://www.baidu.com/";
    private WebView web_view;

    @Override
    protected void initData(Bundle savedInstanceState) {
        //加载网页
        web_view.loadUrl(url);
        web_view.setWebViewClient(new WebViewClient(){
            //设置加载开始和加载完成的监听
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("aaa", "页面开始加载");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("aaa", "页面加载完成");

            }
        });
        //在本应用加载
        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);//支持js

    }

    @Override
    protected void initView(View view) {
        web_view = view.findViewById(R.id.web_view);
    }

    @Override
    protected int initLayout() {
        return R.layout.fa_fragment;
    }
}
