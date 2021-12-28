package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description 加工企业的详情vo
 * @Author wg
 * @Date 2021/5/26 14:58
 **/
@Data
@ApiModel(value = "欧鳗企业信息VO对象")
public class OmProcDetailVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "欧洲鳗鲡出让企业")
    private String cellComp;
    @ApiModelProperty(value = "欧洲鳗鲡受让企业")
    private String transferComp;
    @ApiModelProperty(value = "进口规格")
    private String importSize;
    @ApiModelProperty(value = "欧洲鳗鲡交易数量(吨)")
    private Double dealNum;
    @ApiModelProperty(value = "欧洲鳗鲡交易日期")
    private Date dealDate;
    @ApiModelProperty(value = "出让企业经办人签字")
    private String sellSign;

}
