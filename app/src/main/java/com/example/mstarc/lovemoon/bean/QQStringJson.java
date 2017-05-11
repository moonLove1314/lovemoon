package com.example.mstarc.lovemoon.bean;

/**
 * Created by Mstarc on 2016-12-30.
 */

public class QQStringJson {
//    {
//        "error_code": 0,//返回状态码
//            "reason": "success",//返回原因
//            "result": {//返回实体内容
//        "data": {
//            "conclusion": "[大吉+官运+财运+才艺]如龙得云，青云直上，智谋奋进，才略奏功",//QQ号码测试结论
//                    "analysis": "欲望难足希望高，计谋成功财力豪，猜疑嫉妒性自改，如龙乘云势运开。智能超人贯彻大志，富贵无比，不甘寂寞，叱吒风云之大吉数，但容易发生牢骚
//            及贪心、欲望太多而永不知足，为其缺点。切忌沉迷投机，可免贻误前程。"//结论分析
//        }
//    }
//    }
    public String error_code;
    public String reason;
    public Result result;

    public class Result{
           public Data data;
    }
    public class Data{
        public String conclusion;
        public String analysis;
    }
}
