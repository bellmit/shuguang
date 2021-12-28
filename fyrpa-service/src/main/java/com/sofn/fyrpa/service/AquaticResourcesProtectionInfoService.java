package com.sofn.fyrpa.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sofn.common.model.Result;
import com.sofn.common.model.User;
import com.sofn.fyrpa.model.AquaticResourcesProtectionInfo;
import com.sofn.fyrpa.vo.*;

public interface AquaticResourcesProtectionInfoService {

    /**
     * 新增
     * @param aquaticResourcesProtectionInfoVo
     * @param environmentResourcesVo
     * @param managerOrgVo
     * @return
     */
    Result addResourcesProtection(AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo, User user);


    /**
     * 保存并上报
     * @param aquaticResourcesProtectionInfoVo
     * @param environmentResourcesVo
     * @param managerOrgVo
     * @return
     */
    Result addAndSbResourcesProtection(AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user);

    /**
     * 修改
     * @param id
     * @param aquaticResourcesProtectionInfoVo
     * @param environmentResourcesVo
     * @param managerOrgVo
     * @return
     */
    Result updateResourcesProtection(String id,AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user);


    /**
     * 修改并上报
     * @param id
     * @param aquaticResourcesProtectionInfoVo
     * @param environmentResourcesVo
     * @param managerOrgVo
     * @return
     */
    Result updateAndSbResourcesProtection(String id,AquaticResourcesProtectionInfoVo aquaticResourcesProtectionInfoVo, EnvironmentResourcesVo environmentResourcesVo, ManagerOrgVo managerOrgVo,User user);

    /**
     * 删除
     * @param id
     * @return
     */
    Result deleteResourcesProtection(String id);

    /**
     * 上报
     * @param id
     * @return
     */
    Result sbResourcesProtection(String id);


    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param submitTime
     * @param keyword
     * @return
     */
   Result<IPage<AquaticResourcesProtectionInfoVoList>> selectPageList(Integer pageNo, Integer pageSize, String submitTime,String keyword,User user);

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
