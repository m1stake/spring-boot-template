/**
 * @Title: SwaggerConfig
 * @author wanchao-南昌泽诺信息科技有限公司
 * @date 2018/1/31 12:22
 */
package com.songfeifan.samples.sbt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    /**
     * commonDocket
     * @return Docket
     */
    @Bean
    public Docket commonDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("API接口文档")
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.songfeifan.samples.sbt.controller"))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(headerParameter())
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes())
                ;
    }

    private List<SecurityScheme> securitySchemes() {
        return Collections.singletonList(
                new BasicAuth("basicAuth")

        );
    }

    /**
     * 创建API信息
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().build();
    }

    private List<Parameter> headerParameter() {
        Parameter p = new ParameterBuilder().name("x-prefer-service-ip").parameterType("header")
                .modelRef(new ModelRef("string"))
                .defaultValue("0.0.0.0").build();
        return Collections.singletonList(p);
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder().forPaths(x -> x.equals("/")).securityReferences(
                securityReferences()
        ).build());
    }

    private List<SecurityReference> securityReferences() {
        return Collections.singletonList(
                SecurityReference.builder().reference("basicAuth").scopes(new AuthorizationScope[0])
                        .build()
        );
    }
}
