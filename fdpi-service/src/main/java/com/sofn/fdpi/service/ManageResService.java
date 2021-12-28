package com.sofn.fdpi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sofn.common.utils.PageUtils;
import com.sofn.fdpi.model.ManageRes;
import com.sofn.fdpi.vo.ForfeiProcessFrom;
import com.sofn.fdpi.vo.ManageResInfoVo;
import com.sofn.fdpi.vo.ResProcessFrom;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Description:
 * @Auther: Bay
 * @Date: 2020/1/3 16:14
 */
public interface ManageResService extends IService<ManageRes> {
    PageUtils<ManageRes> getManageResList(Map<String, Object> map, int pageNo, int pageSize);

    String saveManageRes(ManageResInfoVo manageResInfoVo);

    ManageRes getManageResInfo(String id);

    void updateStatus(String id, int status);

    String deleteManageResInfo(String id);

    String updateManageResInfo(ManageResInfoVo manageResInfoVo);

    @Transactional
    void insertManageResProcess(ResProcessFrom resProcessFrom);

    void pass(ResProcessFrom resProcessFrom);
    void back(ResProcessFrom resProcessFrom);
}
