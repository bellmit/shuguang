package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description: 进出口
 * @Auther: xiaobo
 * @Date: 2019-12-30 15:15
 */
@TableName("IMPORTS_EXPORTS")
@ApiModel(value = "进出口表单对象")
@Data
public class ImportsExports extends BaseModel<ImportsExports> {
    @ApiModelProperty(value = "主键")
    @TableId(value = "ID", type = IdType.UUID)
    private  String id;

    /**
     * 进出口审核编号
     */
    @Length(max = 30,message = "进出口审核编号不能超过30位")
    @NotBlank(message = "进出口审核编号不为空")
    @ApiModelProperty(name = "impAuform", value = "进出口审核编号")
    private String impAuform;
    /**
     * 进口单位
     */
    @Length(max = 20,message = "进口单位不能超过20位")
    @ApiModelProperty(name = "impComp", value = "进口单位")
    private String impComp;
    /**
     * 出口单位
     */
    @Length(max = 20,message = "出口单位不能超过20位")
    @ApiModelProperty(name = "exComp", value = "出口单位")
    private String exComp;
    /**
     * 有效日期
     */
    @ApiModelProperty(name = "validityTime", value = "有效日期")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validityTime;


    /**
     *   签证机关
     */
    @Length(max = 20,message = "签证机关不能超过20位")
    @NotBlank(message = "签证机关不为空")
    @ApiModelProperty(name = "visaAuth", value = "签证机关")
    private String visaAuth;
    /**
     * 签发日期
     */
    @ApiModelProperty(name = "issueDate", value = "签发日期")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issueDate;
    @Valid
    @TableField(exist = false)
    @ApiModelProperty(value = "进出口物种")
    private List<ImportsExportsSpecies> ies;
    @ApiModelProperty("是否打印，0否 1：是")
    private  String isPrint;
    @TableField(exist = false)
    @ApiModelProperty(value = "能否打印")
    private Boolean canPrint;
    @TableField(exist = false)
    @ApiModelProperty(value = "能否操作")
    private Boolean canHandle;

}
