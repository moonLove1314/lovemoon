package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mstarc on 2017-01-16.
 */

public class TodayOfHistoryString implements Serializable {

    public String error_code;
    public String reason;
    public String total;
    public List<TodayOfHistryRusult> result;

    public class TodayOfHistryRusult{
         public String year;
         public String day;
         public String month;
         public String title;
         public String type;
    }

}
