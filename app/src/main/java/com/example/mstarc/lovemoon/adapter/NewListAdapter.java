package com.example.mstarc.lovemoon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.NewDataModel;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-21.
 */

public class NewListAdapter extends BaseAdapter {
    private List<NewDataModel> list;

    public NewListAdapter(List<NewDataModel> list){
        this.list = list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public NewDataModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_new_list_item,null);
        }
        ImageView iv_pic1 = (ImageView) view.findViewById(R.id.iv_pic1);
        ImageView iv_pic2 = (ImageView) view.findViewById(R.id.iv_pic2);
        ImageView iv_pic3 = (ImageView) view.findViewById(R.id.iv_pic3);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_author_name = (TextView) view.findViewById(R.id.tv_author_name);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);

        NewDataModel bean = getItem(i);
        tv_title.setText(bean.getTitle());
        if(bean.getThumbnail_pic_s() != null){
            if(!bean.getThumbnail_pic_s().equals("")){
                iv_pic1.setVisibility(View.VISIBLE);
                Glide.with(viewGroup.getContext()).load(bean.getThumbnail_pic_s()).into(iv_pic1);
            }else{
                iv_pic1.setVisibility(View.GONE);
            }
        }else{
            iv_pic1.setVisibility(View.GONE);
        }

        if(bean.getThumbnail_pic_s02() != null){
            if(!bean.getThumbnail_pic_s02().equals("")){
                iv_pic2.setVisibility(View.VISIBLE);
                Glide.with(viewGroup.getContext()).load(bean.getThumbnail_pic_s02()).into(iv_pic2);
            }else{
                iv_pic2.setVisibility(View.GONE);
            }
        }else{
            iv_pic2.setVisibility(View.GONE);
        }
        if(bean.getThumbnail_pic_s03() != null){
            if(!bean.getThumbnail_pic_s03().equals("")){
                iv_pic3.setVisibility(View.VISIBLE);
                Glide.with(viewGroup.getContext()).load(bean.getThumbnail_pic_s03()).into(iv_pic3);
            }else{
                iv_pic3.setVisibility(View.GONE);
            }
        }else{
            iv_pic3.setVisibility(View.GONE);
        }

        tv_author_name.setText(bean.getAuthor_name());
        tv_date.setText(bean.getDate());

        return view;
    }
}
