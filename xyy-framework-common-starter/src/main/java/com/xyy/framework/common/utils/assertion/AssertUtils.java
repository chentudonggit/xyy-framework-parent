package com.xyy.framework.common.utils.assertion;

import com.xyy.framework.common.exceptions.runtime.InsiderException;
import com.xyy.framework.common.exceptions.runtime.UnifiedException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * AssertUtils
 *
 * @author chentudong
 * @date 2019/9/14 13:10
 * @since 1.0
 */
public class AssertUtils
{
    private static final String NULL_ARRAY = "[]";
    private static final String NULL_OBJECT = "{}";

    /**
     * 判断字段是否为空， 为空返回null 否则返回本身
     *
     * @return String
     */
    public static String isNullReturnParam(String key)
    {
        if (StringUtils.isBlank(key))
        {
            return null;
        }
        if (NULL_ARRAY.equals(key) || NULL_OBJECT.equals(key))
        {
            return null;
        }
        return key;
    }

    /**
     * 判断是否存在
     *
     * @param compared compared
     * @param sources  sources
     * @return boolean
     */
    public static boolean isPresence(String compared, List<String> sources)
    {
        boolean flag = false;
        if (Objects.nonNull(compared) && Objects.nonNull(sources) && !sources.isEmpty())
        {
            if (sources.contains(compared))
            {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * obj为空抛出message给用户
     *
     * @param obj     obj
     * @param message message
     * @throws UnifiedException UnifiedException
     */
    public static void isNullThrowMessageToUser(Object obj, String message) throws UnifiedException
    {
        if (Objects.isNull(obj) || StringUtils.isBlank(obj.toString()))
        {
            throwMessageToUser(message);
        }
    }

    /**
     * 判断 var1 是否小于等于  var2
     *
     * @param var1     var1
     * @param var2     var2
     * @param message1 message1
     * @param message2 message2
     * @throws UnifiedException UnifiedException
     */
    public static void beforeOrEqual(Date var1, Date var2, String message1, String message2) throws UnifiedException
    {
        before(var1, var2, message1);
        if (var1.equals(var2))
        {
            throwMessageToUser(message2);
        }
    }

    /**
     * 判断 var1 是否小于  var2
     *
     * @param var1    var1
     * @param var2    var2
     * @param message message
     * @throws UnifiedException UnifiedException
     */
    public static void before(Date var1, Date var2, String message) throws UnifiedException
    {
        isNull(var1, "参数1 不能为空");
        isNull(var2, "参数2 不能为空");
        if (var1.before(var2))
        {
            throwMessageToUser(message);
        }
    }

    /**
     * 抛异常信息给用户
     *
     * @param errorMessage errorMessage
     * @throws UnifiedException UnifiedException
     */
    public static void throwMessageToUser(String errorMessage) throws UnifiedException
    {
        throw new UnifiedException(setMsg(errorMessage));
    }

    /**
     * 封装msg
     *
     * @param msg msg
     * @return String
     */
    private static String setMsg(String msg)
    {
        return StringUtils.isBlank(msg) ? "网络异常， 请稍后重试！" : msg;
    }

    /**
     * @param obj    obj
     * @param result result
     * @param <T>    <T>
     * @return T
     */
    public static <T> T isNull(Object obj, Object result)
    {
        return (Objects.nonNull(obj) && StringUtils.isNotBlank(obj.toString())) ? transform(obj) : transform(result);
    }

    /**
     * 类型转换
     *
     * @param obj obj
     * @param <T> <T>
     * @return T
     */
    public static <T> T transform(Object obj)
    {
        return (T) obj;
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

    public static <T> T isNullReturnParam(Object obj, T t)
    {
        if (isNull(obj))
        {
            return t;
        }
        return transform(obj);
    }
}
