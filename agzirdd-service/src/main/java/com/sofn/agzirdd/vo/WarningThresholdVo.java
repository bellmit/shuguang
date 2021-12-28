package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.ThresholdValue;
import com.sofn.agzirdd.model.WarningThreshold;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Chlf
 */
@ApiModel
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class WarningThresholdVo extends WarningThreshold {

    @ApiModelProperty(name = "thresholdValueList", value = "阈值参数List")
    private List<ThresholdValue> thresholdValueList;

}
