/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 18:34
 */
package com.sofn.common.swagger;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.classmate.ResolvedType;
import com.google.common.base.Optional;
import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.schema.Annotations;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger.schema.ApiModelProperties;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自定义枚举属性插件
 * <p>
 * 用于在swagger文档中显示枚举的可选值
 *
 * @author lijiang
 * @version 1.0
 **/
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class EnumPropertyPlugin implements ModelPropertyBuilderPlugin {
    @SneakyThrows
    @Override
    public void apply(ModelPropertyContext modelPropertyContext) {
        Optional<ApiModelProperty> annotation = Optional.absent();

        if (modelPropertyContext.getAnnotatedElement().isPresent()) {
            annotation = annotation.or(ApiModelProperties.findApiModePropertyAnnotation(modelPropertyContext.getAnnotatedElement().get()));
        }
        if (modelPropertyContext.getBeanPropertyDefinition().isPresent()) {
            annotation = annotation.or(Annotations.findPropertyAnnotation(modelPropertyContext.getBeanPropertyDefinition().get(), ApiModelProperty.class));
        }
        final Class<?> rawPrimaryType = modelPropertyContext.getBeanPropertyDefinition().get().getRawPrimaryType();
        //过滤得到目标类型
        if (annotation.isPresent() && IEnum.class.isAssignableFrom(rawPrimaryType)) {
            //获取IEnum的value值
            IEnum[] values = (IEnum[]) rawPrimaryType.getEnumConstants();
            final List<String> displayValues = Arrays.stream(values).map(ienum -> ienum.getValue().toString()).collect(Collectors.toList());
            final AllowableListValues allowableListValues = new AllowableListValues(displayValues, rawPrimaryType.getTypeName());
            ApiModelProperty apiModelProperty = annotation.get();

            StringBuilder sb = new StringBuilder();
            sb.append(apiModelProperty.value() + " ( ");
            for (IEnum iEnum : values) {
                sb.append(iEnum.toString() + ": " + iEnum.getValue() + ", ");
            }
            sb.append(")");
            sb.deleteCharAt(sb.lastIndexOf(", "));

            String description = sb.toString();
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(apiModelProperty);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);
            Map memberValues = (Map) declaredField.get(invocationHandler);
            // 动态设置ApiModelProperty注解的value值
            memberValues.put("value", description);

            //固定设置为String类型
            final ResolvedType resolvedType = modelPropertyContext.getResolver().resolve(String.class);
            modelPropertyContext.getBuilder().allowableValues(allowableListValues).type(resolvedType);
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}
