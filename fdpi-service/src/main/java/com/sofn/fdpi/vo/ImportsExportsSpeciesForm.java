package com.sofn.fdpi.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-02 13:55
 */
@Data
@ApiModel(value = "修改进出口物种表单对象")
public class ImportsExportsSpeciesForm {

    @ApiModelProperty(value = "主键")
    private String id;
    /**
     * 出口id
     */

    @ApiModelProperty(name = "exportsId", value = "出口id")
    private String exportsId;
    /**
     * 物种名
     */
    @NotBlank(message = "物种名下拉获取，不为空")
    @ApiModelProperty(name = "speName", value = "物种名")
    private String speName;
    /**
     *保护级别
     */
    @NotBlank(message = "保护级别下拉获取不为空")
    @ApiModelProperty(name = "proLevel", value = "保护级别")
    private String proLevel;
    /**
     *重量/数量
     */
    @Length(max = 10,message = "重量/数量不能超过10位")
    @NotBlank(message = "重量/数量不为空")
    @ApiModelProperty(name = "amount", value = "重量/数量")
    private String amount;
    /**
     *来源地/目的地
     */
    @Length(max = 100,message = "来源地/目的地不能超过100位")
    @NotBlank(message = "来源地/目的地不为空")
    @ApiModelProperty(name = "source", value = "来源地/目的地")
    private String source;
    /**
     *进口岸
     */
    @Length(max = 100,message = "进口岸不能超过100位")
    @NotBlank(message = "进口岸不为空")
    @ApiModelProperty(name = "port", value = "进口岸")
    private String port;

}
