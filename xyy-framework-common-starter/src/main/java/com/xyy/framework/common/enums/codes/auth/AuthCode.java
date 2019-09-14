package com.xyy.framework.common.enums.codes.auth;

import com.google.common.collect.ImmutableMap;
import com.xyy.framework.common.web.constants.code.response.ResultCodeSequence;
import com.xyy.framework.common.web.response.code.ResultCode;
import io.swagger.annotations.ApiModelProperty;

/**
 * 认证状态码
 *
 * @author chentudong
 * @date 2019/9/14 13:23
 * @since 1.0
 */
public enum AuthCode implements ResultCode
{
    /**
     * AUTH_USERNAME_NONE 请输入账号
     */
    AUTH_USERNAME_NONE(false, ResultCodeSequence.CODE_ERROR_POPUP, "请输入账号！"),
    /**
     * AUTH_PASSWORD_NONE 请输入密码
     */
    AUTH_PASSWORD_NONE(false, ResultCodeSequence.CODE_ERROR_POPUP, "请输入密码！"),
    /**
     * AUTH_VERIFY_CODE_NONE 请输入验证码
     */
    AUTH_VERIFY_CODE_NONE(false, ResultCodeSequence.CODE_ERROR_POPUP, "请输入验证码！"),
    /**
     * AUTH_ACCOUNT_NOT_EXISTS 账号不存在
     */
    AUTH_ACCOUNT_NOT_EXISTS(false, ResultCodeSequence.CODE_ERROR_POPUP, "账号不存在！"),
    /**
     * AUTH_CREDENTIAL_ERROR 账号或密码错误
     */
    AUTH_CREDENTIAL_ERROR(false, ResultCodeSequence.CODE_ERROR_POPUP, "账号或密码错误！"),
    /**
     * AUTH_LOGIN_ERROR 登陆过程出现异常请尝试重新操作
     */
    AUTH_LOGIN_ERROR(false, ResultCodeSequence.CODE_ERROR_POPUP, "登录失败，请重试！"),
    /**
     * AUTH_LOGIN_APPLY_TOKEN_FAIL 申请令牌失败
     */
    AUTH_LOGIN_APPLY_TOKEN_FAIL(false, ResultCodeSequence.AUTH_CODE + 1, "申请令牌失败！"),
    /**
     * AUTH_LOGIN_TOKEN_SAVE_FAIL  存储令牌失败
     */
    AUTH_LOGIN_TOKEN_SAVE_FAIL(false, ResultCodeSequence.AUTH_CODE + 2, "存储令牌失败！"),
    /**
     * AUTH_LOGOUT_FAIL 退出失败
     */
    AUTH_LOGOUT_FAIL(false, ResultCodeSequence.CODE_ERROR_POPUP, "退出失败，请重试！");

    /**
     * 操作代码
     */
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    /**
     * 操作代码
     */
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;

    private static final ImmutableMap<Integer, AuthCode> CACHE;

    static
    {
        final ImmutableMap.Builder<Integer, AuthCode> builder = ImmutableMap.builder();
        for (AuthCode commonCode : values())
        {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    AuthCode(boolean success, Integer code, String message)
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
