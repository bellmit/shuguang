package com.sofn.agpjyz.vo;

import com.sofn.agpjyz.enums.ProcessEnum;
import com.sofn.agpjyz.model.Source;
import com.sofn.common.utils.BoolUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel(value = "资源调查VO对象")
public class SourceVo {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "调查日期")
    private Date survey;
    @ApiModelProperty(value = "调查人")
    private String investigator;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "物种名称ID")
    private String specId;
    @ApiModelProperty(value = "物种名称")
    private String specValue;
    @ApiModelProperty(value = "拉丁学名")
    private String latin;
    @ApiModelProperty(value = "俗名")
    private String commonName;
    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "省名")
    private String provinceName;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "市名")
    private String cityName;
    @ApiModelProperty(value = "县")
    private String county;
    @ApiModelProperty(value = "县名")
    private String countyName;
    @ApiModelProperty(value = "海拔")
    private String altitude;
    @ApiModelProperty(value = "分布面积")
    private String distribution;
    @ApiModelProperty(value = "种群数量")
    private String amount;
    @ApiModelProperty(value = "形态特征")
    private String features;
    @ApiModelProperty(value = "生物学特性")
    private String characteristic;
    @ApiModelProperty(value = "濒危状况ID")
    private String endangeredId;
    @ApiModelProperty(value = "濒危状况名称")
    private String endangeredValue;
    @ApiModelProperty(value = "威胁因素")
    private String threaten;
    @ApiModelProperty(value = "年平均气温")
    private String temperature;
    @ApiModelProperty(value = "大于等于10℃年积温(℃)")
    private String greater;
    @ApiModelProperty(value = "年平均降水量")
    private String precipitation;
    @ApiModelProperty(value = "年平均日照时数")
    private String sunshine;
    @ApiModelProperty(value = "年蒸发量")
    private String evaporation;
    @ApiModelProperty(value = "植被类型ID")
    private String vegetationId;
    @ApiModelProperty(value = "植被类型名称")
    private String vegetationValue;
    @ApiModelProperty(value = "植被覆盖率")
    private String vegetationCoverage;
    @ApiModelProperty(value = "土壤类型ID")
    private String soilId;
    @ApiModelProperty(value = "土壤类型名称")
    private String soilValue;
    @ApiModelProperty(value = "土壤肥力")
    private String soilFertility;
    @ApiModelProperty(value = "保护与利用状况")
    private String protectionUtilization;
    @ApiModelProperty(value = "评价和建议")
    private String suggest;
    @ApiModelProperty(value = "状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
    private String status;
    @ApiModelProperty(value = "状态 0已保存 1已撤回 2已上报 3市级退回 4市级通过 5省级退回 6省级通过 7总站退回 8总站通过")
    private String statusName;
    @ApiModelProperty(value = "是否专家填报")
    private String expertReport;
    @ApiModelProperty(value = "是否专家填报名称")
    private String expertReportName;
    @ApiModelProperty(value = "生境类型VO对象")
    private List<HabitatTypeVo> habitatTypeVos;
    @ApiModelProperty(value = "生境类型名称")
    private String  habitatTypeName;
    @ApiModelProperty(value = "生境类型ids")
    private List<String> habitatTypeIds;
    @ApiModelProperty(value = "图片附件VO对象(根)")
    private List<PictureAccessoriesVo> root;
    @ApiModelProperty(value = "图片附件VO对象(茎)")
    private List<PictureAccessoriesVo> stem;
    @ApiModelProperty(value = "图片附件VO对象(叶)")
    private List<PictureAccessoriesVo> leaf;
    @ApiModelProperty(value = "图片附件VO对象(花)")
    private List<PictureAccessoriesVo> flower;
    @ApiModelProperty(value = "图片附件VO对象(果)")
    private List<PictureAccessoriesVo> fruit;
    @ApiModelProperty(value = "图片附件VO对象(种子)")
    private List<PictureAccessoriesVo> seed;
    @ApiModelProperty(value = "图片附件VO对象(其它)")
    private List<PictureAccessoriesVo> other;
    @ApiModelProperty(value = "能否审核")
    private Boolean canAudit;
    @ApiModelProperty(value = "能否撤回")
    private Boolean canCancel;
    @ApiModelProperty(value = "能否修改")
    private Boolean canEdit;
    @ApiModelProperty(value = "审核进程记录VO对象")
    private List<AuditProcessVo> auditProcessVos;

    /**
     * 实体类转VO类
     */
    public static SourceVo entity2Vo(Source entity) {
        SourceVo vo = null;
        if (!Objects.isNull(entity)) {
            vo = new SourceVo();
            BeanUtils.copyProperties(entity, vo);
            vo.setStatusName(ProcessEnum.getVal(entity.getStatus()));
            vo.setExpertReportName(BoolUtils.Y.equals(entity.getExpertReport()) ? "是" : "否");
        }
        return vo;
    }
}
