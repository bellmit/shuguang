package com.sofn.service;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gebp
 * @date 2018/8/17 10:19
 */
public class LocalPlatformNotifier {
     //给地方平台get  国家平台 post  true
    public String notify(String requestURL, String paraName, String paraValue, String charset, boolean isPost) throws Exception {
        String result = "";

        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        // 请求超时
        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
        // 读取超时
        defaultHttpClient.getParams().setParameter(
                CoreConnectionPNames.SO_TIMEOUT, 5600);

        if (isPost) {

            try {
                HttpPost httpPost = new HttpPost(StringUtils
                        .trimToEmpty(requestURL));

                // 建立一个NameValuePair数组，用于存储欲传送的参数
                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();

                // 添加参数
                params.add(new BasicNameValuePair(paraName, paraValue));

                httpPost.setEntity(new UrlEncodedFormEntity(params, charset));

                // 发送Post,并返回一个HttpResponse对象
                HttpResponse response = defaultHttpClient.execute(httpPost);

                // 如果状态码为200,就是正常返回
                if (response.getStatusLine().getStatusCode() == 200) {
                    result = new String(EntityUtils.toString(response.getEntity(),
                            charset));

                    // 得到返回的字符串
                    // 打印输出

                    // 301 代表永久性转移 302 代表暂时性转移
                } else if (response.getStatusLine().getStatusCode() == 302
                        || response.getStatusLine().getStatusCode() == 301) {

                    result = response.getFirstHeader("Location").getValue();
                }
            } finally {
                defaultHttpClient.getConnectionManager().shutdown();
            }
        } else {
            // 建立HttpGet对象
            try {
                // 拼接请求参数字符串
                String reqData = "";

                reqData = paraName + "=" + paraValue;

                HttpGet httpGet = new HttpGet();
                httpGet.setURI(new URI(requestURL + "?" + reqData));

                // 发送Get,并返回一个HttpResponse对象
                HttpResponse response = defaultHttpClient.execute(httpGet);
                // 如果状态码为200,就是正常返回
                if (response.getStatusLine().getStatusCode() == 200) {
                    result = new String(EntityUtils.toString(response.getEntity(),
                            charset));
                    // 得到返回的字符串
                    // 301 代表永久性转移 302 代表暂时性转移
                } else if (response.getStatusLine().getStatusCode() == 302
                        || response.getStatusLine().getStatusCode() == 301) {

                    result = response.getFirstHeader("Location").getValue();
                }
            } finally {
                defaultHttpClient.getConnectionManager().shutdown();
            }
        }
        return result.trim();
    }

}
