package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.model.SysSubsystem;
import com.sofn.sys.model.SysUser;
import com.sofn.sys.service.SysResourceService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.service.SysUserRolePermissionService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.vo.SysResourceForm;
import com.sofn.sys.vo.SysResourceTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户权限相关接口
 *
 * @author heyongjie
 * @date 2019/7/24 9:17
 */
@Service
@Slf4j
public class SysUserRolePermissionServiceImpl implements SysUserRolePermissionService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysSubsystemService sysSubsystemService;

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public Map<String, Set<String>> getUserHasPermission(String userId, String subSystem) {
        // 根据用户ID 和子系统ID 返回当前用户有的权限（不返回角色信息）
        // 1. 根据用户ID查询用户
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            throw new SofnException("用户不存在");
        }
        // 2. 判断用户状态
        String userStatus = user.getStatus();
        if (StringUtils.isBlank(userStatus)) {
            throw new SofnException("当前用户状态信息为空");
        }
        if (!SysManageEnum.STATUS_1.getCode().equals(userStatus)) {
            throw new SofnException("当前用户状态异常");
        }
        // 3. 判断子系统是否存在
        SysSubsystem sysSubsystem = sysSubsystemService.getOne(new QueryWrapper<SysSubsystem>()
            .eq("APP_ID", subSystem)
            .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
        if (sysSubsystem == null) {
            throw new SofnException("子系统不存在");
        }

        // 4. 找到当前用户有的权限
        Map<String, Set<String>> userHasPermission = Maps.newHashMap();
        Set<String> menus;
        Set<String> buttons;


        // 查询当前用户的所有角色
        List<String> roles = UserUtil.getLoginUserRoleIdList();
        if (roles == null) {
            throw new SofnException("当前用户没有角色");
        }
        List<SysResourceForm> userHaveResource = sysResourceService.getResourceByRoleIdsOrRoleCodesOrAppId(roles, Lists.newArrayList(sysSubsystem.getAppId()), true,true);
        if(!CollectionUtils.isEmpty(userHaveResource)){

            menus = userHaveResource.stream().filter(item->SysManageEnum.RESOURCE_MENU.getCode().equals(item.getType()))
                    .map(SysResourceForm::getPermission).collect(Collectors.toSet());

            buttons = userHaveResource.stream().filter(sys -> SysManageEnum.RESOURCE_BUTTON.getCode().equals(sys.getType()))
                    .map(SysResourceForm::getPermission).collect(Collectors.toSet());

            if (!CollectionUtils.isEmpty(menus)) {
                userHasPermission.put("menus", menus);
            }
            if (!CollectionUtils.isEmpty(buttons)) {
                userHasPermission.put("buttons", buttons);
            }
        }

        return userHasPermission;
    }

    private void getAllNode(SysResourceTreeVo userHasResourceTree, List<SysResourceTreeVo> sysResourceTreeVos) {
        if (sysResourceTreeVos != null) {
            if (userHasResourceTree != null) {
                // 添加子节点
                List<SysResourceTreeVo> children = userHasResourceTree.getChildren();
                if (!CollectionUtils.isEmpty(children)) {
                    children.forEach(c -> getAllNode(c, sysResourceTreeVos));
                }
                // 添加本身
                userHasResourceTree.setChildren(null);
                sysResourceTreeVos.add(userHasResourceTree);
            }
        }
    }

}
