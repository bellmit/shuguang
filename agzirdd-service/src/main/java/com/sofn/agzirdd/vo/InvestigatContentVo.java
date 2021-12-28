package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.InvestigatContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * * @Description: 物种监测模块-调查内容Vo
 * @author Administrator
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class InvestigatContentVo extends InvestigatContent {

    @ApiModelProperty(name = "resultImgUrl", value = "效果图Url")
    private String resultImgUrl;

    @ApiModelProperty(name = "utilizeImgUrl", value = "利用方式图片Url")
    private String utilizeImgUrl;

    @ApiModelProperty(name = "speciesImgUrl", value = "新发物种图URL")
    private String speciesImgUrl;

    public InvestigatContentVo(){}
    /**
     * 将vo转换为po对象
     */
    public static InvestigatContent getInvestigatContent(InvestigatContentVo investigatContentVo){
        InvestigatContent investigatContent = new InvestigatContent();
        BeanUtils.copyProperties(investigatContentVo,investigatContent);
        return investigatContent;
    }
    /**
     * po转换为vo
     */
    public static InvestigatContentVo getInvestigatContentVo(InvestigatContent investigatContent){
        InvestigatContentVo investigatContentVo = new InvestigatContentVo();
        BeanUtils.copyProperties(investigatContent,investigatContentVo);
        return investigatContentVo;
    }
}
