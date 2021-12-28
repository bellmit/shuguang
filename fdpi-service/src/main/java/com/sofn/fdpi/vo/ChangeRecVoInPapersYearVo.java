package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  物种变更记录
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("物种变更记录")
public class ChangeRecVoInPapersYearVo implements Serializable {

    @ApiModelProperty("变更时间")
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    @ApiModelProperty("变更原因（变更操作）")
    private String changeReason;
    //对方单位
    @ApiModelProperty("对方单位")
    private String otherCompName;
    //变更物种
    @ApiModelProperty("变更物种")
    private String speciesName;
    //变更数量
    @ApiModelProperty("变更数量")
    private String changeNum;
    //备注
    @ApiModelProperty("备注")
    private String remark;
}
