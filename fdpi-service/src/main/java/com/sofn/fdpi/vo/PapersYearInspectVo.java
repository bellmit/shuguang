package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Description:  证书年审列表对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书年审列表对象")
public class PapersYearInspectVo implements Serializable {
    @ApiModelProperty("证书年审主键ID")
    private String id;
    @ApiModelProperty("年度")
    private String year;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("状态中文")
    private String statusName;
    @ApiModelProperty("企业ID")
    private String compId;
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("联系人")
    private String linkMan;
    @ApiModelProperty("区域中文")
    private String regionInCh;
    @ApiModelProperty("创建时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("申请编号")
    private String applyNum;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("是否显示撤回按钮")
    private Boolean isShowCancel;
    @ApiModelProperty(value = "能否审核")
    private Boolean canAudit;
    @ApiModelProperty("省")
    private String compProvince;
    @ApiModelProperty("市")
    private String compCity;
    @ApiModelProperty("县")
    private String compDistrict;

}
