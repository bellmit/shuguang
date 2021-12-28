package com.sofn.provider.dgap;

import com.github.pagehelper.PageInfo;
import com.sofn.core.base.BaseProviderImpl;
import com.sofn.core.support.dubbo.spring.annotation.DubboService;
import com.sofn.dao.dgap.TDgapResourceDirectoryExpandMapper;
import com.sofn.dao.dgap.TDgapResourceExpandMapper;
import com.sofn.model.generator.TDgapResourceDirectory;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

/** 资源目录展示列表 Created by Administrator on 2016/10/20. */
@DubboService(interfaceClass = TDgapResourceDirectoryProvider.class)
public class TDgapResourceDirectoryProviderImpl extends BaseProviderImpl<TDgapResourceDirectory>
    implements TDgapResourceDirectoryProvider {
  @Autowired private TDgapResourceDirectoryExpandMapper dgapResourceDirectoryExpandMapper;
  @Autowired private TDgapResourceExpandMapper tDgapResourceExpandMapper;

  /*  资源目录分页展示*/
  @Override
  public PageInfo<Map<String, Object>> list(Map<String, Object> params) {
    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
    List<Map<String, Object>> list = dgapResourceDirectoryExpandMapper.getPagesList(params);
    for (Map<String, Object> m : list) {
      String id = (String) m.get("ID");
      int countByResourceDirectoryId = tDgapResourceExpandMapper.getResourceByDirId(id).size();
      if (countByResourceDirectoryId == 0) {
        m.put("flagStatus", "enable");
      } else m.put("flagStatus", "disEnable");
    }
    long count = dgapResourceDirectoryExpandMapper.getPageCount(params);
    pageInfo.setList(list);
    pageInfo.setTotal(count);
    return pageInfo;
  }

  @Override
  public List<TDgapResourceDirectory> getTypes(String name) {
    return dgapResourceDirectoryExpandMapper.getTypes(name);
  }

  @Override
  public List<TDgapResourceDirectory> getType(String name) {
    return dgapResourceDirectoryExpandMapper.getType(name);
  }

  public List<TDgapResourceDirectory> getAllDirs() {
    return dgapResourceDirectoryExpandMapper.getAllDir();
  }

  @Override
  public List<TDgapResourceDirectory> getByDirName(String dirName) {
    List<TDgapResourceDirectory> list = dgapResourceDirectoryExpandMapper.getByDirName(dirName);
    return list;
  }

  @Override
  public List<TDgapResourceDirectory> getByDirNameY(String dirName) {
    List<TDgapResourceDirectory> list = dgapResourceDirectoryExpandMapper.getByDirNameY(dirName);
    return list;
  }

  @Override
  public int addResourceDir(TDgapResourceDirectory resDir) {
    int i = dgapResourceDirectoryExpandMapper.addResourceDir(resDir);
    return i;
  }

  @Override
  public List<Map<String, Object>> list() {
    return dgapResourceDirectoryExpandMapper.getList();
  }
}
