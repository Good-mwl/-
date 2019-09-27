package com.bw.mawenlong20190919;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bw.base.BaseActivity;

/**
 * author: 马文龙
 * data: 2019/9/21 10:10:00
 * 功能:
 */
public class ShowActivity extends BaseActivity {
    private WebView webView;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());//设置在当前应用下展示网页
        webView.getSettings().setJavaScriptEnabled(true);

    }

    @Override
    protected void initView() {
        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected int initLayout() {
        return R.layout.showlayout;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }
}

