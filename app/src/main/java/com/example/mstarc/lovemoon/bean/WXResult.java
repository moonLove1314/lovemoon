package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mstarc on 2016-12-26.
 */

public class WXResult implements Serializable{

    private String totalPage;
    private String ps;
    private String pno;
    private List<WXList> list;

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public List<WXList> getList() {
        return list;
    }

    public void setList(List<WXList> list) {
        this.list = list;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
