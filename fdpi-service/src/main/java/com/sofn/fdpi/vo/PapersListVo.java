package com.sofn.fdpi.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-01-08 10:30
 */
@Data
@ApiModel(value = "证书列表对象")
public class PapersListVo {
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
    @ApiModelProperty("企业类型")
    private String compType;
    //法人
    @ApiModelProperty("法人")
    private String legal;
    @ApiModelProperty("核定物种")
    private String issueSpe;
    @ApiModelProperty("有效期至")
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date dataClos;
    @ApiModelProperty("流程状态")
    private String parStatus;
    @ApiModelProperty("能否打印")
    private Boolean canPrint;
    @ApiModelProperty(value = "能否操作")
    private Boolean canHandle;
    @TableField(exist = false)
    private List<SpecListVo> ls;


    private String provincialId;
    private String cityId;
    private String areaId;
}
