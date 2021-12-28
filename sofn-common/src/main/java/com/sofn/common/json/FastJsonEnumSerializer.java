/*
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 15:07
 */
package com.sofn.common.json;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastjson枚举类型序列化器
 *
 * @author lijiang
 * @version 1.0
 **/
public final class FastJsonEnumSerializer implements ObjectSerializer {
    /**
     * fastjson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     *
     * @param serializer json序列化器
     * @param object src the object that needs to be converted to Json.
     * @param fieldName parent object field name
     * @param fieldType parent object field type
     * @param features parent object field serializer features
     * @throws IOException IO异常
     */
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        boolean isWriteEnumUsingToString = out.isEnabled(SerializerFeature.WriteEnumUsingToString);

        if (object instanceof IEnum<?>) {
            IEnum<?> iEnum = (IEnum<?>) object;
            String val = isWriteEnumUsingToString ? iEnum.toString() : iEnum.getValue().toString();
            out.write("\"" + val + "\"");
        } else {
            out.writeEnum((Enum<?>) object);
        }
    }
}
