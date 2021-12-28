package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("新增vo类")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetManagerAddVo {
    private TargetTwoManagerAddVo targetTwoManagerAddVo;
    private TargetOneManagerAddVo targetOneManagerAddVo;

}