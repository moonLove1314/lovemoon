package com.example.mstarc.lovemoon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.bean.SongResult;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-22.
 */

public class SongResultAdapter extends BaseAdapter {
    private List<SongResult> list;
    public SongResultAdapter(List<SongResult> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public SongResult getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inflate_song_item,null);
        }
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        SongResult bean = getItem(i);
        tv_name.setText(bean.getName());
        return view;
    }
}
