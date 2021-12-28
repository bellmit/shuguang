package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.sofn.fdpi.enums.*;
import com.sofn.fdpi.model.SignboardApply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @Deacription
 * @Author yumao
 * @Date 2019/12/26 17:23
 **/
@Data
@ApiModel(value = "标识申请VO对象")
public class SignboardApplyVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "物种ID")
    private String speId;
    @ApiModelProperty(value = "物种名称")
    private String speName;
    @ApiModelProperty(value = "企业ID")
    private String compId;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "企业类型")
    private String compType;
    @ApiModelProperty(value = "联系人")
    private String linkman;
    @ApiModelProperty(value = "区域-省")
    private String compProvince;
    @ApiModelProperty(value = "区域-市")
    private String compCity;
    @ApiModelProperty(value = "区域-县")
    private String compDistrict;
    @ApiModelProperty(value = "通讯地址")
    private String contactAddress;
    @ApiModelProperty(value = "邮政编码")
    private String postAddress;
    @ApiModelProperty(value = "法人代表")
    private String legal;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "电子邮箱")
    private String email;
    @ApiModelProperty(value = "申请类型")
    private String applyType;
    @ApiModelProperty(value = "申请类型名称")
    private String applyTypeName;
    @JSONField(format = "yyyy-MM-dd")
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "申请数量")
    private Integer applyNum;
    @ApiModelProperty(value = "核发数量")
    private Integer allotmentNum;
    @ApiModelProperty(value = "物种利用类型")
    private String speUtilizeType;
    @ApiModelProperty(value = "物种利用类型名称")
    private String speUtilizeTypeName;
    @ApiModelProperty(value = "物种来源")
    private String speSource;
    @ApiModelProperty(value = "物种来源名称")
    private String speSourceName;
    @ApiModelProperty(value = "流程状态 1未上报2已上报3初审退回4初审通过5复审退回6复审通过7终审退回8终审通过")
    private String processStatus;
    @ApiModelProperty(value = "流程状态名称")
    private String processStatusName;
    @ApiModelProperty(value = "最后一次审核意见")
    private String lastAdvice;
    @ApiModelProperty(value = "标识申请列表表单对象列表")
    private List<SignboardApplyListVo> applyList;
    @ApiModelProperty(value = "申请编号")
    private String applyCode;
    @ApiModelProperty(value = "拟销售省份")
    private String saleProvince;
    @ApiModelProperty(value = "制品大鲵含量")
    private String andriasContent;
    @ApiModelProperty(value = "产品介绍")
    private String introduction;
    @ApiModelProperty(value = "标识类型")
    private String type;
    @ApiModelProperty(value = "鲟鱼子酱编码")
    private String citesCode;
    @ApiModelProperty(value = "是否能核发")
    private Boolean canAllotment;
    @ApiModelProperty(value = "物种编码")
    private String speCode;
    @ApiModelProperty(value = "是否显示撤回按钮")
    private boolean isShowCancel;
    @ApiModelProperty(value = "是否能审核（通过/退回）")
    private boolean canAudit;
    @ApiModelProperty(value = "直属审核人员")
    private String auditer;


    /**
     * 实体类转VO类
     */
    public static SignboardApplyVo entity2Vo(SignboardApply signboardApply) {
        SignboardApplyVo vo = new SignboardApplyVo();
        BeanUtils.copyProperties(signboardApply, vo);
        vo.setSpeUtilizeTypeName(SpeciesUtilizeTypeEnum.getVal(vo.getSpeUtilizeType()));
        vo.setApplyTypeName(SignboardApplyTypeEnum.getVal(vo.getApplyType()));
        vo.setSpeSourceName(SpeciesSourceEnum.getVal(vo.getSpeSource()));
        vo.setProcessStatusName(SignboardApplyProcessEnum.getVal(vo.getProcessStatus()));
        return vo;
    }


}
