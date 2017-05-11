package com.example.mstarc.lovemoon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstarc.lovemoon.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MySwipBackActivity extends Activity {
    private ListView mListView;
    private static List<User> mList = new ArrayList<>();
    private MyAdapter adapter;
    private EditText currentHasFocusEditText = null;
    private int lastHeightDiff = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_swip_back);
        mListView = (ListView) findViewById(R.id.listView);
        adapter = new MyAdapter();
        mListView.setAdapter(adapter);


    }

    class MyAdapter extends BaseAdapter {
        private int index = -1;// 当前获取焦点的位置
        private HashMap<Integer, String> contentMap = new HashMap<Integer, String>();// 保存edittext的数据,避免软键盘弹出和隐藏对数据的影响

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public String getItem(int position) {
            return "";
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_item_test, null);

            ViewHolder holder = new ViewHolder();
            holder.tv1 = (TextView) view.findViewById(R.id.tv1);
            holder.tv2 = (EditText) view.findViewById(R.id.tv2);

            holder.tv1.setText("tab" + position);

            if (contentMap.get(position) == null) {
                contentMap.put(position, "");// 保存一下
            }

            holder.tv2.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    index = position;
                    return false;
                }
            });

            holder.tv2.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence s, int start, int before,
                                          int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.e("myadapter", "文字内容已经改变,当前文字:" + s.toString());
                    contentMap.put(position, s.toString());// 把改变后的数据存储起来
                }
            });

            if (contentMap.get(position) != null) {
                Log.e("myadapter", position + "content不为空");
                holder.tv2.setText(contentMap.get(position));
            } else {
                Log.e("myadapter", position + "content为空");
                holder.tv2.setText("");
            }

            // 设置焦点和edittext设置光标位置,记住啊.一定要先设置text.然后再设置焦点和光标位置
            if (index != -1 && index == position) {
                holder.tv2.requestFocus();
                holder.tv2.setSelection(holder.tv2.getText().toString().length());
            }

            return view;
        }
    }

    static class ViewHolder {
        TextView tv1;
        EditText tv2;
    }


}
