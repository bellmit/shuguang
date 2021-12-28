package com.sofn.fdpi.vo;

import com.sofn.fdpi.enums.SignboardStatusEnum;
import com.sofn.fdpi.model.SignboardApplyList;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * 标识申请详细列表类
 *
 * @Author yumao
 * @Date 2019/1/2 17:29
 **/
@Data
@ApiModel(value = "标识申请列表VO对象")
public class SignboardApplyListVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "申请ID")
    private String applyId;
    @ApiModelProperty(value = "标识ID")
    private String signboardId;
    @ApiModelProperty(value = "标识编码")
    private String code;
    @ApiModelProperty(value = "标识状态")
    private String status;
    @ApiModelProperty(value = "标识状态名称")
    private String statusName;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "物种名称")
    private String speName;
    @ApiModelProperty(value = "物种ID")
    private String speId;
    @ApiModelProperty(value = "物种转移状态，Y转移中，其他都是非转移中（‘N’或者空）")
    private String transferStatus;

    /**
     * 实体类转VO类
     */
    public static SignboardApplyListVo entity2Vo(SignboardApplyList entity) {
        SignboardApplyListVo vo = new SignboardApplyListVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setStatusName(SignboardStatusEnum.getVal(vo.getStatus()));
        return vo;
    }

}
