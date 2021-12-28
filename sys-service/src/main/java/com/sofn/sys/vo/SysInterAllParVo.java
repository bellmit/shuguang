package com.sofn.sys.vo;

import com.sofn.sys.model.SysInterHeader;
import com.sofn.sys.model.SysInterJson;
import com.sofn.sys.model.SysInterParams;
import com.sofn.sys.model.SysInterReturn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel("接口参数整体对象")
public class SysInterAllParVo {

	@ApiModelProperty("请求参数头")
	private List<SysInterHeader> sysInterHeader;

	@ApiModelProperty("get请求参数形式")
	private List<SysInterParams> sysInterParams;

	@ApiModelProperty("请求体传入json字符串")
	private List<SysInterJson> sysInterJson;

	@ApiModelProperty("参数接口外键")
	private String interId;

	@ApiModelProperty("接口返回参数")
	private List<SysInterReturn> sysInterReturns;


}
