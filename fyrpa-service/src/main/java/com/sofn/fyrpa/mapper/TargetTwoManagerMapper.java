package com.sofn.fyrpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sofn.fyrpa.model.TargetTwoManager;
import com.sofn.fyrpa.vo.TargetManagerListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TargetTwoManagerMapper extends BaseMapper<TargetTwoManager> {

    /**
     * 通过id查询
     * @param id
     * @return
     */
    TargetTwoManager selectTargetTwoManagerById(@Param("id") String id);

    /**
     * 分页查询list
     * @param page
     * @param targetName
     * @param startTime
     * @param endTime
     * @param targetType
     * @return
     */
    IPage<TargetManagerListVo> selectListData(Page<TargetManagerListVo> page, @Param("targetName") String targetName, @Param("startTime")String startTime,
                                              @Param("endTime")String endTime,@Param("targetType") String targetType);


    List<TargetTwoManager> selectTargetTwoList();

    /**
     * 通过一级id查询指标集合
     * @param targetOneManagerId
     * @return
     */
    List<TargetTwoManager> selectTargetTwoListByOneId(@Param("targetOneManagerId")String targetOneManagerId);

    /**
     * 通过指标名称查询指标
     * @param targetName
     * @return
     */
    List<TargetTwoManager> selectTargetTwoManagerByName(@Param("targetName") String targetName);
}