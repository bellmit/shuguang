package com.sofn.fdpi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.excel.annotation.ExcelField;
import com.sofn.common.excel.annotation.ExcelImportCheck;
import com.sofn.common.model.BaseModel;
import com.sofn.fdpi.vo.PapersSpecVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 证书
 */
@ApiModel("证书对象")
@TableName("PAPERS")
@Data
public class Papers extends BaseModel<Papers> {
    @ApiModelProperty("证书id")
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
//    @ApiModelProperty("物种id")
//    //物种ID
//    private String speId;
    //1：人工繁育；2：驯养繁殖；3：经营利用
    @ApiModelProperty("证书类型：1：人工繁育；2：驯养繁殖；3：经营利用")
    private String papersType;

    //证书编号
    @ApiModelProperty("证书编号")
    private String papersNumber;
    //企业名称
    @ApiModelProperty("企业名称")
    private String compName;
    @ApiModelProperty("企业地址")
    private String compAddress;
    //    private String papersName;
    @ApiModelProperty("有效期开始时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dataStart;
    @ApiModelProperty("有效期结束时间")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dataClos;
    //发证机关
    @ApiModelProperty("发证机关")
    private String issueUnit;
    //核发日期
    @ApiModelProperty("发证日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date issueDate;
//    需要注释
//    //核定物种
//    @ApiModelProperty("核定物种")
//    private String issueSpe;
//    //核定数量
//    @ApiModelProperty("核定数量")
//    private Integer issueNum;
//
    @ApiModelProperty("状态")
    //0：省级新建；1：绑定未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回;7:撤回
    private String parStatus;
    //企业ID
    @ApiModelProperty("企业ID")
    private String compId;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    //证书绑定来源:0：注册绑定；1：证书绑定
    @ApiModelProperty("证书绑定来源:0：注册绑定；1：证书绑定")
    private String source;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("申请单号")
    private String applyNum;
// 需要注释
//    //证书名称
//    @ApiModelProperty("证书名称")
//    private String fileName;
//    //证书文件编号
//    @ApiModelProperty("证书文件编号")
//    private String fileId;
//    //证书文件路径
//    @ApiModelProperty("证书文件路径")
//    private String filePath;
//
    @ApiModelProperty("企业信息")
    @TableField(exist = false)
    private TbComp tbComp;

    @ApiModelProperty("人工繁育目的id，物种保护1经营利用2人工繁育3科学研究4其他5")
    private String purpose;

    @ApiModelProperty("添加所属省份")
    private String provincialId;

    @ApiModelProperty("添加所属市")
    private String cityId;

    @ApiModelProperty("添加所属区县")
    private String areaId;

    @ApiModelProperty("来源：导入证书中新增字段")
    private String sourceForm;
    @TableField(exist = false)
    @ApiModelProperty("人工繁育目的value值，物种保护1经营利用2人工繁育3科学研究4其他5")
    private String purposeName;

    @ApiModelProperty("技术负责人,业主回复后,人工繁育许可证新增字段")
    private String technicalDirector;

    @ApiModelProperty("经营方式（业主回复后,经营许可证新增字段）")
    private String modeOperation;

    @ApiModelProperty("销售去向（业主回复后,经营许可证新增字段）")
    private String salesDestination;

    @ApiModelProperty("证书绑定是否生效,也就是审核通过的最后一次证书")
    private String isEnable;

    @ApiModelProperty("证书物种列表")
    @TableField(exist = false)
    private List<PapersSpecVo> speciesList;

    @ApiModelProperty("证书物种列表")
    @TableField(exist = false)
    private List<FileManage> fileList;
    @ApiModelProperty("许可证正本：是否打印，0否 1：是")
    private  String isPrint;

    @ApiModelProperty("许可证副本打印：是否打印，0未打印 1：封面打印，2 内页打印，3：打印")
    private  String isCopyPrint;

    @ApiModelProperty("备案级别")
    private String filingLevel;
}
