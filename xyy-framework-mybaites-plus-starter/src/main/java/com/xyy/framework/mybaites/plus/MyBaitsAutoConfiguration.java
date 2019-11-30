package com.xyy.framework.mybaites.plus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * MyBaitsAutoConfiguration
 *
 * @author chentudong
 * @date 2019/11/30 11:25
 * @since 1.0
 */
@Configuration
@ComponentScan
@PropertySource(value = {"classpath:/bootstrap.yml"}, ignoreResourceNotFound = true)
public class MyBaitsAutoConfiguration
{
}
