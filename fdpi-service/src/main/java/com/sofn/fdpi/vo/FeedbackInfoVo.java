package com.sofn.fdpi.vo;


import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/30 15:41
 */
@ApiModel(value="反馈VO对象")
@Data
public class FeedbackInfoVo extends BaseVo<FeedbackInfoVo> {
    @ApiModelProperty(value = "主键id（新增不用传）")
    private String id;
   /* @ApiModelProperty(value = "企业id")
    //企业id
    private String compId;*/
   @Size(max = 30,message = "物种标识长度不超过30位")
    @ApiModelProperty(value = "物种标识")
    //物种标识
    private String code;
    @NotBlank(message="违法单位必填")
    @Size(max = 20,message = "违法单位不能超过20位")
    @ApiModelProperty(value = "违法单位")
    //违法单位
    private String ffUnit;
    @NotBlank(message="违法地点必填")
    @Size(max = 50,message = "违法地点不能超过50位")
    @ApiModelProperty(value = "违法地点")
    private String ffLocal;
    @NotBlank(message="违法描述必填")
    @Length(max = 500,message = "违法描述不能超过500位")
    @ApiModelProperty(value = "违法描述")
    private String ffDesc;
    @NotBlank(message="执法人必填")
    @Size(max = 10,message = "反馈人员或执法人不能超过10位")
    @ApiModelProperty(value = "反馈人员或执法人")
    private String ffPerson;

    @ApiModelProperty(value = "反馈时间或执法时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date ffDate;
    @NotBlank(message="物种名必填")
    @Size(max = 10,message = "物种名不能超过30位")
    @ApiModelProperty(value = "物种名")
    private String speName;
    @ApiModelProperty(value = "反馈类型,不用传")
    //反馈类型
    private String brType;
//    @ApiModelProperty(value = "反馈状态")
//    //反馈状态
//    private String brStatus;
    /*@ApiModelProperty(name = "deptId", value = "部门ID")
    private String deptId;*/
//    @ApiModelProperty(name = "ffFrom", value = "反馈来源 1 公众 2 部门")
//    private String ffFrom;
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;
    @ApiModelProperty(name = "province", value = "省code")
    private String province;
    @ApiModelProperty(name = "city", value = "市code")
    private String city;
    @ApiModelProperty(name = "area", value = "区code")
    private String area;
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManageForm> files;
    @ApiModelProperty(value = "执法人员电话")
    private String ffPhone;
}
