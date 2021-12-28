package com.sofn.sys.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sofn.common.model.BaseModel;
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
@ApiModel("应用接口管理")
public class SysInterfaceManageForm {
	@ApiModelProperty("id")
	@TableId(type= IdType.UUID)
	private String id;

	@ApiModelProperty("接口名称")
	private String interName;

	@ApiModelProperty("请求方式")
	private String requestModel;

	@ApiModelProperty("请求参数,clob类型，存储json字符串，需要转义")
	private String requestPar;

	@ApiModelProperty("所属子系统id")
	private String sSubsystem;

	@ApiModelProperty("返回参数，clob类型，存储json字符串，需要转义")
	private String returnPar;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("接口地址")
	private String interUrl;



	/**
	 * 将表单对象转为持久层对象
	 * @param sysInterfaceManageForm
	 * @return
	 */
	public static SysInterfaceManage getSysInterfaceManage(SysInterfaceManageForm sysInterfaceManageForm){
		SysInterfaceManage sysInterfaceManage = new SysInterfaceManage();
		BeanUtils.copyProperties(sysInterfaceManageForm,sysInterfaceManage);
		if (sysInterfaceManageForm.getRequestPar()!=null && sysInterfaceManageForm.getReturnPar()!=null){
		sysInterfaceManage.setRequestPar(sysInterfaceManageForm.getRequestPar());
		sysInterfaceManage.setReturnPar(sysInterfaceManageForm.getReturnPar());
		}
		return sysInterfaceManage;

	}

	/**
	 * 将model转为表单对象
	 * @param sysInterfaceManage
	 * @return
	 */
	public static SysInterfaceManageForm getSysInterfaceManageForm(SysInterfaceManage sysInterfaceManage){
		SysInterfaceManageForm sysInterfaceManageForm = new SysInterfaceManageForm();
		BeanUtils.copyProperties(sysInterfaceManage,sysInterfaceManageForm);
//		if (sysInterfaceManage.getReturnPar()!=null&&sysInterfaceManage.getRequestPar()!=null) {
//			//把model的字节转为string
//			String RequestPar = new String(sysInterfaceManage.getRequestPar());
//			String ReturnPar = new String(sysInterfaceManage.getReturnPar());
//			sysInterfaceManageForm.setRequestPar(RequestPar);
//			sysInterfaceManageForm.setReturnPar(ReturnPar);
//		}
		if(StringUtils.isBlank(sysInterfaceManageForm.getRemark())){
			sysInterfaceManageForm.setRemark("");
		}
		return sysInterfaceManageForm;
	}





}