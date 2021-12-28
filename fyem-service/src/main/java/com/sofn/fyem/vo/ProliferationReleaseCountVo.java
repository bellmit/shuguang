package com.sofn.fyem.vo;

import com.sofn.fyem.excelmodel.ProliferationReleaseExcel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description: 各地区增殖放流关键数据vo
 * @Author: DJH
 * @Date: 2020/5/15 11:29
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class ProliferationReleaseCountVo {
    @ApiModelProperty(name = "countList", value = "各地区增殖放流关键数据list")
    private List<ProliferationReleaseExcel> countList;
}
