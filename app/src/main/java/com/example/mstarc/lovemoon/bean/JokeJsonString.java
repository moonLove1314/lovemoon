package com.example.mstarc.lovemoon.bean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class JokeJsonString {
    private List<JokeResult> result;
    private String error_code;
    private String reason;

    public List<JokeResult> getResult() {
        return result;
    }

    public void setResult(List<JokeResult> result) {
        this.result = result;
    }

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
}
