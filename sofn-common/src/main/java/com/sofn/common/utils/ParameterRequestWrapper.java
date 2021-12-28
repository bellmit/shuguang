package com.sofn.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

/**
 * 代码参考  https://blog.csdn.net/zhang840327220/article/details/84909568
 *
 * 重写HttpServletRequestWrapper移除请求参数中的空格
 *
 * @author heyongjie
 * @date 2020/3/9 17:22
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> params = Maps.newHashMap();

    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        Map<String, String[]> requestMap = request.getParameterMap();
        this.params.putAll(requestMap);
        this.modifyParameterValues();
    }

    /**
     * 重写getInputStream方法  post类型的请求参数必须通过流才能获取到值
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isEmpty(header)){
            return super.getInputStream();
        }
        //非json类型，直接返回
        if (!header.equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            if(!header.equalsIgnoreCase("text/plain;charset=UTF-8")){
                return super.getInputStream();
            }
        }
        //为空，直接返回
        String json = "";
        try{
            json = StringUtils.trimAllWhitespace(IOUtils.toString(super.getInputStream(), "utf-8"));
        }catch (Exception e){
            e.printStackTrace();
            json = "";
        }
        if (StringUtils.startsWithIgnoreCase(json, JsonToken.START_ARRAY.asString())) { // 支持数组对象
            return new MyServletInputStream((ByteArrayInputStream) IOUtils.toInputStream(json, StandardCharsets.UTF_8));
        }
        if (StringUtils.isEmpty(json)) {
            return super.getInputStream();
        }
        Map<String, Object> map = StringJsonUtils.jsonStringToMap(json);

        ByteArrayInputStream bis = new ByteArrayInputStream(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
        return new MyServletInputStream(bis);
    }

    /**
     * 将parameter的值去除空格后重写回去
     */
    private void modifyParameterValues() {
        Set<String> set = params.keySet();
        for (String key : set) {
            String[] values = params.get(key);
            if (values.length > 0) {
                values[0] = values[0].trim();
                params.put(key, values);
            }
        }
    }

    /**
     * 重写getParameter 参数从当前类中的map获取
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues
     */
    @Override
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    class MyServletInputStream extends ServletInputStream {
        private ByteArrayInputStream bis;

        MyServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return bis.read();
        }
    }
}
