package com.sofn.dao.dgap;

import com.sofn.core.annotation.MyBatisDao;
import com.sofn.core.base.BaseExpandMapper;
import com.sofn.model.generator.TDgapResourceDirectory;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/** Created by Administrator on 2016/10/20. */
@MyBatisDao
public interface TDgapResourceDirectoryExpandMapper extends BaseExpandMapper {
  List<Map<String, Object>> getPagesList(Map<String, Object> params);

  long getPageCount(Map<String, Object> params);

  List<TDgapResourceDirectory> getTypes(String name);

  List<TDgapResourceDirectory> getType(String name);

  List<TDgapResourceDirectory> getAllDir();

  int addResourceDir(TDgapResourceDirectory resDir);

  List<Map<String, Object>> getList();
  /*  资源目录名表单验证*/
  List<TDgapResourceDirectory> getByDirName(@Param("dirName") String dirName);

  List<TDgapResourceDirectory> getByDirNameY(@Param("dirName") String dirName);
}
