package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description: 物种监测模块-入侵物种
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@TableName("MONITOR_INVASIVE_ALIEN_SPECIES")
@Data
public class MonitorInvasiveAlienSpecies extends Model<MonitorInvasiveAlienSpecies> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesMonitorId", value = "物种监测表id")
    private String speciesMonitorId;

    @ApiModelProperty(name = "speciesType", value = "物种类型(0-植物,1-动物,2-微生物)")
    private String speciesType;

    @ApiModelProperty(name = "speciesTypeName", value = "物种类型名称")
    private String speciesTypeName;

    @ApiModelProperty(name = "speciesId", value = "物种id")
    private String speciesId;

    @ApiModelProperty(name = "speciesName", value = "入侵物种中文名称")
    private String speciesName;

    @ApiModelProperty(name = "latinName", value = "拉丁学名")
    private String latinName;

    @ApiModelProperty(name = "amount", value = "数量:株或个")
    private String amount;

    @ApiModelProperty(name = "coverRatio", value = "覆盖度")
    private String coverRatio;

    @ApiModelProperty(name = "orderNumber", value = "序号")
    private String orderNumber;

    @ApiModelProperty(name = "longitude", value = "经度，保留小数点后6位小数")
    private String longitude;

    @ApiModelProperty(name = "latitude", value = "纬度，保留小数点后6位小数")
    private String latitude;

    @ApiModelProperty(name = "altitude", value = "海拔")
    private String altitude;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

}