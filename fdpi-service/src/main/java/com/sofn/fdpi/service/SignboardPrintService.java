package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.SignboardPrint;
import com.sofn.fdpi.vo.SignboardPrintForm;
import com.sofn.fdpi.vo.SignboardPrintVo;

import java.util.List;
import java.util.Map;

public interface SignboardPrintService extends IService<SignboardPrint> {

    /**
     * 新增标识打印
     */
    SignboardPrint insertSignboardPrint(SignboardPrintForm signboardPrintForm, String createUserId);

    /**
     * 分页查询标识打印
     */
    PageUtils<SignboardPrintVo> listPage(Map<String, Object> params, Integer pageNo, Integer pageSize);

    /**
     * 根据打印ID查看企业名称
     */
    String getCompNameByPrintId(String id);

    /**
     * 更新打印状态为已打印
     */
    SignboardPrint updateStatusByPrintId(String id);


    String getYearMaxSequenceNum(String contractNum);

    void updateSignboardPrint(String id , String contractNum);

    List<SignboardPrintVo> listByApplyId(String applyId);

    void print(String printId);

    int delByCompId(String compId);
}
