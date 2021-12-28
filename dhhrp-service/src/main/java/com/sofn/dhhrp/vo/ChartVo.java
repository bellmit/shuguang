package com.sofn.dhhrp.vo;

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
    @ApiModelProperty(value = "群体数量")
    private Integer amount;
    @ApiModelProperty(value = "性别")
    private String gender;
    @ApiModelProperty(value = "比例")
    private Integer ratio;


    public static List<ChartVo> getAllMonthEffectiveNullData() {
        List<ChartVo> res = new ArrayList<>(12);
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0, "", 0));
        }
        return res;
    }

    public static List<ChartVo> getAllMonthRatioNullData() {
        List<ChartVo> res = new ArrayList<>(24);
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0, "male", 0));
        }
        for (int i = 0; i < 12; i++) {
            res.add(new ChartVo(i + 1, 0, "female", 0));
        }
        return res;
    }
}
