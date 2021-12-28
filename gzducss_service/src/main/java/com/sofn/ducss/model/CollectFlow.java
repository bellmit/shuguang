package com.sofn.ducss.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("collect_flow")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CollectFlow extends Model<CollectFlow> {
    private String id;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市ID")
    private String cityId;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @ApiModelProperty(name = "level", value = "审核等级；3-区县级，4-市级，5-省级，6-部级")
    private Byte level;

    @ApiModelProperty(name = "theoryNum", value = "理论资源量")
    private BigDecimal theoryNum;

    @ApiModelProperty(name = "collectNum", value = "可收集资源量")
    private BigDecimal collectNum;

    @ApiModelProperty(name = "mainNum", value = "市场主体规模化利用量")
    private BigDecimal mainNum;

    @ApiModelProperty(name = "farmerSplitNum", value = "农户分散利用量")
    private BigDecimal farmerSplitNum;

    @ApiModelProperty(name = "directReturnNum", value = "直接还田量")
    private BigDecimal directReturnNum;

    @ApiModelProperty(name = "strawUtilizeNum", value = "秸秆利用量")
    private BigDecimal strawUtilizeNum;

    @ApiModelProperty(name = "synUtilizeNum", value = "综合利用率")
    private BigDecimal synUtilizeNum;

    @ApiModelProperty(name = "status", value = "0保存1已上报2已读3已退回4已撤回5已通过")
    private Byte status;

    @ApiModelProperty(name = "isreport", value = "是否生成报告,0-未生成；1-已生成")
    private Byte isreport;

    private String createUserId;

    private String createUser;

    @ApiModelProperty(name = "buyOtherNum", value = "外县购入量")
    private BigDecimal buyOtherNum;

    @ApiModelProperty(name = "exportNum", value = "调出量")
    private BigDecimal exportNum;

    private Date createDate;


    /**
     * 如果值等于0就重新设置0  有的时候BigDecimal的0是0e-10  在优炫数据库中会报错
     */
    public void setZeroValue(){
        Class aClass = this.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                Object o = declaredField.get(this);
                if(o instanceof BigDecimal){
                    BigDecimal b = (BigDecimal) o;
                    if(b.compareTo(BigDecimal.ZERO) == 0){
                        declaredField.setAccessible(true);
                        declaredField.set(this, BigDecimal.ZERO);
                        declaredField.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }


    }

    public static void main(String[] args) {
        CollectFlow collectFlow = new CollectFlow();
        collectFlow.setTheoryNum(new BigDecimal("23.5"));
        collectFlow.setAreaId("231");

        collectFlow.setStrawUtilizeNum(new BigDecimal("0"));
        collectFlow.setZeroValue();
        System.out.println(collectFlow);

    }


}