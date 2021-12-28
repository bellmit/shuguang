package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.model.BaseData;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.vo.Excel.ImportBaseDataVo;
import com.sofn.sys.vo.SysBaseDataAndTypeVo;
import com.sofn.sys.vo.SysBaseDataVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础数据相关接口
 * @author zqc
 */
public interface SysBaseDataService extends IService<SysBaseData> {

    /**
     * 分页获取基础数据
     * @param typeKeyName   基础数据类型
     * @param pageNo  从哪条记录开始
     * @param pageSize  每页显示多少条
     * @return   PageUtils<HashMap<String,Object>>
     */
    PageUtils<HashMap<String,Object>> getBaseDataInfo(String typeKeyName, Integer pageNo, Integer pageSize);

    /**
     * 保存基础数据信息
     * @param sysBaseDataVo  基础数据
     */
    void saveBaseDataInfo(SysBaseDataVo sysBaseDataVo);


    /**
     * 更改基础数据启用信息
     * @param id  基础数据ID
     * @param enable    是否启用
     */
    void updateBaseDataInfo(String id, String enable);

    /**
     * 删除基础数据信息
     * @param id  待删除ID
     */
    void deleteBaseDataInfo(String id);

    /**
     *  根据名字查询基础信息
     * @param baseDataName  基础信息名字
     * @return  List<SysBaseData>
     */
    List<SysBaseData> getBaseDataByName(String baseDataName);

    /**
     * 根据基础数据类型获取基础信息
     * @param baseDataTypeId   基础数据类型ID
     * @param baseDataName   基础数据名字
     * @param baseDataCode   基础数据类型
     * @param id    数据字典ID
     * @return  List<SysBaseData>
     */
    List<SysBaseData> getBaseDataByValueAndType(String baseDataTypeId,String baseDataName, String baseDataCode,String id);

    /**
     * 导出基础数据信息
     * @param filePath   导出的文件位置
     */
    void exportBaseData(String filePath);

    /**
     * 根据ID获取基础数据信息
     * @param id  基础数据信息ID
     * @return   List<SysBaseData>
     */
    List<SysBaseData> getBaseDataById(String id);

    /**
     * 获取基础数据名字和值根据typeId
     * @param typename   类型名称
     * @param baseDataCode  基础数据代码
     * @return   List<SysBaseData>
     */
    List<SysBaseData> getBaseDataNameByValueAndType(String typename, String baseDataCode);

    /**
     * 根据typeCode获取基础数据信息
     * @param typeValue   基础数据类型
     * @return   List<SysBaseData>
     */
    List<SysBaseData> getBaseDataListByType(String typeValue);

    /**
     * 根据子系统代码和系统类型和基础数据代码获取信息
     * @param appId  子系统代码
     * @param type    基础数据类型
     * @param key     基础数据信息代码
     * @return  基础数据信息
     */
    BaseData getByAppIdAndTypeAndKey(String appId, String type, String key);


    /**
     * 根据子系统代码和系统类型和基础数据值获取信息
     * @param appId  子系统代码
     * @param type    基础数据类型
     * @param value   基础数据信息值
     * @return  基础数据信息
     */
    BaseData getByAppIdAndTypeAndValue(String appId, String type, String value);

    /**
     * 根据子系统代码和系统类型和类型获取值
     * @param appId   系统代码
     * @param type     基础数据类型
     * @return   List<BaseData>
     */
    List<BaseData> getByAppIdAndType(String appId, String type);

    /**
     * 根据名称和字典类型查询代码
     * @param appId   系统代码
     * @param baseDataTypeCode   基础数据类型Code
     * @param baseDataNames   基础数据名称
     * @return  List<BaseData>
     */
    List<BaseData> getBaseDataCodeByNames(String appId,String baseDataTypeCode,String baseDataNames);


    /**
     * 获取基础数据字典值
     * @param keyword   字典的代码或者字典的名称
     * @param baseDataTypeCode   数据字典的Code
     * @param pageNo     从哪里开始
     * @param pageSize   每页显示多少条
     * @return    PageUtils<SysBaseData>
     */
    PageUtils<SysBaseData> getSysBaseDataByPage(String keyword,String baseDataTypeCode, Integer pageNo, Integer pageSize);


    /**
     *  根据ID更新数据字典信息
     * @param sysBaseData  sysBaseData
     */
    void updateBaseDataInfoById(SysBaseData sysBaseData);

    /**
     * 根据Excel导入数据
     * @param importBaseDataVos   Excel中的数据
     */
    void importBaseData(List<ImportBaseDataVo> importBaseDataVos);

    /**
     * 分页获取数据字典值和数据字典类型信息
     * @param params   参数
     * @param pageNo   从哪条数据显示
     * @param pageSize  每页显示多少条
     * @return     PageUtils<SysBaseDataAndTypeVo>
     */
    PageUtils<SysBaseDataAndTypeVo> getSysBaseDataAndType(Map<String,Object> params, Integer pageNo, Integer pageSize);


}
