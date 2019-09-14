package com.xyy.framework.common.utils.id;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

/**
 * 生成惟一id
 *
 * @author chentudong
 * @date 2019/9/14 13:15
 * @since 1.0
 */
public class IdGeneratorUtils
{
    /**
     * 生成唯一id
     *
     * @return String
     */
    public static String uniqueId()
    {
        return NanoIdUtils.randomNanoId();
    }
}
