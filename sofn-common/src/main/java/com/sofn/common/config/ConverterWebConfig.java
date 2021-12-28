/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 18:16
 */
package com.sofn.common.config;

import com.sofn.common.converter.EnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义转换器配置
 *
 * @author lijiang
 * @version 1.0
 **/
@Configuration
public class ConverterWebConfig implements WebMvcConfigurer {

    /**
     * 将转换器工厂添加到Spring
     *
     * @param registry 格式转换注册器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }
}
