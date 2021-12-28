package com.sofn.flow;

import com.cvicse.workflow.util.WorkflowConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@EnableAspectJAutoProxy(exposeProxy=true)
@EnableEurekaClient
@SpringBootApplication
public class WorkflowApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext  context = SpringApplication.run(WorkflowApplication.class, args);
        String[] profiles = context.getEnvironment().getActiveProfiles();
        String inforConfigFileName = String.format("config/inforflow-%s.xml", profiles[0]);
        ClassPathResource resource = new ClassPathResource(inforConfigFileName);
        InputStream inputStream = resource.getInputStream();
        WorkflowConfig.initBeanFactory(inputStream);
    }
}
