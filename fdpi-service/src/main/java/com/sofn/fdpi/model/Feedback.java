package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.common.model.BaseModel;
import com.sofn.fdpi.vo.FileManageForm;
import com.sofn.fdpi.vo.FileManageVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/28 09:35
 */
@TableName("TB_BREAK")
@Data
@ApiModel(value = "反馈对象")
public class Feedback extends BaseModel<Feedback> {
    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.UUID )
    private String id;
   /* //企业id
    private String compId;*/
    //物种标识
   @ApiModelProperty(value = "物种标识")
    private String code;
    //违法单位
    @ApiModelProperty(value = "违法单位")
    private String ffUnit;
    //违法地点
    @ApiModelProperty(value = "违法地点")
    private String ffLocal;
    //违法描述
    @ApiModelProperty(value = "违法描述")
    private String ffDesc;

    //反馈人员或执法人
    @ApiModelProperty(value = "反馈人员或执法人")
    private String ffPerson;
    //反馈时间或执法时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "反馈时间或执法时间")
    private Date ffDate;
    //物种名
    @ApiModelProperty(value = "物种名")
    private String speName;
    //反馈类型
    @ApiModelProperty(value = "反馈类型 ")
    private String brType;
    //反馈状态
    @ApiModelProperty(value = "反馈状态")
    private String brStatus;
    //部门id
    @ApiModelProperty(value = "部门id")
    private String deptId;
    //反馈来源
    @ApiModelProperty(value = "反馈来源1公众 2部门")
    private String ffFrom;
    //邮箱
    @ApiModelProperty(value = "邮箱")
    private String email;
    //省
    @ApiModelProperty(value = "省")
    private String province;
    //市
    @ApiModelProperty(value = "市")
    private String city;
    //区
    @ApiModelProperty(value = "区")
    private String area;
    @ApiModelProperty("添加所属省份")
    private String provinceId;
    @ApiModelProperty("状态：0未反馈，1已反馈")
    private String status;
    @ApiModelProperty("备案：0不备案，1备案上报")
    private String record;
    @ApiModelProperty("反馈意见")
    private String advice;
    @ApiModelProperty("直属部门id")
    private String direclyId;

    @TableField(exist = false)
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManage> files;
    @TableField(exist = false)
    @ApiModelProperty(value = "所在地")
    private String  localPlace;
    @ApiModelProperty(value = "执法人员电话")
    private String ffPhone;



}
