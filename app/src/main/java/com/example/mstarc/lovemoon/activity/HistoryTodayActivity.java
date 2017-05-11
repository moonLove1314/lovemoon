package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstarc.lovemoon.adapter.HistoryTodayAdapter;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.HistoryBean;
import com.example.mstarc.lovemoon.bean.HistoryListBean;
import com.example.mstarc.lovemoon.utils.DateUtils;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HistoryTodayActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    private TextView tv_title;
    private ListView listView;
    private Engine mEngine;
    private HistoryTodayAdapter adapter;
    private List<HistoryListBean> list = new ArrayList<>();
    private Dialog dialog;

    @Override
    public int contentView() {
        return R.layout.activity_history_today;

    }

    @Override
    public void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        listView = (ListView) findViewById(R.id.listView);
        dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
        mEngine = new Retrofit.Builder()
                .baseUrl(Constans.HISTORY_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(Engine.class);
    }

    @Override
    public void setListener() {
        tv_title.setOnClickListener(this);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {

        mEngine.loadHistoryTodayData(Constans.API_KEY,Constans.API_VERSION, DateUtils.getCurrentMonth(),DateUtils.getCurrentDay())
                .enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                WeiboDialogUtils.closeDialog(dialog);
                String str = response.body();
                Logger.t("1111").d(str);

                //解析数据
                Gson gson = new Gson();
                HistoryBean listBean = gson.fromJson(str,HistoryBean.class);

                list = listBean.getResult();
                adapter = new HistoryTodayAdapter(HistoryTodayActivity.this,list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                WeiboDialogUtils.closeDialog(dialog);

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == tv_title){
            finish();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HistoryListBean bean = (HistoryListBean) adapter.getItem(i);
        Intent intent = new Intent(HistoryTodayActivity.this,HistoryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
