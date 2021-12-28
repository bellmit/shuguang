package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.*;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description 欧鳗进口数据库交互实体对象
 * @Author wg
 * @Date 2021/5/12 10:04
 **/
@Data
@TableName("om_import_form")
public class OmEelImportFrom extends BaseModel<OmEelImportFrom> {

    private String id;

    private String credential;

    private String importMan;

    private String importPort;

    private String importCountry;

    private String specName;

    private String specimenType;

    private Date approveTime;

    private Date periodOfValidity;

    private String credApprove;

    private Double quantity;

    private Double remainingQty;

    private Integer size;

    private String sellComp;

    private Double unitPrice;

    private Double money;

    private String citesId;

    private String miniRatifyFileId;

    private String entrustImportComp;

    private String linkman;

    private String telephone;

    private String phoneNumber;

    private String customsList;

    private Date importDate;

    private String province;
    //操作人
    private String operator;

}
