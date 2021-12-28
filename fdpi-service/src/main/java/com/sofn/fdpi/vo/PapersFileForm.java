package com.sofn.fdpi.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@ApiModel("证书信息修改保存表单对象")
@Data
public class PapersFileForm implements Serializable {
    @NotBlank(message = "不能为空")
    @Length(max = 32,message = "证书id长度超长！")
    @ApiModelProperty("证书id")
    private String papersId;
    @ApiModelProperty("证书图片列表")
    List<FileManageVo> fileList;
}
