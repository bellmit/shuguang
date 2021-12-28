package com.sofn.fdpi;

import com.sofn.fdpi.config.FdpiFeignConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@ComponentScan("com.sofn")
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(defaultConfiguration = FdpiFeignConfiguration.class)
@EnableTransactionManagement
@EnableScheduling
public class FdpiApplication {

    public static void main(String[] args) {
        Environment env = SpringApplication.run(FdpiApplication.class, args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! \n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port")
        );
    }
}
