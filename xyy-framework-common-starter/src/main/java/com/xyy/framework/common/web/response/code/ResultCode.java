package com.xyy.framework.common.web.response.code;

/**
 * 定义返回响应的规范
 *
 * @author chentudong
 * @date 2019/9/14 13:21
 * @since 1.0
 */
public interface ResultCode
{
    /**
     * success
     *
     * @return Boolean
     */
    Boolean success();

    /**
     * code
     *
     * @return Integer
     */
    Integer code();

    /**
     * message
     *
     * @return String
     */
    String message();
}
