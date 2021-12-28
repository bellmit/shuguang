package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.fyrpa.vo.AquaticResourcesProtectionInfoVoList;
import com.sofn.fyrpa.vo.InformationAuditVo;

public interface ProvinceAquaticResourcesProtectionInfoService {

    /**
     * 驳回(未经专家与已过专家审核)
     * @param informationAuditVo
     * @return
     */
     Result isNotPass(InformationAuditVo informationAuditVo,String id);

    /**
     * 省级通过(未经专家与已过专家审核)
     * @param informationAuditVo
     * @return
     */
    Result isPass(InformationAuditVo informationAuditVo,String id);


    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param submitTime
     * @param keyword
     * @param regionCodeArr
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(Integer pageNo, Integer pageSize, String submitTime, String keyword, String []regionCodeArr, User user);

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


    /**
     * 查询筛选当前省级已通过的数据
     * @param pageNo
     * @param pageSize
     * @param submitTime
     * @param keyword
     * @param regionCodeArr
     * @return
     */
    Result<IPage<AquaticResourcesProtectionInfoVoList>> selectListIsPass(Integer pageNo, Integer pageSize, String submitTime, String keyword,String []regionCodeArr,User user);

    /**
     * 省级用户上报保护区
     * @param id
     * @param user
     * @return
     */
    Result<String> report(String id, User user);
}
