package com.sofn.fdpi.vo;

import com.sofn.fdpi.enums.SturgeonPaperEnum;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.model.SturgeonReprint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱标识补打VO对象")
public class SturgeonReprintVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "企业id")
    private String compId;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "企业类型")
    private String compType;
    @ApiModelProperty(value = "鲟鱼子酱ID")
    private String sturgeonId;
    @ApiModelProperty(value = "申请单号")
    private String applyCode;
    @ApiModelProperty(value = "申请类型1国外2国内")
    private String applyType;
    @ApiModelProperty(value = "证书编号")
    private String credentials;
    @ApiModelProperty(value = "标识数量")
    private Integer labelSum;
    @ApiModelProperty(value = "补打数量")
    private Integer reprintSum;
    @ApiModelProperty(value = "图片")
    private String imgIds;
    @ApiModelProperty(value = "文件")
    private String fileId;
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "状态名称")
    private String statusName;
    @ApiModelProperty(value = "审核意见")
    private String opinion;
    @ApiModelProperty(value = "申请日期")
    private Date applyTime;
    @ApiModelProperty(value = "是否显示撤回按钮")
    private Boolean isShowCancel;
    @ApiModelProperty(value = "标识ids")
    private List<String> signboardIds;

    /**
     * 实体类转VO类
     */
    public static SturgeonReprintVo entity2Vo(SturgeonReprint entity) {
        SturgeonReprintVo vo = new SturgeonReprintVo();
        BeanUtils.copyProperties(entity, vo);
        if ("2".equals(entity.getApplyType())) {
            vo.setStatusName(SturgeonPaperEnum.getVal(vo.getStatus()));
        } else {
            vo.setStatusName(SturgeonStatusEnum.getVal(vo.getStatus()));
        }
        return vo;
    }


}
