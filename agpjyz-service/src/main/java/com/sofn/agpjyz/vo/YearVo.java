package com.sofn.agpjyz.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description:
 * @Auther: xiaobo
 * @Date: 2020-03-13 9:29
 */
@ApiModel(value = "文件VO对象")
@Data
public class YearVo {
    @ApiModelProperty("年份")
    private String yearTime;
}
