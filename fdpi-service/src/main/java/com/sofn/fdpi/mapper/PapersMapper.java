package com.sofn.fdpi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sofn.fdpi.model.Papers;
import com.sofn.fdpi.vo.*;
import com.sofn.fdpi.vo.exportBean.ExporrtPapers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 经营许可证，人工繁殖证
 * @Auther: xiaobo
 * @Date: 2019/12/27 09:26
 */
@Repository
public interface PapersMapper extends BaseMapper<Papers> {
    /**
     * 根据证书编号查看证书是否存在
     * @param papersNumber
     * @return
     */
    Papers getPaperByPapersNumber(@Param("papersNumber") String papersNumber);

    /**
     * 根据id获取证书详情
     * @param id
     * @return
     */
    Papers getPapersById(@Param("id") String id);

    /**
     * 移除证书
     * @param id
     * @return
     */
    int removePapers(@Param("id") String id);


    List<PapersListVo> getArtificialPaperList(Map map);


    /**
     * 获取经营证书列表(条件)
     * @param map
     * @return
     */
    List<PapersListVo> getManagementPaperList(Map map);
    /**
     * 获取证书下拉列表
     * wuXy
     * 2019-12-30 11:38:38
     * @param map 证书类型：1：人工繁育；2：驯养繁殖；3：经营利用
     * @return List<SelectVo>
     */
    List<SelectVo> listPapersForSelect(Map<String, Object> map);

    /**
     * 获取证书绑定申请列表
     * wuXY
     * 2019-12-31 11:20:44
     * @param map 查询参数
     * @return 返回list对象
     */
    List<PapersVo> listForBinding(Map<String, Object> map);

    /**
     * 修改证书状态
     * wuXY
     * 2019-12-31 12:21:11
     * @param map 参数
     * @return 0/1
     */
    int updateStatusById(Map<String, Object> map);

    int updateStatusByIds(Map<String, Object> map);

    /**
     * 根据id获取证书信息
     * wuXY
     * 2019-12-31 17:21:11
     * @param papersId 证书id
     * @return EnterpriseForm对象
     */
    PapersBindingVo getPapersInfo(@Param("papersId") String papersId);

    /**
     * 获取证书绑定审核列表
     * wuXY
     * 2020-1-2 10:08:57
     * @param map 查询参数
     * @return list
     */
    List<PapersVo> listForBindingApprove(Map<String,Object> map);
    /**
     * 获取证书绑定审核列表(NEW)
     * wuXY
     * 2020-1-2 10:08:57
     * @param map 查询参数
     * @return list
     */
    List<PapersVo> listForBindingApproveNew(Map<String,Object> map);

    int deleteBindingByPapersId(Map<String, Object> map);

    Papers getPaperByNumber(Map map);
    Papers getPaperByNumber1(Map map);


    int updateIsEnableByPapersType(@Param("papersType") String papersType,@Param("compId") String compId);

    /**
     * 获取打印列表
     * @param map
     * @return
     */
    List<PaperPrintVo> getPrintList(Map map);

    List<LicenceVo> getLicence(Map map);

    /**
     * 通过企业名称获取最近一次证书中企业的信息
     * wuXy
     * 2020-11-4 10:56:59
     * @param compName 企业名称
     * @return 企业对象
     */
    CompInRegisterVo getCompByCompName(@Param("compName") String compName);
    /**
     *
     */
    List<PapersCacheVo> getCache();

    String getTodayMaxApplyNum(String todayStr);

    String getMaxPaperNumber(String paperNumber);

    PapersBindingVo lastMonthPrompting(Map<String, Object> params);

    List<ExporrtPapers> exportPapers(Map<String, Object> params);
}
