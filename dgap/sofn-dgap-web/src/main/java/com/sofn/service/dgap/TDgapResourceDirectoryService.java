package com.sofn.service.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseService;
import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.generator.TDgapResourceDirectory;
import com.sofn.provider.dgap.TDgapResourceDirectoryProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Created by Administrator on 2016/10/20. */
@Service
public class TDgapResourceDirectoryService
    extends BaseService<TDgapResourceDirectoryProvider, TDgapResourceDirectory> {
  @DubboReference
  public void setProvider(TDgapResourceDirectoryProvider provider) {
    this.provider = provider;
  }
  /**
   * 分页查询
   *
   * @param
   * @param pageNum
   * @param pageSize
   * @return
   */
  public PageInfo<Map<String, Object>> list(String name, int pageNum, int pageSize) {
    Map<String, Object> params = new HashMap<>();
    // page
    params.put(Constants.PAGE_NUM, pageNum);
    params.put(Constants.PAGE_SIZE, pageSize);
    // query
    params.put("name", name);
    return provider.list(params);
  }

  public List<TDgapResourceDirectory> selectAll(String name) {
    return provider.getTypes(name);
  }

  public List<TDgapResourceDirectory> selectTypes(String name) {
    return provider.getType(name);
  }

  public List<TDgapResourceDirectory> getAlldirs() {
    return provider.getAllDirs();
  }

  public int addResourceDir(TDgapResourceDirectory resDir) {
    int i = provider.addResourceDir(resDir);
    return i;
  }

  public List<Map<String, Object>> list() {
    return provider.list();
  }

  public boolean getByDirName(String dirName) {
    List<TDgapResourceDirectory> list = provider.getByDirName(dirName);
    return list.size() > 0;
  }

  public List<TDgapResourceDirectory> getByDirNameY(String dirName) {
    List<TDgapResourceDirectory> list = provider.getByDirNameY(dirName);
    return list;
  }
}
