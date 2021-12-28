package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-23 9:21
 */
@TableName("papers_spec")
@Data
@ApiModel(value = "证书物种")
public class PapersSpec extends BaseModel<PapersSpec> {

    @ApiModelProperty(value = "证书id，新增不传")
    private String papersId;
    @ApiModelProperty(value = "物种id")
    private String specId;
    @TableField(exist = false)
    @ApiModelProperty(value = "物种名")
    private String specName;
    @Length(max = 30,message = "证书编号不能超过30")
    @ApiModelProperty(value = "来源")
    private String source;
    @ApiModelProperty(value = "方式")
    private String mode;
    @ApiModelProperty(value = "数量")
    private Integer amount;
    @ApiModelProperty(value = "单位")
    private String unit;
}
