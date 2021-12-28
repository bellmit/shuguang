package com.sofn.core.config;/**
 * Created by Administrator on 2018-7-9.
 */

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * @author lijiang
 * @create 2018-07-09 18:20
 * @desc 自定义缓存key生成器
 **/
public class CustomKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object o, Method method, Object... objects) {
        StringBuilder sb = new StringBuilder();
        sb.append(o.getClass().getName());
        sb.append("#");
        sb.append(method.getName());
        sb.append("(");
        for (Object obj : objects) {
            if (obj == null) {
                sb.append(obj);
            } else {
                sb.append(obj.toString());
            }
            sb.append("&");
        }
        sb.append(")");
        return sb.toString();
    }
}
