package com.example.mstarc.lovemoon.bean;

import java.util.List;

/**
 * Created by Mstarc on 2016-12-21.
 */

public class Result  {
    private String stat;
    private List<NewDataModel> data;
    public void setStat(String stat) {
        this.stat = stat;
    }
    public String getStat() {
        return stat;
    }

    public void setData(List<NewDataModel> data) {
        this.data = data;
    }
    public List<NewDataModel> getData() {
        return data;
    }
}
