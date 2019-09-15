package com.xyy.framework.security.oauth2.config.resource.server;

import com.xyy.framework.web.common.config.swagger.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * ResourceServerConfig
 *
 * @author chentudong
 * @date 2019/9/15 15:07
 * @since 1.0
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    /**
     * 公钥
     */
    private static final String PUBLIC_KEY = "xyypublic.key";

    /**
     * 定义JwtTokenStore，使用jwt令牌
     *
     * @param jwtAccessTokenConverter jwtAccessTokenConverter
     * @return TokenStore
     */
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter)
    {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 定义JJwtAccessTokenConverter，使用jwt令牌
     *
     * @return JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter()
    {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }

    /**
     * 获取非对称加密公钥 Key
     *
     * @return 公钥 Key
     */
    private String getPubKey()
    {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try
        {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            return br.lines().collect(Collectors.joining("\n"));
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
            return null;
        }
    }

    /**
     * Http安全配置，对每个到达系统的http请求链接进行校验
     *
     * @param http http
     * @throws Exception Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //所有请求必须认证通过
        http.authorizeRequests()
                //下边的路径放行
                .antMatchers(SwaggerConfig.swaggerPath()).permitAll()
                // 下面被注释掉的代码，与hystrix性能监控的API相关
                .antMatchers("/actuator/**", "/proxy.stream", "/hystrix/**", "/favicon.ico", "/userService/findByUserName").permitAll()
                .anyRequest().authenticated();
    }
}
