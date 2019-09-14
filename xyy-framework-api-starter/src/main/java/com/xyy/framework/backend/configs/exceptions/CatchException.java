package com.xyy.framework.backend.configs.exceptions;

import com.google.common.collect.ImmutableMap;
import com.netflix.client.ClientException;
import com.xyy.framework.common.enums.codes.common.CommonCode;
import com.xyy.framework.common.exceptions.runtime.InsiderException;
import com.xyy.framework.common.exceptions.runtime.UnifiedException;
import com.xyy.framework.common.utils.assertion.AssertUtils;
import com.xyy.framework.common.web.constants.code.response.ResultCodeSequence;
import com.xyy.framework.common.web.response.code.ResultCode;
import com.xyy.framework.web.response.ResponseResult;
import feign.RetryableException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * CatchException
 *
 * @author chentudong
 * @date 2019/9/14 21:41
 * @since 1.0
 */
@ControllerAdvice
public class CatchException
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CatchException.class);
    private static final String MESSAGE = "message:";

    /**
     * 使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
     */
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    /**
     * 使用builder来构建一个异常类型和错误代码的异常
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();

    static
    {
        //在这里加入一些基础的异常类型判断
        builder.put(RetryableException.class, CommonCode.SERVICE_CALL_ERROR);
        builder.put(ClientException.class, CommonCode.SERVICE_CALL_ERROR);
        builder.put(HttpMessageNotReadableException.class, CommonCode.MISSING_REQUEST_BODY);
    }



    /**
     * 捕获 CustomException 类型的异常
     * ResponseBody 转成Json返回给前端
     *
     * @since 1.0
     */
    @ExceptionHandler(InsiderException.class)
    @ResponseBody
    public ResponseResult customException(InsiderException e)
    {
        // 记录日志
        LOGGER.error(e.getMessage(), e);
        // 把错误信息返回给前端
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), false, getMessage(e));
    }

    /**
     * 统一弹窗提示
     * @param e e
     * @return ResponseResult
     */
    @ExceptionHandler(UnifiedException.class)
    @ResponseBody
    public ResponseResult unifiedException(UnifiedException e)
    {
        // 记录日志
        LOGGER.error(e.getMessage(), e);
        // 把错误信息返回给前端
        return new ResponseResult(ResultCodeSequence.CODE_ERROR_POPUP, false, getMessage(e));
    }

    /**
     * 捕获 Exception 类型的异常
     * ResponseBody 转成Json返回给前端
     *
     * @since 1.0
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception e)
    {
        LOGGER.error("catch exception : {}\r\nexception: ", e.getMessage(), e);
        if (Objects.isNull(EXCEPTIONS))
        {
            EXCEPTIONS = builder.build();
        }
        Throwable cause = e.getCause();
        Class c;
        if (Objects.isNull(cause))
        {
            c = e.getClass();
        } else
        {
            c = cause.getClass();
        }
        final ResultCode resultCode = EXCEPTIONS.get(c);
        final ResponseResult responseResult;
        if (Objects.nonNull(resultCode))
        {
            responseResult = new ResponseResult(resultCode);
        } else
        {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

    /**
     * 获取异常信息
     *
     * @param e e
     * @return String
     */
    private String getMessage(Exception e)
    {
        AssertUtils.isNull(e, "异常类不能为空");
        // 把错误信息返回给前端
        String message = e.getMessage();
        if (StringUtils.isNoneBlank(message))
        {
            if (message.contains(MESSAGE))
            {
                message = message.substring(message.lastIndexOf(MESSAGE)).replace(MESSAGE, "");
            }
        } else
        {
            message = "未知错误！";
        }
        return message;
    }
}
