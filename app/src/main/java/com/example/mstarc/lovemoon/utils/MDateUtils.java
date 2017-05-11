package com.example.mstarc.lovemoon.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.text.format.Time;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author jinghq
 * @category 时间处理通用类
 */
public class MDateUtils {
    Context context;
    public static class MTime {
        String year;
        String month;
        String day;
        String minute;
        String hour;
        String sec;

        public MTime(String year, String month, String day, String hour,
                     String minute, String sec) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.minute = minute;
            this.hour = hour;
            this.sec = sec;

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

        public String getMinute() {
            return minute;
        }

        public void setMinute(String minute) {
            this.minute = minute;
        }

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public String getSec() {
            return sec;
        }

        public void setSec(String sec) {
            this.sec = sec;
        }

    }

    /**
     * @param timezone 时区  "GMT+8"
     * @return 一个带有年，月，日，时，分，秒的日期
     */
    public static MTime getSysTime(String timezone) {
        // "GMT+8"
        Time time = new Time(timezone);
        time.setToNow();

        String year = time.year + "";

        String month = (time.month + 1) + "";
        if (month.length() <= 1) {
            month = "0" + month;
        }
        String day = time.monthDay + "";
        if (day.length() <= 1) {
            day = "0" + day;
        }

        String minute = time.minute + "";

        if (minute.length() <= 1) {
            minute = "0" + minute;
        }
        String hour = time.hour + 8 + "";

        if (hour.length() <= 1) {
            hour = "0" + hour;
        }
        String sec = time.second + "";

        if (sec.length() <= 1) {
            sec = "0" + sec;
        }
        MTime mTime = new MTime(year, month, day, hour, minute, sec);

        return mTime;

    }

    /**
     * @param format 时区
     * @return 一个带有年，月，日，时，分，秒的日期
     */
    public static String getSysTimeForMat(String format) {
        // "GMT+8"
        Time time = new Time("GMT+8");

        return time.format(format);

    }

    /**
     * @return 一个带有年，月，日，时，分，秒的日期
     */
    public static MTime getSysTime() {

        return getSysTime("GMT+8");

    }

    /**
     * @return 当前时间 形式如:2012-12-01 12:12:00
     */
    public static String TimeForMatAll() {
        return TimeForMatDay() + " " + TimeForMatSec();

    }

    /**
     * @return 当前时间 形式如:2012-12-01 12:12
     */
    public static String TimeForMatMin() {
        MTime mt = getSysTime("GMT+8");
        return TimeForMatDay() + " " + mt.hour + ":" + mt.minute;

    }

    /**
     * @return 当前时间 形式如:2012-12-01
     */
    public static String TimeForMatDay() {
        MTime mt = getSysTime("GMT+8");
        return mt.year + "-" + mt.month + "-" + mt.day;

    }

    /**
     * @return 当前时间 形式如:12:12:00
     */
    public static String TimeForMatSec() {
        MTime mt = getSysTime("GMT+8");
        return mt.hour + ":" + mt.minute + ":" + mt.sec;

    }

    /**
     * @category 解析Mstarc接口的特殊时间
     */
    public static long parseMJsonTime(String time) {
        Locale locale = Locale.ENGLISH;
        time = time.toLowerCase(locale);
        time = time.replace("/date(", "").replace(")/", "");
        long longTime = 0;

        try {
            longTime = Long.parseLong(time);
        } catch (Exception e) {
            longTime = 0;
        }

        return longTime;
    }

    /**
     * @param time 要格式化的字符串
     * @return String
     * @author jinghq
     * @category 格式化日期
     */
    public static String formatDate(long time, String formarStr, int offset) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(formarStr, Locale.CHINA);
            Date date = new Date(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY)
                    - offset);
            return df.format(cal.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param time 要格式化的字符串
     * @return String
     * @author jinghq
     * @category 格式化日期
     */
    public static String formatDate(long time, String formarStr) {
        return formatDate(time, formarStr, 0);
    }

    /**
     * @param time      long型数据
     * @param formarStr yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateUnix(long time, String formarStr) {
        long timeu = time;
        timeu = timeu * 1000;
        return formatDate(timeu, formarStr, 0);
    }

    public static String formatDate(long time) {
        return formatDate(time, "yyyy-MM-dd");
    }

    public static String formatDateTime(long time) {
        return formatDate(time, "HH:mm:ss");
    }

    public static String formatDateAll(long time) {
        return formatDate(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDate(String time) {
        return formatDate(time, "yyyy-MM-dd");
    }

    /**
     * @param time 要格式化的字符串
     * @return String
     * @author jinghq
     * @category 格式化日期
     */
    public static String formatDate(String time, String formarStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(formarStr, Locale.CHINA);
            return df.format(df.parse(time));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * @param time 要格式化的字符串
     * @return String
     * @author jinghq
     * @category 格式化日期
     */
    public static Date string2Date(String time, String arStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(arStr, Locale.CHINA);
            return df.parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date string2Date(String time) {
        return string2Date(time, "yyyy-MM-dd");
    }

    /**
     * @param dateStr
     * @return
     * @category utc获取时间
     */
    public static Date getDateFromString(String dateStr) {
        long dateValue = Long.parseLong(dateStr);
        Date date = new Date(dateValue);
        return date;
    }

    /**
     * @param date
     * @param flag
     * @return
     * @category 根据flag获取年、月、日、时、分、秒
     */
    public static int getValueForDate(Date date, int flag) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(flag);
    }

    /**
     * @param time long型时间
     * @param flag true带时分秒,false不带时分秒
     * @return String
     * @author jinghq
     * @category 将long转换成UTC时间
     */
    public static String long2TimeWord(long time, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        int am_pm = cal.get(Calendar.AM_PM);

        String yearStr = year > 9 ? "" + year : "0" + year;
        String monthStr = month > 9 ? "" + month : "0" + month;
        String dayStr = day > 9 ? "" + day : "0" + day;

        String hourStr = hour > 9 ? "" + hour : "0" + hour;
        if (am_pm == Calendar.PM) {
            hour += 12;
            hourStr = hour > 9 ? "" + hour : "" + hour;
        }
        String minStr = min > 9 ? "" + min : "0" + min;
        String secStr = sec > 9 ? "" + sec : "0" + sec;

        switch (flag) {
            case 0:
                return "" + yearStr + "-" + monthStr + "-" + dayStr + " " + hourStr
                        + ":" + minStr + ":" + secStr;
            case 1:
                return "" + yearStr + "-" + monthStr + "-" + dayStr;
            default:
                return "";
        }

    }
    /**
     * 解析MStarc格式的时间
     *
     * @param riqi
     * @param format
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getTime(String riqi, String format) {
        String realtime = "";
        if (MTextUtils.notEmpty(riqi)) {
            riqi = riqi.toLowerCase();
            String time = riqi.replace("/date(", "").replace(")/", "");
            try {
                long ltime = Long.parseLong(time);

                realtime = MDateUtils.formatDate(ltime, format, 8);

            } catch (NumberFormatException e) {


            }

        }
        return realtime;
    };
}
