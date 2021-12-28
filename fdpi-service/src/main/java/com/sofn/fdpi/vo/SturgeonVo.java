package com.sofn.fdpi.vo;

import com.sofn.fdpi.enums.SturgeonStatusDomesticEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.model.Sturgeon;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱VO对象")
public class SturgeonVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "企业id")
    private String compId;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "品种")
    private String variety;
    @ApiModelProperty(value = "企业类型")
    private String compType;
    @ApiModelProperty(value = "证书图片id(支撑平台文件id)")
    private String fileId;
    @ApiModelProperty(value = "证书编号")
    private String credentials;
    @ApiModelProperty(value = "贸易类型")
    private String trade;
    @ApiModelProperty(value = "附录")
    private String appendix;
    @ApiModelProperty(value = "标本类型")
    private String sample;
    @ApiModelProperty(value = "来源代码")
    private String source;
    @ApiModelProperty(value = "加工厂代码")
    private String handle;
    @ApiModelProperty(value = "数量或净重")
    private Double sum;
    @ApiModelProperty(value = "申请单号")
    private String applyCode;
    @ApiModelProperty(value = "原产国代码")
    private String origin;
    @ApiModelProperty(value = "出口国")
    private String export;
    @ApiModelProperty(value = "分装国")
    private String split;
    @ApiModelProperty(value = "审批时间")
    private Date auditTime;
    @ApiModelProperty(value = "证书核发地")
    private String issueAddr;
    @ApiModelProperty(value = "状态 1未上报 2已上报(待审核) 3已退回 4已通过")
    private String status;
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    @ApiModelProperty(value = "审批时间")
    private Date applyTime;
    @ApiModelProperty(value = "审核意见")
    private String opinion;
    @ApiModelProperty(value = "标签数量")
    private Integer labelSum;
    @ApiModelProperty(value = "鲟鱼子酱子表VO对象")
    private List<SturgeonSubVo> sturgeonSubVos;
    @ApiModelProperty(value = "是否显示撤回按钮")
    private Boolean isShowCancel;
    @ApiModelProperty(value = "是否能审核（通过/退回）")
    private boolean canAudit;
    @ApiModelProperty(value = "直属审核人员")
    private String auditer;
    @ApiModelProperty(value = "区域-省")
    private String compProvince;
    @ApiModelProperty(value = "区域-市")
    private String compCity;
    @ApiModelProperty(value = "区域-县")
    private String compDistrict;

    /**
     * 实体类转VO类
     */
    public static SturgeonVo entity2Vo(Sturgeon entity) {
        SturgeonVo vo = new SturgeonVo();
        BeanUtils.copyProperties(entity, vo);
        if ("2".equals(entity.getApplyType())) {
            vo.setStatusName(SturgeonStatusDomesticEnum.getVal(vo.getStatus()));
        } else {
            vo.setStatusName(SturgeonStatusEnum.getVal(vo.getStatus()));
        }
        return vo;
    }

}
