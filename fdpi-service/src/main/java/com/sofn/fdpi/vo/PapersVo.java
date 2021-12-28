package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.sofn.fdpi.model.FileManage;
import com.sofn.fdpi.model.Papers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:  证书绑定申请对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书绑定申请对象")
public class PapersVo implements Serializable {
    @ApiModelProperty("证书id(主键)")
    private String id;
    //1：人工繁育；2：驯养繁殖；3：经营利用
    @ApiModelProperty("证书类型")
    private String papersType;
    @ApiModelProperty("证书类型名称")
    private String papersTypeName;
    //证书编号
    @ApiModelProperty("证书编号")
    private String papersNumber;
    //企业名称
    @ApiModelProperty("企业名称")
    private String compName;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    @ApiModelProperty("联系人")
    private String linkMan;
    @ApiModelProperty("区域中文")
    private String regionInCh;
    @ApiModelProperty("技术负责人,业主回复后,人工繁育许可证新增字段")
    private String technicalDirector;
    @ApiModelProperty("人工繁育目的id，物种保护1经营利用2人工繁育3科学研究4其他5")
    private String purpose;
    @ApiModelProperty("人工繁育目的id，物种保护1经营利用2人工繁育3科学研究4其他5")
    private String purposeName;
    //核定物种
    @ApiModelProperty("核定物种")
    private String issueSpe;
    @ApiModelProperty("有效期至")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dataClos;
    @ApiModelProperty("发证日期")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date issueDate;
    @ApiModelProperty("发证机关")
    private String issueUnit;
    //0：省级新建；1：绑定未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回
    @ApiModelProperty("审核状态")
    private String parStatus;
    //0：省级新建；1：绑定未上报；2：上报；3：初审通过；4;初审退回；5：复审通过；6：复审退回
    @ApiModelProperty("审核状态中文")
    private String parStatusName;
    @ApiModelProperty("申请编号")
    private String applyNum;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("企业编号")
    private String compCode;
    @ApiModelProperty("是否显示撤回按钮")
    private Boolean isShowCancel;
    @ApiModelProperty(value = "能否审核")
    private Boolean canAudit;

    @ApiModelProperty("证书物种列表")
    @TableField(exist = false)
    private List<PapersSpecVo> speciesList;

    @ApiModelProperty("证书物种列表")
    @TableField(exist = false)
    private List<FileManage> fileList;

    @ApiModelProperty("省")
    private String compProvince;
    @ApiModelProperty("市")
    private String compCity;
    @ApiModelProperty("县")
    private String compDistrict;

    /**
     * 实体类转VO类
     */
    public static PapersVo entity2Vo(Papers entity) {
        PapersVo vo = new PapersVo();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
