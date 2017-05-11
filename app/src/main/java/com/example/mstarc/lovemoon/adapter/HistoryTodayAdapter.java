package com.example.mstarc.lovemoon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.HistoryListBean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-20.
 */

public class HistoryTodayAdapter extends BaseAdapter {

    private List<HistoryListBean> list;
    private LayoutInflater inflater;

    public HistoryTodayAdapter(Context context,List<HistoryListBean> list){
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = inflater.inflate(R.layout.inflate_item,null);
        }
        ImageView iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_des = (TextView) view.findViewById(R.id.tv_des);
        TextView tv_lunar = (TextView) view.findViewById(R.id.tv_lunar);

        HistoryListBean bean = (HistoryListBean) getItem(i);
//        Glide.with(banner.getContext()).load(model).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) view);
        Glide.with(viewGroup.getContext()).load(bean.getPic()).placeholder(R.mipmap.ic_history).error(R.mipmap.ic_history).into(iv_pic);
        tv_des.setText(bean.getDes());
        tv_title.setText(bean.getTitle());
        tv_lunar.setText("【阴历】 "+bean.getLunar());
        return view;
    }
}
