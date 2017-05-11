package com.example.mstarc.lovemoon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.WXList;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class WXAdapter extends RecyclerView.Adapter<WXAdapter.ItemViewHolder> {
    private List<WXList> list;
    private Context context;
    private OnItemClickListener listener;
    public WXAdapter(Context context,List<WXList> list){
        this.context = context;
        this.list = list;
    }
    public void addDatas(List<WXList> lists){
        if(list != null){
            list.addAll(lists);
        }else{
            list = lists;
        }
       notifyDataSetChanged();
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_wx_item_list,null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final WXList bean = list.get(position);
         holder.tv_title.setText(bean.getTitle());
         Glide.with(context).load(bean.getFirstImg()).error(R.mipmap.ic_wx).into(holder.iv_firstImg);

        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.clickItem(bean);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title;
        public ImageView iv_firstImg;
        public LinearLayout ll_bg;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_firstImg = (ImageView) itemView.findViewById(R.id.iv_firstImg);
            ll_bg = (LinearLayout) itemView.findViewById(R.id.ll_bg);
        }
    }

    public interface OnItemClickListener{
        void clickItem(WXList bean);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
    }
}
