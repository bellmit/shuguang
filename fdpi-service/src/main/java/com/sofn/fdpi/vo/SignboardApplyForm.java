package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识申请表单对象")
public class SignboardApplyForm {

    @ApiModelProperty(value = "主键")
    private String id;


    @NotNull(message = "物种ID不能为空")
    @ApiModelProperty(value = "物种ID")
    private String speId;

    @NotNull(message = "企业ID不能为空")
    @ApiModelProperty(value = "企业ID")
    private String compId;

    @Size(max = 1, message = "申请类型值过长 1 配发 2 换发 3 补发 4 注销")
    @ApiModelProperty(value = "申请类型(1 配发 2 换发 3 补发 4 注销)", example = "1")
    private String applyType;

    @ApiModelProperty(value = "申请数量", example = "10")
    private Integer applyNum;

    @Size(max = 1, message = "物种利用类型值过长 1 驯养展演 2 人工繁育 ")
    @ApiModelProperty(value = "物种利用类型(1 驯养展演 2 人工繁育 )", example = "1")
    private String speUtilizeType;

    @Size(max = 2, message = "物种来源值过长 1 引进 2 自繁 ")
    @ApiModelProperty(value = "物种来源(1 引进 2 自繁)", example = "1")
    private String speSource;

    @Size(max = 1, message = "流程状态值过长 1未上报2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过 ")
    @NotBlank(message = "流程状态不能为空")
    @ApiModelProperty(value = "流程状态 1未上报2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过", example = "1")
    private String processStatus;

    @ApiModelProperty(value = "标识申请列表表单对象列表")
    private List<SignboardApplyListForm> applyList;

    @ApiModelProperty(value = "标识类型")
    private String type;

    @ApiModelProperty(value = "鲟鱼子酱编码")
    private String citesCode;

    @ApiModelProperty(value = "标识类型")
    private String saleProvince;

    @ApiModelProperty(value = "制品大鲵含量")
    private String andriasContent;

    @ApiModelProperty(value = "产品介绍")
    private String introduction;

}
