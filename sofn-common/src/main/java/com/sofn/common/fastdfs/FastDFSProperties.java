/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.fastdfs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * fastdfs文件服务器客户端配置项
 *
 * @author lijiang
 * @create 2019-04-01 13:45
 **/
@Data
public final class FastDFSProperties {
    private String connectTimeoutInSeconds = "";
    private String networkTimeoutInSeconds = "";
    private String charset = "";
    private String httpAntiStealToken = "";
    private String httpSecretKey = "";
    private String httpTrackerHttpPort = "";
    @NotBlank
    private String trackerServers;
    private String downloadPath = "";
    private String tempFilePath = "";
    private String forbidFilenameSuffix = "";
    private String forbidMediaType = "";
    private Long uploadFileSizeUpper = 1024L * 1024 * 200;
}
