package com.xyy.framework.common.exceptions.runtime;

/**
 * 抛出给开发人员
 *
 * @author chentudong
 * @date 2019/9/14 13:12
 * @since 1.0
 */
public class InsiderException extends RuntimeException
{
    public InsiderException(String message)
    {
        super(message);
    }
}
