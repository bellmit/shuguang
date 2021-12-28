package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.PrintStatusEnum;
import com.sofn.fdpi.model.SignboardPrint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
@ApiModel(value = "标识打印VO对象")
public class SignboardPrintVo {

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "企业名称")
    private String compName;

    @ApiModelProperty(value = "企业地址")
    private String contactAddress;

    @ApiModelProperty(value = "委托时间")
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date entrustDate;

    @ApiModelProperty(value = "联系人")
    private String linkman;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "委托数量")
    private Integer num;

    @ApiModelProperty(value = "申请单号")
    private String applyCode;

    @ApiModelProperty(value = "合同号")
    private String contractNum;

    @ApiModelProperty(value = "打印状态 1已打印 0未打印")
    private String status;

    @ApiModelProperty(value = "打印状态名称")
    private String statusName;

    @ApiModelProperty(value = "企业类型")
    private String compType;

    @ApiModelProperty(value = "能否上传")
    private Boolean canUpload;

    @ApiModelProperty(value = "申请类型1标识申请2国内鱼子酱申请")
    private String applyType;

    /**
     * 实体类转VO类
     */
    public static SignboardPrintVo entity2Vo(SignboardPrint entity) {
        SignboardPrintVo vo = new SignboardPrintVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setCanUpload(PrintStatusEnum.NOT_PRINTED.getKey().equals(vo.getStatus()));
        vo.setStatusName(PrintStatusEnum.getVal(vo.getStatus()));
        return vo;
    }
}
