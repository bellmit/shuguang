package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.vo.SysDictVo;

import java.util.HashMap;
import java.util.List;

public interface SysDictService extends IService<SysDict> {

    public PageUtils<HashMap<String,Object>> getDictInfo(String typekeyname, Integer pageNo, Integer pageSize);

    public void saveDictInfo(SysDictVo sysDictVo);

    public void updateDictInfo(String id, String enable);

    public void deleteDictInfo(String id);

    public List<SysDict> getDictByName(String dictname);

    public List<SysDict> getDictByValueAndType(String dicttypeid,String dictname, String dictcode);

    void exportDict(String filePath);

    public List<SysDict> getDictById(String id);

    List<SysDict> getDictNameByValueAndType(String typename, String dictcode);

    List<SysDict> getDictListByType(String typevalue);
}
