package com.xyy.framework.mybaites.plus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.xyy.framework.mybaites.plus.config.handler.BaseMetaObjectHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlusConfig
 *
 * @author chentudong
 * @date 2019/11/17 2:36
 * @since 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.xyy.**.mapper.**", "com.xyy.**.dao.**"})
public class MybatisPlusConfig
{
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public ISqlInjector setSqlInjector()
    {
        return new LogicSqlInjector();
    }

    @Bean
    protected MetaObjectHandler setMetaObjectHandler()
    {
        return new BaseMetaObjectHandler();
    }
}
