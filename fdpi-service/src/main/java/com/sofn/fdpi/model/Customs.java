package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-06-29 14:49
 */
@ApiModel("海关对象")
@TableName("CUSTOMS")
@Data
public class Customs {

    @ApiModelProperty("主键id")
    private String id;
    //    证号
    @Size(max = 30,message = "报关单编号不超过30位")
    @ApiModelProperty("报关单编号")
    private String customsNumber;
    @Valid
    @ApiModelProperty("证书物种信息")
    @TableField(exist = false)
    private List<CustomsSpec> ps;
    @ApiModelProperty("物种学名(用于列表，隔开)")
    @TableField(exist = false)
    private String speciesNames;
    @ApiModelProperty("单位/数量(用于列表，隔开)")
    @TableField(exist = false)
    private String units;
    private Date createTime;
    @TableField(exist = false)
    @ApiModelProperty(value = "能否操作")
    private Boolean canHandle;
}
