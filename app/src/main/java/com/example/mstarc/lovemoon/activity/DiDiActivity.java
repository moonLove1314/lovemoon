package com.example.mstarc.lovemoon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mstarc.lovemoon.bean.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DiDiActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "DiDiActivity";
    private ListView mListView;
    private List<User> list = new ArrayList<>();
    private MyAdapter adapter;
    private HashMap<User,CheckBox> selected= new HashMap<>();

    private ArrayList<Boolean> checkList = new ArrayList<>(); // 判断listview单选位置
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_di_di);
        mListView = (ListView) findViewById(R.id.listView);
        addData();
        adapter = new MyAdapter();
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

    }

    //设置选中的位置，将其他位置设置为未选
    public void checkPosition(int position) {
        for (int i = 0; i < checkList.size(); i++) {
            if (position == i) {// 设置已选位置
                checkList.set(i, true);
            } else {
                checkList.set(i, false);
            }
        }
        adapter.notifyDataSetChanged();
    }


    private void addData(){
        for (int i = 0;i < 20;i++){
         list.add(new User("aa"));
            checkList.add(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          startActivity(new Intent(this, NewTopActivity.class));
    }

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_test_item,null);
            User user = list.get(position);
            CheckBox chb_play = (CheckBox) view.findViewById(R.id.chb_play);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_name.setText(user.getName()+position);
            chb_play.setChecked(checkList.get(position));
            chb_play.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                       checkPosition(position);
                    }else{
                        checkList.set(position,false);
                    }
                }
            });

            return view;
        }
    }
  private void setSelectOne(User user){
      Iterator<User> iterator = selected.keySet().iterator();
      while(iterator.hasNext()){
          User key = iterator.next();
          CheckBox checkBox = selected.get(key);
          checkBox.setChecked(false);


      }

  }
}
