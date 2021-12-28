package com.sofn.ducss.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.ducss.model.SysDict;
import com.sofn.ducss.util.PageUtils;
import com.sofn.ducss.vo.SysDictAllVo;
import com.sofn.ducss.vo.SysDictVo;

import java.util.HashMap;
import java.util.List;

public interface SysDictService extends IService<SysDict> {

    public PageUtils<HashMap<String, Object>> getDictInfo(String typekeyname, Integer pageNo, Integer pageSize);

    public void saveDictInfo(SysDictVo sysDictVo);

    public void updateDictInfo(String id, String enable);

    public void deleteDictInfo(String id);

    public List<SysDict> getDictByName(String dictname);

    public List<SysDict> getDictByValueAndType(String dicttypeid, String dictname, String dictcode);

    void exportDict(String filePath);

    public List<SysDict> getDictById(String id);

    List<SysDict> getDictNameByValueAndType(String typename, String dictcode);

    List<SysDict> getDictListByType(String typevalue);

    List<SysDict> getDictListByTypeAndName(String typevalue, String keyword);

    List<SysDictAllVo> getSysDictAllList();

    SysDict getAllByName(String dicttypeid, String dictname);

    String saveOrUpdateDict(SysDictVo sysDictVo, SysDict dictDb);
}
