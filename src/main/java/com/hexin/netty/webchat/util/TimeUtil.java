package com.hexin.netty.webchat.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author hexin
 *
 * 2019年6月20日 下午4:11:41
 */
public class TimeUtil {
    private TimeUtil(){};

    /******yyyyMMddHHmmssSSS***/
    public static final String FORMAT_MILSECOND = "yyyyMMddHHmmssSSS";
    /**yyyy-MM-dd HH:mm:ss**/
    public static final String FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    /**yyyy-MM-dd HH:mm**/
    public static final String FORMAT_MIN = "yyyy-MM-dd HH:mm";
    /**yyyy-MM-dd**/
    public static final String FORMAT_DAY = "yyyy-MM-dd";
    /**HH:mm:ss**/
    public static final String FORMAT_TIME = "HH:mm:ss";
    /**HH:mm:ss**/
    public static final String FORMAT_TIME2 = "HH-mm-ss";
    /**
     *
     * 将日期转成特定格式的字符串
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if(null == date || StringUtils.isEmpty(format)){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static Date getCurrentDate(){
        return Calendar.getInstance().getTime();
    }

    /**
     *
     * 将日期字符串转成Date
     * @param dateStr
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String format) throws ParseException {
        if(StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(format)){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateStr);
    }
}
