package com.sofn.pcstd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Slf4j
@ComponentScan("com.sofn")
@EnableFeignClients(basePackages = {"com.sofn"})
@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class PcstdServiceApplication {

	public static void main(String[] args) {
		Environment env = SpringApplication.run(PcstdServiceApplication.class, args).getEnvironment();
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! \n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				env.getProperty("server.port")
		);
	}

	@Bean
	public TaskScheduler taskScheduler(){
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    taskScheduler.setPoolSize(10);
    return taskScheduler;
  }

}
