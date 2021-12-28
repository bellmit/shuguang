package com.sofn.fdpi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description 欧鳗进口企业表单提交vo对象
 * @Author wg
 * @Date 2021/5/16 15:11
 **/
@Data
@ApiModel(value = "欧鳗进口企业表单提交form对象")
public class OmEelImportVo {

    @ApiModelProperty(value = "主键，新增不传更新传")
    private String id;

    @NotBlank(message = "允许进出口证明书不能为空")
    @Length(max = 64, message = "允许进出口证明书长度不能超过64位")
    @ApiModelProperty(value = "《允许进出口证明书》号")
    private String credential;

    @NotBlank(message = "允许进出口证明书不能为空")
    @Length(max = 64, message = "进口者长度不能超过64位")
    @ApiModelProperty(value = "进口者")
    private String importMan;

    @NotBlank(message = "进口口岸不能为空")
    @Length(max = 32, message = "进口口岸不能超过32位")
    @ApiModelProperty(value = "进口口岸")
    private String importPort;

    @NotBlank(message = "进口国不能为空")
    @Length(max = 32, message = "进口国不能超过32位")
    @ApiModelProperty(value = "进口国")
    private String importCountry;

    @NotBlank(message = "种名不能为空")
    @Length(max = 32, message = "种名不能超过32位")
    @ApiModelProperty(value = "种名")
    private String specName;

    @NotBlank(message = "标本类型不能为空")
    @Length(max = 32, message = "标本类型不能为空")
    @ApiModelProperty(value = "标本类型")
    private String specimenType;

    @NotNull(message = "审批时间不能为空")
    @ApiModelProperty(value = "审批时间")
    private Date approveTime;

    @NotNull(message = "有效期不能为空")
    @ApiModelProperty(value = "有效期至")
    private Date periodOfValidity;

    @NotBlank(message = "证书核发地不能为空")
    @Length(max = 32, message = "证书核发地不能超过32位")
    @ApiModelProperty(value = "证书核发地")
    private String credApprove;

    @NotNull(message = "进口数量不能为空")
    @Min(0)
    @ApiModelProperty(value = "进口数量(吨)")
    private Double quantity;

    @NotNull(message = "规格不能为空")
    @ApiModelProperty(value = "规格,目前0是白仔鳗，1是黑仔鳗")
    private Integer size;

    @NotBlank(message = "卖方公司名称不能为空")
    @Length(max = 64)
    @ApiModelProperty(value = "卖方公司名称")
    private String sellComp;

    @NotNull(message = "单价不能为空")
    @ApiModelProperty(value = "单价（美元/千克）")
    private Double unitPrice;

    @NotNull(message = "合同金额不能为空")
    @ApiModelProperty(value = "合同金额（美元）")
    private Double money;

    @NotBlank(message = "出口国CITES证书号不能为空")
    @Length(max = 32, message = "出口国CITES证书号长度不能超过32位")
    @ApiModelProperty(value = "出口国CITES证书号")
    private String citesId;

    @NotBlank(message = "农业部批准文件号不能为空")
    @Length(max = 32, message = "农业部批准文件号不能超过32位")
    @ApiModelProperty(value = "农业部批准文件号")
    private String miniRatifyFileId;

    @NotBlank(message = "委托进口企业不能为空")
    @Length(max = 32, message = "委托进口企业不能为空")
    @ApiModelProperty(value = "委托进口企业")
    private String entrustImportComp;

    @NotBlank(message = "联系人不能为空")
    @Length(max = 32, message = "联系人不能为空")
    @ApiModelProperty(value = "联系人")
    private String linkman;

    @NotBlank(message = "座机号不能为空")
    @Length(max = 32, message = "座机号不能超过32位")
    @ApiModelProperty(value = "座机号")
    private String telephone;

    @NotBlank(message = "手机号不能为空")
    @Length(max = 32, message = "手机号不能超过32位")
    @ApiModelProperty(value = "手机")
    private String phoneNumber;

    @NotBlank(message = "海关报送单号不能为空")
    @Length(max = 32, message = "海关报送单号不能超过32位")
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;

    @NotNull(message = "进口日期不能为空")
    @ApiModelProperty(value = "进口日期")
    private Date importDate;

    @ApiModelProperty(value = "文件列表")
    @Valid
    private List<OmFileForm> files;
}
