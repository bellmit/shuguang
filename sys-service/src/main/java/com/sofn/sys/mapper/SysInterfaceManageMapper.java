package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysInterfaceManage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SysInterfaceManageMapper extends BaseMapper<SysInterfaceManage> {
	List<SysInterfaceManage> findByCondition(Map<String, Object> params);

	void saveSysInterfacePar(SysInterfaceManage sysInterfaceManage);

	void update(SysInterfaceManage sysInterfaceManage);

	SysInterfaceManage getOne(String id);

	void deleteById(String id);

	/**
	 * 批量删除
	 * @param idList   待删除ID
	 * @param updateUserId   操作用户
	 * @param updateTime   操作时间
	 */
	void batchDelete(@Param("ids") List<String> idList, @Param("updateUserId") String updateUserId, @Param("updateTime") Date updateTime);

	/**
	 * 查询此接口是否被删除
	 * @param interId
	 * @return
	 */
	String findStatus(String interId);
}
