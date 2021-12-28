package com.sofn.flow.config;

import com.google.common.collect.Lists;
import com.sofn.flow.util.TokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.Locale;


/**
 * Swagger 配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value(value = "${swagger.base-package:com.sofn}")
    String basePackage;
    @Value(value = "${swagger.name:}")
    String name;
    @Value(value = "${swagger.prefix:}")
    String prefix;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        String title = String.format(Locale.getDefault(), "%s[以下接口需在URL前加%s前缀]", name, prefix);
        String desc = String.format(Locale.getDefault(), "%s Restful API,测试步骤，1.登录获取TOKEN（调用登录接口，验证码可以默认填写sofn）" +
                "2. 填写得到的TOKEN，需要权限的接口展开后右边会有一个红色或者蓝色的!，红色的表示需要填写TOKEN，蓝色的表示已经填写TOKEN", name);
        return new ApiInfoBuilder()
                .title(title)
                .description(desc)
                .version("1.0")
                .build();
    }

    /**
     * 给所有接口带上Token
     * 通过Swagger2的securitySchemes配置全局参数：
     * 如下列代码所示，securitySchemes的ApiKey中增加一个名为“Authorization”，type为“header”的参数。
     * @return
     */
    private List<ApiKey> securitySchemes() {
        return  Lists.newArrayList(new ApiKey(TokenUtil.TOKEN_HEADNAME,TokenUtil.TOKEN_HEADNAME,"header"));
    }

    /**
     * 在Swagger2的securityContexts中通过正则表达式，设置需要使用参数的接口（或者说，是去除掉不需要使用参数的接口），
     * 如下列代码所示，通过PathSelectors.regex("^(?!auth).*$")，
     * 所有包含"auth"的接口不需要使用securitySchemes。即不需要使用上文中设置的名为“Authorization”，
     * type为“header”的参数。
     * @return
     */
    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!login).*$"))
                        .forPaths(PathSelectors.regex("^(?!captcha).*$"))
                        .build()
        );
    }


    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }

}
