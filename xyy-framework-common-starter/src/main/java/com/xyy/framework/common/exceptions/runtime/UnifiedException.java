package com.xyy.framework.common.exceptions.runtime;

/**
 * 统一弹窗异常
 *
 * @author chentudong
 * @date 2019/9/14 13:12
 * @since 1.0
 */
public class UnifiedException extends RuntimeException
{
    public UnifiedException(String message)
    {
        super(message);
    }
}
