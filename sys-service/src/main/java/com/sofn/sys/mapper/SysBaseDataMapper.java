package com.sofn.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.common.model.BaseData;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.vo.SysBaseDataAndTypeVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SysBaseDataMapper extends BaseMapper<SysBaseData> {

    List<SysBaseData> getBaseDataInfo();

    void saveBaseDataInfo(SysBaseData sysBaseData);

    void batchSaveBaseDataInfo(@Param("sysBaseDataList") List<SysBaseData> sysBaseDataList);

    void updateBaseDataInfo(@Param(value = "id") String id, @Param(value = "enable") String enable, @Param(value = "updateTime") Date updateTime, @Param(value = "updateUserId") String updateUserId);

    /**
     *  根据ID更新数据字典信息
     * @param sysBaseData  sysBaseData
     */
    void updateBaseDataInfoById(@Param("sysBaseData") SysBaseData sysBaseData);

    void deleteBaseDataInfo(@Param(value = "ids") List<String> id);

    List<SysBaseData> getBaseDataByName(@Param(value = "baseDataName") String baseDataName);


    /**
     * 根据类型ID  数据字典值代码  数据字典值名称 查询字典值
     * @param basedatatypeid   数据字典类型ID
     * @param basedataname     数据字典值名称
     * @param basedatacode     数据字典值代码
     * @param id    如果传入ID，那么就不包含该数据
     * @return  List<SysBaseData>
     */
    List<SysBaseData> getBaseDataByValueAndType(@Param(value="basedatatypeid") String basedatatypeid,
                                                @Param(value = "basedataname") String basedataname,
                                                @Param(value="basedatacode") String basedatacode,
                                                @Param("id") String id);

    /**
     * 批量获取字典值
     * @param ids  ID集合
     * @return  List<SysBaseData>
     */
    List<SysBaseData> getBaseDataById(@Param("ids") List<String> ids);

    List<SysBaseData> getBaseDataNameByValueAndType(@Param(value = "typeValue") String typeValue,@Param(value="baseDatacode") String basedatacode);

    List<SysBaseData> getBaseDataListByType(@Param(value = "typevalue") String typevalue);

    List<SysBaseData> getBaseDataListByTypeId(@Param(value = "typeId") String typeId);

    List<BaseData> getListByAppIdAndType(@Param(value = "appId") String appId, @Param(value = "type") String type);

    BaseData getByTypeAndKey(@Param(value = "type") String type, @Param(value = "key") String key);

    /**
     * 根据字典类型获取字典
     * @param types  文件类型
     * @return    List<BaseData>
     */
    List<SysBaseData> getBaseDataByBaseTypeIds(@Param(value = "types") List<String> types);


    /**
     * 根据名称和字典类型查询代码
     * @param appId   系统代码
     * @param baseDataTypeCode   基础数据类型Code
     * @param baseDataNames   基础数据名称
     * @return  List<BaseData>
     */
    List<BaseData> getBaseDataCodeByNames(@Param("appId") String appId,@Param("baseDataTypeCode") String baseDataTypeCode,@Param("baseDataNames") List<String> baseDataNames);


    /**
     *  按条件查询基础数据字典值
     * @param params   字典类型代码。。。
     * @return  List<SysBaseData>
     */
    List<SysBaseData> getBaseDataCodeByParams(Map<String,Object> params);


    /**
     * 分页获取数据字典值和数据字典类型信息
     * @param params   参数
     * @return    List<SysBaseDataAndTypeVo>
     */
    List<SysBaseDataAndTypeVo> getSysBaseDataAndType(Map<String,Object> params);
}
