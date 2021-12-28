package com.sofn.fdpi.vo;

import com.sofn.fdpi.enums.SturgeonPaperEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.model.SturgeonPaper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "鲟鱼子酱标签纸VO对象")
public class SturgeonPaperVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "企业Id")
    private String compId;
    @ApiModelProperty(value = "申请单号")
    private String applyCode;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "A规格")
    private Integer paperA;
    @ApiModelProperty(value = "A单价")
    private Double priceA;
    @ApiModelProperty(value = "B规格")
    private Integer paperB;
    @ApiModelProperty(value = "B单价")
    private Double priceB;
    @ApiModelProperty(value = "箱贴")
    private Integer paperS;
    @ApiModelProperty(value = "箱贴单价")
    private Double priceS;
    @ApiModelProperty(value = "总金额")
    private BigDecimal total;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "状态")
    private String statusName;
    @ApiModelProperty(value = "申请时间")
    private Date applyTime;
    @ApiModelProperty(value = "审核意见")
    private String opinion;
    @ApiModelProperty(value = "快递信息")
    private String express;
    @ApiModelProperty(value = "能否撤回")
    private Boolean canCancel;

    /**
     * 实体类转VO类
     */
    public static SturgeonPaperVo entity2Vo(SturgeonPaper entity) {
        SturgeonPaperVo vo = new SturgeonPaperVo();
        BeanUtils.copyProperties(entity, vo);
        if ("2".equals(entity.getApplyType())) {
            vo.setStatusName(SturgeonPaperEnum.getVal(vo.getStatus()));
        } else {
            vo.setStatusName(SturgeonStatusEnum.getVal(vo.getStatus()));
        }
        return vo;
    }
}
