package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("DISTRIBUTION_MAP")
public class DistributionMap extends Model<DistributionMap> {
    @TableId(type = IdType.UUID)
    @ApiModelProperty(name = "id", value = "id(不用传递)")
    private String id;
    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;
    @ApiModelProperty(name = "speciesName", value = "物种名称")
    private String speciesName;
    @ApiModelProperty(name = "provinceId", value = "省区划ID，6位编号")
    private String provinceId;
    @ApiModelProperty(name = "cityId", value = "市区划ID，6位编号")
    private String cityId;
    @ApiModelProperty(name = "countyId", value = "区县区划ID，6位编号")
    private String countyId;
    @ApiModelProperty(name = "areaName", value = "省市区组合名")
    private String areaName;
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;
    @ApiModelProperty(name = "finder", value = "发现者")
    private String finder;
    @ApiModelProperty(name = "gps", value = "gps")
    private String gps;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "发现时间")
    private Date createTime;
    @ApiModelProperty(name = "provinceName", value = "省名称")
    private String provinceName;
    @ApiModelProperty(name = "cityName", value = "市名称")
    private String cityName;
    @ApiModelProperty(name = "countyName", value = "区县名称")
    private String countyName;
    @ApiModelProperty(name = "investigatorCompany", value = "调查人单位")
    private String investigatorCompany;
    @ApiModelProperty(name = "amount", value = "样方内株（个）数/平米")
    private String amount;
    @ApiModelProperty(name = "area", value = "发生面积")
    private String area;
    @ApiModelProperty(name = "resultImg", value = "效果图")
    private String resultImg;
    @ApiModelProperty(name = "utilizeImg", value = "利用情况照片")
    private String utilizeImg;
    @ApiModelProperty(name = "speciesImg", value = "新发物种图")
    private String speciesImg;
    @ApiModelProperty(name = "speciesInvestigationId", value = "关联调查ID")
    private String speciesInvestigationId;

}