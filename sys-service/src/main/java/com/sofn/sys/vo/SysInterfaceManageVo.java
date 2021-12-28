package com.sofn.sys.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sofn.sys.model.SysInterfaceManage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import java.util.Date;


/**
 * sys_interface_manage
 * @author zy 2020-04-23
 */
@Data
@ApiModel("支撑接口管理")

public class SysInterfaceManageVo {
	@ApiModelProperty("id")
	@TableId(type= IdType.UUID)
	private String id;

	@ApiModelProperty("接口名称")
	private String interName;

	@ApiModelProperty("请求方式")
	private String requestModel;

	@ApiModelProperty("请求参数，clob类型，存储json字符串，需要转义")
	private String requestPar;

	@ApiModelProperty("所属子系统id")
	private String sSubsystem;

	@ApiModelProperty("返回参数，clob类型，存储json字符串，需要转义")
	private String returnPar;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("操作人")
	private String operator;

	@ApiModelProperty("操作时间")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date operateTime;

	@ApiModelProperty("接口地址")
	private String interUrl;


	
	public static SysInterfaceManageVo getSysInterfaceManageVo(SysInterfaceManage sysInterfaceManage){
		SysInterfaceManageVo sysInterfaceManageVo = new SysInterfaceManageVo();
		BeanUtils.copyProperties(sysInterfaceManage,sysInterfaceManageVo);
		if (sysInterfaceManage.getReturnPar()!=null&&sysInterfaceManage.getRequestPar()!=null) {
			//把model的字节转为string
			String RequestPar = sysInterfaceManage.getRequestPar();
			String ReturnPar = sysInterfaceManage.getReturnPar();
			sysInterfaceManageVo.setRequestPar(RequestPar);
			sysInterfaceManageVo.setReturnPar(ReturnPar);
		}

		if(StringUtils.isBlank(sysInterfaceManageVo.getRemark())){
			sysInterfaceManageVo.setRemark("");
		}
		return sysInterfaceManageVo;
	}


}