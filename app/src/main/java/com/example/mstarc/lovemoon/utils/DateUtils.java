package com.example.mstarc.lovemoon.utils;

import java.util.Calendar;

/**
 * Created by Mstarc on 2016-12-20.
 */

public class DateUtils {
    private static Calendar calendar = Calendar.getInstance();

    public static String getCurrentMonth(){
        String month = (calendar.get(Calendar.MONTH)+1)+"";
        return month;
    }
    public static String getCurrentDay(){
        String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
        return  day;
    }


}
