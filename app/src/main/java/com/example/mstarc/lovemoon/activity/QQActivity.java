package com.example.mstarc.lovemoon.activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mstarc.lovemoon.api.Constans;
import com.example.mstarc.lovemoon.base.BaseActivity;
import com.example.mstarc.lovemoon.bean.QQStringJson;
import com.example.mstarc.lovemoon.utils.ToastUtils;
import com.example.mstarc.lovemoon.view.WeiboDialogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class QQActivity extends BaseActivity implements View.OnClickListener{
    private TextView tv_conclusion;
    private TextView tv_analysis;
    private EditText et_find_qq;
    private Button btn_search;
    private Dialog dialog;
    @Override
    public int contentView() {
        return R.layout.activity_qq;
    }

    @Override
    public void initView() {
        tv_conclusion = (TextView) findViewById(R.id.tv_conclusion);
        tv_analysis = (TextView) findViewById(R.id.tv_analysis);
        et_find_qq = (EditText) findViewById(R.id.et_keywords);
        btn_search = (Button) findViewById(R.id.btn_search);

        getRxJavaEngine(Constans.QQ_BASE_URL);

    }
    private void getData(String keyWord){
        mEngine.getQQdatas(Constans.QQ_API_KEY,keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<QQStringJson>() {
                    @Override
                    public void onCompleted() {
                        WeiboDialogUtils.closeDialog(dialog);
                    }

                    @Override
                    public void onError(Throwable e) {
                        WeiboDialogUtils.closeDialog(dialog);
                    }

                    @Override
                    public void onNext(QQStringJson qqStringJson) {
                        WeiboDialogUtils.closeDialog(dialog);
                        ToastUtils.showInfo(QQActivity.this,qqStringJson.reason);
                        tv_conclusion.setText(qqStringJson.result.data.conclusion);
                        tv_analysis.setText(qqStringJson.result.data.analysis);

                    }
                });
    }

    @Override
    public void setListener() {
        btn_search.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        if(v == btn_search){
            String keyWord = et_find_qq.getText().toString().trim();

            if(keyWord.equals("")){
                ToastUtils.showInfo(this,"关键字不能为空");
            }else{
                dialog = WeiboDialogUtils.createLoadingDialog(this,"加载中...");
                getData(keyWord);
                View view = getWindow().peekDecorView();
                if (v != null) {
                    InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }

        }

    }
}
