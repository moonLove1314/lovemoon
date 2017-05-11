package com.example.mstarc.lovemoon.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleListChoiceActivity extends AppCompatActivity {

    private ListView listView;

    private Map<Integer, Boolean> isSelected;

    private List beSelectedData = new ArrayList();

    ListAdapter adapter;

    private List cs = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list_choice);
        listView = (ListView) this.findViewById(R.id.lv_single_list);
        cs  = new ArrayList();
        cs.add("aaaaaa");
        cs.add("bbbbbb");
        cs.add("cccccc");
        cs.add("dddddd");
        cs.add("eeeeee");
        cs.add("ffffff");
        cs.add("gggggg");
        cs.add("hhhhhh");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");
        cs.add("jjjjjj");

        initList();
    }

    void initList(){

        if (cs == null || cs.size() == 0)
            return;
        if (isSelected != null)
            isSelected = null;
        isSelected = new HashMap<Integer, Boolean>();
        for (int i = 0; i < cs.size(); i++) {
            isSelected.put(i, false);
        }
        // 清除已经选择的项
        if (beSelectedData.size() > 0) {
            beSelectedData.clear();
        }
        adapter = new ListAdapter(this, cs);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.i("map", cs.get(position).toString());
            }
        });

    }

    class ListAdapter extends BaseAdapter {

        private Context context;

        private List cs;

        private LayoutInflater inflater;

        public ListAdapter(SingleListChoiceActivity context, List data) {
            this.context = context;
            this.cs = data;
            initLayoutInflater();
        }

        void initLayoutInflater() {
            inflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return cs.size();
        }

        public Object getItem(int position) {
            return cs.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position1, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            View view = null;
            final int position = position1;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_adapter,
                        null);
                holder = new ViewHolder();
                holder.checkBox = (CheckBox) convertView
                        .findViewById(R.id.item_cb_section);
                holder.tv_sequence = (TextView) convertView
                        .findViewById(R.id.tv_zxing_section_sequence);
                holder.tv_sectionname = (TextView) convertView
                        .findViewById(R.id.tv_zxing_sectionname);
                convertView.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // 当前点击的CB
                    boolean cu = !isSelected.get(position);
                    // 先将所有的置为FALSE
                    for(Integer p : isSelected.keySet()) {
                        isSelected.put(p, false);
                    }
                    // 再将当前选择CB的实际状态
                    isSelected.put(position, cu);
                    ListAdapter.this.notifyDataSetChanged();
                    beSelectedData.clear();
                    if(cu) beSelectedData.add(cs.get(position));
                }
            });
            holder.tv_sequence.setText(String.valueOf(position + 1));
            holder.tv_sectionname.setText(cs.get(position).toString());
            holder.checkBox.setChecked(isSelected.get(position));
            return convertView;
        }

        public void notifyDataSetChanged() {
        }
    }

    class ViewHolder {

        CheckBox checkBox;

        TextView tv_sequence;

        TextView tv_sectionname;

    }
}
