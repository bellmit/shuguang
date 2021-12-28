/**
 * @Author 文俊云
 * @Date 2019/12/30 16:03
 * @Version 1.0
 */
package com.sofn.fdpi.model;

import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2019/12/30 16:03
 * @Version 1.0
 */

@Data
public class Transfer extends BaseModel {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("标志编码集合，以“，”间隔")
    private String signId;
    @ApiModelProperty("增加企业ID")
    private String addCompanyId;
    @ApiModelProperty("转移审核状态")
    private String transferStatus;
    @ApiModelProperty("减少企业ID")
    private String reduceCompanyId;
    @ApiModelProperty("创建人ID")
    private String createUserId;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新人ID")
    private String updateUserId;
    @ApiModelProperty("更新时间")
    private Date updateTime;
    @ApiModelProperty("复审减少意见")
    private String secondOpnion;
    @ApiModelProperty("初审减少意见")
    private String firstOpnion;
    @ApiModelProperty("复审增加意见")
    private String fourthOpnion;
    @ApiModelProperty("初审增加意见")
    private String thirdOpnion;
    @ApiModelProperty("删除标志")
    private String delFlag;
    @ApiModelProperty("增加方合同名称")
    private String addContractName;
    @ApiModelProperty("增加方合同ID")
    private String addContractId;
    @ApiModelProperty("增加方合同路径")
    private String addContractPath;
    @ApiModelProperty("减少方合同名称")
    private String reduceContractName;
    @ApiModelProperty("减少方合同ID")
    private String reduceContractId;
    @ApiModelProperty("减少方合同路径")
    private String reduceContractPath;

    @ApiModelProperty("评估意见文件名称")
    private String assessmentName;
    @ApiModelProperty("评估意见文件ID")
    private String assessmentId;
    @ApiModelProperty("评估意见文件路径")
    private String assessmentPath;


    @ApiModelProperty("是否有无证书标识，0有，1没有")
    private String isHavePaper;
}
