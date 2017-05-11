package com.example.mstarc.lovemoon.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mstarc on 2017-01-14.
 */

public class FoodString implements Serializable {

    public String error_code;
    public String reason;
    public List<FoodResult> result;

    public class FoodResult{
        public String id;
        public String name;
    }
}
