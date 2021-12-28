package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.ChangeRecord;
import com.sofn.fdpi.model.ChangeType;
import com.sofn.fdpi.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/30 09:40
 */
public interface ChangeRecordMapper extends BaseMapper<ChangeRecord> {
    void save(ChangeRecord changeRecord);
    List<SpeciesSelect> listSpeciesSelect(Map map);
    void updateChangeRecord(ChangeRecord changeRecord);
    List<ChangeType> listChangeType();
    List<ChangeRecordCompanyVO> getCompanyByIdOrName(Map map);
    List<ChangeRecordCompanyVO> listCompanyByIdOrName(Map map);
    ChangeRecordDetailVO getChangeRecordDetailById(Map map);
    List<ChangeRecordDetailVO> listChangeRecordDetail(Map map);
    List<ChangeRecordDetailVO> listChangeRecordDetail2(Map map);

    /**
     * 证书年审新增中获取物种变更记录列表
     * wuXY
     * 2020-1-6 10:28:51
     * @param map 查询条件(compId,endTime)
     * @return List<ChangeRecVoInPapersYear>
     */
    List<ChangeRecVoInPapersYearVo> listByConditionForInspect(Map<String,Object> map);
    /**
     * 证书年审编辑/明细中获取物种变更记录列表
     * wuXY
     * 2020-1-6 10:28:51
     * @param map 查询条件(inspectId,endTime)
     * @return List<ChangeRecVoInPapersYear>
     */
    List<ChangeRecVoInPapersYearVo> listByInspectConditionForInspect(Map<String,Object> map);

    /**
     * 用于查询该物种是否有在物种转移的流程中
     *xb
     * @param compId 公司id
     * @param speId 物种id
     * @return
     */
    List<CheckVo>  checkOnProcess(@Param("compId")String compId, @Param("speId")String speId);

    /**
     * 用于查询物种变更（变更减少）正在申请中的 数量
     * xb
     * @param compId 公司id
     * @param speId 物种id
     * @return
     */
    Integer  getReportNum(@Param("compId")String compId, @Param("speId")String speId);
    /**
     * 用于查询物种变更（变更减少）正在申请中的 数量
     * xb
     * @param compId 公司id
     * @param speId 物种id
     * @return
     */
    Integer  getReportTranNum(@Param("compId")String compId, @Param("speId")String speId);


//
    List<ChangeRecord>  getAssemblyId(Map<String,Object> map);


    ChangeRecord getChangeRecordbyId(@Param("id")String id);

    /**
     * 查询时间内申请状态不为保存的数量
     * @param changeDateStart
     * @param changeDateEnd
     * @return
     */
    Integer getApplyNum(String changeDateStart,String changeDateEnd);
}
