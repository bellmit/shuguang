package com.sofn.fyem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyem.model.CityAudit;
import com.sofn.fyem.model.ReporManagement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 上报管理Mapper
 * @Author: mcc
 */
@Mapper
public interface ReporManagementMapper extends BaseMapper<ReporManagement> {

    /**
     * 获取上报信息
     * @param params params
     * @return List<ReporManagement>
     */
    List<ReporManagement> getReporManagementByCondition(Map<String,Object> params);


    /**
     * 修改上报信息状态
     * @param params
     * @return
     */
    int updateStatus(Map<String,Object> params);

    /**
     * 删除满足条件的数据
     * @param countParams
     */
    void deleteByParams(Map<String, Object> countParams);
}


