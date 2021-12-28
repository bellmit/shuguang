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
 * @Date: 2020-06-24 14:12
 */
@ApiModel("濒管办对象")
@TableName("PERITONEUM")
@Data
public class Peritoneum {
//    主键
    @ApiModelProperty("主键id")
    private String id;
//    证号
    @Size(max = 30,message = "证号不超过30位")
    @ApiModelProperty("证号")
    private String certificateNo;
    @Valid
    @ApiModelProperty("证书物种信息")
    @TableField(exist = false)
    private List<PeritoneumSpec> ps;
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
