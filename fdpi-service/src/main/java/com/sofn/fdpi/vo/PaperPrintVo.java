package com.sofn.fdpi.vo;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-07-14 15:30
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "证书打印列表对象")
public class PaperPrintVo {
    @ApiModelProperty("主键")
    private String id;
    //1：人工繁育；2：驯养繁殖；3：经营利用
    @ApiModelProperty("证书类型")
    private String papersType;
    //证书编号
    @ApiModelProperty("证书编号")
    private String papersNumber;
    //企业名称
    @ApiModelProperty("企业名称")
    private String compName;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    @ApiModelProperty("公司地址")
    private String compAdd;

//    @ApiModelProperty("核定物种")
//    private String issueSpe;
    @ApiModelProperty("有效期至")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date dataClos;
    @ApiModelProperty("是否打印，0否 1：是")
    private String isPrint;
    @ApiModelProperty("是否打印，0:未打印 1：未打印 2未打印 3打印")
    private String isCopyPrint;
//    @ApiModelProperty("经营方式（业主回复后,经营许可证新增字段）")
//    private String modeOperation;
//    @TableField(exist = false)
//    private List<SpecListVo> ls;
}
