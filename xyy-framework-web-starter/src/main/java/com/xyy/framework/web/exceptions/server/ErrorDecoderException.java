package com.xyy.framework.web.exceptions.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyy.framework.common.exceptions.runtime.InsiderException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * 异常解析  （抛原始异常给调用者）
 *
 * @author chentudong
 * @date 2019/9/14 15:53
 * @since 1.0
 */
public class ErrorDecoderException implements ErrorDecoder
{
    private Logger logger = LoggerFactory.getLogger(ErrorDecoderException.class);
    @Override
    public Exception decode(String s, Response response)
    {
        try
        {
            Response.Body body = response.body();
            if (Objects.nonNull(body))
            {
                // 获取原始的返回内容
                String json = Util.toString(body.asReader());
                JSONObject responseJson = JSON.parseObject(json);
                String classPath = responseJson.getString("exception");
                if (StringUtils.isBlank(classPath))
                {
                    String error = responseJson.getString("error");
                    if (StringUtils.isNotBlank(error))
                    {
                        Exception exception = new InsiderException("path:" + responseJson.getString("path")
                                + "  error:" + responseJson.getString("error")
                                + "  status:" + responseJson.getString("status")
                                + "  message:" + responseJson.getString("message"));
                        logger.error("catch exception : {}\r\nexception: ", json, exception);
                        return exception;
                    }
                }
                // 取得Class对象
                Class<?> cls = Class.forName(classPath);
                Constructor<Exception> con = (Constructor<Exception>) cls.getConstructor(String.class);
                Exception exception = con.newInstance(responseJson.getString("message"));
                logger.error("catch exception : {}\r\nexception: ", json, exception);
                return exception;
            }
        } catch (Exception var4)
        {
            return new InsiderException(var4.getMessage());
        }
        return new InsiderException("处理失败.");
    }
}
