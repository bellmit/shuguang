package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@ApiModel(value = "鲟鱼子酱标识补打表单对象")
public class SturgeonReprintForm {

    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "鲟鱼子酱ID(证书下拉列表key值)")
    private String sturgeonId;
    @ApiModelProperty(value = "标识数量", example = "42")
    private Integer labelSum;
    @Max(value = 100000000,message = "补打数量必须小于10亿")
    @Min(value = 0, message = "补打数量必须大于0")
    @ApiModelProperty(value = "补打数量", example = "8")
    private Integer reprintSum;
    @ApiModelProperty(value = "图片, 多id用英文,隔开")
    private String imgIds;
    @ApiModelProperty(value = "文件")
    private String fileId;
    @ApiModelProperty(value = "状态", example = "1")
    private String status;
    @ApiModelProperty(value = "标识ids")
    private List<String> signboardIds;
    @ApiModelProperty(value = "申请类型1国外2国内")
    private String applyType;

}
