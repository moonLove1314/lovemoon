package com.example.mstarc.lovemoon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.JokeResult;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ItemViewHolder> {

    private List<JokeResult> list;

    public JokeAdapter(List<JokeResult> list) {
        this.list = list;

    }

    public void addDatas(List<JokeResult> list) {
        if (this.list != null) {
            this.list.addAll(list);
        } else {
            this.list = list;
        }
        this.notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_joke_item_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tv_content.setText(list.get(position).getContent());
        holder.tv_num.setText("(" + (position + 1)+")");
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_content;
        public TextView tv_num;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
        }
    }
}
