/**
 * @Author 文俊云
 * @Date 2020/1/2 13:48
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author 文俊云
 * @Date 2020/1/2 13:48
 * @Version 1.0
 */

@Data
public class CompanySpeciesVO  extends BaseModel<CompanySpeciesVO> {
    @ApiModelProperty("公司ID")
    private String companyId;
    @ApiModelProperty("公司名称")
    private String companyName;
    @ApiModelProperty("物种ID")
    private String speciesId;
    @ApiModelProperty("物种名称")
    private String speciesName;
    @ApiModelProperty("物种数量")
    private String speciesNum;
    @ApiModelProperty("ID")
    private String id;
    @ApiModelProperty("最后变更时间")
    private Date lastChangeTime;
}
