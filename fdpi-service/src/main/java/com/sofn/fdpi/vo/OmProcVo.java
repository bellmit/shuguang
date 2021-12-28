package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description 欧鳗加工企业vo对象
 * @Author wg
 * @Date 2021/5/20 9:37
 **/
@Data
@ApiModel
public class OmProcVo {
    @Length(max = 64, message = "主键不能超过64位")
    @ApiModelProperty(value = "主键,新增不传")
    private String id;

    @NotBlank(message = "欧洲鳗鲡出让企业不能为空")
    @Length(max = 64, message = "欧洲鳗鲡出让企业长度不能大于64位")
    @ApiModelProperty(value = "欧洲鳗鲡出让企业（本系统的企业传主键，数据补录直接传名字）")
    private String cellComp;

    @NotBlank(message = "欧洲鳗鲡受让企业不能为空")
    @Length(max = 64, message = "欧洲鳗鲡受让企业长度不能大于64位")
    @ApiModelProperty(value = "欧洲鳗鲡受让企业（本系统的企业传主键，数据补录直接传名字）")
    private String transferComp;

    @NotBlank(message = "允许进出口证明书不能为空")
    @Length(max = 64, message = "《允许进出口证明书》号不能大于64位")
    @ApiModelProperty(value = "《允许进出口证明书》号")
    private String credential;

    @NotNull(message = "进口规格不能为空")
    @ApiModelProperty(value = "进口规格")
    private Integer importSize;

    @NotBlank(message = "来源国不能为空")
    @Length(max = 32, message = "来源国不能超过32位")
    @ApiModelProperty(value = "来源国")
    private String importCountry;

    @NotNull(message = "欧洲鳗鲡进口日期不能为空")
    @ApiModelProperty(value = "欧洲鳗鲡进口日期")
    private Date importDate;

    @NotBlank(message = "出口国CITES证书号不能为空")
    @Length(max = 64, message = "出口国CITES证书号不能大于64位")
    @ApiModelProperty(value = "出口国CITES证书号")
    private String citesId;

    @NotBlank(message = "海关报送单号不能为空")
    @Length(max = 64, message = "海关报送单号不能大于64位")
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;

    @NotBlank(message = "农业部批准文件号不能为空")
    @Length(max = 64, message = "农业部批准文件号不能大于64位")
    @ApiModelProperty(value = "农业部批准文件号")
    private String miniRatifyFileId;

    @NotNull(message = "欧洲鳗鲡交易数量不能为空")
    @ApiModelProperty(value = "欧洲鳗鲡交易数量(吨)")
    private Double dealNum;

    @NotNull(message = "欧洲鳗鲡交易日期不能为空")
    @ApiModelProperty(value = "欧洲鳗鲡交易日期")
    private Date dealDate;

    @NotBlank(message = "出让企业经办人签字不能为空")
    @Length(max = 64, message = "受让企业经办人签字不能大于64位")
    @ApiModelProperty(value = "出让企业经办人签字")
    private String sellSign;

    @NotBlank(message = "受让企业经办人签字不能为空")
    @Length(max = 64, message = "受让企业经办人签字不能大于64位")
    @ApiModelProperty(value = "受让企业经办人签字")
    private String transferSign;

    @NotBlank(message = "数据类型不能为空")
    @ApiModelProperty(value = "数据类型，1是本系统企业。2是非本系统企业(数据补录)")
    private String dealType;

    @Valid
    @ApiModelProperty(value = "文件列表")
    private List<OmFileForm> files;
}