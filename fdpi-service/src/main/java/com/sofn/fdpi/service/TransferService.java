package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.ChangeRecord;
import com.sofn.fdpi.model.ChangeRecordProcess;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Auther: wenjunyun
 * @Date: 2019/12/30 14:37
 */

public interface TransferService extends IService<ChangeRecord> {
     /**
      * @author 文俊云
      * @date 2020-01-13 15:16
      * @param
      * @return
      * @throws
      * @since
      * 根据公司的ID 或者名称查询公司信息
      */
     ChangeRecordCompanyVO getCompanyByIdOrName(Map map);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:16
      * @param
      * @return
      * @throws
      * @since
      * 根据公司的ID 或者名称查询公司信息
      */
     List<ChangeRecordCompanyVO> listCompanyByIdOrName(Map map);


     /**
      * @author 文俊云
      * @date 2020-01-13 15:18
      * @param
      * @return
      * @throws
      * @since
      * 根据公司的ID 获取公司的标志
      */
     List<CompanySignVO> listSignByCompanyId(String companyId);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:18
      * @param
      * @return
      * @throws
      * @since
      * 保存物种转移记录
      */
     void saveTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:18
      * @param
      * @return
      * @throws
      * @since
      * 删除物种转移记录(软删除)
      */
     void deleteTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:20
      * @param
      * @return
      * @throws
      * @since
      * 更新物种转移记录
      */
     void updateTransfer(TransferVO transferVO, ChangeRecordProcess changeRecordProcess);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:20
      * @param
      * @return
      * @throws
      * @since
      * 查询物种转移分页数据
      */
     PageUtils<TransferVO> listTransferVO(Map map, Integer pageNo, Integer pageSize);
     PageUtils<TransferVO> listTransferVO2(Map map, Integer pageNo, Integer pageSize);
     /**
      * @author 文俊云
      * @date 2020-01-13 15:21
      * @param
      * @return
      * @throws
      * @since
      * 查询单条物种转移详情
      */
     TransferVO getTransferVO(Map map);


     PageUtils<TransferProcessVO> listTransferProcessVO(Map<String,Object> map, Integer pageNo, Integer pageSize);

     /**
      *  从流程拿分页数据
      * @param map
      * @param pageNo
      * @param pageSize
      * @return
      */
     PageUtils<TransferProcessVO> listTransferProcessVOByassembly(Map<String,Object> map, Integer pageNo, Integer pageSize);

     List<TransferProcessVO> getTransferProcessVO(Map<String,Object> map);
     List<TransferProcessVO> getTransferProcessVOByassembly(Map<String,Object> map);
     List<Signboard> listSignboardApplyListVo(Map map);



     AreaVO listSpeciesCountVOProvince(Map<String,Object> map, Integer pageNo, Integer pageSize);

     AreaVO listSpeciesCountVOCity(Map<String,Object> map, Integer pageNo, Integer pageSize);

     AreaVO listSpeciesCountVOCountry(Map<String,Object> map, Integer pageNo, Integer pageSize);

     AreaVO listSpeciesCountVORegiones(Map<String,Object> map, Integer pageNo, Integer pageSize);

     List<SpeciesCountVO> listSpeciesCountVOProvince(Map<String,Object> map);

     List<SpeciesCountVO> listSpeciesCountVOCity(Map<String,Object> map);

     List<SpeciesCountVO> listSpeciesCountVOCountry(Map<String,Object> map);

     List<SpeciesCountVO> listSpeciesCountVORegiones(Map<String,Object> map);

     CompVO listCompanyCount(Map<String,Object> map, Integer pageNo, Integer pageSize);

     List<CompCountVO> listCompanyCount(Map<String,Object> map);


    void cancel(String id);

    AreaVO listSpeciesCountVORegionesNew(Map<String, Object> params, Integer pageNo, Integer pageSize);
}
