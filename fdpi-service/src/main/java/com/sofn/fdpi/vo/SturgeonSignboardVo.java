package com.sofn.fdpi.vo;

import com.sofn.common.utils.BoolUtils;
import com.sofn.fdpi.enums.SturgeonStatusEnum;
import com.sofn.fdpi.model.Sturgeon;
import com.sofn.fdpi.model.SturgeonSignboard;
import com.sofn.fdpi.model.SturgeonSignboardDomestic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

@Data
@ApiModel(value = "鲟鱼子酱标签VO对象")
public class SturgeonSignboardVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "箱号")
    private Integer caseNum;
    @ApiModelProperty(value = "企业id")
    private String compId;
    @ApiModelProperty(value = "企业名称")
    private String compName;
    @ApiModelProperty(value = "打印企业名称")
    private String printCompName;
    @ApiModelProperty(value = "企业类型")
    private String compType;
    @ApiModelProperty(value = "证书编号")
    private String credentials;
    @ApiModelProperty(value = "标签纸规格")
    private String label;
    @ApiModelProperty(value = "标识号码")
    private String signboard;
    @ApiModelProperty(value = "是否第三方打印")
    private String thirdPrint;
    @ApiModelProperty(value = "申请单号")
    private String applyCode;
    @ApiModelProperty(value = "标签打印状态 Y已打印N未打印")
    private String labelPrintStatus;
    @ApiModelProperty(value = "箱贴打印状态 Y已打印N未打印")
    private String stickerPrintStatus;

    @ApiModelProperty(value = "标签打印状态 Y已打印N未打印")
    private String labelPrintStatusName;
    @ApiModelProperty(value = "箱贴打印状态 Y已打印N未打印")
    private String stickerPrintStatusName;
    @ApiModelProperty(value = "打印次数")
    private Integer printSum;
    @ApiModelProperty(value = "能否打印")
    private Boolean canPrint;

    /**
     * 实体类转VO类
     */
    public static SturgeonSignboardVo entity2Vo(SturgeonSignboard entity) {
        SturgeonSignboardVo vo = new SturgeonSignboardVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setLabelPrintStatusName(getPrintName(vo.getLabelPrintStatus()));
        vo.setStickerPrintStatusName(getPrintName(vo.getStickerPrintStatus()));
        return vo;
    }

    /**
     * 实体类转VO类
     */
    public static SturgeonSignboardVo entity2Vo(SturgeonSignboardDomestic entity) {
        SturgeonSignboardVo vo = new SturgeonSignboardVo();
        BeanUtils.copyProperties(entity, vo);
        vo.setLabelPrintStatusName(getPrintName(vo.getLabelPrintStatus()));
        vo.setStickerPrintStatusName(getPrintName(vo.getStickerPrintStatus()));
        if (BoolUtils.Y.equals(vo.getThirdPrint())) {
            vo.setCanPrint(false);
            vo.setPrintCompName("北京翰龙翔天防伪科技有限公司");
        } else {
            vo.setCanPrint(true);
            vo.setPrintCompName(vo.getCompName());
        }
        return vo;
    }

    private static String getPrintName(String bool) {
        if (StringUtils.isBlank(bool)) {
            return null;
        }
        return BoolUtils.N.equals(bool) ? "可打印" : "已打印";
    }


    ;


}
