package com.sofn.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author Zhang Yi
 * @Date 2020/9/30 11:35
 * @Version 1.0
 * 发送http接口
 */
@Configuration
public class RestTemplateConfig {
//
//	@Bean
//	public RestTemplate restTemplate() {
//		RestTemplate restTemplate = new RestTemplate(
//				new HttpComponentsClientHttpRequestFactory()); // 使用HttpClient，支持GZIP
//		restTemplate.getMessageConverters().set(1,
//				new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 支持中文编码
//		return restTemplate;
//	}

	@Bean
	public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory);
		restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(Charset.forName("utf-8")));
		return restTemplate;
	}

	@Bean
	public ClientHttpRequestFactory simple(){
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(5000);
		return factory;
	}











}
