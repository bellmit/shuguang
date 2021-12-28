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
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CollectFlowWithTotalVo extends Model<CollectFlow> {

    @ApiModelProperty(name = "collectFlowVoList", value = "列表数据")
    private List<CollectFlowVo> collectFlowVoList;

    @ApiModelProperty(name = "collectFlowVoTotal", value = "合计数据")
    private CollectFlowVo collectFlowVoTotal;

    @ApiModelProperty(name = "regionTotalNum", value = "区划总个数")
    private int regionTotalNum;

    @ApiModelProperty(name = "regionReportNum", value = "已上报区划总个数")
    private int regionReportNum;

    public CollectFlowWithTotalVo(){}

}
