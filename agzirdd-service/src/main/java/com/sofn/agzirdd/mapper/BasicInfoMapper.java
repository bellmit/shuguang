package com.sofn.agzirdd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.agzirdd.model.BasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 入侵生物监测点基础信息Mapper
 * @Author: mcc
 * @Date: 2020\3\5 0005
 */
@Mapper
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {

    /**
     * 获取满足条件的入侵生物监测点基础信息List
     * @param params 监测点名称,省id,市id,县id,状态,开始时间,结束时间
     * @return List<BasicInfo>
     */
    List<BasicInfo> getBasicInfoByCondition(Map<String,Object> params);

    /**
     * 获取指定id的入侵生物监测点基础信息
     * @param id id
     * @return BasicInfo
     */
    BasicInfo getBasicInfoById(String id);

    /**
     * 修改监测点基本信息状态
     * @param params params
     */
    void updateStatus(Map<String,Object> params);

}