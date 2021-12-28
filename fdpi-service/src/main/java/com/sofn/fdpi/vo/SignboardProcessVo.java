package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.SignboardApplyProcessEnum;
import com.sofn.fdpi.enums.SignboardApplyTypeEnum;
import com.sofn.fdpi.model.SignboardProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 标识流程类
 *
 * @Author yumao
 * @Date 2019/12/31 9:10
 **/
@Data
@ApiModel(value = "标识流程VO对象")
public class SignboardProcessVo {


    @ApiModelProperty(value = "申请ID")
    private String applyId;

    @ApiModelProperty(value = "操作人")
    private String person;

    @ApiModelProperty(value = "操作编码")
    private String code;

    @ApiModelProperty(value = "申请数量")
    private Integer applyNum;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "审核状态名称")
    private String statusName;

    @ApiModelProperty(value = "审核意见")
    private String advice;

    @JSONField(format = "yyyy-MM-dd HH:mm")
    @ApiModelProperty(value = "操作时间")
    private Date conTime;

    @ApiModelProperty(value = "物种名称")
    private String speName;

    @ApiModelProperty(value = "申请类型")
    private String applyType;

    @ApiModelProperty(value = "申请类型名称")
    private String applyTypeName;

    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;

    @ApiModelProperty(value = "申请编号")
    private String applyCode;

    /**
     * 实体类转VO类
     */
    public static SignboardProcessVo SignboardProcessVo2Vo(SignboardProcess signboardProcess) {
        SignboardProcessVo vo = new SignboardProcessVo();
        BeanUtils.copyProperties(signboardProcess, vo);
        vo.setStatusName(SignboardApplyProcessEnum.getVal(vo.getStatus()));
        vo.setApplyTypeName(SignboardApplyTypeEnum.getVal(vo.getApplyType()));
        return vo;
    }

    public static SignboardProcessVo map2Vo(Map<String, Object> map) {
        SignboardProcessVo vo = new SignboardProcessVo();
        vo.setConTime((Date) map.get("createTime"));
        String status = Objects.isNull(map.get("status")) ? null : map.get("status").toString();
        vo.setStatus(status);
        vo.setStatusName(SignboardApplyProcessEnum.getVal(status));
        vo.setPerson(Objects.isNull(map.get("person")) ? null : map.get("person").toString());
        vo.setAdvice(Objects.isNull(map.get("advice")) ? null : map.get("advice").toString());
        return vo;
    }
}
