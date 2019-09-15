package com.xyy.framework.security.oauth2;

import com.xyy.framework.security.oauth2.config.interceptor.fegin.client.FeignClientInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * OauthAutoConfiguration
 *
 * @author chentudong
 * @date 2019/9/15 15:01
 * @since 1.0
 */
@ComponentScan
@Configuration
public class OauthAutoConfiguration
{
    /**
     * Service层互相调用时，需要token，new一个拦截器就能解决这个问题
     *
     * @return FeignClientInterceptor
     */
    @Bean
    public FeignClientInterceptor getFeignClientInterceptor()
    {
        return new FeignClientInterceptor();
    }
}
