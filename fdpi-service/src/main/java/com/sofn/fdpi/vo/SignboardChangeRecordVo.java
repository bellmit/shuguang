package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.SignboardChangeStatusEnum;
import com.sofn.fdpi.model.SignboardChangeRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value = "标识变更记录VO对象")
public class SignboardChangeRecordVo {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "标识ID")
    private String signboardId;

    @ApiModelProperty(value = "物种ID")
    private String speId;

    @ApiModelProperty(value = "物种名称")
    private String speName;

    @ApiModelProperty(value = "企业ID")
    private String compId;

    @ApiModelProperty(value = "企业名称")
    private String compName;

    @ApiModelProperty(value = "标识编码")
    private String code;

    @ApiModelProperty(value = "标识变更状态 1配发 2在养 3转移-减少 4转移-增加 5换发 6补发 7注销")
    private String status;

    @ApiModelProperty(value = "标识变更状态名称")
    private String statusName;

    @ApiModelProperty(value = "变更日期")
    @JSONField(format = "yyyy-MM-dd")
    private Date changeTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 实体类转VO类
     */
    public static SignboardChangeRecordVo entity2Vo(SignboardChangeRecord entity) {
        SignboardChangeRecordVo vo = new SignboardChangeRecordVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setStatusName(SignboardChangeStatusEnum.getVal(vo.getStatus()));
        return vo;
    }
}
