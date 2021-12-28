package com.sofn.fdpi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/2 17:48
 */
@ApiModel(value = "罚没信息VO对象")
@Data
public class ForfeiInfoVo extends BaseVo<ForfeiInfoVo> {
    /**
     * 罚没ID
     */
    @ApiModelProperty(value = "罚没ID")
    private String id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private String compId;

    /**
     * 罚没类型    IS_LIVE("1", "活体"1", "活体"),
     *     IS_GOODS("", "制品0");
     */
    @ApiModelProperty(value = "罚没类型 活体1/制品0")
    private String ffType;

    /**
     * 时间
     */
    @NotNull(message = "罚没时间不可以为空")
    @ApiModelProperty(value = "罚没时间")
    private Date ffDate;

    @ApiModelProperty(value = "标识编码")
    private String code;
    /**
     * 地点
     */
    @NotBlank(message="地点必填")
    @Length(max = 50,message = "地点不能超过50")
    @ApiModelProperty(value = "地点")
    private String ffLocal;

    /**
     * 物种名
     */
    @NotBlank(message="物种名必填")
//    @Length(max = 50,message = "物种名不能超过10")
    @ApiModelProperty(value = "物种名")
    private String speName;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Long speNum;

    /**
     * 体况/规格
     */
    @NotBlank(message="体况/规格必填")
    @Length(max = 500,message = "体况/规格不能超过500")
    @ApiModelProperty(value = "体况/规格")
    private String speCon;

    /**
     * 救助场所
     */
    @NotBlank(message="救助场所必填")
    @Length(max = 50,message = "救助场所不能超过50")
    @ApiModelProperty(value = "救助场所")
    private String resSites;

    @TableField(exist = false)
    @ApiModelProperty(value = "文件表单列表")
    private List<FileManageForm> files;

    /**
     * 处置意见
     */
    @NotBlank(message="处置意见必填")
    @Length(max = 500,message = "处置意见不能超过500")
    @ApiModelProperty(value = "处置意见")
    private String ffIdea;

    /**
     * 处置方案
     */
    @ApiModelProperty(value = "处置方案")
    private String ffMeth;

    /**
     * 处置单位
     */
    @ApiModelProperty(value = "处置单位")
    private String ffUnit;

    /**
     * 处置时间
     */
    @NotNull(message = "处置时间不能为空")
    @ApiModelProperty(value = "处置时间")
    private  Date disDate;

    /**
     * 制品型号
     */
    @Length(max = 30,message = "制品型号不能超过30")
    @ApiModelProperty(value = "制品型号")
    private String proModel;

    /**
     * 包装方式
     */
    @Length(max = 30,message = "包装方式不能超过30")
    @ApiModelProperty(value = "包装方式")
    private String packType;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态  1未上报 6已上报 ")
    private int status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人员ID
     */
    @ApiModelProperty(value = "创建人员ID")
    private String createUserId;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人员ID
     */
    @ApiModelProperty(value = "修改人员ID")
    private String updateUserId;

    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private String delFlag;
    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(value = "区")
    private String area;

}
