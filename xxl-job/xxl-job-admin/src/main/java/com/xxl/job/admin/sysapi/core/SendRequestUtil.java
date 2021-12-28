package com.xxl.job.admin.sysapi.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 因定时调度使用的spring boot版本是1.X
 * 所以不能导入common 就不能使用UserUtil工具
 * 所以这里使用java发送请求来实现掉接口的过程
 * 发送请求工具
 * @author heyongjie
 * @date 2019/12/11 17:34
 */
@Component
public class SendRequestUtil {

    @Value("${xxl.sysurl}")
    private String requestUrl;

    /**
     * 获取随机的URL
     * @return  localhost:7999/
     */
    private String randomRequest(){
        if(requestUrl == null || "".equals(requestUrl)){
            return "";
        }
        if(requestUrl.contains(",")){
            List<String> list = Arrays.asList(requestUrl.split(","));
            int requestUrlNum = (int)(1+Math.random()*(10-1+1));
            return list.get(requestUrlNum -1 );
        }else{
            return requestUrl;
        }
    }


    /**
     * 向指定URL发送GET方法的请求
     */
    public String sendGet(String url, String param,String token) {
        try {
            String msg =  sendGet(url,param,token,null);
            msg = msg.toString();
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 向指定URL发送GET方法的请求
     */
    public String sendGet(String url, String param,String token, Map<String, String> header) throws  IOException {
        StringBuilder result = new StringBuilder();
        BufferedReader in ;
        String urlNameString = randomRequest() + url + "?" + param;
        URL realUrl = new URL(urlNameString);
        // 打开和URL之间的连接
        URLConnection connection = realUrl.openConnection();
        //设置超时时间
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(15000);
        // 设置通用的请求属性

        if(header == null){
            header = new HashMap<>(1);
            header.put("Authorization",token);
        }
        if (header!=null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        // 建立实际的连接
        connection.connect();
        // 获取所有响应头字段
        Map<String, List<String>> map = connection.getHeaderFields();
        // 遍历所有的响应头字段
        for (String key : map.keySet()) {
            System.out.println(key + "--->" + map.get(key));
        }
        // 定义 BufferedReader输入流来读取URL的响应，设置utf8防止中文乱码
        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        String line;
        while ((line = in.readLine()) != null) {
            result.append(line);
        }
        in.close();
        return result.toString();
    }

}
