package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.ChangeRecord;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.ChangeType;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/17 14:37
 */

public interface ChangeRecordService extends IService<ChangeRecord> {
     /**
      * @author 文俊云
      * @date 2020-01-19 09:30
      * @param
      * @return
      * @throws
      * @since
      * 保存变更记录，变更流水
      */
     void saveChangeRec(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess);
     /**
      * @author 文俊云
      * @date 2020-01-19 09:31
      * @param
      * @return
      * @throws
      * @since
      * 企业物种下拉
      */
     List<SpeciesSelect> listSpeciesSelect(Map map);
     /**
      * @author 文俊云
      * @date 2020-01-19 09:32
      * @param
      * @return
      * @throws
      * @since
      * 更新变更记录
      */
     void updateChangeRecord(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess);
     /**
      * @author 文俊云
      * @date 2020-01-19 09:34
      * @param
      * @return
      * @throws
      * @since
      * 变更原因
      */
     List<ChangeType> listChangeType();
     /**
      * @author 文俊云
      * @date 2020-01-19 09:35
      * @param
      * @return
      * @throws
      * @since
      * 获取企业信息
      */
     ChangeRecordCompanyVO getCompanyByIdOrName(Map map);
     /**
      * @author 文俊云
      * @date 2020-01-19 09:35
      * @param
      * @return
      * @throws
      * @since
      * 根据变更记录ID查询变更信息
      */
     ChangeRecordDetailVO getChangeRecordDetailById(Map map);
     /**
      * @author 文俊云
      * @date 2020-01-19 09:35
      * @param
      * @return
      * @throws
      * @since
      * 分页查询变更记录列表
      */
     PageUtils<ChangeRecordDetailVO> listChangeRecordDetailVO(Map map, Integer pageNo, Integer pageSize);

     /**
      * @author 文俊云
      * @date 2020-03-17 15:20
      * @param
      * @return
      * @throws
      * @since
      */
     PageUtils<ChangeRecordProcessVo> listProcess(Map<String,Object> map, Integer pageNo, Integer pageSize);

     /**
      * @author 文俊云
      * @date 2020-03-17 15:20
      * @param
      * @return
      * @throws
      * @since
      */
     PageUtils<ChangeRecordProcessVo> listProcessByassembly(Map<String,Object> map, Integer pageNo, Integer pageSize);



     List<ChangeRecordProcessVo> getProcess(Map<String,Object> map);

     /**
      * @author 文俊云
      * @date 2020-01-19 09:32
      * @param
      * @return
      * @throws
      * @since
      * 更新变更记录
      */
     void deleteChangeRecord(ChangeRecord changeRecord, ChangeRecordProcess changeRecordProcess);


     /**
      * 企业查询列表-》详情-》物种变更列表
      * wuXY
      * 2020-11-5 14:20:47
      * @param compId 企业id
      * @param inspectId 年审id
      * @param speciesId 物种id
      * @return 物种变更分页列表
      */
     PageUtils<List<ChangeRecVoInPapersYearVo>> listPageForChangeRecBySpecies(String compId,String inspectId,String speciesId,Integer pageNo,Integer pageSize);

     /**
      * 获取物种变更正在流程中的数量
      */
     Integer getReportNum(String compId,String speId);
     /**
      * 获取物种转移正在流程中的数量
      */
     Integer  getReportNumForTrans(String compId,String speId);

     List<ChangeRecordProcessVo> getProcessByassembly(Map<String,Object> map);

    void cancel(String id);

     List<ChangeRecord> listRecordByCompId(String compId);
}
