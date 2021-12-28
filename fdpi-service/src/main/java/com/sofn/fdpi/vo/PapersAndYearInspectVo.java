package com.sofn.fdpi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("《企业信息》-》《证书信息》中《编辑和查看》获取证书信息")
public class PapersAndYearInspectVo implements Serializable {
    //证书id
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "证书类型")
    private String papersType;
    @ApiModelProperty(value = "证书类型名称")
    private String papersTypeName;
    @ApiModelProperty(value = "证书编号")
    private String papersNumber;
    @ApiModelProperty(value = "发证机关")
    private String issueUnit;
    @ApiModelProperty(value = "核发日期")
    private Date issueDate;
    @ApiModelProperty(value = "核定物种")
    private String issueSpe;
    @ApiModelProperty(value = "核定物种数量")
    private Integer issueNumber;
    @ApiModelProperty(value = "有效日期开始")
    private Date dataStart;
    @ApiModelProperty(value = "有效日期结束")
    private Date dataClos;
    @ApiModelProperty(value = "证书文件名称")
    private String fileName;
    @ApiModelProperty(value = "证书文件id")
    private String fileId;
    @ApiModelProperty(value = "证书文件路径")
    private String filePath;

//    @TableField(exist = false)
//    @ApiModelProperty("年审记录列表")
//    private List<PapersYearInspectProcessVo> inspectList;

}
