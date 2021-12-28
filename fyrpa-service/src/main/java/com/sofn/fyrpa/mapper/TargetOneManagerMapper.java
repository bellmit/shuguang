package com.sofn.fyrpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyrpa.model.TargetOneManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetOneManagerMapper extends BaseMapper<TargetOneManager> {

    /**
     * 通过id查询可编辑
     * @param id
     * @return
     */
    TargetOneManager selectTargetOneManagerById(@Param("id") String id);

    /**
     * 通过属于指标名称查询
     * @param isTargetName
     * @return
     */
    TargetOneManager selectTargetOneManagerByName(@Param("isTargetName") String isTargetName);

    /**
     * 详情页查询
     * @param id
     * @return
     */
    TargetOneManager selectTargetOneManagerDatailsById(@Param("id") String id);

    /**
     * 查询集合
     * @return
     */
    List<TargetOneManager>selectTargetOneManagerList();

    /**
     * 通过指标名称查询数据集
     * @return
     */
    List<TargetOneManager>selectTargetOneManagerByNameList(@Param("targetName") String targetName);


}
