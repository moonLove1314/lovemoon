package com.example.mstarc.lovemoon.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageBottomFragment extends Fragment {

    public PageBottomFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String content = getArguments().getString("content");
        TextView textView = new TextView(getActivity());
        textView.setText(content);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setBackgroundColor(0xFFececec);

        return  textView;
//        return inflater.inflate(R.layout.fragment_page_bottom, container, false);
    }
}
