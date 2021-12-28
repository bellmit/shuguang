package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.model.Transfer;
import com.sofn.fdpi.model.TransferSign;
import com.sofn.fdpi.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/30 09:40
 */
public interface TransferMapper extends BaseMapper<Transfer> {
    void saveTransfer(TransferVO transferVO);
    List<CompanySignVO> listSignByCompanyId(Map map);
    void saveTransferSpecies(Map<String,Object> map);
    void updateTransfer(TransferVO transferVO);
    void deleteTransferSpecies(Map<String,Object> map);
    List<SpeciesSelect> listSpeciesSelect(String id);
    List<TransferVO> listTransferVO(Map<String,Object> map);
    List<TransferVO> listTransferVO2(Map<String,Object> map);
    TransferVO getTransferVO(Map<String,Object> map);
    void saveTransferProcess(ChangeRecordProcess changeRecordProcess);
    SpeciesSelect getSpeciesSelect(Map<String,Object> map);
    void updateSign(Map<String,Object> map);
    List<TransferProcessVO> listTransferProcessVO(Map<String,Object> map);
    List<TransferProcessVO> getTransferProcessVO(Map<String,Object> map);
    List<FileManageVo> listPapersFilesInfo(Map<String,Object> map);
    List<Signboard> listSignboardApplyListVo(Map<String,String[]> map);
    List<SpeciesCountVO> listSpeciesCountVOProvince(Map map);
    List<SpeciesCountVO> listSpeciesCountVOCity(Map map);
    List<SpeciesCountVO> listSpeciesCountVOCountry(Map map);
    List<SpeciesCountVO> listSpeciesCountVORegiones(Map map);

    SpeciesCountVO sumSpeciesCountVOProvince(Map map);
    SpeciesCountVO sumSpeciesCountVOCity(Map map);
    SpeciesCountVO sumSpeciesCountVOCountry(Map map);
    SpeciesCountVO sumSpeciesCountVORegiones(Map map);

    List<CompCountVO> listCompanyCount(Map map);
    CompCountVO sumCompanyCount(Map map);

    void saveTransferSign(Map map);
    void deleteTransferSign(Map map);
    List<TransferSign> listTransferSign(Map map);

    /**
     *
     * @param compId
     * @param speId
     * @return
     */
    List<CheckVo> checkOnProcess(@Param("compId") String compId, @Param("speId") String speId);

    /**
     *查询当前企业的一开始流程的 业务主键id
     */
    List<TransferVO> getAssemblyId(Map map);
//
    TransferVO     getInAssemblyId(@Param("id")String id);

    String getTodayMaxApplyNum(String todayStr);

    String getAddCompIdById(String id);

    List<SpeciesCountVO> listSpeciesCountVORegionesNew(Map<String, Object> params);
}
