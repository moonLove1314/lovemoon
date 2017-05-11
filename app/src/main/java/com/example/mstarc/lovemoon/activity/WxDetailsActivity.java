package com.example.mstarc.lovemoon.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.WXList;

public class WxDetailsActivity extends BaseActivity {
    private WebView webView;

    @Override
    public int contentView() {
        return R.layout.activity_wx_details;
    }

    @Override
    public void initView() {
        webView = (WebView) findViewById(R.id.webView);
        WXList bean = (WXList) getIntent().getSerializableExtra("data");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        webView.requestFocusFromTouch();
        // 设置支持缩放
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(bean.getUrl());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }});
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }
}
