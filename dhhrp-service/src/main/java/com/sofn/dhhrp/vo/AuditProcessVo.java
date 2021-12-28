package com.sofn.dhhrp.vo;

import com.sofn.dhhrp.enums.ProcessEnum;
import com.sofn.dhhrp.model.AuditProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

@Data
@ApiModel(value = "审核进程记录VO对象")
public class AuditProcessVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    @ApiModelProperty(value = "审核人")
    private String auditor;
    @ApiModelProperty(value = "审核人名称")
    private String auditorName;
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;
    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    /**
     * 实体类转VO类
     */
    public static com.sofn.dhhrp.vo.AuditProcessVo entity2Vo(AuditProcess entity) {
        AuditProcessVo vo = new AuditProcessVo();
        if (!Objects.isNull(entity)) {
            BeanUtils.copyProperties(entity, vo);
            vo.setStatusName(ProcessEnum.getVal(entity.getStatus()));
        }
        return vo;
    }
}
