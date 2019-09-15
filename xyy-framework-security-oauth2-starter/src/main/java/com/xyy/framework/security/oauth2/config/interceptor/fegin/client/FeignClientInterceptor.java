package com.xyy.framework.security.oauth2.config.interceptor.fegin.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Objects;

/**
 * FeignClientInterceptor
 *
 * @author chentudong
 * @date 2019/9/15 15:04
 * @since 1.0
 */
public class FeignClientInterceptor implements RequestInterceptor
{
    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param requestTemplate requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (Objects.nonNull(requestAttributes))
        {
            HttpServletRequest request = requestAttributes.getRequest();
            //取出当前请求的header，找到jwt令牌
            Enumeration<String> headerNames = request.getHeaderNames();
            if (Objects.nonNull(headerNames))
            {
                while (headerNames.hasMoreElements())
                {
                    String headerName = headerNames.nextElement();
                    String headerValue = request.getHeader(headerName);
                    // 将header向下传递
                    requestTemplate.header(headerName, headerValue);
                }
            }
        }
    }
}
