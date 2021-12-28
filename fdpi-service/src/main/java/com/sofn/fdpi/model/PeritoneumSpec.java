package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-24 14:29
 */
@ApiModel("濒管办物种对象")
@TableName("PERITONEUM_SPEC")
@Data
public class PeritoneumSpec {
    //    主键
    @ApiModelProperty("主键id")
    private String id;
    @Size(max = 10,message = "物种学名不超过10位")
    @ApiModelProperty("物种学名")
    private String speciesName;
    @Size(max = 10,message = "单位不超过10位")
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("濒管办ID")
    private String peritoneumId;
}
