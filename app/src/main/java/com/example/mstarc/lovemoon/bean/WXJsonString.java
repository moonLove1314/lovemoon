package com.example.mstarc.lovemoon.bean;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class WXJsonString {
//    {
//        "reason": "success",
//            "result": {
//        "list": [
//        "totalPage": 16,
//                "ps": 20,
//                "pno": 1
//    },
//        "error_code": 0
//    }
    private String reason;
    private String error_code;
    private WXResult result;

    public WXResult getResult() {
        return result;
    }

    public void setResult(WXResult result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
