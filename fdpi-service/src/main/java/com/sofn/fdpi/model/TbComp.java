package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;


@TableName("TB_COMP")
@Data
public class TbComp extends BaseModel<TbComp>{
    //企业id（支撑平台组织机构id）
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    //公司名称
    private String compName;

    //省
    @TableField(value = "COMP_PROVINCE")
    private String compProvince;

    //市
    @TableField(value = "COMP_CITY")
    private String compCity;

    //区
    @TableField(value = "COMP_DISTRICT")
    private String compDistrict;
    //行政区划中文
    private String regionInCh;

    @TableField(value = "CONTACT_ADDRESS", exist = true)
    private String contactAddress;
    //邮政编码
    @TableField(value = "POST_ADDRESS", exist = true)
    private String postAddress;

    //法人
    @TableField(value = "LEGAL", exist = true)
    private String legal;

    @TableField(value = "LINKMAN", exist = true)
    private String LinkMan;

    @TableField(value = "PHONE", exist = true)
    private String phone;

    @TableField(value = "EMAIL", exist = true)
    private String email;
    //0：注册；1：已审核；2：退回
    @TableField(value = "COMP_STATUS", exist = true)
    private String compStatus;

    @TableField(exist = false)
    private String compStatusName;
    //直属机构
    private String direclyId;
    //省级机构
    private String provincialId;

    //营业执照上传的文件名称
    private String busLicenseFileName;
    //营业执照上传的文件ID
    private String busLicenseFileId;
    //营业执照上传的文件路径
    private String busLicenseFilePath;
    //直属机构级别
    private String directOrgLevel;
    //企业编号
    private String compCode;
    //企业类型
    private String compType;
    //申请单号
    private String applyNum;
}
