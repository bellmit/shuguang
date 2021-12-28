package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import lombok.Data;

import java.util.Date;

/**
 * @Description 加工企业model对象
 * @Author wg
 * @Date 2021/5/20 9:59
 **/
@Data
@TableName("om_breed_to_proc")
public class OmProc extends BaseModel<OmProc> {
    private String id;

    private String cellComp;

    private String transferComp;

    private String credential;

    private Integer importSize;

    private String importCountry;

    private Date importDate;

    private String citesId;

    private String customsList;

    private String miniRatifyFileId;

    private Double dealNum;

    private Double obversion;

    private Date dealDate;

    private String sellSign;

    private String transferSign;

    private String province;

    private String dealType;

    private Double remainingQty;

    private Double remainingQtyConvert;

    private String operator;
}
