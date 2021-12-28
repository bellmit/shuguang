package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName FileSystemVo
 * @Description: TODO
 * @Author liling
 * @Date 2021/2/20
 * @Version V1.0
 **/
@Data
@ApiModel(value = "SYS服务器磁盘使用情况")
public class FileSystemVo {
    /**
     * 磁盘名称
     */
    @ApiModelProperty("磁盘名称")
    private String systemName;
    /**
     * 磁盘总量
     */
    @ApiModelProperty("磁盘总量")
    private String size;
    /**
     * 磁盘使用量
     */
    @ApiModelProperty("磁盘使用量")
    private String used;
    /**
     * 磁盘剩余量
     */
    @ApiModelProperty("磁盘剩余量")
    private String free;
    /**
     * 磁盘使用百分比
     */
    @ApiModelProperty("磁盘使用百分比")
    private String sizeUsedPoint;

    @Override
    public String toString() {
        return "FileSystemVo{" +
                "systemName='" + systemName + '\'' +
                ", size='" + size + '\'' +
                ", used='" + used + '\'' +
                ", free='" + free + '\'' +
                ", sizeUsedPoint='" + sizeUsedPoint + '\'' +
                '}';
    }
}
