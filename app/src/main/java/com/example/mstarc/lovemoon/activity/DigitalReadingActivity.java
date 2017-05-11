package com.example.mstarc.lovemoon.activity;

import android.widget.TextView;

import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.DigitalDatas;
import com.example.mstarc.lovemoon.utils.ToastUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DigitalReadingActivity extends BaseActivity {
    private TextView tv;
    @Override
    public int contentView() {
        return R.layout.activity_digital_reading;
    }

    @Override
    public void initView() {
        tv = (TextView) findViewById(R.id.tv);
        getRxJavaEngine(Constans.DIGITAL_READING_BASE_URL);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {
        mEngine.getDigitalDatas(Constans.DIGITAL_READING_API_KEY)
              .subscribeOn(Schedulers.io())
//              .unsubscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Subscriber<DigitalDatas>() {
                  @Override
                  public void onCompleted() {
                      ToastUtils.showInfo(DigitalReadingActivity.this,"完成");
                  }

                  @Override
                  public void onError(Throwable e) {

                      ToastUtils.showInfo(DigitalReadingActivity.this,e.getMessage());
                  }

                  @Override
                  public void onNext(DigitalDatas digitalDatas) {
                      List<DigitalDatas.Rank> rank = digitalDatas.result.GetRankTypeRsp.RankList.Rank;
                      tv.setText(rank.get(0).rankName+"\n"+rank.get(1).rankName+"\n"+rank.get(2).rankName+"\n"+rank.get(3).rankName);
                      ToastUtils.showInfo(DigitalReadingActivity.this,digitalDatas.reason);
                  }
              });

    }
}
