package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mstarc on 2017-01-11.
 */

public class QWYS implements Serializable {

    public String reason;
    public String error_code;
    public List<Result> result;

    public class Result implements Serializable{
        public String ctime;
        public String title;
        public String description;
        public String picUrl;
        public String url;
    }
}
