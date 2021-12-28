package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-08-19 15:36
 */
@ApiModel("许可证列表对象")
@Data
public class LicenceVo {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("证书类型（1：人工繁育；2：驯养繁殖；3：经营利用 4：特许捕获证）")
    private String papersType;
    @ApiModelProperty("证书编号")
    private String papersNumber;
    @ApiModelProperty("公司名")
    private String compName;
    @ApiModelProperty("截止日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date   dataClos;
    @ApiModelProperty("保护等级")
    private String proLevel;
}
