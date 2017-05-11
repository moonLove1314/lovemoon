package com.example.mstarc.lovemoon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mstarc.lovemoon.adapter.WXAdapter;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.WXJsonString;
import com.example.mstarc.lovemoon.bean.WXList;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WXActivity extends BaseActivity implements SwipeRecyclerView.OnLoadListener,WXAdapter.OnItemClickListener{
    private List<WXList> list = new ArrayList<>();
    private SwipeRecyclerView recyclerView;
    private WXAdapter adapter;
    private int page = 1;
    private boolean isAdd = false;

    @Override
    public int contentView() {
        return R.layout.activity_wx;
    }

    @Override
    public void initView() {
        recyclerView = (SwipeRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.getSwipeRefreshLayout()
                .setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        recyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(this));

        getEngine(Constans.WX_BASE_URL);
    }

    @Override
    public void setListener() {
          recyclerView.setOnLoadListener(this);
    }

    @Override
    public void initData() {
        getDataFromService();

    }

    private void getDataFromService(){
        mEngine.getWXData(Constans.WX_API_KEY,page).enqueue(new Callback<WXJsonString>() {
            @Override
            public void onResponse(Call<WXJsonString> call, Response<WXJsonString> response) {
                WXJsonString bean = response.body();
                ToastUtils.showInfo(WXActivity.this,bean.getReason());
                list = bean.getResult().getList();
                if(isAdd){
                       adapter.addDatas(list);
                       recyclerView.complete();
                }else{
                    adapter = new WXAdapter(WXActivity.this,list);

                    recyclerView.setAdapter(adapter);
                    recyclerView.complete();
                }
                adapter.setOnItemClickListener(WXActivity.this);
            }

            @Override
            public void onFailure(Call<WXJsonString> call, Throwable throwable) {
                ToastUtils.showInfo(WXActivity.this,"error");
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        isAdd = false;
        getDataFromService();


    }

    @Override
    public void onLoadMore() {
        page++;
        isAdd = true;
        getDataFromService();
    }

    @Override
    public void clickItem(WXList bean) {
        Intent intent = new Intent(this,WxDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
