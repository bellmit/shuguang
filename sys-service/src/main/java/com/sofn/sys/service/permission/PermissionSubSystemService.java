package com.sofn.sys.service.permission;

import com.sofn.common.treeutil.service.TreeService;
import com.sofn.sys.vo.SysSubsystemForm;
import com.sofn.sys.vo.SysSubsystemTreeVo;

import java.util.List;

/**
 * 子系统权限相关
 * @author heyongjie
 * @date 2019/12/19 17:10
 */
public interface PermissionSubSystemService  extends TreeService<SysSubsystemTreeVo> {

    /**
     * 获取用户有的子系统
     *
     * @param subsystemName    子系统名称
     * @return   SysSubsystemTreeVo
     */
    SysSubsystemTreeVo getUserHasSubsystem(String subsystemName);

    /**
     * 获取用户拥有的所有子系统ID
     * @return List<String>
     */
    List<String> getUserHasSubsystemIds();


    /**
     * 登录用户所属系统的APPID集合
     * @return
     */
    List<String> getUserAppIds();


    /**
     * 获取当前登录用户有的AppId集合
     * @param token  当前登录用户token
     * @return  getUserAppIds
     */
    List<String> getUserAppIds(String token);

    /**
     * 获取当前用户有权限的系统列表
     * @return  getUserHaveSystemList
     */
    List<SysSubsystemForm> getUserHaveSystemList();

    /**
     * 校验用户是否可以访问系统
     * @param appIds   需要校验的系统
     */
    void checkUserCanAccessByAppId(List<String> appIds);

    /**
     * 校验用户是否可以访问系统
     * @param ids   需要校验的系统
     */
    void checkUserCanAccessById(List<String> ids);

}
