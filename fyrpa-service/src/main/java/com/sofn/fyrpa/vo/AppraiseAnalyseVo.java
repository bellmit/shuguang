package com.sofn.fyrpa.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("评价分析对象vo")
@Data
public class AppraiseAnalyseVo {

    @ApiModelProperty(name = "numericalValue",value ="数值" )
    private double numericalValue;

    @ApiModelProperty(name = "score",value ="得分" )
    private double score;

}
