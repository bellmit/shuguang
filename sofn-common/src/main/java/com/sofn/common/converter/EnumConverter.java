/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 16:36
 */
package com.sofn.common.converter;

import com.baomidou.mybatisplus.core.enums.IEnum;
import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义从字符串到枚举类型的转换器
 *
 * @author lijiang
 * @version 1.0
 **/
public final class EnumConverter implements Converter<String, IEnum<Serializable>> {
    private final Map<String, IEnum<Serializable>> enumMap = new HashMap<>();

    public EnumConverter(Class<IEnum<Serializable>> enumType) {
        IEnum<Serializable>[] enums = enumType.getEnumConstants();
        for (IEnum<Serializable> e : enums) {
            enumMap.put(e.getValue() + "", e);
            //此处添加可避免下面convert方法抛出IllegalArgumentException异常后被系统捕获再次调用默认方案
            enumMap.put(((Enum) e).name(), e);
        }
    }

    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param source the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public IEnum<Serializable> convert(String source) {
        IEnum<Serializable> result = enumMap.get(source);
        if (result == null) {
            //抛出该异常后，会调用 spring 的默认转换方案，即使用 枚举字面量进行映射
            throw new IllegalArgumentException("No element matches " + source);
        }
        return result;
    }
}
