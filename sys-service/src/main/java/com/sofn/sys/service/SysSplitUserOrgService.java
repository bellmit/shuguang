package com.sofn.sys.service;

import java.util.List;

/**
 * 因为现在的SysOrgService会和SysUserService循环引用，所以把机构中要相互应用的给拆分成一个独立的Service
 * @author heyongjie
 * @date 2020/6/16 14:06
 */
public interface SysSplitUserOrgService {

    /**
     * 刪除机构
     * @param id  待删除机构ID
     */
    void deleteOrg(String id);


    /**
     * 获取用户有的机构ID可访问的机构ID
     * @param userId   用户ID
     * @param appId    系统AppId  可为空
     * @return  List<String>
     */
    List<String> getUserHaveOrgIds(String userId, String appId);


    /**
     * 批量删除机构
     * @param ids   待删除的ID
     */
    void batchDelete(List<String> ids);
}
