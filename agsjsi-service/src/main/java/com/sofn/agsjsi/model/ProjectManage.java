package com.sofn.agsjsi.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "计划管理")
@TableName(value = "project_manage")
public class ProjectManage extends BaseModel<ProjectManage> {

    @ApiModelProperty(name = "id", value = "编码")
    @TableId
    private String id;
    @NotBlank(message = "计划名称必填！")
    @Length(max = 100,message = "计划名称长度不能超过100！")
    @ApiModelProperty(name = "name", value = "计划名称")
    private String name;
    @ApiModelProperty(name = "content", value = "计划内容")
    private String content;
    @NotBlank(message = "所属机构必填！")
    @Length(max = 32,message = "所属结构ID长度不能超过32！")
    @ApiModelProperty(name = "orgId", value = "所属机构ID")
    private String orgId;
    @NotBlank(message = "所属机构必填！")
    @Length(max = 100,message = "所属机构名称长度不能超过100！")
    @ApiModelProperty(name = "orgName", value = "所属机构名称")
    private String orgName;
    @ApiModelProperty(name = "planTimeStart", value = "计划周期开始时间")
    private Date planTimeStart;
    @ApiModelProperty(name = "planTimeEnd", value = "计划周期结束时间")
    private Date planTimeEnd;
    @ApiModelProperty(name = "status", value = "状态-1：已保存；2：已下发；3：已查看")
    private String status;
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "createUserId", value = "创建人id")
    private String createUserId;
    @ApiModelProperty(name = "updateTime", value = "修改时间")
    private Date updateTime;
    @ApiModelProperty(name = "updateUserId", value = "修改人id")
    private String updateUserId;
    @ApiModelProperty(name = "createUserName", value = "创建人名字")
    private String createUserName;
    @ApiModelProperty(name = "updateUserName", value = "修改人名字")
    private String updateUserName;
    @ApiModelProperty(name = "delFlag", value = "删除标识")
    private String delFlag;

}
