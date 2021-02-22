package com.java.tool;

import com.java.string.StringCheck;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTools {
    /*** 1毫秒 */
    public static final long ONE_MILLI_SECOND = 1l;
    /*** 1秒 */
    public static final long ONE_SECOND = ONE_MILLI_SECOND * 1000;
    /*** 1分钟 */
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    /*** 1小时 */
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    /*** 1天 */
    public static final long ONE_DAY = ONE_HOUR * 24;


    /**
     * 根据日期时间字符串获取日期时间
     *
     * @param datetime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime parseByFormat(@Nonnull String datetime, String format) {

        if (StringCheck.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * 转换格式
     * 将任意输入格式的日期字符串转换为yyyyMMdd
     *
     * @param date
     * @param format 为输入的格式
     * @return yyyyMMdd
     */
    public static String transferDate(String date, String format) {
        if ("yyyy-MM".equals(format)) {
            return transferMonth(date, format);
        }
        LocalDate localDate = parseByFormatDate(date, format);
        StringBuffer re = new StringBuffer();
        re.append(localDate.getYear());
        int a = localDate.getMonth().getValue();
        String month = a < 10 ? "0" + a : String.valueOf(a);
        re.append(month);
        int dayOfMonth = localDate.getDayOfMonth();
        String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
        re.append(day);
        return re.toString();
    }

    /**
     * 根据日期字符串获取日期
     *
     * @param date
     * @param format 为null时默认为yyyy-MM-dd
     * @return
     */
    public static LocalDate parseByFormatDate(@Nonnull String date, String format) {
        if (StringCheck.isEmpty(format)) {
            format = "yyyy-MM-dd";
        }
        if ("yyyy-MM".equals(format)) {
            date = transferMonth(date, format) + "01";
            format = "yyyyMMdd";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }

    /**
     * 获取指定格式的日期字符串
     *
     * @param dateTime
     * @param format   为null或者空时默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateTime(@Nonnull LocalDateTime dateTime, String format) {
        //时间转字符串格式化
        if (StringCheck.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.SIMPLIFIED_CHINESE);
        return dateTime.format(formatter);
    }


    /**
     * LocalDateTime 转为 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 根据时间获取时间戳
     *
     * @param dateTime
     * @return
     */
    public static Long dateTimeToTimestamp(LocalDateTime dateTime) {

        if (dateTime == null) {
            return null;
        }
        return dateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }


    /**
     * 根据时间戳获取日期时间
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToDateTime(Long timestamp) {
        return timestamp == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }


    /**
     * 获取当日开始时间0点
     */
    public static LocalDateTime todayStartTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
    }

    /**
     * 获取当日结束时间0点
     *
     * @return
     */
    public static LocalDateTime todayEndTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    }

    /**
     * 转换yyyy-MM类型的日期
     * 将时间字符串转换为yyyyMM格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String transferMonth(String date, String format) {
        StringBuffer yearMonth = new StringBuffer();
        StringBuffer year = new StringBuffer();
        StringBuffer Month = new StringBuffer();
        int len = format.length();
        if (len == 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            char a = format.charAt(i);
            switch (a) {
                case 'y':
                    year.append(date.charAt(i));
                    break;
                case 'M':
                    Month.append(date.charAt(i));
                    break;
            }
        }
        yearMonth.append(year).append(Month);
        return yearMonth.toString();
    }

    public static String getGMTTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        return sdf.format(c.getTime());
    }

}
