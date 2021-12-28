package com.sofn.fdpi.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @Description 返回给前端的vo对象
 * @Author wg
 * @Date 2021/5/26 9:17
 **/
@Data
public class OmBreedReVo {
    @ApiModelProperty(value = "主键,新增不传")
    private String id;
    @ApiModelProperty(value = "欧洲鳗鲡出让企业")
    private String cellComp;
    @ApiModelProperty(value = "欧洲鳗鲡受让企业")
    private String transferComp;
    @ApiModelProperty(value = "进口规格")
    private Integer importSize;
    @ApiModelProperty(value = "来源国")
    private String importCountry;
    @ApiModelProperty(value = "进口日期")
    private Date importDate;
    @ApiModelProperty(value = "出口国CITES证书号")
    private String citesId;
    @ApiModelProperty(value = "《允许进出口证明书》号")
    private String credential;
    @ApiModelProperty(value = "海关报送单号")
    private String customsList;
    @ApiModelProperty(value = "农业部批准文件号")
    private String miniRatifyFileId;
    @ApiModelProperty(value = "欧洲鳗鲡交易数量(吨)")
    private Double dealNum;
    @ApiModelProperty(value = "欧洲鳗鲡交易日期")
    private Date dealDate;
    @ApiModelProperty(value = "出让企业经办人签字")
    private String sellSign;
    @ApiModelProperty(value = "受让企业经办人签字")
    private String transferSign;
    @ApiModelProperty(value = "文件列表")
    private List<OmFileVO> files;
}
