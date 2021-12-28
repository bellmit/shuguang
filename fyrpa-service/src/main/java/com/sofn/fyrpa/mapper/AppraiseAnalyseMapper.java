package com.sofn.fyrpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fyrpa.model.AppraiseAnalyse;
import com.sofn.fyrpa.vo.AppraiseAnalyseDetailsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraiseAnalyseMapper extends BaseMapper<AppraiseAnalyse> {

    /**
     * 求和求总评分
     * @param resourcesProtectionId
     * @return
     */
    Integer selectSum(@Param("resourcesProtectionId") String resourcesProtectionId);

    /**
     * 评分详情页查看
     * @param targetOneId
     * @return
     */
    List<AppraiseAnalyseDetailsVo> selectAppraiseAnalyseDetails(@Param("targetOneId") String targetOneId,@Param("resourceId") String resourceId);


    /**
     * 一级指标详情页查看
     * @param resourceId
     * @return
     */
    List<AppraiseAnalyseDetailsVo> selectAppraiseAnalyseDetails2(@Param("resourceId") String resourceId);

}
