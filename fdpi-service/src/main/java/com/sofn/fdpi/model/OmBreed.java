package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Description 欧鳗养殖企业数据模型
 * @Author wg
 * @Date 2021/5/14 17:06
 **/
@Data
@TableName("om_import_to_breed")
public class OmBreed extends BaseModel<OmBreed> {
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
