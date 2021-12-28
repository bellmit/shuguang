package com.sofn.ducss.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ChangXieVO
 * @Description TODO
 * @Author Administrator
 * @Date2020/12/24 15:35
 * Version1.0
 **/
@Data
public class ChangXieVO {
    @ApiModelProperty("新用户连接到文档参与协同或者用户")
    private List<Object> actions;
    @ApiModelProperty("文档标识")
    private String key;
    @ApiModelProperty("当前需要保存的文档url")
    private String url;
    @ApiModelProperty("执行强制保存时，此参数有效")
    private Integer forcesavetype;
    @ApiModelProperty(" 0 - 文档标识为空\n" +
            " 1 - 正在编辑文档\n" +
            " 2 - 文档已准备保存\n" +
            " 3 - 文档保存错误\n" +
            " 4 - 文档关闭，文档没有修改\n" +
            " 6 - 正在编辑文档，但保存文档。\n" +
            " 7 - 强制保存文档时发生错误")
    private Integer status;
    @ApiModelProperty("用户自定义参数")
    private String userdata;

}
