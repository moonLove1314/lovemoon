package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstarc.lovemoon.adapter.SongResultAdapter;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.SongJson;
import com.example.mstarc.lovemoon.bean.SongResult;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.LoadMoreView;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TangSongActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView tv_title;
    private EditText et_keywords;
    private Button btn_search;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private LoadMoreView loadMoreView;
    private Engine mEngine;
    private List<SongResult> list = new ArrayList<>();
    private SongResultAdapter adapter;
    private Dialog dialog;
    private ImageButton ib_cancle;
    private boolean isCancle = false;
    @Override
    public int contentView() {
        return R.layout.activity_tang_song;
    }

    @Override
    public void initView() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        et_keywords = (EditText) findViewById(R.id.et_keywords);
        btn_search = (Button) findViewById(R.id.btn_search);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        loadMoreView = new LoadMoreView(this);
        listView = (ListView) findViewById(R.id.listView);
        ib_cancle = (ImageButton) findViewById(R.id.ib_cancle);

        mEngine = new Retrofit.Builder()
                .baseUrl(Constans.TANG_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);

    }

    @Override
    public void setListener() {
        tv_title.setOnClickListener(this);
        btn_search.setOnClickListener(this);
        ib_cancle.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        et_keywords.addTextChangedListener(textWatcher);

    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String keyWord = et_keywords.getText().toString().trim();
            if(keyWord.equals("")){
                ib_cancle.setVisibility(View.GONE);
            }else{
                ib_cancle.setVisibility(View.VISIBLE);
                getData(keyWord);
            }


        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initData() {

    }
     private void getData(String keyWord){
         mEngine.getSongJsonData(Constans.TANG_API_KEY,keyWord).enqueue(new Callback<SongJson>() {
             @Override
             public void onResponse(Call<SongJson> call, Response<SongJson> response) {
                 SongJson bean = response.body();
                 ToastUtils.showInfo(getApplicationContext(),bean.getReason());
                 tv_title.setText("唐诗宋词（共"+bean.getTotal()+"条）");
                 WeiboDialogUtils.closeDialog(dialog);
                 list = bean.getResult();
                 if(list != null){
                     adapter = new SongResultAdapter(list);
                     listView.setAdapter(adapter);
                 }


             }

             @Override
             public void onFailure(Call<SongJson> call, Throwable t) {
                 WeiboDialogUtils.closeDialog(dialog);

             }
         });
     }
    @Override
    public void onClick(View view) {
         if(view == tv_title){
             finish();
         }else if(view == btn_search){
             String keyWord = et_keywords.getText().toString().trim();

             if(keyWord.equals("")){
                ToastUtils.showInfo(this,"关键字不能为空");
             }else{
                 dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
                 getData(keyWord);
                 View v = getWindow().peekDecorView();
                 if (v != null) {
                     InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                     inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                 }
             }

         }else if(view == ib_cancle){
             et_keywords.setText("");
             list.clear();
             adapter.notifyDataSetChanged();
             tv_title.setText("唐诗宋词（共0条）");
         }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        SongResult bean = adapter.getItem(i);
        Intent intent = new Intent(this,TangDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
