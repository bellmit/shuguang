/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 17:17
 */
package com.sofn.common.converter;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义枚举转换器工厂
 *
 * @author lijiang
 * @version 1.0
 **/
public final class EnumConverterFactory implements ConverterFactory<String, IEnum<Serializable>> {
    private final Map<Class<IEnum<Serializable>>, Converter<String, IEnum<Serializable>>> converterMap = new ConcurrentHashMap<>();

    /**
     * Get the converter to convert from S to target type T, where T is also an instance of R.
     *
     * @param targetType the target type to convert to
     * @return a converter from S to T
     */
    @Override
    public <T extends IEnum<Serializable>> Converter<String, T> getConverter(Class<T> targetType) {
        Class<IEnum<Serializable>> type = (Class<IEnum<Serializable>>) targetType;
        Converter<String, IEnum<Serializable>> converter = converterMap.get(type);
        if (converter == null) {
            converter = new EnumConverter(type);
            converterMap.put(type, converter);
        }
        return (Converter<String, T>) converter;
    }
}
