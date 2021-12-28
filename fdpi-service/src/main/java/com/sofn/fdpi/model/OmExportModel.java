package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author wg
 * @Date 2021/5/21 17:20
 **/
@Data
@TableName("om_export")
public class OmExportModel extends BaseModel<OmExportModel> {
    private String id;
    private String sourceBreed;
    private String credential;
    private Integer importSize;
    private String sourceProc;
    private String portOfDispatch;
    private String reachPort;
    private String reachCountry;
    private String spenName;
    private String goodsType;
    private String goal;
    private String source;
    private Double num;
    private String size;
    private Double unitPrice;
    private Double cargoMoney;
    private Date outOfDate;
    private String postal;
    private String linkman;
    private String linkPhone;
    private String fax;
    private String address;
    private Double exportVolume;
    private Double obversion;
    private String operator;
    private String province;
}
