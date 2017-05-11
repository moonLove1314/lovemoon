package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;

/**
 * Created by Mstarc on 2016-12-22.
 */

public class SongResult implements Serializable {

//    "id": "5b1af161-3c30-41cf-87cb-238c649c0945",
//            "name": "梦李白二首"
//    "error_code": 0,
//            "reason": "Succes",
//            "result": {
//        "id": "fbbc1ccd-fabb-4429-9277-e78bb46c0d25",
//                "biaoti": "从今若许才相见",
//                "jieshao": "【注释】\r\n",
//                "zuozhe": "力量的力",
//                "neirong": "沧海有情覆万山，青天无泪满人间。\r\n人生若只如初见，不是欢心不是缘。\r\n"
//    }
//}

    private String id;
    private String name;
    private String biaoti;
    private String jieshao;
    private String neirong;
    private String zuozhe;


    public String getZuozhe() {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getJieshao() {
        return jieshao;
    }

    public void setJieshao(String jieshao) {
        this.jieshao = jieshao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
