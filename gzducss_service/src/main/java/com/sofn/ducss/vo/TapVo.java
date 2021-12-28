package com.sofn.ducss.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sofn.ducss.model.CollectFlow;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TapVo extends Model<CollectFlow> {

    @ApiModelProperty(name = "regionTotalNum", value = "区划总个数")
    private int regionTotalNum;

    @ApiModelProperty(name = "regionPassNum", value = "已审核通过区划个数")
    private int regionPassNum;

    public TapVo(){}

}
