package com.example.mstarc.lovemoon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.SongResult;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-23.
 */

public class SongResultRecyclerAdapter extends RecyclerViewAdapter<SongResult> {

    private List<SongResult> list;

    public SongResultRecyclerAdapter(Context context, List<SongResult> list) {
        super(context, list);
        init(list);
    }

    public SongResultRecyclerAdapter(Context context, List<SongResult> list, View headerView) {
        super(context, list, headerView);
        this.list = list;
        init(list);
    }

    public SongResultRecyclerAdapter(Context context, List<SongResult> list, View headerView, View footerView) {
        super(context, list, headerView, footerView);
        init(list);
    }
    private void init(List<SongResult> list){
        this.list = list;
    }

    @Override
    protected void onBindHeaderView(View headerView) {
        LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.FILL_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
        headerView.setLayoutParams(layoutParams);

    }

    @Override
    protected void onBindFooterView(View footerView) {
        LinearLayout.LayoutParams layoutParams =  new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.FILL_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(layoutParams);
    }

    @Override
    protected void onBindItemView(RecyclerView.ViewHolder holder, SongResult bean) {
      ListViewHolder viewHolder = (ListViewHolder) holder;
        viewHolder.tv_name.setText(bean.getName());

    }


    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_song_item,null));
    }
    static class ListViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public ListViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
