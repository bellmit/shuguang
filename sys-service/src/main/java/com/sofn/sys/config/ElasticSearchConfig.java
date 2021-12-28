package com.sofn.sys.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author heyongjie
 * @date 2020/9/16 15:58
 */
@Configuration
@Slf4j
public class ElasticSearchConfig {

    @Value("${sofn.elasticsearch.ip}")
    private String ip;
    @Value("${sofn.elasticsearch.host}")
    private String host;
    @Value("${sofn.elasticsearch.userName}")
    private String userName;
    @Value("${sofn.elasticsearch.password}")
    private String password;
    @Bean
    public RestClient restClient() {

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost(ip, Integer.parseInt(host), "http")
        ).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        });;
        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                super.onFailure(node);
                log.error("ElasticSearch 节点初始化失败：{}", node);
            }
        });
        return restClientBuilder.build();
    }

}
