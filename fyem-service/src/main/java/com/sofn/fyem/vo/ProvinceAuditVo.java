package com.sofn.fyem.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 省级审核数据Vo
 * @Author: DJH
 * @Date: 2020/4/28 14:04
 */
@ApiModel(value = "ProvinceAuditVo", description = "省级审核数据Vo")
@TableName(value = "PROVINCE_AUDIT")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProvinceAuditVo extends BaseVo<ProvinceAuditVo> {

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市id")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;

    @ApiModelProperty(name = "belongYear", value = "上报年度")
    private String belongYear;

    @ApiModelProperty(name = "organizationName", value = "上报单位")
    private String organizationName;

    @ApiModelProperty(name = "roleCode", value = "角色code")
    private String roleCode;

    @ApiModelProperty(name = "status", value = "待审状态")
    private String status;

    @ApiModelProperty(name = "statusCode", value = "状态码code")
    private String statusCode;
}
