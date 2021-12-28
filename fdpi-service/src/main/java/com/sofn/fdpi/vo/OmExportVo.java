package com.sofn.fdpi.vo;

import com.sofn.fdpi.vo.OmFileForm;
import com.sofn.fdpi.vo.OmFileVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author wg
 * @Date 2021/6/7 14:20
 **/
@Data
@ApiModel(value = "再出口详情Vo对象")
public class OmExportVo {
    @ApiModelProperty(value = "主键，新增不传")
    private String id;
    @ApiModelProperty(value = "来源养殖场")
    private String sourceBreed;
    @ApiModelProperty(value = "允许进出口证明书号")
    private String credential;
    @ApiModelProperty(value = "欧鳗种类白籽鳗黑籽鳗")
    private Integer importSize;
    @ApiModelProperty(value = "发货口岸")
    private String portOfDispatch;
    @ApiModelProperty(value = "到达口岸")
    private String reachPort;
    @ApiModelProperty(value = "到达国家")
    private String reachCountry;
    @ApiModelProperty(value = "物种名称")
    private String spenName;
    @ApiModelProperty(value = "货物类型")
    private String goodsType;
    @ApiModelProperty(value = "目的")
    private String goal;
    @ApiModelProperty(value = "来源")
    private String source;
    @ApiModelProperty(value = "数量(吨)这个字段不是交易数量,目前只是一个文本框")
    private Double num;
    @ApiModelProperty(value = "交易数量(吨)(根据目前的需求来说是整条数据交易)")
    private Double exportVolume;
    @ApiModelProperty(value = "折算比例(吨)")
    private Double obversion;
    @ApiModelProperty(value = "规格及含量")
    private String size;
    @ApiModelProperty(value = "单价")
    private Double unitPrice;
    @ApiModelProperty(value = "货物总金额")
    private Double cargoMoney;
    @ApiModelProperty(value = "出口日期")
    private Date outOfDate;
    @ApiModelProperty(value = "邮政编码")
    private String postal;
    @ApiModelProperty(value = "联系人")
    private String linkman;
    @ApiModelProperty(value = "联系电话")
    private String linkPhone;
    @ApiModelProperty(value = "传真")
    private String fax;
    @ApiModelProperty(value = "详细地址")
    private String address;
    @ApiModelProperty(value = "欧鳗文件列表")
    private List<OmFileVO> files;
}
