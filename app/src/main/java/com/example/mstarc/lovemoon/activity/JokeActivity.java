package com.example.mstarc.lovemoon.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.mstarc.lovemoon.adapter.JokeAdapter;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.JokeJsonString;
import com.example.mstarc.lovemoon.bean.JokeResult;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JokeActivity extends BaseActivity implements SwipeRecyclerView.OnLoadListener{
    private SwipeRecyclerView recyclerView;
    private JokeAdapter adapter;
    private List<JokeResult> list = new ArrayList<>();
    private Engine mEngine;
    private int page = 1;
    private boolean isAdd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int contentView() {
        return R.layout.activity_joke;
    }

    @Override
    public void initView() {
        recyclerView = (SwipeRecyclerView) findViewById(R.id.swipeRefreshLayout);

        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));

        mEngine = new Retrofit.Builder()
                .baseUrl(Constans.JOKE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }

    @Override
    public void setListener() {
        recyclerView.setOnLoadListener(this);

    }
    private void requestDataFromService(){

        mEngine.getJokeData(Constans.JOKE_API_KEY,page,"desc","1418816972").enqueue(new Callback<JokeJsonString>() {
            @Override
            public void onResponse(Call<JokeJsonString> call, Response<JokeJsonString> response) {
                JokeJsonString bean = response.body();
                ToastUtils.showInfo(JokeActivity.this,bean.getReason());
                list = bean.getResult();
                Log.e("集合",list.get(0).getContent());
                if(isAdd){
                    adapter.addDatas(list);
                    recyclerView.complete();
                }else{
                    adapter  = new JokeAdapter(list);
                    recyclerView.setAdapter(adapter);
                    recyclerView.complete();
                }

            }

            @Override
            public void onFailure(Call<JokeJsonString> call, Throwable throwable) {
                ToastUtils.showInfo(JokeActivity.this,"网络错误");

            }
        });

    }

    @Override
    public void initData() {
        requestDataFromService();
    }

    @Override
    public void onRefresh() {
        page = 1;
        isAdd = false;
        list.clear();
        requestDataFromService();



    }

    @Override
    public void onLoadMore() {
        isAdd = true;
        page++;
        requestDataFromService();


    }
}
