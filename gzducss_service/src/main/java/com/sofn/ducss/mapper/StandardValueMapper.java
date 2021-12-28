package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.StandardValue;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * @Author Zhang Yi
 * @Date 2020/10/29 10:07
 * @Version 1.0
 * 标椎值mapper
 */
public interface StandardValueMapper extends BaseMapper<StandardValue> {

	/**
	 * copy年度标准
	 * @param copyYear
	 * @param beYear
	 */
	void copystandardValue(@Param("copyYear") String copyYear, @Param("beYear")String beYear);

	/**
	 * 根据年份查询省份Ids
	 * @param year
	 * @return
	 */
	ArrayList<String> selectAreaIdsByYear(@Param("year") String year);

}
