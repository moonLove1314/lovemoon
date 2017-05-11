package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;

/**
 * Created by Mstarc on 2016-12-20.
 */

public class HistoryListBean implements Serializable{
//    "_id": "17221220",
//            "title": "康熙皇帝驾崩",
//            "pic": "http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201108/4/42142833957.jpg",
//            "year": 1722,
//            "month": 12,
//            "day": 20,
//            "des": "在294年前的今天，1722年12月20日 (农历冬月十三)，康熙皇帝驾崩。",
//            "lunar": "壬寅年冬月十三"
//    content
    private String _id;
    private String title;
    private String pic;
    private String year;
    private String month;
    private String day;
    private String des;
    private String lunar;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }
}
