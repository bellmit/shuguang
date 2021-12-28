package com.sofn.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName SysStatusVo
 * @Description: TODO
 * @Author liling
 * @Date 2021/2/20
 * @Version V1.0
 **/
@Data
public class SysStatusVo {
    /**
     * 命令集合
     */
    @ApiModelProperty("命令集合")
    private List<String> commands;
    /**
     * 磁盘使用状况
     */
    @ApiModelProperty("磁盘使用状况")
    private List<FileSystemVo> fileSystemVos;

    /**
     * CPU使用状况
     */
    @ApiModelProperty("CPU使用状况")
    private List<CpuVo> cpuVos;

    /**
     * 内存使用状况
     */
    @ApiModelProperty("内存使用状况")
    private List<MemVo> memVos;

    /**
     * 服务ip
     */
    @ApiModelProperty("服务ip")
    private String sysIp;
}
