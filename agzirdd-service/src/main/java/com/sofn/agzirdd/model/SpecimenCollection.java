package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
/**
 * @Description: 物种采集模块-标本采集基本信息
 * @Author: mcc
 * @Date: 2020\3\17 0017
 */
@TableName("SPECIMEN_COLLECTION")
@Data
public class SpecimenCollection extends Model<SpecimenCollection> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "belongYear", value = "所属年度")
    private String belongYear;

    @NotBlank(message = "采集号不能为空!")
    @ApiModelProperty(name = "collectNumber", value = "采集号")
    private String collectNumber;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "collectTime", value = "采集时间")
    private Date collectTime;

    @NotBlank(message = "采集人不能为空!")
    @Size(max = 30,message = "采集人长度不能超过30个字")
    @ApiModelProperty(name = "gatherer", value = "采集人")
    private String gatherer;

    @NotBlank(message = "采集单位不能为空!")
    @Size(max = 20,message = "采集单位长度不能超过20个字")
    @ApiModelProperty(name = "company", value = "采集单位")
    private String company;

    @NotBlank(message = "省级不能为空!")
    @ApiModelProperty(name = "provinceId", value = "省级id")
    private String provinceId;
    private String provinceName;

    @NotBlank(message = "市级不能为空!")
    @ApiModelProperty(name = "cityId", value = "市级id")
    private String cityId;
    private String cityName;

    @NotBlank(message = "纬度不能为空!")
    @ApiModelProperty(name = "countyId", value = "区县id")
    private String countyId;
    private String countyName;

    @NotBlank(message = "乡村/镇不能为空!")
    @ApiModelProperty(name = "town", value = "乡村/镇")
    private String town;

    @NotBlank(message = "经度不能为空!")
    @ApiModelProperty(name = "longitude", value = "经度")
    private String longitude;

    @NotBlank(message = "纬度不能为空!")
    @ApiModelProperty(name = "latitude", value = "纬度")
    private String latitude;

    @ApiModelProperty(name = "altitude", value = "海拔")
    private String altitude;

    @ApiModelProperty(name = "status", value = "状态:0-已保存,1-已提交,2-已撤回,3-市级通过,4-市级退回," +
            "5-省级通过,6-省级退回,7-总站通过,8-总站退回,9-专家填报,10-专家提交")
    private String status;

    @ApiModelProperty(name = "type", value = "标本类型：0-植物；1-动物；2-微生物")
    private String type;

    @ApiModelProperty(name = "createUserId", value = "创建者id(不用传递)")
    private String createUserId;

    @ApiModelProperty(name = "createUserName", value = "创建者姓名(不用传递)")
    private String createUserName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "createTime", value = "创建时间(不用传递)")
    private Date createTime;

    @ApiModelProperty(name = "roleCode", value = "角色码(不用传递)")
    private String roleCode;

    @ApiModelProperty(name = "cfdd", value = "存放地点，限制50字以内")
    private String cfdd;

    @ApiModelProperty(name = "cftj", value = "存放条件，1-常温、2-冷藏（4℃）、3-冷冻（-20℃）、4-超低温（-80℃）、5-其他")
    private String cftj;
}