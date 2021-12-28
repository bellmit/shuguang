package com.sofn.agzirdd.vo;

import com.sofn.agzirdd.model.MonitorContent;
import com.sofn.agzirdd.model.MonitorContent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * * @Description: 物种监测模块-监测内容Vo
 * @author Administrator
 */
@ApiModel
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MonitorContentVo extends MonitorContent {

    @ApiModelProperty(name = "resultImgUrl", value = "效果图Url")
    private String resultImgUrl;

    @ApiModelProperty(name = "utilizeImgUrl", value = "利用方式图片Url")
    private String utilizeImgUrl;

    @ApiModelProperty(name = "workImgUrl", value = "工作照片Url")
    private String workImgUrl;

    @ApiModelProperty(name = "speciesImgUrl", value = "物种照片Url")
    private String speciesImgUrl;

    public MonitorContentVo(){}
    /**
     * 将vo转换为po对象
     */
    public static MonitorContent getMonitorContent(MonitorContentVo monitorContentVo){
        MonitorContent monitorContent = new MonitorContent();
        BeanUtils.copyProperties(monitorContentVo,monitorContent);
        return monitorContent;
    }
    /**
     * po转换为vo
     */
    public static MonitorContentVo getMonitorContentVo(MonitorContent monitorContent){
        MonitorContentVo monitorContentVo = new MonitorContentVo();
        BeanUtils.copyProperties(monitorContent,monitorContentVo);
        return monitorContentVo;
    }
}
