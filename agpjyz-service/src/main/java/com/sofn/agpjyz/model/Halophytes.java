package com.sofn.agpjyz.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-02-27 14:17
 */

@TableName("HALOPHYTES")
@Data
public class Halophytes {
    @ApiModelProperty(value = "主键id")
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    //    保护点ID
    @ApiModelProperty(value = "保护点ID")
    private String protectId;
    //    保护点value
    @ApiModelProperty(value = "保护点value")
    private String protectValue;
    //  伴生植物名称
    @ApiModelProperty(value = "伴生植物名称")
    private String associated;
    //    数量
    @ApiModelProperty(value = "数量")
    private String amount;
    //    生长状况
    @ApiModelProperty(value = "生长状况")
    private String growth;
    // 物种丰富度
    @ApiModelProperty(value = "物种丰富度")
    private String richness;
    // 操作人
    @ApiModelProperty(value = "操作人")
    private String inputer;
    // 操作时间
    @ApiModelProperty(value = "操作时间")
    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputerTime;

}
