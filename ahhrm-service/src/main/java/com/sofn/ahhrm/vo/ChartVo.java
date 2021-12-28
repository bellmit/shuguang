package com.sofn.ahhrm.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "报表Vo类")
@AllArgsConstructor
public class ChartVo {

    @ApiModelProperty(value = "月份")
    private Integer month;
    //    @ApiModelProperty(value = "公母比例")
//    private String proportion;
    @ApiModelProperty(value = "有效群体含量")
    private Double effectiveGroup;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "比例")
    private Integer ratio;


    public static List<ChartVo> getAllMonthEffectiveNullData() {
        List<ChartVo> res = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0d, "", 0));
        }
        return res;
    }

    public static List<ChartVo> getAllMonthRatioNullData() {
        List<ChartVo> res = new ArrayList<>(24);
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0d, "male", 0));
        }
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0d, "female", 0));
        }
        return res;
    }
}
