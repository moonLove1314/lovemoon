package com.example.mstarc.lovemoon.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 俊杰 on 2016/7/19.
 */
public class MTextUtils {

    /**
     * Returns true if the string is null or 0-length
     *
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        if (text == null) {
            return true;
        } else {
            return TextUtils.isEmpty(text);
        }
    }

    /**
     * 验证是不是中国大陆正确的手机号
     *
     * @param PhoneNum
     * @return
     */
    public static boolean isPhone(String PhoneNum) {
        String str = "^((13[0-9])|(147)|(149)|(145)|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";
        if (PhoneNum == null) {
            return false;
        } else {
            return RegexCheckValidity(PhoneNum, str);
        }

    }

    /**
     * @param data  校验目标字符串
     * @param regex 正则表达式
     * @return boolean 是否匹配正则
     * @author jinghq
     * @category 根据正则检查数据有效性
     */
    public static boolean RegexCheckValidity(String data, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(data);
        return m.matches();
    }

    /**
     * Returns true if the text is not null
     *
     * @param text
     * @return
     */
    public static boolean notEmpty(String text) {
        return !isEmpty(text);
    }


    /**
     * @param  context
     * @param  data
     * */
    public static  void showInfo(Context context, String data) {

        Toast.makeText(context, data, Toast.LENGTH_LONG).show();
    }
    /**
     * Returns true if the string is null or 0-length or without space is null
     *
     * @param text
     * @return
     */
    public static boolean isAbsolutelyEmpty(String text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        } else {
            if (TextUtils.isEmpty(text.replaceAll(" ", ""))) {
                return true;
            } else {
                return false;
            }
        }
    }
    /**
     * 解析MStarc格式的时间
     *
     * @param riqi
     * @return
     */

    public static String getTime(String riqi) {
        return getTime(riqi, "yyyy-MM-dd HH:mm:ss");
    };

    /**
     * 解析MStarc格式的时间
     *
     * @param riqi
     * @param format
     * @return
     */
    public static String getTime(String riqi, String format) {

        // /Date(1449410400000+0800)/

        riqi = riqi.replace("+0800", "");

        String realtime = "";
        if (MTextUtils.notEmpty(riqi)) {
            riqi = riqi.toLowerCase();
            String time = riqi.replace("/date(", "").replace(")/", "");
            try {
                long ltime = Long.parseLong(time);

                realtime = MDateUtils.formatDate(ltime, format, 8);
                // Out.i("CommMethod", "时间: " + realtime);
            } catch (NumberFormatException e) {
                // Out.e("CommMethod", "时间值错误");
                realtime = "时间值错误";
            }

        }
        return realtime;
    };


}
