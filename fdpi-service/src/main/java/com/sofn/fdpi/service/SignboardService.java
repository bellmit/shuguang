package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.Signboard;
import com.sofn.fdpi.model.SignboardApply;
import com.sofn.fdpi.vo.*;

import java.util.List;
import java.util.Map;

public interface SignboardService extends IService<Signboard> {

    /**
     * 新增标识（批量）
     */
    void insertSignboardBatch(SignboardApply signboardApply, String createUserId);

    /**
     * 换发标识
     */
    Signboard changeSignboard(SignboardApplyListVo signboardApplyListVo);

    /**
     * 补发标识
     */
    Signboard reIssue(String signboardId);

    SignboardVo getSignboard(String id);

    SignboardVo getSignboardByCode(String code, String type);

    /**
     * 更新标识
     */
    Signboard updateSignboard(SignboardForm signboardForm);

    PageUtils<SignboardVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    Long listPageTotalCount(Map<String, Object> params);

    List<SignboardVo> listPageData(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 更新标识状态
     */
    Signboard updateStatus(String id, String status);

    /**
     * 更新打印状态
     */
    Signboard updatePrintStatus(String id, String printStatus);

    /**
     * 批量更新打印状态
     */
    Integer updatePrintStatusBatch(List<String> ids, String printStatus);

    /**
     * 激活标识
     */
    void activate(String id, String status);

    /**
     * 激活标识(批量)
     */
    void activateBatch(SignActivForm signActivForm);

    /**
     * 查找补发、换发需要的标识信息
     */
    List<SignboardApplyListVo> getListInfo(String compId, String speId, String queryType);


    PageUtils<SignboardApplyListVo> getListInfoPage(String compId, String speId, Integer pageNo, Integer pageSize);

    /**
     * 标识申请换发、补发时,查看物种利用类型列表和物种来源列表
     */
    Map<String, List<SelectVo>> getSpeSourceAndUtilizeType(String speId, String compId);

    PageUtils<SignboardVoForInspect> listPageForSignboard(String compId, String inspectId, String speciesId, String signboardCode, String signboardType, Integer pageNo, Integer pageSize);

    /**
     * 标识申请换发、补发时,查看物种存量和已配发标识数
     */
    Map<String, Integer> getStockAndAllotted(String speId, String compId, String signboardType);


    List<String> listSignboardCode(String printId);

    /**
     * 完善芯片编码
     */
    void updateChip(String id, String chipAbroad, String chipDomestic);

    List<Signboard> listByPrintId(String printId);

    List<String> delByCodes(List<String> codes);

    void updateDelFlagByPringId(String pringId, String delFlag);

    void updatePrintStatusByPringId(String pringId, String printStatus);

    int delByCompId(String compId);
}
