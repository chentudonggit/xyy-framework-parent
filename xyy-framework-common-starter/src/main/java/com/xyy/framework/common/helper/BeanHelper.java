package com.xyy.framework.common.helper;

import com.xyy.framework.common.uilts.AssertUtils;
import org.dozer.DozerBeanMapper;

import java.util.*;

/**
 * BeanHelper
 *
 * @author chentudong
 * @date 2019/11/9 22:56
 * @since 1.0
 */
public class BeanHelper
{
    private static DozerBeanMapper dozerBeanMapper;

    static
    {
        dozerBeanMapper = new DozerBeanMapper();
    }

    private BeanHelper()
    {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 实体转换
     *
     * @param s      源
     * @param tClass 目标
     * @param <T>    T
     * @param <S>    S
     * @return T
     */
    public static <T, S> T convert(S s, Class<T> tClass)
    {
        return Objects.isNull(s) ? null : dozerBeanMapper.map(s, tClass);
    }

    /**
     * Convert list.
     *
     * @param <T>     the type parameter
     * @param <S>     the type parameter
     * @param sources the sources
     * @param clazz   the clazz
     * @return the list
     */
    public static <T, S> List<T> convert(Collection<S> sources, Class<T> clazz)
    {
        List<T> result = new ArrayList<>();
        if (Objects.nonNull(sources))
        {
            for (S s : sources)
            {
                result.add(convert(s, clazz));
            }
        }
        return result;
    }

    /**
     * 获取参数
     *
     * @param map map
     * @param key key
     * @param <T> T
     * @return T
     */
    public static <T> T getParams(Map map, String key)
    {
        if (AssertUtils.isNull(map) || AssertUtils.isNull(key))
        {
            return null;
        }
        return (T) map.get(key);
    }
}
