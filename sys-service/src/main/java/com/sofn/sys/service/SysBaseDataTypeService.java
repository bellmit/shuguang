package com.sofn.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.sys.model.SysBaseDataType;
import com.sofn.sys.vo.SysBaseTypeAndDataVo;

import java.util.List;
import java.util.Map;

public interface SysBaseDataTypeService extends IService<SysBaseDataType> {

    public List<SysBaseDataType> getBaseDataTypeByName(String typename, String typevalue);

    public List<SysBaseDataType> getBaseDataTypeByName1(String typename,String typevalue);

    public void saveBaseDataType(SysBaseDataType sysBaseDataType);

    public void delBaseDataType(String id);

    public void updateBaseDataType(String id,String typename,String typevalue);

    public List<SysBaseDataType> getBaseDataTypeById(String baseDataTypeId);

    void saveSubSystemBaseData(String subsystemid, String baseDataIds);

    List<SysBaseDataType> getBaseDataBySubSystem(String subsystemid);

    List<SysBaseDataType> getBaseDataParentBySubSystem(String subsystemid);

    List<SysBaseDataType> getBaseDataCurNodeBySubSystem(String subsystemid);

    /**
     * 按条件分页获取数据字典类型
     * @param params  条件
     * @param pageNo   第几页
     * @param pageSize  每页显示多少条
     * @return  PageUtils<List<SysBaseTypeAndDataVo>>
     */
    PageUtils<SysBaseTypeAndDataVo> getSysBaseDataByPage(Map<String,Object> params, Integer pageNo, Integer pageSize);


    /**
     * 按条件分页获取数据字典类型
     * @param keyword     字典值获取字典类型
     * @param pageNo   第几页
     * @param pageSize  每页显示多少条
     * @return  PageUtils<List<SysBaseTypeAndDataVo>>
     */
    PageUtils<SysBaseDataType> getBaseTypeByPage(String keyword, Integer pageNo, Integer pageSize);

}
