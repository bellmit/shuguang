package com.sofn.ducss.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.ducss.model.StrawUtilizeDetail;
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
public class StrawUtilizeDetailVo implements Serializable {

    @ApiModelProperty(name = "strawUtilizeDetailList", value = "规模化秸秆利用量数据列表")
    private List<StrawUtilizeDetail> strawUtilizeDetailList;

    @ApiModelProperty(name = "strawUtilizeId", value = "规模化秸秆利用量ID")
    private String strawUtilizeId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "addTime", value = "填报时间")
    private Date addTime;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "mainNo", value = "市场主体序号")
    private String mainNo;

    @ApiModelProperty(name = "mainName", value = "市场主体名称")
    private String mainName;

    @ApiModelProperty(name = "corporationName", value = "法人名称")
    private String corporationName;

    @ApiModelProperty(name = "companyPhone", value = "单位电话")
    private String companyPhone;

    @ApiModelProperty(name = "mobilePhone", value = "手机号码")
    private String mobilePhone;

    @ApiModelProperty(name = "address", value = "详细地址")
    private String address;

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

    @ApiModelProperty(name = "strawUtilizeDetailVoByYear", value = "最近一年的数据")
    private StrawUtilizeDetailVo strawUtilizeDetailVoByYear;

}
