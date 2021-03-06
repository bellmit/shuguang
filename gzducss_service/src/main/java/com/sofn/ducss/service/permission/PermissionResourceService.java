package com.sofn.ducss.service.permission;

import com.sofn.ducss.util.treeutil.service.TreeService;
import com.sofn.ducss.vo.SysResourceTreeVo;

/**
 * 资源权限相关
 * @author heyongjie
 * @date 2019/12/19 18:09
 */
public interface PermissionResourceService extends TreeService<SysResourceTreeVo> {

    /**
     * 获取用户有的SysResourceTreeVo菜单
     * @param appId   系统ID
     * @param resourceName   资源名称
     * @return  SysResourceTreeVo
     */
    SysResourceTreeVo getUserHasResourceTree(String appId, String resourceName);


}
