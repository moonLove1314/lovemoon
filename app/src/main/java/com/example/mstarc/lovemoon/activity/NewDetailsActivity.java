package com.example.mstarc.lovemoon.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.NewDataModel;
import com.example.mstarc.lovemoon.view.ProgressWebView;

public class NewDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private ImageView iv_share;
    private RelativeLayout rl_bg;
//    private WebView webView;


    @Override
    public int contentView() {
        return R.layout.activity_new_details;
    }

    @Override
    public void initView() {
        NewDataModel bean = (NewDataModel) getIntent().getSerializableExtra("data");
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        rl_bg = (RelativeLayout) findViewById(R.id.rl_bg);
        iv_back.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        ProgressWebView webView = (ProgressWebView) findViewById(R.id.webView);
        webView.loadUrl(bean.getUrl());

//        webView = (WebView) findViewById(R.id.webView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setAppCacheEnabled(false);
//        webView.requestFocusFromTouch();
//        // 设置支持缩放
//        webView.getSettings().setBuiltInZoomControls(true);
//        //弹出js alert
//        webView.loadUrl(bean.getUrl());
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view == iv_back) {
            finish();
        }else if(view == iv_share){
            Toast.makeText(this,"敬请期待",Toast.LENGTH_SHORT).show();
        }

    }
}
