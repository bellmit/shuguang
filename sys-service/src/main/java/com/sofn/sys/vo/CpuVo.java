package com.sofn.sys.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CpuVo
 * @Description: TODO
 * @Author liling
 * @Date 2021/2/20
 * @Version V1.0
 **/
@Data
@ApiModel(value = "SYS服务器CPU使用情况")
public class CpuVo {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 用户空间占用CPU的百分比
     */
    @ApiModelProperty("用户空间占用CPU的百分比")
    private String us;
    /**
     * 内核空间占用CPU的百分比
     */
    @ApiModelProperty("内核空间占用CPU的百分比")
    private String sy;
    /**
     * 改变过优先级的进程占用CPU的百分比
     */
    @ApiModelProperty("改变过优先级的进程占用CPU的百分比")
    private String ni;
    /**
     * 空闲CPU百分比
     */
    @ApiModelProperty("空闲CPU百分比")
    private String id;
    /**
     * IO等待占用CPU的百分比
     */
    @ApiModelProperty("IO等待占用CPU的百分比")
    private String wa;
    /**
     * 硬中断（Hardware IRQ）占用CPU的百分比
     */
    @ApiModelProperty("硬中断（Hardware IRQ）占用CPU的百分比")
    private String hi;
    /**
     * 软中断（Software Interrupts）占用CPU的百分比
     */
    @ApiModelProperty("软中断（Software Interrupts）占用CPU的百分比")
    private String si;
    /**
     * 实时虚拟机占用物理机的百分比
     */
    @ApiModelProperty("实时虚拟机占用物理机的百分比")
    private String st;

    @Override
    public String toString() {
        return "CpuVo{" +
                "name='" + name + '\'' +
                ", us='" + us + '\'' +
                ", sy='" + sy + '\'' +
                ", ni='" + ni + '\'' +
                ", id='" + id + '\'' +
                ", wa='" + wa + '\'' +
                ", hi='" + hi + '\'' +
                ", si='" + si + '\'' +
                ", st='" + st + '\'' +
                '}';
    }
}
