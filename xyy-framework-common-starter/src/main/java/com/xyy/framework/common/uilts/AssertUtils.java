package com.xyy.framework.common.uilts;

import com.xyy.framework.common.exceptions.runtime.InsiderException;
import com.xyy.framework.common.exceptions.runtime.UnifiedException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * AssertUtils
 *
 * @author chentudong
 * @date 2019/11/9 16:58
 * @since 1.0
 */
public class AssertUtils
{
    private AssertUtils()
    {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判空抛异常给开发者
     *
     * @param obj obj
     * @param msg msg
     */
    public static void isNull(Object obj, String msg)
    {
        if (isNull(obj))
        {
            msgDevelopment(msg);
        }
    }

    /**
     * isNullToUser
     *
     * @param obj obj
     * @param msg msg
     */
    public static void isNullToUser(Object obj, String msg)
    {
        if (isNull(obj))
        {
            msgUser(msg);
        }
    }

    /**
     * boolean isNull
     *
     * @param obj obj
     * @return boolean
     */
    public static boolean isNull(Object obj)
    {
        return (Objects.isNull(obj) || StringUtils.isBlank(obj.toString()));
    }

    /**
     * 为空， 抛出RuntimeException
     *
     * @param obj obj
     * @param e   e
     */
    public static void isNull(Object obj, RuntimeException e)
    {
        if (isNull(obj))
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断为空,返回 null
     *
     * @param obj obj
     * @param <T> <T>
     * @return T
     */
    public static <T> T isNullReturnNull(Object obj)
    {
        return isNull(obj) ? null : (T) obj;
    }

    /**
     * msgDevelopment
     *
     * @param msg msg
     */
    public static void msgDevelopment(String msg)
    {
        throw new InsiderException(msg(msg));
    }

    /**
     * msgUser
     *
     * @param msg msg
     */
    public static void msgUser(String msg)
    {
        throw new UnifiedException(msg(msg));
    }

    /**
     * msg
     *
     * @param msg msg
     * @return String
     */
    public static String msg(String msg)
    {
        return StringUtils.isBlank(msg) ? "网络异常，请稍后重试！" : msg;
    }
}
