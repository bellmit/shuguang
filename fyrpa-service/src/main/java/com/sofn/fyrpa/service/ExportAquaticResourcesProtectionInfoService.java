package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.fyrpa.vo.AquaticResourcesProtectionInfoVoList;
import com.sofn.fyrpa.vo.InformationAuditVo;
import com.sofn.fyrpa.vo.ListVo;

public interface ExportAquaticResourcesProtectionInfoService {

    /**
     * 驳回
     * @param informationAuditVo
     * @return
     */
     Result isNotPass(InformationAuditVo informationAuditVo, String id);

    /**
     * 专家审核通过
     * @param informationAuditVo
     * @return
     */
    Result isPass(InformationAuditVo informationAuditVo, String id);


    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param submitTime
     * @param keyword
     * @param regionCodeArr
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(Integer pageNo, Integer pageSize, String submitTime,
                                                                       String keyword,String []regionCodeArr,User user);

    /**
     *详情页查询
     * @param id
     * @return
     */
    Result selectDetailsById(String id);


    /**
     * 审批意见查询
     * @param id
     * @return
     */
    Result selectInfoAuditList(String id);
}
