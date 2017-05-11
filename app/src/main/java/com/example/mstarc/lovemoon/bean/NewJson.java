package com.example.mstarc.lovemoon.bean;

/**
 * Created by Mstarc on 2016-12-21.
 */

public class NewJson {

//    "reason": "成功的返回",
//            "result": {
//        "stat": "1",
//                "data": [
//        {
    private String reason;
    private Result result;
//    private String stat;
//    private List<NewDataModel> data;
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

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
