package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.TodayOfHistoryString;
import com.example.mstarc.lovemoon.utils.DateUtils;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.PullToRefreshView;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodayActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        PullToRefreshView.OnFooterRefreshListener, PullToRefreshView.OnHeaderRefreshListener {
    private static final String TAG = "TodayActivity";
    private TextView tv_marquee;
    private RadioGroup rg;
    private RadioButton rb_left;
    private RadioButton rb_right;
    private ListView mListView;
    private List<TodayOfHistoryString.TodayOfHistryRusult> list = new ArrayList<>();
    private TodayAdapter adapter;
    private String type = "1";
    private int page = 1;
    private PullToRefreshView mPullToRefreshView;
    private boolean isAdd = false;

    private Dialog dialog;


    @Override
    public int contentView() {
        return R.layout.activity_today;
    }

    @Override
    public void initView() {
        tv_marquee = (TextView) findViewById(R.id.tv_marquee);
        rg = (RadioGroup) findViewById(R.id.rg);
        rb_left = (RadioButton) findViewById(R.id.rb_left);
        rb_right = (RadioButton) findViewById(R.id.rb_right);
        mListView = (ListView) findViewById(R.id.listView);
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh_view);

        getEngine(Constans.TODAY_BASE_URL);
    }

    @Override
    public void setListener() {
        rg.setOnCheckedChangeListener(this);
        mPullToRefreshView.setOnFooterRefreshListener(this);
        mPullToRefreshView.setOnHeaderRefreshListener(this);
    }

    private String marqueeData(List<TodayOfHistoryString.TodayOfHistryRusult> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            String title = list.get(i).title;
            String date = list.get(i).year + "-" + list.get(i).month + "-" + list.get(i).day;
            sb.append("(" + (i + 1) + ")" + date + " " + title + "。");
        }
        return sb.toString();
    }

    private void getDataFromService(String type, int page) {
        dialog = WeiboDialogUtils.createLoadingDialog(this, "数据加载中...");
        mEngine.getTodayData(Constans.TODAY_API_KEY,
                DateUtils.getCurrentMonth(),
                DateUtils.getCurrentDay(),
                type, page).enqueue(new Callback<TodayOfHistoryString>() {
            @Override
            public void onResponse(Call<TodayOfHistoryString> call, Response<TodayOfHistoryString> response) {

                WeiboDialogUtils.closeDialog(dialog);

                TodayOfHistoryString bean = response.body();

//                Log.e(TAG, bean.error_code + "---" + bean.reason +"---");


                list = bean.result;
                tv_marquee.setText("共" + bean.total + "条" + "   " + marqueeData(list));
                Logger.t(TAG).d(bean.result);
                if (!isAdd) {
                    adapter = new TodayAdapter();
                    adapter.setList(list);
                    mListView.setAdapter(adapter);
                } else {
                    adapter.addDatas(list);
                    if (list.size() == 0) {
                        ToastUtils.showInfo(TodayActivity.this, "没有更多数据");
                    } else {
                        ToastUtils.showInfo(TodayActivity.this, "加载成功");
                    }
                }
                mPullToRefreshView.onFooterRefreshComplete();
                mPullToRefreshView.onHeaderRefreshComplete();

            }

            @Override
            public void onFailure(Call<TodayOfHistoryString> call, Throwable t) {
                mPullToRefreshView.onFooterRefreshComplete();
                mPullToRefreshView.onHeaderRefreshComplete();
                WeiboDialogUtils.closeDialog(dialog);
            }
        });
    }

    @Override
    public void initData() {
        getDataFromService(type, page);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rb_left) {
            type = "1";
            getDataFromService(type, 1);
        } else if (checkedId == R.id.rb_right) {
            type = "2";
            getDataFromService(type, 1);
        }
    }

    private void getData() {
        isAdd = false;
        page = 1;
        getDataFromService(type, page);

    }

    private void addData() {
        isAdd = true;
        page++;
        getDataFromService(type, page);
    }

    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        addData();
        Log.e(TAG, page + "");

    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        getData();
        Log.e(TAG, page + "");

    }

    class TodayAdapter extends BaseAdapter {

        private List<TodayOfHistoryString.TodayOfHistryRusult> list;

        public void setList(List<TodayOfHistoryString.TodayOfHistryRusult> list) {
            this.list = list;
        }

        public void addDatas(List<TodayOfHistoryString.TodayOfHistryRusult> list) {
            if (this.list != null) {
                this.list.addAll(list);
            } else {
                this.list = list;
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public TodayOfHistoryString.TodayOfHistryRusult getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_item, null);
            }
            TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            TextView tv_date = (TextView) convertView.findViewById(R.id.tv_date);

            TodayOfHistoryString.TodayOfHistryRusult bean = getItem(position);
            tv_title.setText(bean.title);
            tv_date.setText(bean.year + "-" + bean.month + "-" + bean.day);
            return convertView;
        }
    }
}
