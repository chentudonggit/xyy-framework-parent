package com.xyy.framework.web.response;

import com.xyy.framework.common.builder.page.PageVO;
import com.xyy.framework.common.web.response.code.ResultCode;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;

/**
 * ResponseResult
 *
 * @author chentudong
 * @date 2019/9/14 16:02
 * @since 1.0
 */
public class ResponseResult extends LinkedHashMap<String, Object>
{
    /**
     * KEY_DATA
     *
     * @since 1.0
     */
    private static final String KEY_DATA = "data";
    /**
     * KEY_CODE
     *
     * @since 1.0
     */
    private static final String KEY_CODE = "code";
    /**
     * KEY_MESSAGE
     *
     * @since 1.0
     */
    private static final String KEY_MESSAGE = "message";
    /**
     * KEY_SUCCESS
     *
     * @since 1.0
     */
    private static final String KEY_SUCCESS = "success";
    /**
     * 总页码
     *
     * @since 1.0
     */
    private static final String KEY_TOTAL_PAGE = "total_page";
    /**
     * 总记录数
     *
     * @since 1.0
     */
    private static final String KEY_TOTAL_COUNT = "total_count";
    /**
     * 当前页码
     *
     * @since 1.0
     */
    private static final String KEY_PAGE = "page";
    /**
     * 每页显示多少条数据
     *
     * @since 1.0
     */
    private static final String KEY_SIZE = "size";

    /**
     * ResponseResult
     *
     * @param resultCode resultCode
     */
    public ResponseResult(ResultCode resultCode)
    {
        this.put(KEY_CODE, resultCode.code());
        this.put(KEY_MESSAGE, resultCode.message());
        this.put(KEY_SUCCESS, resultCode.success());
    }

    /**
     * ResponseResult
     *
     * @param code       code
     * @param successful successful
     * @param message    message
     */
    public ResponseResult(Integer code, Boolean successful, String message)
    {
        this.put(KEY_CODE, code);
        this.put(KEY_MESSAGE, message);
        this.put(KEY_SUCCESS, successful);
    }

    /**
     * code
     *
     * @param resultCode resultCode
     * @return ResponseResult
     * @since 1.0
     */
    public static ResponseResult code(ResultCode resultCode)
    {
        return new ResponseResult(resultCode);
    }

    /**
     * data
     *
     * @param data data
     * @return ResponseResult
     * @since 1.0
     */
    public static ResponseResult data(Object data)
    {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseResult responseResult = new ResponseResult(httpStatus.value(), true,
                httpStatus.getReasonPhrase());
        responseResult.put(KEY_DATA, data);
        return responseResult;
    }

    /**
     * ResponseResult
     *
     * @param page page
     * @return ResponseResult
     * @since 1.0
     */
    public static ResponseResult data(PageVO page)
    {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseResult responseResult = new ResponseResult(httpStatus.value(), true,
                httpStatus.getReasonPhrase());
        //分页数据
        responseResult.put(KEY_DATA, page.getData());
        responseResult.put(KEY_TOTAL_PAGE, page.getTotalPage());
        responseResult.put(KEY_TOTAL_COUNT, page.getTotalCount());
        responseResult.put(KEY_PAGE, page.getPage());
        responseResult.put(KEY_SIZE, page.getSize());
        return responseResult;
    }

    /**
     * 一般用于 对数据的，增删改
     * 增删改有可能会失败
     *
     * @param row 改变数据库的记录数量
     * @return ResponseResult
     * @since 1.0
     */
    public static ResponseResult row(long row)
    {
        HttpStatus httpStatus = HttpStatus.OK;
        boolean isSuccessful = true;
        if (row == 0L)
        {
            isSuccessful = false;
            httpStatus = HttpStatus.NOT_MODIFIED;
        }
        ResponseResult responseResult = new ResponseResult(httpStatus.value(), isSuccessful,
                httpStatus.getReasonPhrase());
        responseResult.put(KEY_DATA, row);
        return responseResult;
    }

    /**
     * successfulMessage
     *
     * @param message message
     * @return ResponseResult
     * @since 1.0
     */
    public ResponseResult successfulMessage(String message)
    {
        boolean isSuccessful = (Boolean) this.get(KEY_SUCCESS);
        if (isSuccessful)
        {
            this.put(KEY_MESSAGE, message);
        }
        return this;
    }

    /**
     * errorMessage
     *
     * @param message message
     * @return ResponseResult
     * @since 1.0
     */
    public ResponseResult errorMessage(String message)
    {
        boolean isSuccessful = (Boolean) this.get(KEY_SUCCESS);
        if (!isSuccessful)
        {
            this.put(KEY_MESSAGE, message);
        }
        return this;
    }

    /**
     * 报错信息
     *
     * @param message message
     * @return ResponseResult
     * @since 1.0
     */
    public ResponseResult failure(String message)
    {
        this.put(KEY_SUCCESS, false);
        this.put(KEY_CODE, HttpStatus.BAD_GATEWAY);
        this.put(KEY_MESSAGE, message);
        return this;
    }

    /**
     * 报错信息
     *
     * @param code    code
     * @param message message
     * @return ResponseResult
     */
    public ResponseResult failure(Integer code, String message)
    {
        this.put(KEY_SUCCESS, false);
        this.put(KEY_CODE, code);
        this.put(KEY_MESSAGE, message);
        return this;
    }

    /**
     * addAttribute
     *
     * @param key   key
     * @param value value
     * @return ResponseResult
     * @since 1.0
     */
    public ResponseResult addAttribute(String key, Object value)
    {
        this.put(key, value);
        return this;
    }
}
