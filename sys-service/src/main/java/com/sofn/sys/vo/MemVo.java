package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MemVo
 * @Description: TODO
 * @Author liling
 * @Date 2021/2/20
 * @Version V1.0
 **/
@Data
@ApiModel(value = "SYS服务器内存使用情况")
public class MemVo {
    /**
     * 内存名称
     */
    @ApiModelProperty("内存名称")
    private String systemName;
    /**
     * 物理内存总量
     */
    @ApiModelProperty("物理内存总量")
    private String size;
    /**
     * 使用中的内存总量
     */
    @ApiModelProperty("使用中的内存总量")
    private String used;
    /**
     * 内存剩余量
     */
    @ApiModelProperty("内存剩余量")
    private String free;

    @Override
    public String toString() {
        return "MemVo{" +
                "systemName='" + systemName + '\'' +
                ", size='" + size + '\'' +
                ", used='" + used + '\'' +
                ", free='" + free + '\'' +
                '}';
    }
}
