package com.sofn.fdpi.vo;


import com.sofn.fdpi.model.Papers;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:  证书年审详细对象
 * @Author: wuXy
 * @Date: 2020-1-3 16:41:37
 */
@Data
@ApiModel("证书年审详细对象")
public class PapersYearInspectViewVo implements Serializable {
    @ApiModelProperty("证书年审ID")
    private String id;
    @ApiModelProperty("年审年度")
    private String year;
    @ApiModelProperty("公司ID")
    private String compId;
    @ApiModelProperty("公司名称")
    private String compName;
    //省
    @ApiModelProperty("省")
    private String compProvince;
    //市
    @ApiModelProperty("市")
    private String compCity;
    //区
    @ApiModelProperty("区")
    private String compDistrict;

    @ApiModelProperty(value = "行政区域中文",example = "四川省-成都市-高新区")
    private String regionInCh;
    
    @ApiModelProperty("通讯地址")
    private String contactAddress;
    //邮政编码
    @ApiModelProperty("邮政编码")
    private String postAddress;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    @ApiModelProperty("联系人")
    private String LinkMan;
    @ApiModelProperty("联系电话")
    private String phone;
    @ApiModelProperty("电子邮箱")
    private String email;
    //营业执照上传的文件名称
    @ApiModelProperty("营业执照上传的文件名称")
    private String busLicenseFileName;
    //营业执照上传的文件ID
    @ApiModelProperty("营业执照上传的文件ID")
    private String busLicenseFileId;
    //营业执照上传的文件路径
    @ApiModelProperty("营业执照上传的文件路径")
    private String busLicenseFilePath;


    @ApiModelProperty("物种信息列表")
    private List<SpecieVoInPapersYear> speciesList;
    @ApiModelProperty("相关证件列表")
    private List<PapersVoInPapersYear> papersList;
    @ApiModelProperty("年审记录历史列表")
    private List<PapersYearInspectHistoryVo> inspectList;
    @ApiModelProperty("物种变更记录")
    private List<ChangeRecVoInPapersYearVo> speciesChangelist;
    @ApiModelProperty("企业编号")
    private String compCode;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("申请编号")
    private String applyNum;
}
