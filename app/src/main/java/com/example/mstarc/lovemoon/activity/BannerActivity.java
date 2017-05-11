package com.example.mstarc.lovemoon.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.app.App;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.BannerModel;
import com.example.mstarc.lovemoon.bean.RefreshModel;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BannerActivity extends BaseActivity{

    private ListView mContentLv;
    private BGABanner mBanner;
    private ContentAdapter mContentAdapter;
    private String defalutTransitionEffect;

    private Engine mEngine;

    @Override
    public int contentView() {
        return R.layout.activity_banner;
    }

    @Override
    public void initView() {
        Logger.t("sssss").d("执行了 onCreate");

        mContentLv = (ListView) findViewById(R.id.lv_content);

        mEngine = new Retrofit.Builder()
                .baseUrl("http://7xk9dj.com1.z0.glb.clouddn.com/banner/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
       defalutTransitionEffect = getIntent().getStringExtra("leixing");
    }

    @Override
    public void setListener() {

        // 初始化HeaderView
        View headerView = View.inflate(this, R.layout.layout_header, null);
        mBanner = (BGABanner) headerView.findViewById(R.id.banner);
        mBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                Glide.with(banner.getContext()).load(model).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) view);
            }
        });
        if(defalutTransitionEffect.equals("cube")){
            mBanner.setTransitionEffect(TransitionEffect.Cube);
        }else if(defalutTransitionEffect.equals("alpha")){
            mBanner.setTransitionEffect(TransitionEffect.Alpha);
        }else if(defalutTransitionEffect.equals("flip")){
            mBanner.setTransitionEffect(TransitionEffect.Flip);
        }else if(defalutTransitionEffect.equals("depth")){
            mBanner.setTransitionEffect(TransitionEffect.Depth);
        }else if(defalutTransitionEffect.equals("accordion")){
            mBanner.setTransitionEffect(TransitionEffect.Accordion);
        }else if(defalutTransitionEffect.equals("default")){
            mBanner.setTransitionEffect(TransitionEffect.Default);
        }else if(defalutTransitionEffect.equals("fade")){
            mBanner.setTransitionEffect(TransitionEffect.Fade);
        }else if(defalutTransitionEffect.equals("zoomFade")){
            mBanner.setTransitionEffect(TransitionEffect.ZoomFade);
        }else  if(defalutTransitionEffect.equals("zoom")){
            mBanner.setTransitionEffect(TransitionEffect.Zoom);
        }else if(defalutTransitionEffect.equals("zoomStack")){
            mBanner.setTransitionEffect(TransitionEffect.ZoomStack);
        }else if(defalutTransitionEffect.equals("zoomCenter")){
            mBanner.setTransitionEffect(TransitionEffect.ZoomCenter);
        }else if(defalutTransitionEffect.equals("stack")){
            mBanner.setTransitionEffect(TransitionEffect.Stack);
        }

        // 初始化ListView
        mContentLv.addHeaderView(headerView);
        mContentAdapter = new ContentAdapter(this);
        mContentLv.setAdapter(mContentAdapter);

    }
    /**
     * 加载头部广告条的数据
     */
    private void loadBannerData() {
        mEngine.fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载广告条数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载内容列表数据
     */
    private void loadContentData() {
        mEngine.loadContentData("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/api/defaultdata.json").enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mContentAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载内容数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void initData() {
        loadBannerData();
        loadContentData();
    }

    private class ContentAdapter extends BGAAdapterViewAdapter<RefreshModel> {

        public ContentAdapter(Context context) {
            super(context, R.layout.item_normal);
        }

        @Override
        protected void fillData(final BGAViewHolderHelper helper, final int position, RefreshModel model) {
            helper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);

            helper.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(position == 0){
                        startActivity(new Intent(getApplicationContext(),HistoryTodayActivity.class));
                    }else if(position == 1){
                        startActivity(new Intent(getApplicationContext(),NewTopActivity.class));
                    }else if(position == 2){
                        startActivity(new Intent(getApplicationContext(),TangSongActivity.class));
                    }else if(position == 3) {
                        startActivity(new Intent(getApplicationContext(),JokeActivity.class));
                    }else if(position == 4){
                        startActivity(new Intent(getApplicationContext(),WXActivity.class));
                    }else if(position == 5){
                        startActivity(new Intent(getApplicationContext(),DigitalReadingActivity.class));
                    }else if(position == 6){
                        startActivity(new Intent(getApplicationContext(),MySwipBackActivity.class));
                    } else if(position == 7){
                        startActivity(new Intent(getApplicationContext(),PageBottomActivity.class));
                    }else if(position == 8) {
                        startActivity(new Intent(getApplicationContext(),QQActivity.class));
                    }else if(position == 9) {
                        startActivity(new Intent(getApplicationContext(),QWYSActivity.class));
                    }else if(position == 10) {
                        startActivity(new Intent(getApplicationContext(),DiDiActivity.class));
                    }else if(position == 11) {
                        startActivity(new Intent(getApplicationContext(),SingleListChoiceActivity.class));
                    }else if(position == 12) {
                        startActivity(new Intent(getApplicationContext(),CookClassActivity.class));
                    }else if(position == 13) {
                        startActivity(new Intent(getApplicationContext(),TodayActivity.class));
                    }else if(position == 14) {
                        startActivity(new Intent(getApplicationContext(),AlbumActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"敬请期待",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}
