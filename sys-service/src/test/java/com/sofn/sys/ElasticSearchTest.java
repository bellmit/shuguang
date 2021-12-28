package com.sofn.sys;

import com.sofn.SysServiceApplicationTests;
import com.sofn.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author heyongjie
 * @date 2020/9/16 16:06
 */
public class ElasticSearchTest extends SysServiceApplicationTests {

    @Autowired
    private RestClient restClient;

    public String getIndexName(String date){
        if(StringUtils.isBlank(date)){
            date = DateUtils.format(new Date(), "yyyy.MM.dd");
        }
        return String.format("sys_dev_operate-%s",date);
    }

    // 测试查询一个
    @Test
    public void testQuery() throws Exception{
        String indexName = getIndexName(null);
        String url = indexName+"/doc/sJaMl3QBYsOKhwHvfczo/_source?_source=message";
        System.out.println(url);


        Request request = new Request("GET", url);
        request.addParameter("pretty","true");
        restClient.performRequest(request);
        Response response = restClient.performRequest(request);
        System.out.println(response.getStatusLine()); System.out.println(EntityUtils.toString(response.getEntity()));

    }

    @Test
    public void testPageQuery() throws Exception{
        String indexName = getIndexName(null);
        String url = indexName+"/doc/_search/_source?_source=message&size=1&from=2";
        Request request = new Request("GET", url);
        request.addParameter("pretty","true");
        restClient.performRequest(request);
        Response response = restClient.performRequest(request);
        System.out.println(response.getStatusLine()); System.out.println(EntityUtils.toString(response.getEntity()));

    }



}
