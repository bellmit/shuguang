package com.sofn.ducss.vo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Zhang Yi
 * @Date 2020/11/19 12:34
 * @Version 1.0
 * Excel传参
 */
@ApiModel(value = "Excel传参")
@Data
public class ExelFileParam {
	@ApiModelProperty("上传文件")
	private MultipartFile file;
	@ApiModelProperty("导入年份")
	private String year;
}
