package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName ProReportForm
 * @Description 省级报告文档信息
 * @Author Administrator
 * @Date2020/12/31 10:25
 * Version1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProReportForm {
    @ApiModelProperty("文档标识")
    private String id;
    @ApiModelProperty("当前保存的文档url")
    private String url;
}
