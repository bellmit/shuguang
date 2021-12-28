package com.sofn.ducss.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.ducss.model.CollectFlow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CollectFlowVo extends Model<CollectFlow> {

    private String id;

    @ApiModelProperty(name = "year", value = "所属年度")
    private String year;

    @ApiModelProperty(name = "provinceId", value = "省id")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市ID")
    private String cityId;

    @ApiModelProperty(name = "areaId", value = "县ID")
    private String areaId;

    @ApiModelProperty(name = "level", value = "审核等级")
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

    @ApiModelProperty(name = "regionName", value = "区划名称")
    private String regionName;

    public CollectFlowVo(){
        this.theoryNum = new BigDecimal(0);
        this.collectNum = new BigDecimal(0);
        this.mainNum = new BigDecimal(0);
        this.farmerSplitNum = new BigDecimal(0);
        this.directReturnNum = new BigDecimal(0);
        this.strawUtilizeNum = new BigDecimal(0);
        this.synUtilizeNum = new BigDecimal(0);
        this.status = 0;
    }

    /**
     * 将vo转换为po对象
     */
    public static CollectFlow getCollectFlow(CollectFlowVo collectFlowVo){
        CollectFlow collectFlow = new CollectFlow();
        BeanUtils.copyProperties(collectFlowVo,collectFlow);
        return collectFlow;
    }


    /**
     * 将po转换为vo对象
     */
    public static CollectFlowVo getCollectFlowVo(CollectFlow collectFlow){
        CollectFlowVo collectFlowVo = new CollectFlowVo();
        BeanUtils.copyProperties(collectFlow,collectFlowVo);
        return collectFlowVo;

    }

}
