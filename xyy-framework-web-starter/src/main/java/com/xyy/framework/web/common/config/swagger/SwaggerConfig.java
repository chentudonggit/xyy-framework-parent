package com.xyy.framework.web.common.config.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * SwaggerConfig
 *
 * @author chentudong
 * @date 2019/9/14 16:01
 * @since 1.0
 */
public class SwaggerConfig
{
    /**
     * HEADER_TICKET
     *
     * @since 1.0
     */
    private static final String HEADER_TICKET = "ticket";
    /**
     * HEADER_AUTHORIZATION
     *
     * @since 1.0
     */
    private static final String HEADER_AUTHORIZATION = "Authorization";

    public static String[] swaggerPath()
    {
        return new String[]{
                "/v2/api-docs", "/swagger-resources/configuration/ui",
                "/swagger-resources", "/swagger-resources/configuration/security",
                "/swagger-ui.html", "/webjars/**", "/", "/csrf"
        };
    }

    @Bean
    public Docket createServiceRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT)
                .groupName("Service API")
                .securitySchemes(defaultSecuritySchemes())
                .securityContexts(defaultSecurityContexts())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Service.class))
                .build();
    }

    @Bean
    public Docket createControllerRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo.DEFAULT)
                .groupName("Controller API")
                .securitySchemes(defaultSecuritySchemes())
                .securityContexts(defaultSecurityContexts())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private List<SecurityContext> defaultSecurityContexts()
    {
        List<SecurityContext> securityContexts = Lists.newArrayList();
        securityContexts.add(SecurityContext
                .builder().securityReferences(defaultAuthorization()).build());
        return securityContexts;
    }

    /**
     * defaultSecuritySchemes
     * Swagger右上角全局header
     *
     * @return List
     */
    private List<? extends SecurityScheme> defaultSecuritySchemes()
    {
        ArrayList<ApiKey> apiKeys = Lists.newArrayList();
        apiKeys.add(new ApiKey(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION, "header"));
        apiKeys.add(new ApiKey(HEADER_TICKET, HEADER_TICKET, "header"));
        return apiKeys;
    }

    /**
     * 每一条Api右边，也提供一个地方给设置header
     */
    private List<SecurityReference> defaultAuthorization()
    {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = Lists.newArrayList();
        securityReferences.add(new SecurityReference(HEADER_AUTHORIZATION, authorizationScopes));
        securityReferences.add(new SecurityReference(HEADER_TICKET, authorizationScopes));
        return securityReferences;
    }

    /**
     * apiInfo
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful API")
                .description("rest api 文档构建利器")
                .version("1.0")
                .build();
    }
}
