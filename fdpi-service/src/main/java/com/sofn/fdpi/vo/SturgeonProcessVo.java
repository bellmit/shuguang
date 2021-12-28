package com.sofn.fdpi.vo;

import com.sofn.fdpi.enums.SturgeonPaperEnum;
import com.sofn.fdpi.enums.SturgeonStatusDomesticEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.model.SturgeonProcess;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Data
@ApiModel(value = "鲟鱼子酱标标识流程VO对象")
public class SturgeonProcessVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "申请ID")
    private String applyId;
    @ApiModelProperty(value = "操作人")
    private String person;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "审核状态名称")
    private String statusName;
    @ApiModelProperty(value = "审核意见")
    private String advice;
    @ApiModelProperty(value = "操作时间")
    private Date conTime;

    /**
     * 实体类转VO类
     */
    public static SturgeonProcessVo entity2Vo(SturgeonProcess entity, String applyType, String type) {
        SturgeonProcessVo vo = new SturgeonProcessVo();
        BeanUtils.copyProperties(entity, vo);
        if ("2".equals(applyType)) {
            if("1".equals(type)) {
                vo.setStatusName(SturgeonStatusDomesticEnum.getVal(vo.getStatus()));
            }else {
                vo.setStatusName(SturgeonPaperEnum.getVal(vo.getStatus()));
            }
        } else {
            vo.setStatusName(SturgeonStatusEnum.getVal(vo.getStatus()));
        }
        return vo;
    }


    public static SturgeonProcessVo map2Vo(Map<String, Object> map) {
        SturgeonProcessVo vo = new SturgeonProcessVo();
        vo.setConTime((Date) map.get("createTime"));
        String status = Objects.isNull(map.get("status")) ? null : map.get("status").toString();
        vo.setStatus(status);
        vo.setStatusName(SturgeonStatusEnum.getVal(status));
        vo.setPerson(Objects.isNull(map.get("person")) ? null : map.get("person").toString());
        vo.setAdvice(Objects.isNull(map.get("advice")) ? null : map.get("advice").toString());
        return vo;
    }

}
