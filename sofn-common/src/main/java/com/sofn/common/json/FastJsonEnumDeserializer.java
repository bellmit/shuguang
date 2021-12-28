/**
 * Copyright (c) 1998-2020 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2020-04-26 20:04
 */
package com.sofn.common.json;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.baomidou.mybatisplus.core.enums.IEnum;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * fastjson枚举类型反序列化器
 *
 * @author lijiang
 * @version 1.0
 **/
public final class FastJsonEnumDeserializer implements ObjectDeserializer {
    /**
     * fastjson invokes this call-back method during deserialization when it encounters a field of the
     * specified type.
     * <p>In the implementation of this call-back method, you should consider invoking
     * for any non-trivial field of the returned object.
     *
     * @param parser    context DefaultJSONParser being deserialized
     * @param type      The type of the Object to deserialize to
     * @param fieldName parent object field name
     * @return a deserialized object of the specified type which is a subclass of {@code T}
     */
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        final JSONLexer lexer = parser.lexer;
        final int token = lexer.token();
        Class cls = (Class) type;
        Object[] enumConstants = cls.getEnumConstants();
        if (IEnum.class.isAssignableFrom(cls)) {
            for (Object enumConstant : enumConstants) {
                IEnum<? extends Serializable> iEnum = (IEnum<? extends Serializable>) enumConstant;
                Enum<?> enumClazz = (Enum<?>) enumConstant;

                String code = iEnum.getValue().toString();
                String desc = iEnum.toString();
                int ordinal = enumClazz.ordinal();
                String name = enumClazz.name();

                if (token == JSONToken.LITERAL_STRING) {
                    if (code.equals(lexer.stringVal()) || name.equals(lexer.stringVal()) || desc.equals(lexer.stringVal())) {
                        return (T) iEnum;
                    }
                } else if (token == JSONToken.LITERAL_INT) {
                    if (ordinal == lexer.intValue()) {
                        return (T) iEnum;
                    }
                }
            }
        } else {
            List<String> nameList = new ArrayList<>();
            List<Integer> ordinalList = new ArrayList<>();

            for (Object enumConstant : enumConstants) {
                Enum<?> iEnum = (Enum<?>) enumConstant;
                nameList.add(iEnum.name());
                ordinalList.add(iEnum.ordinal());
            }

            //没实现IEnum接口的 默认的按名字或者按ordinal
            if (token == JSONToken.LITERAL_INT) {
                int intValue = lexer.intValue();
                lexer.nextToken(JSONToken.COMMA);
                if (intValue < 0 || intValue > enumConstants.length) {
                    throw new JSONException(String.format("parse enum %s error, value : %s", cls.getName(), intValue));
                }
                if (ordinalList.contains(intValue)) {
                    return (T) enumConstants[intValue];
                }
            } else if (token == JSONToken.LITERAL_STRING) {
                String name = lexer.stringVal();
                if (nameList.contains(name)) {
                    return (T) Enum.valueOf(cls, name);
                }
            }
        }
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }
}
