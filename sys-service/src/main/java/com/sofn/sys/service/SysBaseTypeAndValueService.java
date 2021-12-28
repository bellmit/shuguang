package com.sofn.sys.service;

import com.sofn.sys.vo.SysBaseTypeAndValueVo;

import java.util.List;

/**
 * 数据字典类型和数据值分配和子系统相关接口
 * @author heyongjie
 * @date 2020/2/28 14:59
 */
public interface SysBaseTypeAndValueService {


    /**
     * 获取所有的字典类型和字典值
     * @return  List<SysBaseTypeAndValueVo>
     */
    List<SysBaseTypeAndValueVo> getAllBaseTypeAndValue();

    /**
     * 获取子系统有的基础数据字典类型和值
     * @param appId   子系统
     * @return   List<String>
     */
    List<String> getSubSystemHaveBaseTypeAndValueId(String appId);

    /**
     * 保存子系统和基础数据字典的关系
     * @param ids   字典类型和字典值的前缀不同
     * @param appId    系统ID
     */
    void saveSubSystemAndBaseDataRelationship(List<String> ids,String appId);



}
