package com.sofn.ducss.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.ducss.model.ProStillDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProStillDetailVo implements Serializable {

    @ApiModelProperty(name = "proStillDetails", value = "产生量与直接还田量详细数据列表")
    private List<ProStillDetail> proStillDetails;

    @ApiModelProperty(name = "proStillId", value = "产生量与直接还田量ID")
    private String proStillId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "addTime", value = "填报时间")
    private Date addTime;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "department", value = "填报单位")
    private String department;

    @ApiModelProperty(name = "provinceId", value = "省")
    private String provinceId;

    @ApiModelProperty(name = "cityId", value = "市")
    private String cityId;

    @ApiModelProperty(name = "countyId", value = "县")
    private String countyId;

    @ApiModelProperty(name = "popup", value = "是否弹窗（Y：弹出提示框，N：不弹出提示框）")
    private String popup;

    @ApiModelProperty(name = "proStillDetailVoByYear", value = "最近一年的数据")
    private ProStillDetailVo proStillDetailVoByYear;

}
