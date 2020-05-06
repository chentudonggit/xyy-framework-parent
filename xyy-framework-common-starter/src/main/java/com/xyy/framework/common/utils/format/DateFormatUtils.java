package com.xyy.framework.common.utils.format;

import com.xyy.framework.common.utils.assertion.AssertUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间格式化
 *
 * @author chentudong
 * @date 2019/12/1 0:01
 * @since 1.0
 */
public class DateFormatUtils
{
    private DateFormatUtils()
    {
        throw new IllegalStateException("Utility class");
    }

    /**
     * getCalendar
     *
     * @param date date
     * @return Calendar
     */
    public static Calendar getCalendar(Date date)
    {
        date = AssertUtils.isNull(date, new Date());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 加/减分钟数
     *
     * @param date   date
     * @param minute minute
     * @return Date
     */
    public static Date setMinute(Date date, int minute)
    {
        Calendar calendar = getCalendar(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
}
