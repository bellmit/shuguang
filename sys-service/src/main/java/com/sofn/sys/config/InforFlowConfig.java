package com.sofn.sys.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * InforFlow配置
 * @author heyongjie
 * @date 2020/5/28 16:14
 */
@Data
@Configuration
@ConditionalOnProperty(prefix = "sofn.inforFlow", name = "enable", havingValue = "true")
public class InforFlowConfig {

    @Value("${sofn.inforFlow.processModelUrl}")
    private String processModelUrl;

}
