package com.example.mstarc.lovemoon.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.mstarc.lovemoon.api.Engine;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mstarc on 2016-12-19.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public Engine mEngine;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(contentView());

        initView();

        setListener();

        initData();
    }

    // 当前页面布局
    public abstract int contentView();

    public abstract void initView();

    public abstract void setListener();

    public abstract void initData();

    public void getEngine(String url) {
        mEngine = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }
//    OkHttpClient.Builder builder = new OkHttpClient.Builder();
  public void getRxJavaEngine(String url){
       mEngine = new Retrofit.Builder()
               .baseUrl(url)
//               .client(builder.build())
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .build().create(Engine.class);
  }

}
