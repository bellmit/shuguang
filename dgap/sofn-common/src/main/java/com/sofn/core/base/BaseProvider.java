package com.sofn.core.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author sofn
 * @version 2016年5月20日 下午3:19:19
 */
public interface BaseProvider<T extends BaseModel> {
	@Transactional
	T update(T record);

	@Transactional
	void delete(String id, String userId);

	T queryById(String id);

	void init();

	PageInfo<T> query(Map<String, Object> params);
	
	<K> PageInfo<K> getPage(Page<String> ids, Class<K> cls);

	<K> List<K> getList(List<String> ids, Class<K> cls);
}
