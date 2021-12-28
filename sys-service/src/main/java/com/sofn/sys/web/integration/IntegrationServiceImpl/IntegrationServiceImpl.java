package com.sofn.sys.web.integration.IntegrationServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.ExceptionType;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import com.sofn.common.utils.shiro.ShiroUtils;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.IntegrationMapper;
import com.sofn.sys.mapper.SysUserGroupMapper;
import com.sofn.sys.mapper.SysUserMapper;
import com.sofn.sys.mapper.SysUserRoleMapper;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.service.SysUserService;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.LoginVo;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SysUserForm;
import com.sofn.sys.web.integration.IntegrationService.IntegrationService;
import com.sofn.sys.web.integration.IntegrationTokenService.IntegrationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service()
public class IntegrationServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IntegrationService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private IntegrationMapper integrationMapper;
    @Autowired
    private SysUserMapper sysUserMapper;




    @Transactional(rollbackFor = Exception.class)
    public String saveSysUserNew(SysUser sysUser) {
        // 添加用戶
        //1.检查账号是否重复
        checkExists(sysUser);

        //2. 设置常规值
        sysUser.setId(IdUtil.getUUId());
        sysUser.setCreateTime(new Date());
        //sysUser.setCreateUserId(UserUtil.getLoginUserId());
        sysUser.setStatus(SysManageEnum.STATUS_1.getCode());
        sysUser.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());

        // 生成密码和盐值
        String initPassword = sysUser.getInitPassword();
        if (StringUtils.isEmpty(initPassword)) {
            initPassword = IdUtil.getSixNumCode();
        }
        String salt = IdUtil.getUUId();
        String pdMd5 = DigestUtils.md5DigestAsHex(initPassword.getBytes());
        String password = ShiroUtils.sha256(pdMd5, salt);
        sysUser.setPassword(password);
        sysUser.setSalt(salt);

        this.save(sysUser);
        //3.插入用户角色关联表
        List<SysUserRole> sysUserRoles = getSysUserRoleRelations(sysUser);
        if (null != sysUserRoles && sysUserRoles.size() > 0) {
            sysUserRoleMapper.batchSaveSysUserRole(sysUserRoles);
        }

        //4.插入用户角色组关联表
        List<SysUserGroup> sysUserGroups = getSysUserGroupRelations(sysUser);
        if (null != sysUserGroups && sysUserGroups.size() > 0) {
            sysUserGroupMapper.batchSaveSysUserGroup(sysUserGroups);
        }

        return sysUser.getId();
    }

//    修改用户
    @Override
    public void updateSysUser(SysUser sysUser) {
        SysUser sysUser1 = this.baseMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            throw new SofnException("待修改内容不存在");
        }
        sysUser.setUpdateTime(new Date());
       // integrationMapper.updateSysUser(sysUser);

        this.baseMapper.updateById(sysUser);

    }

//    删除用户
    @Override
    public void deleteSysUser(String id) {
        // 1. 查询当前数据是否存在
        SysUser sysUser = this.getById(id);
        if (sysUser == null) {
            throw new SofnException("待删除内容不存在");
        }

        //SysCacheUtils.increaseUserCacheVersion(sysUser.getId());
        //sysUser.preUpdate();
        sysUser.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        sysUserMapper.deleteById(sysUser);
        sysUserRoleMapper.dropUserRolesByUserId(sysUser.getId());
    }


    private void checkExists(SysUser sysUser) {
        if (!RegexUtils.isUsername(sysUser.getUsername())){
            throw new SofnException("账号不符合规范");
        }

        SysUser sysUser2 = sysUserService.findByUserName(sysUser.getUsername());
        if (StringUtils.isBlank(sysUser.getId())) {
            // 新增
            if (sysUser2 != null) {
                throw new SofnException("账号重复");
            }
        } else {
            // 修改
            if (sysUser2 != null && !sysUser2.getId().equals(sysUser.getId())) {
                throw new SofnException("账号重复");
            }
        }

        if (StringUtils.isBlank(sysUser.getOrganizationId())){
            throw new SofnException("请选择组织机构");
        }
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("id",sysUser.getOrganizationId());
        List<SysOrg> orgInfoByOrgLevel = sysOrgService.getInfoByCondition(queryParams);
        if(!CollectionUtils.isEmpty(orgInfoByOrgLevel)){
            SysOrg so =orgInfoByOrgLevel.get(0);
            if (so == null) {
                throw new SofnException("组织机构不存在");
            }
        }
    }

    private List<SysUserRole> getSysUserRoleRelations(SysUser sysUser) {
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        if (sysUser.getRoleList() != null && sysUser.getRoleList().size() > 0) {
            sysUser.getRoleList().forEach(role -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setId(IdUtil.getUUId());
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(role.getId());
                sysUserRoles.add(sysUserRole);
            });
        }
        return sysUserRoles;
    }
    private List<SysUserGroup> getSysUserGroupRelations(SysUser sysUser) {
        List<SysUserGroup> sysUserGroups = new ArrayList<>();
        if (sysUser.getGroupList() != null && sysUser.getGroupList().size() > 0) {
            sysUser.getGroupList().forEach(roleGroup -> {
                SysUserGroup sysUserGroup = new SysUserGroup();
                sysUserGroup.setId(IdUtil.getUUId());
                sysUserGroup.setUserId(sysUser.getId());
                sysUserGroup.setGroupId(roleGroup.getId());
                sysUserGroups.add(sysUserGroup);
            });
        }
        return sysUserGroups;
    }
}
