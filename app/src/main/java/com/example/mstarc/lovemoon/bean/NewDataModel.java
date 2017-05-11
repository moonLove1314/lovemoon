package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;

/**
 * Created by Mstarc on 2016-12-21.
 */

public class NewDataModel implements Serializable {
//    "title": "农民老家倒塌出现一口缸，俩儿子听闻，连夜从外地赶回！",
//            "date": "2016-12-21 09:33",
//            "author_name": "旁观者",
//            "thumbnail_pic_s": "http://05.imgmini.eastday.com/mobile/20161221/20161221_5ad04cba4cc22e0e357fc681b2468f82_cover_mwpm_03200403.png",
//            "thumbnail_pic_s02": "http://05.imgmini.eastday.com/mobile/20161221/20161221_5ad04cba4cc22e0e357fc681b2468f82_cover_mwpl_05500201.png",
//            "thumbnail_pic_s03": "http://05.imgmini.eastday.com/mobile/20161221/20161221_5ad04cba4cc22e0e357fc681b2468f82_cover_mwpl_05500201.png",
//            "url": "http://mini.eastday.com/mobile/161221093316731.html?qid=juheshuju",
//            "uniquekey": "161221093316731",
//            "type": "头条",
//            "realtype": "社会"

    private String title;
    private String date;
    private String author_name;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;
    private String url;
    private String uniquekey;
    private String type;
    private String realtype;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRealtype() {
        return realtype;
    }

    public void setRealtype(String realtype) {
        this.realtype = realtype;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
