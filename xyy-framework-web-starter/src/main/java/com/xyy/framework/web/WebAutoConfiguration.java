package com.xyy.framework.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 自动装备该路径的所有带注解类
 *
 * @author chentudong
 * @date 2019/9/14 15:57
 * @since 1.0
 */
@Configuration
@ComponentScan
public class WebAutoConfiguration
{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}
