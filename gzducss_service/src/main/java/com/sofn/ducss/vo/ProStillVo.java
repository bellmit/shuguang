package com.sofn.ducss.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sofn.ducss.model.ProStillDetail;
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
public class ProStillVo implements Serializable {

    @ApiModelProperty(name = "proStillDetails", value = "产生量与直接还田量详细数据列表")
    private List<ProStillDetail> proStillDetails;

    @ApiModelProperty(name = "proStillId", value = "产生量与直接还田量ID")
    private String proStillId;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "createDate", value = "填报时间")
    private Date addTime;

    @ApiModelProperty(name = "year", value = "年度")
    private String year;

    @ApiModelProperty(name = "department", value = "填报单位")
    @Length(max = 32, message = "填报单位超长")
    private String department;

}
