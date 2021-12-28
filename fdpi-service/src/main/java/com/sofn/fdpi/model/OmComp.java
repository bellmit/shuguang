package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

@Data
@TableName("OM_COMP")
public class OmComp extends BaseModel<OmComp> {

    /**
     * id
     */
    private String id;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 用户名
     */
    private String username;
    /**
     * 单位名称
     */
    private String compName;
    /**
     * 企业类型 欧鳗进口企业 欧鳗养殖企业 欧鳗加工企业
     */
    private String compType;
    /**
     * 所在省份编码
     */
    private String provinceCode;
    /**
     * 所在省份名称
     */
    private String provinceName;
    /**
     * 法人代表
     */
    private String legalPerson;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 通讯地址
     */
    private String contactAddress;
    /**
     * 邮政编码
     */
    private String postal;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 驯养繁育许可证
     */
    private String tameBreedClience;
    /**
     * 准许驯养繁殖情况鳗苗(吨）
     */
    private Double tameAllowTon;
    /**
     * 经营利用许可证编号
     */
    private String manageLicence;
    /**
     * 准许经营利用情况(吨)
     */
    private Double allowManageTon;
    /**
     * 状态 0申报1不通过2通过
     */
    private String status;


}
