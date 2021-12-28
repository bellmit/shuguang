package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SysDictMapper  extends BaseMapper<SysDict> {

    List<SysDict> getDictInfo();

    void saveDictInfo(SysDict sysDict);

    void updateDictInfo(@Param(value = "id") String id,@Param(value = "enable") String enable,@Param(value = "updateTime") Date updateTime,@Param(value = "updateUserId") String updateUserId);

    void deleteDictInfo(@Param(value = "ids") List<String> id);

    List<SysDict> getDictByName(@Param(value = "dictName") String dictname);

    List<SysDict> getDictByValueAndType(@Param(value="dicttypeid") String dicttypeid,@Param(value = "dictname") String dictname,@Param(value="dictcode") String dictcode);

    List<SysDict> getDictById(String id);

    List<SysDict> getDictNameByValueAndType(@Param(value = "typeValue") String typeValue,@Param(value = "dictcode") String dictcode);

    List<SysDict> getDictListByType(@Param(value = "typevalue") String typevalue);

    List<SysDict> getDictListByTypeId(@Param(value = "typeId") String typeId);
}
