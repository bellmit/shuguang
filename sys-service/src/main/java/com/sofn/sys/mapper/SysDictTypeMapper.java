package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysDictType;
import com.sofn.sys.vo.SysDictTypeAndValueVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SysDictTypeMapper extends BaseMapper<SysDictType> {

    List<SysDictType> getDictTypeByName(@Param(value="typename") String typename,@Param(value="typevalue") String typevalue);

    List<SysDictType> getDictTypeByName1(@Param(value="typename") String typename,@Param(value="typevalue") String typevalue);

    void saveDictType(SysDictType sysDictType);

    void delDictType(String id);

    void updateDictType(@Param(value="id") String id, @Param(value="typename") String typename, @Param(value="typevalue") String typevalue, @Param(value="updateTime") Date updateTime,@Param(value="updateUserId") String updateUserId);

    List<SysDictType> getDictTypeById(String dicttypeid);

    List<SysDictType> getDictCurNodeBySubSystem(@Param(value = "subsystemid") String subsystemid);

    void delSubSystemDict(@Param(value = "dictTypeId") String dictTypeId,@Param(value = "subsystemid") String subsystemid);

    void saveSubSystemDict(@Param(value = "subid") String subid, @Param(value = "subsystemid") String subsystemid, @Param(value = "dictID") String dictID);


    /**
     * 根据名称或者字典类型value字段模糊匹配内容
     * @param params   模糊匹配字段   keyword
     * @return   List<SysDict>
     */
    List<SysDictTypeAndValueVo> getDictTypeListByKeyword(Map<String,Object> params);
}
