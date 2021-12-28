package com.sofn.agzirdd.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @Description: 物种监测模块-伴生物种
 * @Author: mcc
 * @Date: 2020\3\10 0010
 */
@TableName("MONITOR_COMPANION_SPECIES")
@Data
public class MonitorCompanionSpecies extends Model<MonitorCompanionSpecies> {

    @ApiModelProperty(name = "id", value = "id")
    private String id;

    @ApiModelProperty(name = "speciesMonitorId", value = "物种监测表id")
    private String speciesMonitorId;

    @ApiModelProperty(name = "speciesName", value = "入侵物种中文名称")
    private String speciesName;

    @ApiModelProperty(name = "orderNumber", value = "orderNumber")
    private String orderNumber;

    @ApiModelProperty(name = "amount", value = "数量:株或个")
    private String amount;

    @ApiModelProperty(name = "coverRatio", value = "覆盖度")
    private String coverRatio;

    @ApiModelProperty(name = "remark", value = "备注")
    private String remark;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;


}