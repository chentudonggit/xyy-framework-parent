package com.xyy.framework.web.common.config.date;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * DateFormatter
 *
 * @author chentudong
 * @date 2019/9/14 15:49
 * @since 1.0
 */
@Component
public class DateFormatter implements Formatter<Date>
{
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Override
    public Date parse(String text, Locale locale) throws ParseException
    {
        return formatter.parse(text);
    }

    @Override
    public String print(Date date, Locale locale) {
        return formatter.format(date);
    }
}

