package com.sofn.common.config;

import com.sofn.common.clearlogicaldeletedata.ClearLogicalDeleteData;
import com.sofn.common.clearlogicaldeletedata.properties.ClearLogicalDeleteDataProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 清除逻辑删除数据配置
 * 请配置sofn.cleardata.enable= true 启动该配置
 * 详细配置请参考com.sofn.common.clearlogicaldeletedata.properties.ClearLogicalDeleteDataProperties
 * @author heyongjie
 * @date 2019/11/26 15:10
 */
@Configuration
public class ClearLogicalDeleteDataConfig {

    @Autowired(required = false)
    private DataSource dataSource;

    @Value("${spring.datasource.url:}")
    private String dataSourceUrl;

    @Autowired(required = false)
    private ClearLogicalDeleteDataProperties clearLogicalDeleteDataProperties;

    @Bean
    @ConditionalOnProperty(prefix = "sofn.cleardata", name = "enable", havingValue = "true")
    public ClearLogicalDeleteData getClearLogicalDeleteData(){
        if(clearLogicalDeleteDataProperties != null){
            return new ClearLogicalDeleteData(dataSource,dataSourceUrl,clearLogicalDeleteDataProperties.getDatAgo());
        }
        return null;
    }

}
