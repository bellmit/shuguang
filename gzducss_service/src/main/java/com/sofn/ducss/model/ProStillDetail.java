package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName("pro_still_detail")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ApiModel("产生量与直接还田量填报表")
public class ProStillDetail extends Model<ProStillDetail> {
    private String id;

    @ApiModelProperty(name = "proStillId", value = "还田量主键ID")
    private String proStillId;

    @ApiModelProperty(name = "strawType", value = "秸秆类型")
    private String strawType;// 秸秆类型

    @ApiModelProperty(name = "strawName", value = "秸秆名称")
    private String strawName;//秸秆名称

    @ApiModelProperty(name = "grainYield", value = "粮食产量")
    @Length(max = 38, message = "粮食产量超长")
    private BigDecimal grainYield;// 粮食产量

    @ApiModelProperty(name = "grassValleyRatio", value = "草谷比")
    @Length(max = 38, message = "草谷比超长")
    private BigDecimal grassValleyRatio;// 草谷比

    @ApiModelProperty(name = "collectionRatio", value = "收集系数")
    @Length(max = 38, message = "收集系数超长")
    private BigDecimal collectionRatio;// 收集系数

    @ApiModelProperty(name = "returnRatio", value = "还田比例")
    private BigDecimal returnRatio;// 还田比例

    @ApiModelProperty(name = "returnArea", value = "还田面积")
    @Length(max = 38, message = "还田面积超长")
    private BigDecimal returnArea;//还田面积

    @ApiModelProperty(name = "seedArea", value = "播种面积")
    @Length(max = 38, message = "播种面积超长")
    private BigDecimal seedArea;//播种面积

    @ApiModelProperty(name = "exportYield", value = "调出量")
    @Length(max = 38, message = "调出量超长")
    private BigDecimal exportYield;//调出量

    //ByDataAnalysis-start
    @ApiModelProperty(name = "theoryResource", value = "产生量=粮食产量*草谷比")
    private BigDecimal theoryResource; // 产生量=粮食产量*草谷比

    @ApiModelProperty(name = "collectResource", value = "可收集量=产生量*收集系数")
    private BigDecimal collectResource;// 可收集量=产生量*收集系数

    @ApiModelProperty(name = "returnResource", value = "直接还田量=可收集量*还田比例")
    private BigDecimal returnResource; // 直接还田量=可收集量*还田比例
    //ByDataAnalysis-end

    /**
     * 2021-06-09 新加字段
     */
    @ApiModelProperty("还田方式")
    private String returnType;

    @ApiModelProperty("离田运输方式")
    private String leavingType;

    @ApiModelProperty("运输补贴")
    private BigDecimal transportAmount;

    /**
     * 扩展字段
     */
    @ApiModelProperty(name = "areaId", value = "县ID")
    @TableField(exist = false)
    private String areaId;            // 行政区划ID

    @ApiModelProperty(name = "assigned", value = "是否填值  暂时实际值，后面改为  0-未填写；1-已填写")
    @TableField(exist = false)
    private BigDecimal assigned;       // 是否填值  暂时实际值，后面改为  0-未填写；1-已填写

    //ByDataAnalysis-start
    @ApiModelProperty("时间")
    @TableField(exist = false)
    private String gtime;

    @ApiModelProperty("区域")
    @TableField(exist = false)
    private String area_Id;

    @ApiModelProperty("区域名称")
    @TableField(exist = false)
    private String area_Name;

    @ApiModelProperty("主表实体类")
    @TableField(exist = false)
    private com.sofn.ducss.model.ProStill mainTableenTityClass;

    @ApiModelProperty(name = "indicatorArray", value = "指标数组")
    @TableField(exist = false)
    private HashMap<String, Map<String, Object>> indicatorArray = new HashMap<>();
    //ByDataAnalysis-end

    public ProStillDetail() {
        this.grainYield = new BigDecimal(0);// 粮食产量
        this.grassValleyRatio = new BigDecimal(0);// 草谷比
        this.collectionRatio = new BigDecimal(0);// 收集系数
        this.returnRatio = new BigDecimal(0);// 还田比例=还田面积/播种面积
        this.theoryResource = new BigDecimal(0); // 产生量=粮食产量*草谷比
        this.collectResource = new BigDecimal(0);// 可收集量=产生量*收集系数
        this.returnResource = new BigDecimal(0); // 直接还田量=可收集量*还田比例
        this.returnArea = new BigDecimal(0);//还田面积/播种面积=还田比例
        this.seedArea = new BigDecimal(0);//播种面积
        this.exportYield = new BigDecimal(0);//调出量
        this.transportAmount = new BigDecimal(0);// 交通运输
    }

    // 计算资源量
    public void calculateResource() {

        this.theoryResource = new BigDecimal(0);
        this.collectResource = new BigDecimal(0);
        returnResource = new BigDecimal(0);
        //产生量=粮食产量（吨）*草谷比
        if (grainYield != null && grassValleyRatio != null) {
            theoryResource = grainYield.multiply(grassValleyRatio).setScale(10, BigDecimal.ROUND_UP);
        }
        //可收集资源量=粮食产量（吨）*收集系数
        if (collectionRatio != null) {
            collectResource = theoryResource.multiply(collectionRatio).setScale(10, BigDecimal.ROUND_UP);
        }
        //直接还田量=可收集资源量*还田比例*100
        //此处还田比例已经*100，所以/100
        if (returnRatio != null) {
            returnResource = collectResource.multiply(returnRatio).divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP);
        }


    }
}