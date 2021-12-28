/**
 * @Author 文俊云
 * @Date 2019/12/27 14:39
 * @Version 1.0
 */
package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author 文俊云
 * @Date 2019/12/27 14:39
 * @Version 1.0
 */

@Data
public class ChangeRecordCompanyVO {
    @ApiModelProperty("企业ID")
    private String companyId;
    @ApiModelProperty("企业名称,在物种转移列表展示时候充当操作人使用")
    private String compName;
    @ApiModelProperty("企业类型")
    private String compType;
    @ApiModelProperty("企业所属省")
    private String compProvince;
    @ApiModelProperty("企业所属市")
    private String compCity;
    @ApiModelProperty("企业所属区县")
    private String compDistrict;
    @ApiModelProperty("企业通讯地址")
    private String contactAddress;
    @ApiModelProperty("企业邮政编码")
    private String postAddress;
    @ApiModelProperty("企业法人")
    private String legal;
    @ApiModelProperty("企业联系人")
    private String linkman;
    @ApiModelProperty("企业电话")
    private String phone;
    @ApiModelProperty("企业邮箱")
    private String email;
    @ApiModelProperty("企业直属机构代码")
    private String direclyId;
    @ApiModelProperty("企业省级机构代码")
    private String provincialId;
    @ApiModelProperty("企业直属机构级别")
    private String directOrgLevel;

    @ApiModelProperty("企业拥有的所有证书图片")
    private List<FileManageVo>  listPapersFiles;

}
