package com.sofn.fyem.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "市级审核")
@TableName("CITY_AUDIT")
@Data
public class CityAudit extends Model<CityAudit> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "organizationId", value = "单位id")
    private String organizationId;

    @ApiModelProperty(name = "organizationName", value = "单位名称")
    private String organizationName;

    @ApiModelProperty(name = "status", value = "状态")
    private String status;

    @ApiModelProperty(name = "remark", value = "意见")
    private String remark;

    @ApiModelProperty(name = "roleCode", value = "角色code")
    private String roleCode;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @ApiModelProperty(name = "createUserId", value = "上报人id")
    private String createUserId;

    public CityAudit() {
    }
}