package com.sofn.ducss.service;

import com.sofn.ducss.model.basemodel.Result;
import com.sofn.ducss.model.StandardSet;
import com.sofn.ducss.model.StandardValue;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.StandarSetVo;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Zhang Yi
 * @Date 2020/10/27 17:59
 * @Version 1.0
 * 部级标准值设定
 */
public interface StandardSetService {
	/**
	 * 新增一套标椎
	 * @param standardSet
	 */
	Result<Object> add(StandarSetVo standardSet);

	/**
	 * copy一套标准
	 * @param copyYear
	 * @param beYear
	 */
	void copystandardValue(String copyYear, String beYear);

	/**
	 * 新增一套标准值
	 * @param standardValue
	 */
	void addSetValue(StandardValue standardValue);

	/**
	 * 查询是否有当前年度的数据
	 * @param beYear
	 * @return
	 */
	int selectYear(String beYear);

	/**
	 * 分页查询年度标准
	 * @param pageNo
	 * @param pageSize
	 * @param year
	 * @return
	 */
	Result<PageUtils<StandardSet>> selectPage(Integer pageNo, Integer pageSize, String year);

	/**
	 * 根据年度删除所有标准值
	 * @param year
	 */
	void deleteByYear(String year);

	/**
	 * 单独区划详情标准值展示
	 * @param map
	 * @return
	 */
	List<StandardValue> showStandardValues(HashMap<String, Object> map);

	/**
	 * 根据年份查询省份Ids
	 * @param year
	 * @return
	 */
	List<String> selectAreaIdsByYear(String year);

	/**
	 * 删除原来标准值新增数据
	 * @param standardValues
	 */
	void updateStandSetValue(List<StandardValue> standardValues);

	/**
	 * 查询可选年份copy
	 * @return
	 */
	Result<List<String>> selectCopyYear();

	/**
	 * 刪除标准年度
	 * @param year
	 */
	void deleteStandardByYear(String year);

	/**
	 * 根据年份和省区域id查询系数
	 * @param year
	 * @param province
	 * @return
	 */
	List<StandardValue> showStandardValues(String year,String province);

	/**
	 * 单独展示区划详情省
	 * @param year
	 * @param areaId
	 * @param ifBack 是否回填
	 * @return
	 */
	Result<List<StandardValue>> getGcByProvince(String year, String areaId,Boolean ifBack);

	/**
	 * 单独展示区划详情县
	 * @param year
	 * @param areaId
	 * @return
	 */
	Result<List<StandardValue>> getGcByAreaId(String year, String areaId);
}
