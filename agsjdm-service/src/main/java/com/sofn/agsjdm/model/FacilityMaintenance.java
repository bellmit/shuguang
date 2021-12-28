package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-04-16 9:13
 */
@ApiModel("设施监控管理")
@Data
@TableName("FACILITY_MAINTENANCE")
public class FacilityMaintenance {
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String id;
    /**
     * 湿地区ID
     */
    @NotBlank(message = "湿地区ID不可为空")
    @ApiModelProperty("湿地区ID")
    private String wetlandId;
    /**
     * 湿地区名称
     */
    @ApiModelProperty(value = "湿地区名称(前端新增/修改不用传)")
    @TableField(exist = false)
    private String wetlandName;
    /**
     * 道路情况
     */
    @Length(max = 64, message = "道路情况字符数不可大于64位")
    @ApiModelProperty("道路情况")
    private String road;
    /**
     * 房屋数量
     */
    @Length(max = 64, message = "房屋数量字符数不可大于64位")
    @Pattern(regexp = "\\d*", message = "房屋数量只能是正整数")
    @ApiModelProperty("房屋数量")
    private String houseNum;
    /**
     * 房屋情况
     */
    @Length(max = 64, message = "房屋情况字符数不可大于64位")
    @ApiModelProperty("房屋情况")
    private String house;
    /**
     * 仪器设备
     */
    @Length(max = 64, message = "仪器设备字符数不可大于64位")
    @ApiModelProperty("仪器设备")
    private String instrEq;
    /**
     * 人员情况
     */
    @Length(max = 64, message = "人员情况字符数不可大于64位")
    @ApiModelProperty("人员情况")
    private String personSit;
    /**
     * 备注
     */
    @Length(max = 64, message = "备注字符数不可大于64位")
    @ApiModelProperty("备注")
    private String remarks;
    /**
     * 操作人
     */
    @ApiModelProperty("操作人")
    private String operator;
    /**
     * 操作时间
     */
    @ApiModelProperty("操作时间")
    private Date operatorTime;

    private String province;
    private String city;
    private String county;
}
