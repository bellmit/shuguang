package com.sofn.ducss.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * Created by sofn
 */
@Data
@ConfigurationProperties(prefix = "callback")
@Component
public class FormCallbackConfig {
    private String dir;
}
