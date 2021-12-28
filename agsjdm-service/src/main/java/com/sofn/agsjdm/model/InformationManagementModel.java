package com.sofn.agsjdm.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @Description
 * @Author wg
 * @Date 2021/7/14 9:53
 **/
@Data
@TableName("information_management")
public class InformationManagementModel extends BaseModel<InformationManagementModel> {
    private String id;
    private String wetAreasName;
    private String wetAreasCode;
    private Double totalWetlandArea;
    private String numberOfWetlandPatches;
    private String wetlandClass1;
    private Double wetlandClassArea1;
    private String mainWetlandType1;
    private Double mainWetlandTypeArea1;
    private String wetlandClass2;
    private Double wetlandClassArea2;
    private String mainWetlandType2;
    private Double mainWetlandTypeArea2;
    private String regionInCh;
    private Double longitude;
    private Double latitude;
    private String theSecondaryBasin;
    private String theRiverLevel;
    private String meanSeaLevel;
    private String waterSupply;
    private String seaWetlands;
    private Double salinity;
    private String waterTemperature;
    private String propertyInLand;
    private String vegetationalForm;
    private Double vegetationalFormArea;
    private String dominantPlantChineseName;
    private String dominantPlantLatinName;
    private String familyName;
    private String operator;
    private String provinceCode;
    private String cityCode;
    private String areaCode;
}
