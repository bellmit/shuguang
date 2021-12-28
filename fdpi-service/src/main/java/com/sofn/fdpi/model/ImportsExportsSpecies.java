package com.sofn.fdpi.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @Description: 进出口物种
 * @Auther: xiaobo
 * @Date: 2019-12-30 15:35
 */
@TableName("IMPORTS_EXPORTS_SPECIES")
@Data
@ApiModel(value = "进出口物种表单对象")
public class ImportsExportsSpecies extends BaseModel<ImportsExportsSpecies> {
    /**
     * 出口id
     */
    @ApiModelProperty(name = "id", value = "出口物种表id")
    @TableId(value = "ID",type = IdType.UUID)
    private String id;
    @ApiModelProperty(name = "exportsId", value = "出口id")
    private String exportsId;
    /**
     * 物种名
     */
    @Length(max = 10,message = "物种名不能超过10位")
    @NotBlank(message = "物种名下拉获取，不为空")
    @ApiModelProperty(name = "speName", value = "物种名")
    private String speName;
    /**
     *保护级别
     */

    @ApiModelProperty(name = "proLevel", value = "保护级别")
    @Length(max = 10,message = "保护级别不能超过10位")
    private String proLevel;
    /**
     *重量/数量
     */
    @Length(max = 10,message = "重量/数量不能超过10位")
    @NotBlank(message = "重量/数量不为空")
    @ApiModelProperty(name = "amount", value = "重量/数量")
    private String amount;
    /**
     *来源地/目的地
     */
    @Length(max = 20,message = "来源地/目的地不能超过20")
    @ApiModelProperty(name = "source", value = "来源地/目的地")
    private String source;

    /**
     *进口岸
     */
    @Length(max = 20,message = "进口岸不能超过20")
    @ApiModelProperty(name = "port", value = "进口岸")
    private String port;



}
