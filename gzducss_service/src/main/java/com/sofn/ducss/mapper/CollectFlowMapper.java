package com.sofn.ducss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.ducss.model.CollectFlow;
import com.sofn.ducss.vo.UpdateStatusVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public interface CollectFlowMapper extends BaseMapper<CollectFlow> {

    int deleteByPrimaryKey(String id);

    CollectFlow selectByPrimaryKey(String id);

    int updateByPrimaryKey(CollectFlow record);

    Integer deleteCollectflow(Map<String, Object> params);

    void insertOrUpdate(CollectFlow thisDucCollectFlow);

    List<CollectFlow> getCollectFlowList(Map<String, Object> params);

    List<CollectFlow> getWaitForExamineDataForCity(Map<String, Object> params);

    CollectFlow getForAreaId(Map<String, Object> params);

    List<CollectFlow> getWaitForExamineDataForProvince(Map<String, Object> params);

    CollectFlow selectDucCollectFlow(Map<String, Object> map);

    void updateCollectflow(UpdateStatusVo reqVO);

    int updateRefreshSuperCollectFlowDataById(@Param("id") String id, @Param("areaId")  String areaId, @Param("year") String year,
                                              @Param("ids") List<String> ids);

    List<String> selectAreaIdByIdsAndStatus(@Param("year") String year, @Param("areaIds") String areaIds, @Param("status") String status);

    Integer countReported(@Param("year") String year,@Param("status") String status, @Param("ids") String ids);

    /**
     * 查询全国任何一个县下有无  上报 ，已读，通过的数据
     * @param parms
     * @return
     */
    Integer selectPass(HashMap<String, Object> parms);

    /**
     * 根据年份,状态集合,区域获取通过或上报的数量
     *
     * @param   map 参数map
     *
     * @return  boolean 布尔类型
     */
    Integer countReportAndAudit(Map<String, Object> map);

    /**
     * 根据区域id和状态获取符合条件的县级areaId
     *
     * @param   map 参数map
     *
     * @return  boolean 布尔类型
     */
    List<String> getCountyByCondition(Map<String, Object> map);

    /**
     * 根据年份和状态获取已审核通过的省id集合
     *
     * @param   status 参数map
     * @param   level 参数map
     * @param   year 参数map
     *
     * @return  List<String>
     */
    List<String> getProvinceListByCondition(@Param("status") String status,@Param("level") String level,@Param("year") String year);
}