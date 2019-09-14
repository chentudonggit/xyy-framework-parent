package com.xyy.framework.web.common.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.xyy.framework.web.common.config.date.DateFormatter;
import com.xyy.framework.web.exceptions.server.ErrorDecoderException;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * FeignConfigRegistrar
 *
 * @author chentudong
 * @date 2019/9/14 15:47
 * @since 1.0
 */
@Configuration
public class FeignConfigRegistrar implements FeignFormatterRegistrar
{
    /**
     * 注入Bean : HttpMessageConverters，以支持fastjson
     *
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters()
    {
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fastConvert.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConvert);
    }

    /**
     * 异常
     * @return ErrorDecoder
     */
    @Bean
    public ErrorDecoder errorDecoder()
    {
        return new ErrorDecoderException();
    }

    @Override
    public void registerFormatters(FormatterRegistry registry)
    {
        registry.addFormatter(new DateFormatter());
    }
}
