package com.example.mstarc.lovemoon.bean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-22.
 */

public class SongJson {

//    "total": 12,
//            "result": [
//    {
//            "error_code": 0,
//            "reason": "Succes"


    private String total;
    private List<SongResult> result;
    private String error_code;
    private String reason;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public List<SongResult> getResult() {
        return result;
    }

    public void setResult(List<SongResult> result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
