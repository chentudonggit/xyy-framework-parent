package com.xyy.framework.common.web.request.parameter;

import com.xyy.framework.common.utils.assertion.AssertUtils;

import java.util.Map;

/**
 * 解析请求的参数
 * @author chentudong
 * @date 2019/9/14 13:08
 * @since 1.0
 */
public class RequestMap
{
    /**
     * 获取请求的参数
     *
     * @param map map
     * @param key key
     * @param <T> <T>
     * @return T
     */
    public static <T> T getParameter(Map map, String key)
    {
        AssertUtils.isNull(key, "key 不能为空");
        AssertUtils.isNull(map, "map 为空, 解析失败！");
        if (map.containsKey(key))
        {
            return (T) map.get(key);
        }
        return null;
    }
}
