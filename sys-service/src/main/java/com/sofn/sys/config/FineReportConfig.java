package com.sofn.sys.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * 帆软报表配置
 * 需要登录帆软报表的决策系统依次点击：管理系统->模板认证->最上方的设置按钮->需要认证的模板
 * 将新开发的报表设置为需要认证才能访问，认证方式为数字签名认证，数字秘钥可以自定义
 *
 * @author heyongjie
 * @date 2020/5/26 14:28
 */
@Configuration
@ConditionalOnProperty(prefix = "sofn.fineReport", name = "enable", havingValue = "true")
@Data
public class FineReportConfig {

    /**
     * 帆软报表通过数字签名认证，数字签名使用的密码
     */
    @Value("${sofn.fineReport.password}")
    private String password;

    /**
     * 帆软报表通过数字签名的路径如下：
     * http://localhost:8075/webroot/decision/view/report?viewlet=机构用户数据量.cpt&fine_digital_signature=TOKEN
     * http://localhost:8075/webroot/decision/view/report?viewlet=这段用于用户配置，后面用于自动生成
     */
    @Value("${sofn.fineReport.reportUrl}")
    private String reportUrl;

}
