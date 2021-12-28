package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.sys.model.SysBaseDataType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SysBaseDataTypeMapper extends BaseMapper<SysBaseDataType> {

    List<SysBaseDataType> getBaseDataTypeByName(@Param(value="typename") String typename, @Param(value="typevalue") String typevalue);

    List<SysBaseDataType> getBaseDataTypeByName1(@Param(value="typename") String typename,@Param(value="typevalue") String typevalue);

    void saveBaseDataType(SysBaseDataType sysBaseDataType);

    void delBaseDataType(String id);

    void updateBaseDataType(@Param(value="id") String id, @Param(value="typename") String typename, @Param(value="typevalue") String typevalue, @Param(value="updateTime") Date updateTime, @Param(value="updateUserId") String updateUserId);

    List<SysBaseDataType> getBaseDataTypeById(String baseDatatypeid);

    List<SysBaseDataType> getBaseDataCurNodeBySubSystem(@Param(value = "subsystemid") String subsystemid);

    void delSubSystemBaseData(@Param(value = "baseDataTypeId") String BaseDataTypeId,@Param(value = "subsystemid") String subsystemid);

    void saveSubSystemBaseData(@Param(value = "subid") String subid, @Param(value = "subsystemid") String subsystemid, @Param(value = "basedataid") String baseDataID);

    void delSubSystemBaseDataByType(@Param(value = "baseDataTypeId") String BaseDataTypeId);

    /**
     * 按条件查询基础数据类型
     * @param params  条件 keyword
     * @return  List<SysBaseDataType>
     */
    List<SysBaseDataType> getBaseDataTypeByKeyword(Map<String,Object> params);

    /**
     * 根据数据字典类型代码批量获取数据字典类型
     * @param codes  数据字典类型
     * @return  List<SysBaseDataType>
     */
    List<SysBaseDataType> getBaseDataTypeByCodes(@Param("codes") List<String> codes);
}
