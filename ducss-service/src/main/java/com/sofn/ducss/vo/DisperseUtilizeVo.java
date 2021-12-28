package com.sofn.ducss.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.ducss.model.DisperseUtilizeDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DisperseUtilizeVo implements Serializable {

    @ApiModelProperty(name = "disperseUtilizeDetailList", value = "分散利用量详细数据列表")
    private List<DisperseUtilizeDetail> disperseUtilizeDetailList;

    @ApiModelProperty(name = "disperseUtilizeId", value = "分散利用量ID")
    private String disperseUtilizeId;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(name = "createDate", value = "填报时间")
    private Date addTime;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "department", value = "填报单位")
    @Length(max = 32,message = "填报单位超长")
    private String department;

    @ApiModelProperty(name = "address", value = "详细地址")
    @Length(max = 255,message = "详细地址超长")
    private String address;

    @ApiModelProperty(name = "farmerNo", value = "农户序号")
    private String farmerNo;

    @ApiModelProperty(name = "farmerName", value = "农户姓名")
    @Length(max = 32,message = "户主姓名超长")
    private String farmerName;

    @ApiModelProperty(name = "farmerPhone", value = "农户电话")
    private String farmerPhone;

}
