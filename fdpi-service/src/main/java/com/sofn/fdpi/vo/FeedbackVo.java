package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2019/12/30 14:43
 */
@ApiModel(value="反馈查询VO对象")
@Data
public class FeedbackVo extends BaseVo<FeedbackVo> {
    @ApiModelProperty(name = "pageNo", value = "索引")
    private Integer pageNo;

    @ApiModelProperty(name = "pageSize", value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(name = "code", value = "标识编号")
    private String code;

    @ApiModelProperty(name = "speName", value = "物种名")
    private String speName;
    @ApiModelProperty(name = "ffUnit", value = "违法单位")
    private String ffUnit;
    @ApiModelProperty(name = "startTime", value = "违法时间")
    private String startTime;
    @ApiModelProperty(name = "endTime", value = "违法时间")
    private String endTime;
    @ApiModelProperty(name = "deptId", value = "部门ID")
    private String deptId;
    @ApiModelProperty(name = "ffFrom", value = "反馈来源 1 公众 2 部门")
    private String ffFrom;
}
