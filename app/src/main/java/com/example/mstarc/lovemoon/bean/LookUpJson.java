package com.example.mstarc.lovemoon.bean;

/**
 * Created by Mstarc on 2016-12-22.
 */

public class LookUpJson {
//    {
//        "result": {
//        "id": "fbbc1ccd-fabb-4429-9277-e78bb46c0d25",
//                "biaoti": "从今若许才相见",
//                "jieshao": "【注释】\r\n",
//                "zuozhe": "力量的力",
//                "neirong": "沧海有情覆万山，青天无泪满人间。\r\n人生若只如初见，不是欢心不是缘。\r\n"
//    },
//        "error_code": 0,
//            "reason": "Succes"

    private String error_code;
    private String reason;
    private SongResult result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public SongResult getResult() {
        return result;
    }

    public void setResult(SongResult result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
