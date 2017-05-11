package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.LookUpJson;
import com.example.mstarc.lovemoon.bean.SongResult;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TangDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_title;
    private TextView tv_author;
    private TextView tv_content;
    private TextView tv_jieshao;
    private Engine mEngine;
    private Dialog dialog;

    @Override
    public int contentView() {
        return R.layout.activity_tang_details;
    }

    @Override
    public void initView() {
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_jieshao = (TextView) findViewById(R.id.tv_jieshao);

    }
    private void getData(){
        dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
        SongResult bean = (SongResult) getIntent().getSerializableExtra("data");
        String id = bean.getId();
        mEngine = new Retrofit.Builder()
                .baseUrl(Constans.TANG_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
        mEngine.getLookUpData(Constans.TANG_API_KEY,id).enqueue(new Callback<LookUpJson>() {
            @Override
            public void onResponse(Call<LookUpJson> call, Response<LookUpJson> response) {
                SongResult bean = response.body().getResult();
                Log.e("介绍",bean.getJieshao());
//                ToastUtils.showInfo(TangDetailsActivity.this,response.body().getReason());
                tv_title.setText(bean.getBiaoti());
                tv_author.setText("作者·"+bean.getZuozhe());
                tv_jieshao.setText(bean.getJieshao());
                tv_content.setText(bean.getNeirong());
                WeiboDialogUtils.closeDialog(dialog);
            }

            @Override
            public void onFailure(Call<LookUpJson> call, Throwable t) {
                ToastUtils.showInfo(TangDetailsActivity.this,"失败");
                WeiboDialogUtils.closeDialog(dialog);
            }
        });


    }

    @Override
    public void setListener() {
        tv_title.setOnClickListener(this);

    }

    @Override
    public void initData() {
        getData();

    }

    @Override
    public void onClick(View view) {
        if(view == tv_title){
            finish();
        }
    }
}
