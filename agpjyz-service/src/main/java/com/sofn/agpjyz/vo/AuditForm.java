package com.sofn.agpjyz.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Description:
 * @Author: xiaobo
 * @Date: 2020-09-23 9:39
 */
@ApiModel("审核对象")
@Data
public class AuditForm {
    @ApiModelProperty("主键ids,多id用英文','隔开")
    @NotBlank(message = "主键ids不能为空")
    private String id;
    @Size(max = 2000, message = "审核意见长度不能超过2000位")
    @ApiModelProperty("审核意见")
    private String auditOpinion;
}
