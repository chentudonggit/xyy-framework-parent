package com.xyy.framework.common.enums.codes.common;

import com.xyy.framework.common.web.constants.code.response.ResultCodeSequence;
import com.xyy.framework.common.web.response.code.ResultCode;

/**
 * CommonCode
 *
 * @author chentudong
 * @date 2019/9/14 21:44
 * @since 1.0
 */
public enum CommonCode implements ResultCode
{
    /**
     * SUCCESS 操作成功
     */
    SUCCESS(true, ResultCodeSequence.COMMON + 1, "操作成功！"),
    /**
     * FAIL 操作失败
     */
    FAIL(false, ResultCodeSequence.COMMON + 2, "操作失败！"),
    /**
     * UNAUTHENTICATED 此操作需要登陆系统
     */
    UNAUTHENTICATED(false, ResultCodeSequence.COMMON + 3, "此操作需要登陆系统！"),
    /**
     * UNAUTHORISED 权限不足，无权操作！
     */
    UNAUTHORISED(false, ResultCodeSequence.COMMON + 4, "权限不足，无权操作！"),
    /**
     * MISSING_REQUEST_BODY Post请求中，body中没有传输数据！
     */
    MISSING_REQUEST_BODY(false, ResultCodeSequence.COMMON + 5, "Post请求中，body中没有传输数据！"),

    /**
     * SERVICE_CALL_ERROR 服务调用出错
     */
    SERVICE_CALL_ERROR(false, ResultCodeSequence.COMMON + 6, "微服务调用出错！"),
    /**
     * SERVER_ERROR 抱歉，系统繁忙，请稍后重试！
     */
    SERVER_ERROR(false, ResultCodeSequence.SYSTEM_ERROR, "抱歉，系统繁忙，请稍后重试！");
    /**
     * 操作是否成功
     *
     * @since 1.0
     */
    Boolean success;
    /**
     * 操作代码
     */
    Integer code;
    /**
     * 提示信息
     */
    String message;

    CommonCode(boolean success, int code, String message)
    {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * success
     *
     * @return Boolean
     */
    @Override
    public Boolean success()
    {
        return this.success;
    }

    /**
     * code
     *
     * @return Integer
     */
    @Override
    public Integer code()
    {
        return this.code;
    }

    /**
     * message
     *
     * @return String
     */
    @Override
    public String message()
    {
        return this.message;
    }
}
