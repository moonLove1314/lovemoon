package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.QWYS;
import com.example.mstarc.lovemoon.view.PullToRefreshView;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QWYSActivity extends BaseActivity implements AdapterView.OnItemClickListener,PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
    private static final String TAG = "QWYSActivity";
    private ListView mListView;
    private List<QWYS.Result> list = new ArrayList<>();
    private QWYSAdapter adapter;
    private PullToRefreshView mPullToRefreshView;
    private int page = 1;
    private boolean isAdd = false;
    private Dialog dialog;

    @Override
    public int contentView() {
        return R.layout.activity_qwys;
    }

    @Override
    public void initView() {
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh_view);
        mListView = (ListView) findViewById(R.id.listView);

        getEngine(Constans.QWYS_BASE_URL);
    }

    @Override
    public void setListener() {
        mPullToRefreshView.setOnHeaderRefreshListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        mListView.setOnItemClickListener(this);
    }

    private void getData(){
        isAdd = false;
        page = 1;
        getDataFromService(page);

    }
    private void addData(){
        isAdd = true;
        page ++;
        getDataFromService(page);
    }


    private void getDataFromService(int page){
        dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
        //请求数据
        mEngine.getQWISData(Constans.QWYS_API_KEY, page, 20).enqueue(new Callback<QWYS>() {
            @Override
            public void onResponse(Call<QWYS> call, Response<QWYS> response) {
                WeiboDialogUtils.closeDialog(dialog);
                QWYS bean = response.body();
                Log.e(TAG,bean.result.get(0).url);
                list = bean.result;
                if(isAdd){
                  adapter.addList(list);
                }else {
                    adapter = new QWYSAdapter(list);
                    mListView.setAdapter(adapter);
                }
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.onFooterRefreshComplete();
            }

            @Override
            public void onFailure(Call<QWYS> call, Throwable t) {
                WeiboDialogUtils.closeDialog(dialog);
                mPullToRefreshView.onHeaderRefreshComplete();
                mPullToRefreshView.onFooterRefreshComplete();
            }
        });
    }
    @Override
    public void initData() {
     getDataFromService(page);

    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
      addData();
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
          getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        QWYS.Result bean = adapter.getItem(position);
        Intent intent = new Intent(this,QWYSWebActivity.class);
        intent.putExtra("url",bean.url);
        startActivity(intent);
    }

    class QWYSAdapter extends BaseAdapter {
       private List<QWYS.Result> list;
        public QWYSAdapter(List<QWYS.Result> list){
            this.list = list;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public QWYS.Result getItem(int position) {
            return list.get(position);
        }

        public void addList(List<QWYS.Result> listA) {
            if (this.list != null) {
                this.list.addAll(listA);
            } else {
                this.list = listA;
            }
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_qwys_item, null);
            }
            TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            ImageView iv_picUrl = (ImageView) convertView.findViewById(R.id.iv_picUrl);
            TextView tv_description = (TextView) convertView.findViewById(R.id.tv_description);

            QWYS.Result bean = getItem(position);
            tv_title.setText(bean.title);
            Glide.with(parent.getContext()).load(bean.picUrl).error(R.mipmap.ic_launcher).into(iv_picUrl);
            tv_description.setText(bean.description);
            return convertView;
        }
    }
}
