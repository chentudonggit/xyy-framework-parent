package com.xyy.framework.mybaites.plus;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

/**
 * MyBaitsAutoConfiguration
 *
 * @author chentudong
 * @date 2019/11/30 11:25
 * @since 1.0
 */
@Order(-1)
@Configuration
@ComponentScan
@PropertySource(value = {"classpath:/config/application.yml"}, ignoreResourceNotFound = true)
public class MyBaitsAutoConfiguration
{
}
