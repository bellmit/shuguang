package com.sofn.sys.service;

import com.sofn.common.model.Result;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysInterfaceManage;
import com.sofn.sys.vo.SysInterAllParVo;
import com.sofn.sys.vo.SysInterfaceManageVo;

import java.util.List;
import java.util.Map;

public interface SysInterfaceManageService {

	List<SysInterfaceManageVo> getSysIntefaceContion(Map<String, Object> params);

	PageUtils<SysInterfaceManageVo> getSysIntefaceContion(Map<String, Object> params, Integer pageNo, Integer pageSize);


	/**
	 *新增
	 * @param sysInterfaceManage
	 */
	void saveSysInterfacePar(SysInterfaceManage sysInterfaceManage);

	/**
	 * 保存
	 * @param sysInterfaceManage
	 */
	void update(SysInterfaceManage sysInterfaceManage);

	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	SysInterfaceManageVo getOne(String id);

	/**
	 * 根据id删除数据
	 * @param id
	 */
	void deleteById(String id);

	/**
	 * 批量删除
	 * @param idList
	 */
	void batchDeleteInter(List<String> idList);


	/**
	 * 根据接口外键查询该接口请求参数详情
	 * @param interId
	 * @return
	 */
	SysInterAllParVo showReqParams(String interId);

	/**
	 * 根据字段id删除参数字段
	 * @param id
	 */
	void deleteParamsById(String id);

	/**
	 * 修改请求参数字段
	 * @param sysInterAllParVo
	 */
	void updateParFields(SysInterAllParVo sysInterAllParVo);

	/**
	 * 批量插入请求参数格式
	 * @param sysInterAllParVo
	 */
	void batchInsertParams(SysInterAllParVo sysInterAllParVo);
}
