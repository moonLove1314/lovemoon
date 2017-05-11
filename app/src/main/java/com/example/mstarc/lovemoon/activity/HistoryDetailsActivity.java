package com.example.mstarc.lovemoon.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.HistoryBean;
import com.example.mstarc.lovemoon.bean.HistoryListBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryDetailsActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_title;
    private ImageView iv_pic;
    private TextView tv_lunar;
    private TextView tv_content;
    private String id;
    private Engine mEngine;

    @Override
    public int contentView() {
        return R.layout.activity_history_details;
    }

    @Override
    public void initView() {
        HistoryListBean bean = (HistoryListBean) getIntent().getSerializableExtra("data");
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_lunar = (TextView) findViewById(R.id.tv_lunar);

        tv_title.setText(bean.getTitle());
        tv_lunar.setText("【阳历】 "+bean.getLunar());

        id = bean.get_id();


        mEngine = new Retrofit.Builder()
                .baseUrl(Constans.HISTORY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);

    }

    @Override
    public void setListener() {
        tv_title.setOnClickListener(this);
    }

    @Override
    public void initData() {
        Log.e("id----",id);
        mEngine.loadaHistoryDetailsData(Constans.API_KEY,Constans.API_VERSION,id)
                .enqueue(new Callback<HistoryBean>() {
                    @Override
                    public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                              HistoryBean bean = response.body();
                        Toast.makeText(getApplicationContext(),bean.getReason(),Toast.LENGTH_SHORT).show();

                        tv_content.setText(bean.getResult().get(0).getContent());
                        Glide.with(HistoryDetailsActivity.this)
                                .load(bean.getResult().get(0).getPic())
                                .error(R.mipmap.ic_history)
                                .into(iv_pic);
                    }

                    @Override
                    public void onFailure(Call<HistoryBean> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"网络错误",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == tv_title){
            finish();
        }
    }
}
