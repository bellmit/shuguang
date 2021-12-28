package com.sofn.ducss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@ComponentScan("com.sofn")
@EnableFeignClients(basePackages = {"com.sofn"})
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
//@EnableCanalClient //声明当前的服务是canal的客户端
public class DucssServiceApplication {

	public static void main(String[] args) {
		System.setProperty("es.set.netty.runtime.available.processors", "false");
//		SpringApplication.run(DucssServiceApplication.class, args);
		Environment env = SpringApplication.run(DucssServiceApplication.class, args).getEnvironment();
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! \n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port")
		);
	}

}

