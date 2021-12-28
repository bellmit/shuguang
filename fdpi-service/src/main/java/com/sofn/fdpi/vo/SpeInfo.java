package com.sofn.fdpi.vo;

import com.sofn.fdpi.model.Spe;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Auther:
 * @Date: 2019/12/2 15:48
 * @Description:
 */
@ApiModel
@Data
@AllArgsConstructor
public class SpeInfo {
    @ApiModelProperty(value = "物种编号")
    private String id;
    @NotBlank(message="物种名必填")
    @Size(max = 20, message = "物种名长度不能超过20")
    @ApiModelProperty(value = "物种名")
    private String speName;
//    @Size(max = 100, message = "拉丁名长度不能超过100")
    @ApiModelProperty(value = "拉丁名")
    private String latinName;
    @Size(max = 10, message = "俗名不能超过10")
    @ApiModelProperty(value = "俗名")
    private String localName;
    @Size(max = 10, message = "商品名不能超过10")
    @ApiModelProperty(value = "商品名")
    private String tradeName;
    @ApiModelProperty(name = "proLevel", value = "中国保护水平写死 一级:1 ，2级：2 ，特殊管理要求：3")
    private String proLevel;
    @ApiModelProperty(name = "identify", value = "是否使用标识写死，0:不使用,1 :全程使用,2:销售使用")
    private String identify;
    @ApiModelProperty(name = "pedigree", value = "是否进行谱系写死，否：0是：1")
    private String pedigree;
    @ApiModelProperty(name = "cites", value = "CITES级别，1级：1,2级：2,3级：3")
    private String cites;
    @Size(max = 50, message = "特殊要求不能超过50")
    @ApiModelProperty(value = "特殊要求：可以不传")
    private String requirements;
    @Size(max = 500, message = "简介内容长度不能超过10")
    @ApiModelProperty(value = "简介")
    private String intro;
    @Size(max = 500, message = "生境及习性内容不能超过100")
    @ApiModelProperty(value = "生境及习性")
    private String habit;
    @Size(max = 500, message = "地理分布内容不能超过100")
    @ApiModelProperty(value = "地理分布")
    private String distribution;
    @Size(max = 500, message = "保护现状内容不能超过500")
    @ApiModelProperty(value = "保护现状")
    private String conStatus;
    @ApiModelProperty(value = "物种编号")
    private String speCode;
    @ApiModelProperty(value = "物种类型")
    private String speType;
    @ApiModelProperty(value = "文件表单列表")
    @Valid
    private List<FileManageForm> files;


    /**
     * 将vo转换为po对象
     */
    public static Spe getSpeModel(SpeInfo SpeInfo) {
        Spe spe = new Spe();
        BeanUtils.copyProperties(SpeInfo, spe);
        return spe;
    }

}
