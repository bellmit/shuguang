package com.sofn.fdpi.vo;

import com.sofn.common.excel.annotation.ExcelField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/1 16:05
 **/
@Data
@ApiModel("欧鳗养殖企业和加工企业的列表对象")
public class OmBreedProcTableVo {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "欧洲鳗鲡出让企业")
    private String cellComp;
    @ApiModelProperty(value = "欧洲鳗鲡受让企业")
    private String transferComp;
    @ApiModelProperty(value = "允许进出口证明书号")
    private String credential;
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;
    @ApiModelProperty(value = "进口规格")
    private Integer importSize;
    @ApiModelProperty(value = "交易数量")
    private Double dealNum;
    @ApiModelProperty(value = "折算比例")
    private Double obversion;
    @ApiModelProperty(value = "交易日期")
    private Date dealDate;
    @ApiModelProperty(value = "操作人")
    private String operator;
    @ApiModelProperty(value = "数据所属类型，2是养殖，3是加工")
    private String dataType;
}
