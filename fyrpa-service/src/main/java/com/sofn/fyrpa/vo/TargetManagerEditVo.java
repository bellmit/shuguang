package com.sofn.fyrpa.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("编辑vo类")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TargetManagerEditVo {
    private TargetTwoManagerEditVo targetTwoManagerEditVo;
    private TargetOneManagerEditVo targetOneManagerEditVo;

}