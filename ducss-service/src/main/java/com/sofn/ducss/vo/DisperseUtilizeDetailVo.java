package com.sofn.ducss.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.ducss.model.DisperseUtilizeDetail;
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
public class DisperseUtilizeDetailVo implements Serializable {

    @ApiModelProperty(name = "disperseUtilizeDetailList", value = "分散利用量数据列表")
    private List<DisperseUtilizeDetail> disperseUtilizeDetailList;

    @ApiModelProperty(name = "disperseUtilizeId", value = "分散利用量ID")
    private String disperseUtilizeId;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(name = "addTime", value = "填报时间")
    private Date addTime;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "farmerNo", value = "农户序号")
    private String farmerNo;

    @ApiModelProperty(name = "farmerName", value = "户主姓名")
    private String farmerName;

    @ApiModelProperty(name = "farmerPhone", value = "户主电话")
    private String farmerPhone;

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

    @ApiModelProperty(name = "disperseUtilizeDetailVoByYear", value = "最近一年的数据")
    private DisperseUtilizeDetailVo disperseUtilizeDetailVoByYear;

}
