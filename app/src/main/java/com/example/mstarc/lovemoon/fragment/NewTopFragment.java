package com.example.mstarc.lovemoon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mstarc.lovemoon.activity.NewDetailsActivity;
import com.example.mstarc.lovemoon.activity.R;
import com.example.mstarc.lovemoon.adapter.NewListAdapter;
import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.api.Engine;
import com.example.mstarc.lovemoon.base.BaseFragment;
import com.example.mstarc.lovemoon.bean.NewDataModel;
import com.example.mstarc.lovemoon.bean.NewJson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NewTopFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    private ListView mListView;
    private FrameLayout fl;
    private Engine mEnigine;
    private NewListAdapter adapter;
    private List<NewDataModel> list = new ArrayList<>();
    private String type;
    private String text;
    private LinearLayout ll_pb;
//    ,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    public NewTopFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            text = getArguments().getString("text");
            if(text.equals("推荐")){
               type = "top";
            }else if(text.equals("社会")){
                type = "shehui";
            }else if(text.equals("国内")){
                type = "guonei";
            } else if(text.equals("国际")) {
                type = "guoji";
            }else if(text.equals("娱乐")){
                type = "yule";
            }else if(text.equals("体育")){
                type = "tiyu";
            }else if(text.equals("军事")){
               type = "junshi";
            }else if(text.equals("科技")){
                type = "keji";
            }else if(text.equals("财经")){
                type = "caijing";
            }else if (text.equals("时尚")){
                type = "shishang";
            }
        }

    }


    @Override
    public View bindLayout(LayoutInflater inflater) {
        mRootView = inflater.inflate(R.layout.fragment_new_top, null);
        return mRootView;
    }

    @Override
    public void initView() {
        ll_pb = (LinearLayout) mRootView.findViewById(R.id.ll_pb);
        ll_pb.setVisibility(View.VISIBLE);
        fl = (FrameLayout) mRootView.findViewById(R.id.fl);
        fl.setBackgroundResource(R.mipmap.ll_bg);
        mListView = (ListView) mRootView.findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        mEnigine = new Retrofit.Builder()
                .baseUrl(Constans.NEW_TOP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Engine.class);
    }
    @Override
    protected void initData() {
        getData();
    }
    private void getData(){
         Log.e("类型",type);


         mEnigine.getNewJsonData(type,Constans.NEW_TOP_API_KEY)
                 .enqueue(new Callback<NewJson>() {
            @Override
            public void onResponse(Call<NewJson> call, Response<NewJson> response) {
                NewJson bean = response.body();
                list = bean.getResult().getData();
                adapter = new NewListAdapter(list);
                mListView.setAdapter(adapter);
                ll_pb.setVisibility(View.GONE);
                fl.setBackgroundResource(R.mipmap.bg);
            }

            @Override
            public void onFailure(Call<NewJson> call, Throwable t) {
                Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                ll_pb.setVisibility(View.GONE);
            }
        });



    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        NewDataModel bean = adapter.getItem(i);
        Intent intent = new Intent(getActivity(), NewDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",bean);
        intent.putExtras(bundle);
        startActivity(intent);


    }
}
