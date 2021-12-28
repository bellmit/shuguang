package com.sofn.gatewayservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置类
 */
@SuppressWarnings("ALL")
@Primary
@Configuration
public class SwaggerProvider implements SwaggerResourcesProvider {

    @Autowired
    RouteLocator routeLocator;

    @Override
    public List<SwaggerResource> get() {
        //利用routeLocator动态引入微服务
        List<SwaggerResource> resources = new ArrayList<>();
        //resources.add(swaggerResource("系统管理","/sys/v2/api-docs","1.0"));
        //循环 使用Lambda表达式简化代码
        routeLocator.getRoutes().forEach(route ->{
            // 屏蔽重复的服务
            if (!route.getLocation().equals(route.getId())){
                //动态获取
                resources.add(swaggerResource(route.getId(),route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
