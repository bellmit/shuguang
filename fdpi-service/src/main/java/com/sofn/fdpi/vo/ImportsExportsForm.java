package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-02 13:38
 */
@Data
@ApiModel(value = "修改进出口表单对象")
public class ImportsExportsForm {

    @ApiModelProperty(value = "主键")
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
    @Length(max = 30,message = "进口单位不能超过30位")
//    @NotBlank(message = "进口单位不为空")
    @ApiModelProperty(name = "impComp", value = "进口单位")
    private String impComp;
    /**
     * 出口、再出口单位
     */
    @Length(max = 30,message = "出口、再出口单位不能超过30位")
//    @NotBlank(message = "进口单位不为空")
    @ApiModelProperty(name = "exComp", value = "出口、再出口单位")
    private String exComp;
    /**
     * 有效日期
     */

    @ApiModelProperty(name = "validityTime", value = "有效日期")
    private Date validityTime;

    /**
     *   签证机关
     */
    @Length(max = 30,message = "签证机关不能超过30位")
    @NotBlank(message = "签证机关不为空")
    @ApiModelProperty(name = "visaAuth", value = "签证机关")
    private String visaAuth;
    /**
     * 签发日期
     */

    @ApiModelProperty(name = "issueDate", value = "签发日期")
    private Date issueDate;

    @Valid
    @NotNull(message = "ies不能为空")
    @Size(min = 1, message = "ies至少要有一个自定义属性")
    private List<ImportsExportsSpeciesForm> ies;
}
