package util;

import constant.LoginConstant;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lirb
 * 功能：
 * 日期：2023-4-10-14:23
 * 版本       开发者     描述
 * 1.0.0     lirb     ...
 */
public class DateUtil {
    private static final String COMMON_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
    }

    /**
     * 转化为日期
     * */
    public static Date parseDate(String dateStr, String pattern){
        if (dateStr == null || dateStr.isEmpty()) {
            throw new IllegalArgumentException("dateStr is required");
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(LoginConstant.REGISTINGFOUNDMORECARDID);

        }
    }

    public static Date parseDate(String dateStr){
        return parseDate(dateStr, COMMON_DATE_PATTERN);
    }

    public static Date parseDate(Long time){
        return time == null ? null : new Timestamp(time);
    }

    /**
     * 日期转化为字符串
     * */
    public static String format2Str(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static boolean betweenTime(LocalTime localTime, String beginTimeStr, String endTimeStr) {
        Date now = DateUtil.parseDate(localTime.toString(), "HH:mm");
        Date beginTime = parseDate(beginTimeStr, "HH:mm");
        Date endTime = parseDate(endTimeStr, "HH:mm");
        return now.getTime() >= beginTime.getTime() && now.getTime() < endTime.getTime();
    }


    public static String format2Str(Date date) {
        return format2Str(date, COMMON_DATE_PATTERN);
    }

    public static Long getTime(Date date) {
        return date == null ? null : date.getTime();
    }

    public static Long getTime(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        Date date = parseDate(dateStr);
        return date == null ? null : date.getTime();
    }

    // 获取单天凌晨时间戳
    public static Date getDateZero(){
        //今天零点零分零秒的毫秒数
        long zero=System.currentTimeMillis()/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();

        return new Date(zero);
    }

    /**
     * 获得当月1号零时零分零秒
     * @return
     */
    public static Date initDateByMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public static Date getEndOfDay(Date date) {
        if (date == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     *  设置日期的加减。
     * @param date
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date filling(Date date ,int year,int month, int day,int hour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DAY_OF_MONTH,day);
        calendar.add(Calendar.HOUR_OF_DAY,hour);
        return  calendar.getTime();
    }

    public static Date setTime(Date date ,Integer hour,Integer minute, Integer second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(hour != null)
            calendar.set(Calendar.HOUR_OF_DAY, hour);
        if(minute != null)
            calendar.set(Calendar.MINUTE, minute);
        if(second != null)
            calendar.set(Calendar.SECOND,second);
        return  calendar.getTime();
    }

    public static Date addTime(Date date ,Integer hour,Integer minute, Integer second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(hour != null){
            calendar.add(Calendar.HOUR_OF_DAY, hour);
        }

        if(minute != null){
            calendar.add(Calendar.MINUTE, minute);
        }

        if(second != null){
            calendar.add(Calendar.SECOND,second);
        }
        return  calendar.getTime();
    }

    public static Date addDate(Date date , Integer year, Integer month, Integer day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(year != null){
            calendar.add(Calendar.YEAR, year);
        }
        if(month != null){
            calendar.add(Calendar.MONTH, month);
        }
        if(day != null){
            calendar.add(Calendar.DATE,day);
        }
        return  calendar.getTime();
    }

    public static Date addDateByDay(Date date,Integer day) {
        return addDate(date,null,null,day);
    }

    public static Date hourFilling(Date date,int hour){

        return filling(date ,0,0, 0,hour);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static long differentDaysByMillisecond(Date date1,Date date2)
    {
        long l = date2.getTime() - date1.getTime();
        return l;
    }



    public static Date getOneMonthAgo(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);
        return calendar.getTime();
    }
}
