package com.sofn.common.clearlogicaldeletedata.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author heyongjie
 * @date 2019/11/26 17:19
 */
@Data
@ConfigurationProperties(prefix = "sofn.cleardata")
@Component
public class ClearLogicalDeleteDataProperties {

    /**
     * 是否启用清理逻辑删除的数据
     */
    private boolean enable = false;

    /**
     * 清理多少天之前的数据
     */
    private Integer datAgo = 30;

}
