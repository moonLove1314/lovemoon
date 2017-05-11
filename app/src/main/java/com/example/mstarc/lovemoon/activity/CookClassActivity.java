package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.FoodString;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CookClassActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private List<FoodString.FoodResult> list = new ArrayList<>();
    private ListView mListView;
    private CookClassAdapter adapter;
    Dialog dialog;

    @Override
    public int contentView() {
        return R.layout.activity_cook_class;
    }

    @Override
    public void initView() {
        mListView = (ListView) findViewById(R.id.listView);

        getEngine(Constans.FOOD_BASE_URL);

    }

    @Override
    public void setListener() {
        mListView.setOnItemClickListener(this);
    }

    private void getData() {
        dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
        mEngine.getFoodData(Constans.FOOD_API_KEY, "0").enqueue(new Callback<FoodString>() {
            @Override
            public void onResponse(Call<FoodString> call, Response<FoodString> response) {
                FoodString bean = response.body();
                list = bean.result;
                adapter = new CookClassAdapter();
                mListView.setAdapter(adapter);
                WeiboDialogUtils.closeDialog(dialog);
            }

            @Override
            public void onFailure(Call<FoodString> call, Throwable t) {
                WeiboDialogUtils.closeDialog(dialog);
            }
        });
    }

    @Override
    public void initData() {
        getData();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class CookClassAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public FoodString.FoodResult getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_cook_class_item, null);
            }
            FoodString.FoodResult bean = getItem(position);
            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            tv_name.setText(bean.name);
            return convertView;
        }
    }
}
