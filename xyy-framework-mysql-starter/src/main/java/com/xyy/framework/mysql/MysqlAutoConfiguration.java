package com.xyy.framework.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * MysqlAuthConfiguration
 *
 * @author chentudong
 * @date 2019/9/14 18:54
 * @since 1.0
 */

@Configuration
@ComponentScan
@PropertySource(value = {"classpath:/bootstrap.yml"}, ignoreResourceNotFound = true)
public class MysqlAutoConfiguration
{
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource setDataSource()
    {
        return new DruidDataSource();
    }
}
