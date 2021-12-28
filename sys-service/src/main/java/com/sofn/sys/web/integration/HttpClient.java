package com.sofn.sys.web.integration;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);
    /**
     * 发送 get请求   返回String body
     */
    public String get(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseMessange = "";
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(uri);
            System.out.println("Request URI is:"+ httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine().toString());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    responseMessange = EntityUtils.toString(entity) ;
                    System.out.println("Response content: " + responseMessange);
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseMessange;
    }

    /**
     * 发送 get请求   返回Json
     */
    public String getJson(String uri) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String responseMessange = "";
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet(uri);
            System.out.println("Request URI is:"+ httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine().toString());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    responseMessange = EntityUtils.toString(entity);
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseMessange;
    }


    public String postJson(String url,String body){
        HttpPost post = null;
        String responseMessange = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/x-www.form-urlencoded; charset=utf-8");
            post.setHeader("Connection", "Close");
            // 构建消息实体
            StringEntity entity = new StringEntity(body, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/x-www.form-urlencoded");
            System.out.println("request body is:"+entity.toString());
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            try {
                // 获取响应实体
                HttpEntity httpEntity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine().toString());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + httpEntity.getContentLength());
                    // 打印响应内容
                    responseMessange = EntityUtils.toString(httpEntity);
                }
                System.out.println("------------------------------------");
            } finally {
                ((Closeable) response).close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseMessange;
    }
}
