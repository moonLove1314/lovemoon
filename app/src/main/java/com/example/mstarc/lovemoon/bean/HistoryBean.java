package com.example.mstarc.lovemoon.bean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-20.
 */

public class HistoryBean {


//    "reason": "请求成功！",
//            "error_code": 0
    private List<HistoryListBean> result;
    private String reason;
    private String error_code;


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<HistoryListBean> getResult() {
        return result;
    }

    public void setResult(List<HistoryListBean> result) {
        this.result = result;
    }
}
