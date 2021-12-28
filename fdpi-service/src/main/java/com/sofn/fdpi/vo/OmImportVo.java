package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description 详情vo对象
 * @Author wg
 * @Date 2021/5/25 17:30
 **/
@Data
public class OmImportVo {
    @ApiModelProperty(value = "主键，新增不传")
    private String id;
    @ApiModelProperty(value = "《允许进出口证明书》号")
    private String credential;
    @ApiModelProperty(value = "进口者")
    private String importMan;
    @ApiModelProperty(value = "进口口岸")
    private String importPort;
    @ApiModelProperty(value = "进口国")
    private String importCountry;
    @ApiModelProperty(value = "种名")
    private String specName;
    @ApiModelProperty(value = "标本类型")
    private String specimenType;
    @ApiModelProperty(value = "审批时间")
    private Date approveTime;
    @ApiModelProperty(value = "有效期至")
    private Date periodOfValidity;
    @ApiModelProperty(value = "证书核发地")
    private String credApprove;
    @ApiModelProperty(value = "进口数量(吨)")
    private Double quantity;
    @ApiModelProperty(value = "剩余数量(吨)")
    private Double remainingQty;
    @ApiModelProperty(value = "规格0白仔鳗1黑仔鳗")
    private Integer size;
    @ApiModelProperty(value = "卖方公司名称")
    private String sellComp;
    @ApiModelProperty(value = "单价（美元/千克）")
    private Double unitPrice;
    @ApiModelProperty(value = "合同金额（美元）")
    private Double money;
    @ApiModelProperty(value = "出口国CITES证书号")
    private String citesId;
    @ApiModelProperty(value = "农业部批准文件号")
    private String miniRatifyFileId;
    @ApiModelProperty(value = "委托进口企业")
    private String entrustImportComp;
    @ApiModelProperty(value = "联系人")
    private String linkman;
    @ApiModelProperty(value = "座机号")
    private String telephone;
    @ApiModelProperty(value = "手机")
    private String phoneNumber;
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;
    @ApiModelProperty(value = "进口日期")
    private Date importDate;
    @ApiModelProperty(value = "文件信息")
    private List<OmFileVO> files;
}
