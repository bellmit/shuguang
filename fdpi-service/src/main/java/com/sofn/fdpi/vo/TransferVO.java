/**
 * @Author 文俊云
 * @Date 2019/12/30 16:03
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author 文俊云
 * @Date 2019/12/30 16:03
 * @Version 1.0
 */

@Data
public class TransferVO extends BaseModel<TransferVO> {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("注意此处是编码，不是ID。标志编码，以“，”间隔")
    private String signId;

    @ApiModelProperty("增加企业对象,只需设置addCompanyVO对象的companyId")
    @NotBlank(message = "增加企业对象必须传,只需设置其companyId")
    private ChangeRecordCompanyVO addCompanyVO;

    @ApiModelProperty("审核状态，0：保存；1：上报；2：增加企业直属退回；3：增加企业直属通过；4：减少企业退回；5：减少企业通过；6：减少企业直属退回；7：减少企业直属通过；8：结束")
    private String transferStatus;

    @ApiModelProperty("减少企业对象,只需设置reduceCompanyVO对象的companyId")
    @NotBlank(message = "减少企业对象必须传,只需设置其companyId")
    private ChangeRecordCompanyVO reduceCompanyVO;


    @ApiModelProperty("退回意见")
    private String opnion;

    @ApiModelProperty("删除标志")
    private String delFlag;

    @ApiModelProperty("增加方合同名称")
    private String addContractName;

    @ApiModelProperty("增加方合同ID")
    private String addContractId;

    @ApiModelProperty("增加方合同路径")
    private String addContractPath;

    @ApiModelProperty("减少方审核人")
    private String reduceUserId;

    @ApiModelProperty("减少方合同名称")
    private String reduceContractName;

    @ApiModelProperty("减少方合同ID")
    private String reduceContractId;

    @ApiModelProperty("减少方合同路径")
    private String reduceContractPath;

    @ApiModelProperty("评估意见文件名称")
    private String assessmentName;
    @ApiModelProperty("评估意见文件ID")
    private String assessmentId;
    @ApiModelProperty("评估意见文件路径")
    private String assessmentPath;


    @ApiModelProperty("是否有无证书标识，0有，1没有")
    private String isHavePaper;

    @ApiModelProperty("转移物种列表,以下标0开始的数组形式,例如第一个为speciesSelectList[0].speciesId赋值,第二个则为speciesSelectList[1].speciesId赋值，其他属性以此类推")
    @NotBlank(message = "转移物种列表对象必须传")
    private List<SpeciesSelect> speciesSelectList;

    @ApiModelProperty("操作")
    private String op;

    @ApiModelProperty("新加返回列表转移物种名字段")
    private  String  speName;

    @ApiModelProperty("新加返回列表转移物种物种数量字段")
    private  String  speNum;

    @ApiModelProperty("新加返回审核列表企业名称")
    private  String  compName;

    @ApiModelProperty("新加返回审核列表转移物种物种数量字段")
    private  String  transferType;

    private String isReport;

    @ApiModelProperty("申请单号")
    private  String  applyCode;

    @ApiModelProperty("能否审核/退回")
    private Boolean canAudit;
    @ApiModelProperty("是否显示撤回")
    private Boolean isShowCancel;
}
