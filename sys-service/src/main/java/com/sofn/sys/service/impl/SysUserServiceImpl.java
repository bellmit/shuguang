package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.constants.Constants;
import com.sofn.common.exception.ExceptionType;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.*;
import com.sofn.common.utils.shiro.ShiroUtils;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.*;
import com.sofn.sys.model.*;
import com.sofn.sys.service.*;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.*;
import com.sofn.sys.vo.Excel.ImportSysUserVo;
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

/**
 * @author quzj
 */
@SuppressWarnings("ALL")
@Slf4j
@Service(value = "sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserGroupMapper sysUserGroupMapper;
    @Autowired
    private SysUserSystemMapper userSystemMapper;

    @Autowired
    private SysRoleGroupMapper sysRoleGroupMapper;

    @Autowired
    private SysRoleRoleGroupMapper sysRoleRoleGroupMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysOrgAgentService sysOrgAgentService;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private PermissionRoleService permissionRoleService;

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private SysSubsystemService sysSubsystemService;

  /**
   * 根据用户名查询用
   * @param username 用户名
   * @return SysUser
   */
    @Override
    public SysUser findByUserName(String username) {
        List<SysUser> sysUsers = this.baseMapper.selectList(new QueryWrapper<SysUser>().eq("username", username));
        if(CollectionUtils.isEmpty(sysUsers)){
           return null;
        }

        List<SysUser> collect = sysUsers.stream().filter(item -> !BoolUtils.Y.equals(item.getDelFlag())).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(collect)){
            return null;
        }

        return collect.get(0);
    }

    @Override
    public Auth execuLogin(LoginVo loginVo,boolean isApp,String type) {
        //执行登录前先校验登录失败次数,判定是否限制状态
        Boolean isCanLogin = SysCacheUtils.getFailCountByUserName(loginVo.getUsername());
        if (!isCanLogin){
            List<SysUser> sysUsers = this.baseMapper.selectList(new QueryWrapper<SysUser>().eq("username", loginVo.getUsername()).eq("del_flag","N"));
            if(sysUsers.size() != 1){
                //username只能查出一个用户,为空或多个都是异常情况
                throw new SofnException("用户数据异常！");
            }
            //修改用户账号状态(0:限制,1:正常,2:禁用,3:注销)
//            throw new SofnException("当前用户异常(用户状态：" + SysManageEnum.getEnumDes("STATUS","0") + ")");
            /**
             * 修改用户登录账户锁定提示
             * wb
             */
              throw new SofnException("提示：您的账号密码输入错误次数过多，系统已将账号锁定，请2小时之后再试。");

        }

        // 执行登录
        // 1.校验验证码， 如果不是app登录需要验证验证码
        if(!isApp){
            if (StringUtils.isBlank(loginVo.getCaptchaId()) || StringUtils.isBlank(loginVo.getCaptcha())) {
                throw new SofnException(ExceptionType.CAPTCHA_CORRECT);
            }

            // 获取redis中缓存的验证码
            String cacheCaptcha = SysCacheUtils.getCaptchaById(loginVo.getCaptchaId());

            // 比较验证码是否相同
            if (!StringUtils.equalsIgnoreCase(loginVo.getCaptcha(), cacheCaptcha)) {
                // TODO 默认是sofn 这里上线的时候需要取消 如果不相等
                cacheCaptcha = "sofn";
                if (!StringUtils.equalsIgnoreCase(loginVo.getCaptcha(), cacheCaptcha)) {
                    throw new SofnException(ExceptionType.CAPTCHA_CORRECT);
                }
            }
        }

        // 2. 查询用户
        SysUser user = this.findByUserName(loginVo.getUsername());
        if (user == null) {
            throw new SofnException(ExceptionType.USER_NOT_EXIST);
        }

        if(!SysManageEnum.STATUS_1.getCode().equals(user.getStatus())){
            throw new SofnException("当前用户异常(用户状态：" + SysManageEnum.getEnumDes("STATUS",user.getStatus()) + ")");
        }

        // 3. 校验密码
        String saltPassword = ShiroUtils.sha256(loginVo.getPassword(),user.getSalt());
        if (!StringUtils.equals(saltPassword, user.getPassword())){
            //获取登录失败次数并更新至缓存redis
            Integer userFailCount = SysCacheUtils.getUserFailCountByCache(loginVo.getUsername());
            SysCacheUtils.setFailCountToCache(userFailCount, loginVo.getUsername());
            //校验失败抛异常
            /**
             * 新增用户错误连续3次提示
             * wb
             */
            Integer count =  userFailCount+1;
            if(count>=3 && count<5){
                throw new SofnException("提示：您的账号密码连续输入错误已达"+count+"次，请确认后再试。连续错误达5次后系统将会锁定该账号。");
            }else if(count==5){
                throw new SofnException("提示：您的账号密码连续错误5次，系统已将账号锁定，请2小时之后再试。");
            }else {
            throw new SofnException(ExceptionType.PASSWORD_ERROR);
            }
        }

        // 4. 获取用户组织机构详情
        SysUserForm sysUserForm = new SysUserForm();
        SysOrganization org = null;
        if (StringUtils.isNotBlank(user.getOrganizationId())){
          SysOrg sysOrg = sysOrgService.getOrgById(user.getOrganizationId());
          if(sysOrg == null){
            throw new SofnException("当前用户异常（机构不存在）");
          }

          sysUserForm.setRegionCode(sysOrg.getRegionLastCode());
          org = new SysOrganization();
          sysUserForm.setRegionCode(sysOrg.isThirdOrganization()?sysOrg.getAddressLastCode():sysOrg.getRegionLastCode());
          List<String> regionList = sysRegionService.getFormatIdsOrNamesByCode(sysUserForm.getRegionCode(), "NAME");
          if (!CollectionUtils.isEmpty(regionList)){
            sysUserForm.setRegionNames(StringUtils.join(regionList.toArray()));
          }
          BeanUtils.copyProperties(sysOrg,org);
        }else {
          throw new SofnException("当前用户未分配组织机构");
        }

        BeanUtils.copyProperties(user,sysUserForm);

        // 5. 查询用户角色及角色码
        Set<String> roleList = new HashSet<>();
        Set<String> roleCodeList = new HashSet<>();
        Set<String> subsystemIds = Sets.newHashSet();

        List<SysRole> roles =loadRolesByUserId(user.getId());
        if(CollectionUtils.isEmpty(roles)){
            throw new SofnException("当前用户没有任何角色");
        }
        roles.forEach(sysRole -> {
            if (StringUtils.isNotBlank(sysRole.getRoleName())){
                roleList.add(sysRole.getRoleName());
            }

            if (StringUtils.isNotBlank(sysRole.getRoleCode())){
                roleCodeList.add(sysRole.getRoleCode());
            }

            if(StringUtils.isNotBlank(sysRole.getSubsystemId())){
                subsystemIds.add(sysRole.getSubsystemId());
            }
        });

        sysUserForm.setOrganizationName(org.getOrganizationName());
        sysUserForm.setRoleNames(StringUtils.join(roleList,","));
        sysUserForm.setRoleCodes(roleCodeList);

        // 查询用户能够访问的子系统并且放置在缓存中
        // 如果是超级管理员直接查询所有
        List<SysSubsystem> list;
        if(roleCodeList.contains(SysManageEnum.DEVELOPER_ROLE_CODE.getCode())){
            list = sysSubsystemService.getAllSubsystemList();
        }else{
            list = sysSubsystemService.getSubsystemListByIds(subsystemIds);
        }

        Set<String> realCanShowSubsystemId = Sets.newHashSet();
        Set<String> appIds = Sets.newHashSet();
        if(!CollectionUtils.isEmpty(subsystemIds)){
            if(!CollectionUtils.isEmpty(list)){
                list.forEach(item->{
                    realCanShowSubsystemId.add(item.getId());
                    appIds.add(item.getAppId());
                });
            }
        }

        // 生成token
        String token = "";
        if(isApp){
            token = JwtUtils.generateToken(loginVo.getUsername());
        }else{
            token = JwtUtils.generateToken(loginVo.getUsername(),type);
        }

        // 缓存用户信息
        SysCacheUtils.cacheUserInfo(token, sysUserForm, roles, loginVo, org);
        SysCacheUtils.cacheUserCanShowSystem(token,realCanShowSubsystemId, appIds, sysUserForm.getUsername() );
        //登录成功后清除缓存中的登录失败次数释放资源
        SysCacheUtils.delFailCountInCache(loginVo.getUsername());

        return new Auth(token, sysUserForm);
    }


    @Override
    public PageUtils<SysUserForm> findAllUserList(Map<String, Object> params, Integer pageNo, Integer pageSize) {

        PageHelper.offsetPage(pageNo, pageSize);
        List<SysUser> sysUsers = sysUserMapper.getSysUserByCondition(params);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<SysUser>(sysUsers);
        PageInfo<SysUserForm> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sysUserPageInfo, pageInfo);
        List<SysUserForm> userForm = getUserForm(sysUsers, pageInfo);
        pageInfo.setList(userForm);


        return PageUtils.getPageUtils(pageInfo);
    }

    /**
     * 将SysUsers转为SysUserForms
     * @param sysUsers
     * @param pageInfo
     * @return
     */
    private List<SysUserForm> getUserForm(List<SysUser> sysUsers, PageInfo<SysUserForm> pageInfo) {
        if (!CollectionUtils.isEmpty(sysUsers)) {
            log.info("查询用户列表，开始执行");
            List<String> userIds = sysUsers.stream().map(SysUser::getId).collect(Collectors.toList());
            List<String> orgIds = sysUsers.stream().map(SysUser::getOrganizationId).collect(Collectors.toList());
            // 获取机构信息
            Map<String,Object> queryParams = Maps.newHashMap();
            queryParams.put("ids",orgIds);
            List<SysOrg> sysOrgs = sysOrgService.getInfoByCondition(queryParams);
            if (CollectionUtils.isEmpty(sysOrgs)) {
                log.warn("没有任何用户有机构信息，用户信息：【{}】", sysUsers);
            } else {
                sysOrgs = sysOrgs.stream().filter(sysOrganization -> !"Y".equals(sysOrganization.getDelFlag())).collect(Collectors.toList());
            }
            // 获取用户有的角色信息
            List<SysUserRole> sysUserHasRoleList = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().in("USER_ID", userIds));
            List<SysUserGroup> sysUserHasGroups = sysUserGroupMapper.selectList(new QueryWrapper<SysUserGroup>().in("USER_ID", userIds));
            // 查询角色和角色组详细信息
            List<SysRole> sysRoles = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(sysUserHasRoleList)) {
                List<String> roleIds = sysUserHasRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
                sysRoles = sysRoleMapper.selectList(
                        new QueryWrapper<SysRole>().in("ID", roleIds)
                        .ne("DEL_FLAG", "Y")
                        .eq("status",SysManageEnum.STATUS_1.getCode())
                );
            }
            List<SysRoleGroup> sysRoleGroups = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(sysUserHasGroups)) {
                List<String> groupIds = sysUserHasGroups.stream().map(SysUserGroup::getGroupId).collect(Collectors.toList());
                sysRoleGroups = sysRoleGroupMapper.selectList(new QueryWrapper<SysRoleGroup>().in("ID", groupIds).ne("DEL_FLAG", "Y"));
            }

            // 组装信息
            List<SysUserForm> sysUserFormList = new ArrayList<>();
            List<SysRole> finalSysRoles = sysRoles;
            List<SysRoleGroup> finalSysRoleGroups = sysRoleGroups;
            List<SysOrgVo> finalSysOrganizations = sysOrgService.getVoList(sysOrgs);
            sysUsers.forEach(sysUser -> {
                if (sysUser != null) {
                    SysUserForm sysUserForm = new SysUserForm();
                    BeanUtils.copyProperties(sysUser, sysUserForm);
                    // roleIds    groupIds   roleNames   roleCodes    resourceList
                    // 获取当前用户的机构名称
                    String orgId = sysUser.getOrganizationId();
                    if (StringUtils.isBlank(orgId)) {
                        log.error("用户机构字段为空，用户：【{}】", sysUser);
                        throw new SofnException(String.format("用户%d没有机构信息", sysUser.getId()));
                    }
                    if (CollectionUtils.isEmpty(finalSysOrganizations)) {
                        sysUserForm.setOrganizationName("");
                    } else {
                        Optional<SysOrgVo> first = finalSysOrganizations.stream().filter(sysOrganization -> sysOrganization.getId().equals(orgId)).findFirst();
                        if (first.isPresent()) {
                            sysUserForm.setOrganizationName(first.get().getOrganizationName());
                            // 设置区划信息
                            first.get().getRegionFullCode();
                            sysUserForm.setRegionCode(first.get().getRegionFullCode()  == null ? "" : IdUtil.getStrIdsByList(first.get().getRegionFullCode()));
                            sysUserForm.setRegionNames(first.get().getRegionFullName() == null ? "" : IdUtil.getStrIdsByList(first.get().getRegionFullName()));
                        } else {
                            log.warn("用户【{}】没有机构信息，用户机构ID：【{}】",sysUser.getUsername(), sysUser.getOrganizationId());
                            sysUserForm.setOrganizationName("");
                        }
                    }
                    // 设置角色信息
                    List<String> roleIds = sysUserHasRoleList.stream().filter(sysUserRole -> sysUserRole.getUserId().equals(sysUser.getId()))
                            .collect(Collectors.toList())
                            .stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
                    List<SysRole> sysRoles1 = finalSysRoles.stream().filter(sysRole -> roleIds.contains(sysRole.getId())).collect(Collectors.toList());
                    List<String> setRoleIds = Lists.newArrayList();
                    List<String> setRoleNames = Lists.newArrayList();
                    List<String> setRoleCodes = Lists.newArrayList();
                    if (!CollectionUtils.isEmpty(sysRoles1)) {
                        sysRoles1.forEach(sysRolesBean -> {
                            setRoleIds.add(sysRolesBean.getId());
                            setRoleNames.add(sysRolesBean.getRoleName());
                            setRoleCodes.add(sysRolesBean.getRoleCode());
                        });
                    }
                    sysUserForm.setRoleIds(IdUtil.getStrIdsByList(setRoleIds));
                    sysUserForm.setRoleNames(IdUtil.getStrIdsByList(setRoleNames));
                    // 设置角色组信息
                    if (!CollectionUtils.isEmpty(sysUserHasGroups)) {
                        List<String> groupIds = sysUserHasGroups.stream()
                                .filter(sysUserGroup -> sysUserGroup.getUserId().equals(sysUser.getId()))
                                .collect(Collectors.toList())
                                .stream().map(SysUserGroup::getGroupId)
                                .collect(Collectors.toList());
                        List<SysRoleGroup> sysRoleGroupList = finalSysRoleGroups.stream().filter(sysRoleGroup -> groupIds.contains(sysRoleGroup.getId())).collect(Collectors.toList());
                        List<String> roleGroupList = sysRoleGroupList.stream().map(SysRoleGroup::getId).collect(Collectors.toList());
                        sysUserForm.setGroupIds(IdUtil.getStrIdsByList(roleGroupList));
                    }
                    sysUserFormList.add(sysUserForm);
                }
            });
            log.info("查询用户列表，结束执行");
            return sysUserFormList;
        }
        return Lists.newArrayList();
    }

    /**
     * 功能描述: <br>
     * 〈根据用户ID查询出用户所有的角色ID集合>
     *
     * @Param: [sysUserForm]
     * @Return: void
     * @Author: luoyh
     * @Date: 2019/10/10/010 16:40
     */
    public void getRoleForm(SysUserForm sysUserForm) {
        //通过userRole获取的roleId集合
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.getUserRoleByUserId(sysUserForm.getId());
        List<String> roleIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sysUserRoles)) {
            sysUserRoles.forEach((sysUserRole -> {
                if (sysUserRole != null) {
                    roleIds.add(sysUserRole.getRoleId());
                }
            }));
        }
        //通过UserGroup获取的roleId集合
        List<SysUserGroup> ugList = sysUserGroupMapper.getUserGroupsByUserId(sysUserForm.getId());
        List<String> idsList = new ArrayList<>();
        if (ugList.size() > 0) {
            for (SysUserGroup sug : ugList) {
                List<SysRoleRoleGroup> rrpList = sysRoleRoleGroupMapper.getListByGroupId(sug.getGroupId());
                if (rrpList.size() > 0) {
                    for (SysRoleRoleGroup sysRoleRoleGroup : rrpList) {
                        idsList.add(sysRoleRoleGroup.getRoleId());
                    }
                }
            }
        }
        roleIds.remove(idsList);
        roleIds.addAll(idsList);

        // 去重
        Set<String> roleIdSets = Sets.newHashSet(roleIds);

        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        if (roleIdSets != null && roleIdSets.size() > 0) {
            for (String roleId : roleIdSets) {
                SysRole role = sysRoleMapper.selectById(roleId);
                if (role != null) {
                    roleNameList.add(role.getRoleName());
                    roleIdList.add(roleId);
                }
            }
            sysUserForm.setRoleIds(StringUtils.join(roleIdList, ","));
            sysUserForm.setRoleNames(StringUtils.join(roleNameList, ","));
        }
    }

  public void getUserSystemList(String userId) {
    //通过userRole获取的roleId集合
    List<SysUserRole> sysUserRoles = sysUserRoleMapper.getUserRoleByUserId(userId);
    List<String> roleIds = new ArrayList<>();
    if (!CollectionUtils.isEmpty(sysUserRoles)) {
      sysUserRoles.forEach((sysUserRole -> {
        if (sysUserRole != null) {
          roleIds.add(sysUserRole.getRoleId());
        }
      }));
    }

    //通过UserGroup获取的roleId集合
    List<SysUserGroup> ugList = sysUserGroupMapper.getUserGroupsByUserId(userId);
    List<String> idsList = new ArrayList<>();
    if (ugList.size() > 0) {
      for (SysUserGroup sug : ugList) {
        List<SysRoleRoleGroup> rrpList = sysRoleRoleGroupMapper.getListByGroupId(sug.getGroupId());
        if (rrpList.size() > 0) {
          for (SysRoleRoleGroup sysRoleRoleGroup : rrpList) {
            idsList.add(sysRoleRoleGroup.getRoleId());
          }
        }
      }
    }
    roleIds.remove(idsList);
    roleIds.addAll(idsList);

    // 去重
    Set<String> roleIdSets = Sets.newHashSet(roleIds);

    List<String> roleNameList = new ArrayList<>();
    List<String> roleIdList = new ArrayList<>();
    if (roleIdSets != null && roleIdSets.size() > 0) {
      for (String roleId : roleIdSets) {
        SysRole role = sysRoleMapper.selectById(roleId);
        if (role != null) {
          roleNameList.add(role.getRoleName());
          roleIdList.add(roleId);
        }
      }
    }
  }

    @Override
    public PageUtils<SysUserForm> findUserListBySubsystem(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        // 如果要查询代理机构的数据需要将实际的代理机构查询出来
        String proxyUser = (String) params.get("proxyUser");
        if (BoolUtils.Y.equals(proxyUser)) {
            String organizationLevel = (String) params.get("organizationLevel");
            Map<String,Object> queryParams = Maps.newHashMap();
            queryParams.put("thirdOrg",organizationLevel);
            List<SysOrg> orgInfoByOrgLevel = sysOrgService.getInfoByCondition(queryParams);
            List<String> orgIds = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(orgInfoByOrgLevel)) {
                orgIds = orgInfoByOrgLevel.stream().map(SysOrg::getId).collect(Collectors.toList());
            }
            // 查询代理信息
            String subsystemId = (String) params.get("subsystemId");
            if (StringUtils.isBlank(subsystemId)) {
                // 如果没有传那么显示所有代理关系
                subsystemId = SysManageEnum.SUBSYSTEM_ROOT.getCode();
            }
            // 获取所有的代理机构ID
            List<String> proxyOrgIds = sysOrgAgentService.selectOrgAgentByOrgId(Lists.newArrayList(subsystemId), orgIds, "");
            params.put("proxyOrgs", proxyOrgIds);
        }
        List<SysUser> sysUsers = sysUserMapper.getUserListBySubsystem(params);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<SysUser>(sysUsers);
        List<SysUserForm> sysUserFormList = new ArrayList<>();
        PageInfo<SysUserForm> pageInfo = new PageInfo<>();
        BeanUtils.copyProperties(sysUserPageInfo, pageInfo);
        for (SysUser sysUser : sysUsers) {
            SysUserForm sysUserForm = new SysUserForm();
            sysUserForm.setSex(sysUser.getSex());
            BeanUtils.copyProperties(sysUser, sysUserForm);
            getRoleForm(sysUserForm);
            // 获取组织
            Map<String,Object> queryParams = Maps.newHashMap();
            queryParams.put("id",sysUser.getOrganizationId());
            List<SysOrg> orgInfoByOrgLevel = sysOrgService.getInfoByCondition(queryParams);
            if(!CollectionUtils.isEmpty(orgInfoByOrgLevel)){
                SysOrg sysOrganization = orgInfoByOrgLevel.get(0);
                if (sysOrganization != null && StringUtils.isNotBlank(sysOrganization.getOrganizationName())) {
                    sysUserForm.setOrganizationName(sysOrganization.getOrganizationName());
                }
            }



            sysUserFormList.add(sysUserForm);
        }
        pageInfo.setList(sysUserFormList);

        return PageUtils.getPageUtils(pageInfo);
    }

    private String getRoleNames(List<SysRole> roleList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (SysRole sysRole : roleList) {
            stringBuilder.append(sysRole.getRoleName() + ",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveSysUser(SysUser sysUser) {
        // 添加用戶
        //1.检查账号是否重复
        checkExists(sysUser);

        //2. 设置常规值
        sysUser.setId(IdUtil.getUUId());
        sysUser.setCreateTime(new Date());
        sysUser.setCreateUserId(UserUtil.getLoginUserId());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysUser(SysUser sysUser) {
        // 修改用户
        SysUser sysUser1 = this.baseMapper.selectById(sysUser.getId());
        if (sysUser1 == null) {
            throw new SofnException("待修改内容不存在");
        }
        //检查用户名是否重复
        checkExists(sysUser);

        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUserId(UserUtil.getLoginUserId());
        this.baseMapper.updateById(sysUser);

        //先解除用户跟角色的关系,
        sysUserRoleMapper.dropUserRolesByUserId(sysUser.getId());
        sysUserGroupMapper.dropUserGroupsByUserId(sysUser.getId());
        //再重新绑定用户角色关系
        SysCacheUtils.increaseUserCacheVersion(sysUser.getId());
        List<SysUserRole> sysUserRoles = getSysUserRoleRelations(sysUser);
        if (null != sysUserRoles && sysUserRoles.size() > 0) {
            sysUserRoleMapper.batchSaveSysUserRole(sysUserRoles);
        }
        //4.插入用户角色组关联表
        List<SysUserGroup> sysUserGroups = getSysUserGroupRelations(sysUser);
        if (null != sysUserGroups && sysUserGroups.size() > 0) {
            sysUserGroupMapper.batchSaveSysUserGroup(sysUserGroups);
        }
        // 如果自己改变自己
        if (sysUser.getId().equals(UserUtil.getLoginUserId())) {
            // 重新设置 用户登录机构
            String token = UserUtil.getLoginToken();
            if (!StringUtils.isBlank(token)) {
                // 重新设置 userName
                redisHelper.hset(Constants.UserSession.userNameToken, sysUser.getUsername(), token);
                // 重新设置用户
                redisHelper.hset(token, Constants.UserSession.user, JsonUtils.obj2json(sysUser));
                // 重新设置username
                redisHelper.hset(token, Constants.UserSession.userName, sysUser.getUsername());
                // 重新设置机构
                redisHelper.hset(token, Constants.UserSession.userOrganizationId, sysUser.getOrganizationId());
                // 重新设置角色
                List<SysRole> roles = loadRolesByUserId(sysUser.getId());
                redisHelper.hset(token, Constants.UserSession.userRoles, JsonUtils.obj2json(roles));
            }
        } else {
            // 更改别人信息直接删除token 让用户重新登录   hyj  20200407  将方法抽取到Common中
            UserUtil.delUserCache(Lists.newArrayList(sysUser.getUsername()));
        }

    }

    /**
     * 检查是否重复
     *
     * @param sysUserForm 待检查信息
     * @return false 没有重复
     */
    private void checkExists(SysUser sysUser) {
        if (!RegexUtils.isUsername(sysUser.getUsername())){
            throw new SofnException("账号不符合规范");
        }

        SysUser sysUser2 = this.findByUserName(sysUser.getUsername());
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchSave(List<SysUser> sysUsers) {
        if (!CollectionUtils.isEmpty(sysUsers)) {
            int addNum = 0;
            List<SysUser> sysUserList = Lists.newArrayList();
            // 每100条添加一次
            for (SysUser sysUser : sysUsers) {
                if (sysUser != null) {
                    sysUserList.add(sysUser);
                    addNum++;
                    if (addNum % 100 == 0) {
                        sysUserMapper.batchSave(sysUserList);
                        sysUserList.clear();
                        addNum = 0;
                    }
                }
            }
            if (!CollectionUtils.isEmpty(sysUserList)) {
                sysUserMapper.batchSave(sysUserList);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysUser(String id) {
        // 1. 查询当前数据是否存在
        SysUser sysUser = this.getById(id);
        if (sysUser == null) {
            throw new SofnException("待删除内容不存在");
        }

        SysCacheUtils.increaseUserCacheVersion(sysUser.getId());
        sysUser.preUpdate();
        sysUser.setDelFlag(SysManageEnum.DEL_FLAG_Y.getCode());
        sysUserMapper.deleteById(sysUser);
        sysUserRoleMapper.dropUserRolesByUserId(sysUser.getId());
    }

    @Override
    public List<SysUserForm> getUserByCondition(Map<String, Object> params) {
        List<SysUser> userList = sysUserMapper.getSysUserByCondition(params);
        List<SysUserForm> userFormList = new ArrayList<>();
        for (SysUser user : userList) {
            SysUserForm sysUserForm = new SysUserForm();
            BeanUtils.copyProperties(user, sysUserForm);
            getRoleForm(sysUserForm);
            userFormList.add(sysUserForm);
        }
        return userFormList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach((id) -> {
                deleteSysUser(id);
            });
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(String id, String oldPassword, String newPassword) {
        //1 判断用户是否存在
        SysUser sysUser = this.getById(id);
        if (sysUser == null) {
            throw new SofnException("待修改用户不存在");
        }
        //2 判断密码是否正确
        String checkPassword = ShiroUtils.sha256(oldPassword, sysUser.getSalt());
        if (!checkPassword.equals(sysUser.getPassword())) {
            throw new SofnException("旧密码不正确，更改失败");
        }
        //修改初始化密码
        sysUser.setInitPassword(SysConstant.DEFAULT_PWD);
        //3 更改密码
        String salt = IdUtil.getUUId();
        sysUser.setPassword(ShiroUtils.sha256(newPassword, salt));
        sysUser.setSalt(salt);
        sysUser.preUpdate();
        //在修改时把是否已修改密码状态修改为 "1":已修改
        Integer updated = SysConstant.UPDATED_1;
        sysUser.setUpdated(updated);

        updateById(sysUser);

        // 更改别人信息直接删除token 让用户重新登录
        String token = redisHelper.hget(Constants.UserSession.userNameToken, sysUser.getUsername()) == null
          ? "" : (String) redisHelper.hget(Constants.UserSession.userNameToken, sysUser.getUsername());
        redisHelper.del(token);
        redisHelper.hdel(Constants.UserSession.userNameToken, sysUser.getUsername());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(String id) {
        //1 判断用户是否存在
        SysUser sysUser = this.getById(id);
        if (sysUser == null) {
            throw new SofnException("待修改用户不存在");
        }
        //3 更改密码
        String salt = IdUtil.getUUId();
        String initPassword=SysConstant.DEFAULT_PWD;

//        if (sysUser.getInitPassword().isEmpty()) {
//            initPassword = IdUtil.getSixNumCode();
//        } else {
//            initPassword = sysUser.getInitPassword();
//        }
        sysUser.setInitPassword(initPassword);
        sysUser.setUpdated(0);
        String pdMd5 = DigestUtils.md5DigestAsHex(initPassword.getBytes());
        sysUser.setPassword(ShiroUtils.sha256(pdMd5, salt));
        sysUser.setSalt(salt);
        sysUserMapper.updateById(sysUser);
//        this.baseMapper.updateById(sysUser);
    }

  /**
   * 根据用户ID查询用户角色
   * @param userId 用户id
   * @return 角色列表
   */
    @Override
    public List<SysRole> loadRolesByUserId(String userId) {
        // 缓存中获取角色列表
        List<SysRole> roles = SysCacheUtils.getRoleListByUserId(userId);
        if (CollectionUtils.isEmpty(roles)){
            List<SysRoleVo> loginUserHaveRole = permissionRoleService.getLoginUserHaveRoleNotIncludeUserCreate(userId,false);
            if(CollectionUtils.isEmpty(loginUserHaveRole)){
                throw new SofnException("当前用户没有角色");
            }

            roles = loginUserHaveRole.stream().map(item -> {
                SysRole sysRole = new SysRole();
                BeanUtils.copyProperties(item, sysRole);
                sysRole.setSubsystemId(item.getSubsystemId());
                return sysRole;
            }).collect(Collectors.toList());

            // 缓存用户角色
            SysCacheUtils.cacheRoleListByUserId(userId, roles);
        }

        return roles;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<ImportSysUserVo> userList) {
        if (!CollectionUtils.isEmpty(userList)) {
            // 获取所有的机构
            Set<String> orgName = Sets.newHashSet();
            // 所有的角色名
            Set<String> roleNames = Sets.newHashSet();
            // 所有的角色组名
            Set<String> roleGroupNames = Sets.newHashSet();
            // 所有的用户名
            Set<String> userName = Sets.newHashSet();
            userList.forEach((user) -> {
                if (user != null) {
                    if (StringUtils.isBlank(user.getUserName())) {
                        throw new SofnException("用户名为空");
                    }
                    userName.add(user.getUserName());

                    if (StringUtils.isBlank(user.getOrganizationName())) {
                        throw new SofnException(user.getUserName() + "机构为空");
                    }
                    orgName.add(user.getOrganizationName());

                    if (StringUtils.isBlank(user.getRoleNames())) {
                        throw new SofnException(user.getUserName() + "角色为空");
                    }
                    roleNames.addAll(IdUtil.getIdsByStr(user.getRoleNames()));

                    if (!StringUtils.isBlank(user.getGroupNames())) {
                        roleGroupNames.addAll(IdUtil.getIdsByStr(user.getGroupNames()));
                    }

                }
            });
            // 文档中的用户名不能重复
            if (userName.size() != userList.size()) {
                throw new SofnException("文档中有重复的用户名");
            }
            // 数据有效性校验
            Map<String, List> data = checkImportData(orgName, roleNames, roleGroupNames, userName);
            // 组装  SysUser数据
            List<SysUser> sysUserList = Lists.newArrayList();
            List<SysUserRole> sysUserRoles = Lists.newArrayList();
            List<SysUserGroup> sysUserGroups = Lists.newArrayList();
            setData(sysUserList, sysUserRoles, sysUserGroups, data, userList);
            // 批量添加
            sysUserMapper.batchSave(sysUserList);
            sysUserRoleMapper.batchSaveSysUserRole(sysUserRoles);
            if (!CollectionUtils.isEmpty(sysUserGroups)) {
                sysUserGroupMapper.batchSaveSysUserGroup(sysUserGroups);
            }
        }
    }


    /**
     * 设置值
     *
     * @param sysUserList   最终保存的用户信息
     * @param sysUserRoles  最终保存的用户角色信息
     * @param sysUserGroups 最终保存的用户角色组信息
     * @param data          检验值得时候获取的只值
     * @param userList      Excel中的值
     */
    public void setData(List<SysUser> sysUserList, List<SysUserRole> sysUserRoles, List<SysUserGroup> sysUserGroups, Map<String, List> data, List<ImportSysUserVo> userList) {
        if (!CollectionUtils.isEmpty(userList) && !CollectionUtils.isEmpty(data)) {
            // 这里不做空值判断，因为是经过检验的数据，报错了最好查一下问题并修复
            List<SysRole> sysRoles = data.get("sysRoleInfo");
            List<SysOrg> sysOrganizations = data.get("sysOrgInfo");
            List<SysRoleGroup> sysRoleGroups = data.get("sysRoleGroupInfo");
            userList.forEach(userInfo -> {
                SysUser sysUser = new SysUser();
                sysUser.setId(IdUtil.getUUId());
                String sex = userInfo.getSex();
                sysUser.setSex(Integer.parseInt(SysManageEnum.getEnumCode("SEX_", sex)));
                sysUser.setStatus(SysManageEnum.getEnumCode("STATUS_", userInfo.getStatus()));
                sysUser.setMobile(userInfo.getMobile());
                /**
                 * 修改导入初始化密码
                 * wb
                 */
                sysUser.setInitPassword(SysConstant.DEFAULT_PWD);
                /**
                 * 添加导入用户密码和盐值
                 */
                String salt = IdUtil.getUUId();
                String pdMd5 = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
                sysUser.setPassword(ShiroUtils.sha256(pdMd5, salt));
                sysUser.setSalt(salt);

                sysUser.setNickname(userInfo.getNickname());
                sysUser.setUsername(userInfo.getUserName());
                String email = userInfo.getEmail();
                /*
                新增导入用户添加
                 */
                sysUser.setEmail(email);
                sysUser.setRemark(UserUtil.getLoginUserId() + "批量导入用户");
                sysUser.preInsert();
                // 获取机构ID
                String organizationName = userInfo.getOrganizationName();
                Optional<SysOrg> first = sysOrganizations.stream().filter((org) -> org.getOrganizationName().equals(organizationName)).findFirst();
                if (first.isPresent()) {
                    sysUser.setOrganizationId(first.get().getId());
                } else {
                    throw new SofnException("机构" + organizationName + "不存在");
                }
                sysUserList.add(sysUser);
                // 组装用户角色信息
                String roleNames = userInfo.getRoleNames();
                List<String> excelRoleNames = IdUtil.getIdsByStr(roleNames);
                List<String> roleIds = Lists.newArrayList();
                excelRoleNames.forEach((roleName) -> {
                    Optional<SysRole> first1 = sysRoles.stream().filter(sysRole -> sysRole.getRoleName().equals(roleName)).findFirst();
                    if (first1.isPresent()) {
                        roleIds.add(first1.get().getId());
                    } else {
                        throw new SofnException("角色" + roleName + "不存在");
                    }
                });
                roleIds.forEach((roleId) -> {
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setId(IdUtil.getUUId());
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setUserId(sysUser.getId());
                    sysUserRoles.add(sysUserRole);
                });
                // 组装用户角色组信息
                String roleGroupNames = userInfo.getGroupNames();
                if (!StringUtils.isBlank(roleGroupNames)) {
                    List<String> roleGroupIds = Lists.newArrayList();
                    List<String> excelRoleGroupNames = IdUtil.getIdsByStr(roleGroupNames);
                    excelRoleGroupNames.forEach((e) -> {
                        Optional<SysRoleGroup> first1 = sysRoleGroups.stream().filter(sysRoleGroup -> sysRoleGroup.getGroupName().equals(e)).findFirst();
                        if (first1.isPresent()) {
                            roleGroupIds.add(first1.get().getId());
                        } else {
                            throw new SofnException("角色组" + e + "不存在");
                        }
                    });
                    roleGroupIds.forEach((groupId) -> {
                        SysUserGroup sysUserGroup = new SysUserGroup();
                        sysUserGroup.setId(IdUtil.getUUId());
                        sysUserGroup.setUserId(sysUser.getId());
                        sysUserGroup.setGroupId(groupId);
                        sysUserGroups.add(sysUserGroup);
                    });
                }
            });
        }
    }


    /**
     * 检查导入的数据
     *
     * @param orgName        机构名称
     * @param roleNames      角色名称
     * @param roleGroupNames 角色组名称
     * @param userName       用户名称
     */
    private Map<String, List> checkImportData(Set<String> orgName, Set<String> roleNames, Set<String> roleGroupNames, Set<String> userName) {
        Map<String, List> result = Maps.newHashMap();
        // 1. 用户名是否重复
        if (!CollectionUtils.isEmpty(userName)) {
            List<SysUser> sysUserList = sysUserMapper.selectList(new QueryWrapper<SysUser>().in("USERNAME", userName).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
            if (!CollectionUtils.isEmpty(sysUserList)) {
                throw new SofnException("请检查，有用户名称重复");
            }
            // 2. 检查角色名称是否存在
            if (!CollectionUtils.isEmpty(roleNames)) {
                List<SysRole> sysRoles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().in("ROLE_NAME", roleNames).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                if (CollectionUtils.isEmpty(sysRoles)) {
                    throw new SofnException("没有一个角色存在");
                } else if (sysRoles.size() != roleNames.size()) {
                    List<String> sysRoleNames = sysRoles.stream().map(SysRole::getRoleName).collect(Collectors.toList());
                    List<String> oldRoleNames = Lists.newArrayList(roleNames);
                    oldRoleNames.removeAll(sysRoleNames);
                    throw new SofnException("不存在的角色：【" + IdUtil.getStrIdsByList(oldRoleNames) + "】");
                }
                result.put("sysRoleInfo", sysRoles);

            } else {
                throw new SofnException("必须填入角色信息");
            }
            // 3. 检查机构名称是否正确
            if (!CollectionUtils.isEmpty(orgName)) {

                Map<String,Object> queryParams = Maps.newHashMap();
                queryParams.put("orgNames",Lists.newArrayList(orgName));
                List<SysOrg> sysOrganizations = sysOrgService.getInfoByCondition(queryParams);

                if (CollectionUtils.isEmpty(sysOrganizations)) {
                    throw new SofnException("没有任何一个机构存在");
                }
                if (sysOrganizations.size() != orgName.size()) {
                    List<String> oldOrgNames = Lists.newArrayList(orgName);
                    List<String> sysOrgNames = sysOrganizations.stream().map(SysOrg::getOrganizationName).collect(Collectors.toList());
                    oldOrgNames.removeAll(sysOrgNames);
                    throw new SofnException("不存在的机构为【" + IdUtil.getStrIdsByList(oldOrgNames) + "】");
                }
                result.put("sysOrgInfo", sysOrganizations);
            } else {
                throw new SofnException("每个用户至少得有一个机构");
            }
            // 4. 检查角色组是否存在
            if (!CollectionUtils.isEmpty(roleGroupNames)) {
                List<SysRoleGroup> sysRoleGroups =
                        sysRoleGroupMapper.selectList(new QueryWrapper<SysRoleGroup>().in("GROUP_NAME", roleGroupNames).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                if (CollectionUtils.isEmpty(sysRoleGroups)) {
                    throw new SofnException("没有任何一个角色组角色存在");
                }
                if (sysRoleGroups.size() != roleGroupNames.size()) {
                    List<String> oldRoleGroups = Lists.newArrayList(roleGroupNames);
                    oldRoleGroups.removeAll(sysRoleGroups.stream().map(SysRoleGroup::getGroupName).collect(Collectors.toSet()));
                    throw new SofnException("不存在的角色组为【" + IdUtil.getStrIdsByList(oldRoleGroups) + "】");
                }
                result.put("sysRoleGroupInfo", sysRoleGroups);
            }
        } else {
            throw new SofnException("没有用户需要保存");
        }
        return result;
    }


    @Override
    public List<SysUser> getUserListByOrgId(String orgId, String proxyUser) {
        // 获取机构下的所有机构
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("id",orgId);
        List<SysOrg> sysOrganizations = sysOrgService.getInfoByCondition(queryParams);
        SysOrg sysOrg = null;
        if(!CollectionUtils.isEmpty(sysOrganizations)){
            sysOrg = sysOrganizations.get(0);
        }


        Set<SysOrg> orgList = Sets.newHashSet();
        if(sysOrg != null){
            String parengIds = sysOrg.getParentIds() + "/" + sysOrg.getId();
            queryParams.clear();
            queryParams.put("parentIdStrs",parengIds);
            List<SysOrg> sysOrgs = sysOrgService.getInfoByCondition(queryParams);
            if(!CollectionUtils.isEmpty(sysOrgs)){
                orgList.addAll(sysOrgs);
            }
        }

        Set<String> orgIdSet = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(orgList)) {
            orgIdSet = orgList.stream().map(SysOrg::getId).collect(Collectors.toSet());
        }
        orgIdSet.add(sysOrg.getId());
        //  添加代理机构的信息
        if (BoolUtils.Y.equals(proxyUser)) {
            List<String> userHasSubsystemIds = permissionSubSystemService.getUserHasSubsystemIds();
            if (!CollectionUtils.isEmpty(userHasSubsystemIds)) {
                List<String> proxyOrgIds = sysOrgAgentService.selectOrgAgentByOrgId(userHasSubsystemIds, Lists.newArrayList(orgId), "");
                if (!CollectionUtils.isEmpty(proxyOrgIds)) {
                    orgIdSet.addAll(proxyOrgIds);
                }
            }
        }
        List<SysUser> sysUsers = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(orgIdSet)) {
            sysUsers = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                    .ne("DEL_FLAG", "Y")
                    .eq("STATUS","1")
                    .in("ORGANIZATION_ID", orgIdSet)
            );
        }
        return sysUsers;
    }

    @Override
    public List<SysUserForm> getUserInfoByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<SysUserForm> userInfoByIds = sysUserMapper.getUserInfoByIds(ids);
            if (CollectionUtils.isEmpty(userInfoByIds)) {
                return Lists.newArrayList();
            }
            // 查询角色信息
            userInfoByIds.forEach((e) -> {
                List<String> roleIds = sysUserMapper.getRoleIdsByUserId(e.getId());
                List<String> roleIds2 = Lists.newArrayList();
                List<String> roleNames = Lists.newArrayList();
                Set<String> roleCodes = Sets.newHashSet();
                Set<String> permissions = Sets.newHashSet();
                if (!CollectionUtils.isEmpty(roleIds)) {
                    // 查询具体的权限信息
                    List<Map<String, String>> roleInfoByRoleIds = sysUserMapper.getRoleInfoByRoleIds(roleIds);
                    if (!CollectionUtils.isEmpty(roleInfoByRoleIds)) {
                        roleInfoByRoleIds.forEach(mapInfo -> {
                            String roleId = mapInfo.get("roleid");
                            if (!roleIds2.contains(roleId)) {
                                roleIds2.add(roleId);
                                roleNames.add(mapInfo.get("rolename"));
                                roleCodes.add(mapInfo.get("rolecode") == null ? "" : mapInfo.get("rolecode"));
                            }
                            String permission = mapInfo.get("permission");
                            if (!StringUtils.isBlank(permission)) {
                                permissions.add(permission);
                            }
                        });
                    }
                }
                e.setRoleCodes(roleCodes);
                // 组装信息
                e.setRoleIds(IdUtil.getStrIdsByList(roleIds2));
                e.setRoleNames(IdUtil.getStrIdsByList(roleNames));
                e.setResourceList(permissions);
            });
            return userInfoByIds;
        }
        return Lists.newArrayList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(UpdateUserInfoVo updateUserInfoVo) {
        if (updateUserInfoVo != null) {
            String id = updateUserInfoVo.getId();
            SysUser sysUser = sysUserMapper.selectById(id);
            if (sysUser == null) {
                throw new SofnException("待修改用户不存在");
            }
            if (SysManageEnum.DEL_FLAG_Y.getCode().equals(sysUser.getDelFlag())) {
                throw new SofnException("用户已删除");
            }

            if(StringUtils.isNotBlank(updateUserInfoVo.getEmail())){
                sysUser.setEmail(updateUserInfoVo.getEmail());
            }
            if(StringUtils.isNotBlank(updateUserInfoVo.getMobile())){
                sysUser.setMobile(updateUserInfoVo.getMobile());
            }
            if(StringUtils.isNotBlank(updateUserInfoVo.getStatus())){
                sysUser.setStatus(updateUserInfoVo.getStatus());
            }
            if(updateUserInfoVo.getSex() != null && updateUserInfoVo.getSex() != 0 ){
                sysUser.setSex(updateUserInfoVo.getSex());
            }
            sysUser.setRemark(updateUserInfoVo.getRemark());
            if(StringUtils.isNotBlank(updateUserInfoVo.getNickname())){
                sysUser.setNickname(updateUserInfoVo.getNickname());
            }
            sysUser.preUpdate();
            sysUserMapper.updateById(sysUser);
        }
    }

    @Override
    public PageUtils<BasicUserInfo> getBasicUserInfo(SelectUserConditionVo selectUserConditionVo, Integer pageNo,
                                                     Integer pageSize) {
        if (selectUserConditionVo != null) {
            if (!StringUtils.isBlank(selectUserConditionVo.getStrIds())) {
                selectUserConditionVo.setIds(IdUtil.getIdsByStr(selectUserConditionVo.getStrIds()));
            }
            if (!StringUtils.isBlank(selectUserConditionVo.getRoleIdStrs())) {
                selectUserConditionVo.setRoleIds(IdUtil.getIdsByStr(selectUserConditionVo.getRoleIdStrs()));
            }
            if (pageNo != null && pageSize != null && pageSize != 0) {
                PageHelper.offsetPage(pageNo, pageSize);
            }
            List<BasicUserInfo> basicUserInfo = sysUserMapper.getBasicUserInfo(selectUserConditionVo);
            PageInfo<BasicUserInfo> pageInfo = new PageInfo<>(basicUserInfo);
            return PageUtils.getPageUtils(pageInfo);
        }
        return null;

    }

    @Override
    public PageUtils<BasicUserInfo> getBasicUserInfoNew(SelectUserConditionVo selectUserConditionVo, Integer pageNo, Integer pageSize) {
        if (selectUserConditionVo != null && selectUserConditionVo.getRegioncodes().size() > 0) {
            if (!StringUtils.isBlank(selectUserConditionVo.getStrIds())) {
                selectUserConditionVo.setIds(IdUtil.getIdsByStr(selectUserConditionVo.getStrIds()));
            }
            if (!StringUtils.isBlank(selectUserConditionVo.getRoleIdStrs())) {
                selectUserConditionVo.setRoleIds(IdUtil.getIdsByStr(selectUserConditionVo.getRoleIdStrs()));
            }
            if (pageNo != null && pageSize != null && pageSize != 0) {
                PageHelper.offsetPage(pageNo, pageSize);
            }
            List<BasicUserInfo> basicUserInfo = sysUserMapper.getBasicUserInfoNew(selectUserConditionVo);
            PageInfo<BasicUserInfo> pageInfo = new PageInfo<>(basicUserInfo);
            return PageUtils.getPageUtils(pageInfo);
        }
        return null;

    }

    @Override
    public boolean checkUsernameExist(String id, String username) {
        List<SysUser> userList = sysUserMapper.selectList(new QueryWrapper<SysUser>()
                .eq("USERNAME", username)
                .eq("del_flag","N")
                .ne(StringUtils.isNotBlank(id), "ID", id));
        return !CollectionUtils.isEmpty(userList);
    }

    @Override
    public List<ImportSysUserVo> getExportUserInfo(Map<String, String> params) {
        if (!CollectionUtils.isEmpty(params)) {
            return sysUserMapper.getExportUserInfo(params);
        }
        return null;
    }

    @Override
    public PageUtils<BasicUserInfo> getRoleHaveUserInfo(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        if (CollectionUtils.isEmpty(params) ||params.get("roleId") == null ||StringUtils.isBlank(params.get("roleId").toString())) {
            return null;
        }
        if(pageSize >0){
            PageHelper.offsetPage(pageNo, pageSize);
        }
        // 判断是否是超级管理员
        List<BasicUserInfo> userByRoleIdAndSubsystemId;
        if(permissionSuperAdminService.isSuperman()){
            userByRoleIdAndSubsystemId = sysUserMapper.getUserByRoleIdAndSubsystemId(params);
        }else{
            List<String> userAppIds = permissionSubSystemService.getUserAppIds();
            params.put("orgId", UserUtil.getLoginUserOrganizationId());
            params.put("appIds", userAppIds);
            params.put("limitOrg", BoolUtils.Y);
            params.put("carryChildren", BoolUtils.Y);
            params.put("carryProxyOrg", BoolUtils.Y);
            // 添加上自己创建的
            params.put("userId", UserUtil.getLoginUserId());

            userByRoleIdAndSubsystemId =  sysUserMapper.getUserByRoleIdAndSubsystemId(params);
        }

        PageInfo<BasicUserInfo> pageInfo = new PageInfo<>(userByRoleIdAndSubsystemId);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public PageUtils<List<BasicUserInfo>> getRoleDontHaveUserInfo(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        throw new SofnException("该方法未启用，如果要启用需要添加orgIds分页查询逻辑");
//        if (CollectionUtils.isEmpty(params) ||params.get("roleId") == null ||StringUtils.isBlank(params.get("roleId").toString())) {
//            return null;
//        }
//        if(pageSize > 0){
//            PageHelper.offsetPage(pageNo, pageSize);
//        }
//
//        // 只查询当前用户能够访问的用户
//
//        List<String> allOrgListAndLoginUserId = sysOrgService.getUserHaveOrgIds(null, true, true);
//        if(CollectionUtils.isEmpty(allOrgListAndLoginUserId)){
//            throw new SofnException("当前用户无权访问任何机构");
//        }
//        params.put("orgIds", allOrgListAndLoginUserId);
//        List<BasicUserInfo> userByNotExistsRoleId = sysUserMapper.getUserByNotExistsRoleId(params);
//        PageInfo<BasicUserInfo> pageInfo = new PageInfo<>(userByNotExistsRoleId);
//        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<BasicUserInfo> getAllUser() {
        log.info("查询机构");
        List<String> userHaveOrgIds = sysOrgService.getUserHaveOrgIds(null, true, true);
        log.info("查询机构结束");
        if(CollectionUtils.isEmpty(userHaveOrgIds)){
            log.warn("当前用户【{}】没有访问任何机构的权限",UserUtil.getLoginUserName());
            return Lists.newArrayList();
        }
        List<SysUser> sysUserList;
        log.info("查询基本用户信息");
        if(permissionSuperAdminService.isSuperman()){
            sysUserList = sysUserMapper.selectList(
                    new QueryWrapper<SysUser>()
                            .eq("DEL_FLAG",SysManageEnum.DEL_FLAG_N.getCode())
                            .eq("STATUS", SysManageEnum.STATUS_1.getCode())
            );
        }else{
            GetAllInfoByPageUtil<SysUser> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
            sysUserList = getAllInfoByPageUtil.getAllInfo((param)->{
                return sysUserMapper.selectList(
                        new QueryWrapper<SysUser>().in("ORGANIZATION_ID",(List)param[0])
                                .eq("DEL_FLAG",SysManageEnum.DEL_FLAG_N.getCode())
                                .eq("STATUS", SysManageEnum.STATUS_1.getCode())

                );
            },0,false,"",userHaveOrgIds);
        }
        log.info("查询基本用户信息结束");

        if (!CollectionUtils.isEmpty(sysUserList)) {
            List<BasicUserInfo> basicUserInfos = sysUserList.stream().map(item -> {
                BasicUserInfo basicUserInfo = new BasicUserInfo();
                if (item != null) {
                    basicUserInfo.setId(item.getId());
                    basicUserInfo.setNickname(item.getNickname());
                    basicUserInfo.setUsername(item.getUsername());
                }
                return basicUserInfo;
            }).collect(Collectors.toList());
            return basicUserInfos;
        }
        return null;
    }

    @Override
    public List<String> getLoginUserBelongSystem() {

        return null;
    }

    @Override
    public SysUserForm getUserInfoByUsername(String username) {
        List<SysUser> sysUserList = sysUserMapper.selectList(new QueryWrapper<SysUser>().eq("USERNAME", username));
        if(CollectionUtils.isEmpty(sysUserList)){
            return null;
        }
        if(sysUserList.size() > 1){
            log.error("用户名:{}重复",username);
            return null;
        }
        SysUser sysUser = sysUserList.get(0);
        if(BoolUtils.Y.equals(sysUser.getDelFlag())){
            log.error("用户:{}已删除",username);
            return null;
        }

        String organizationId = sysUser.getOrganizationId();
        if(StringUtils.isEmpty(organizationId)){
            log.error("用户:{}机构不存在",username);
            return null;
        }
        Map<String,Object> queryParams = Maps.newHashMap();
        queryParams.put("id",organizationId);
        List<SysOrg> sysOrganizations = sysOrgService.getInfoByCondition(queryParams);
        SysOrg sysOrg = null;
        if(!CollectionUtils.isEmpty(sysOrganizations)){
            sysOrg = sysOrganizations.get(0);
        }

        if(sysOrg == null|| BoolUtils.Y.equals(sysOrg.getDelFlag())){
            log.error("用户:{}机构不存在",username);
            return null;
        }
        // 设置机构信息
        SysUserForm sysUserForm = new SysUserForm(sysUser);
        sysUserForm.setOrganizationName(sysOrg.getOrganizationName());
        sysUserForm.setOrganizationId(organizationId);
        return sysUserForm;
    }

    @Override
    public List<SysUserForm> getUserInfoByRegionCodeAndAppId(String regionCode, String appId) {
        return getUserByOrgInfoAndAppId(Lists.newArrayList(regionCode), appId, null, true);
    }

    @Override
    public List<SysUserForm> getUserByOrgInfoAndAppId(List<String> regionCodes, String appId, String level, boolean includeRoleAndOrg) {
        List<SysUserForm> sysUserForms = Lists.newArrayList();

        // 1. 根据区划和系统代码查询机构
        List<SysOrg> orgList = sysOrgService.getOrgIdByRegionCodeAndAppId(appId, regionCodes, level);
        if(CollectionUtils.isEmpty(orgList)){
            return sysUserForms;
        }

        // 2. 根据机构信息查询用户
        List<String> collect = orgList.stream().map(SysOrg::getId).collect(Collectors.toList());
        GetAllInfoByPageUtil<SysUser> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();

        List<SysUser> sysUserList = getAllInfoByPageUtil.getAllInfo((param)->{
            return sysUserMapper.selectList(
                    new QueryWrapper<SysUser>().in("ORGANIZATION_ID",(List)param[0])
                    .eq("DEL_FLAG",SysManageEnum.DEL_FLAG_N.getCode())
            );
        },0,false,"",collect);

        if(CollectionUtils.isEmpty(sysUserList)){
            return null;
        }

        if (includeRoleAndOrg){
            List<String> userIds = sysUserList.stream().map(SysUser::getId).collect(Collectors.toList());
            // 3. 根据用户查询角色
            GetAllInfoByPageUtil<UserHaveRoleVo> getAllInfoByPageUtil2 = new GetAllInfoByPageUtil<>();
            List<UserHaveRoleVo> userHaveRoleVos = getAllInfoByPageUtil2.getAllInfo((param)->{
                return sysRoleMapper.getUserHaveRole((List)param[0]);
            },0,false,"",userIds);

            Map<String,SysOrg> orgInfos = Maps.newHashMap();
            orgList.forEach(item->{
                orgInfos.put(item.getId(),item);
            });

            sysUserForms = sysUserList.stream().map(item->{
                SysUserForm sysUserForm = new SysUserForm(item);
                // 设置机构和区划
                SysOrg sysOrg = orgInfos.get(item.getOrganizationId());
                if(sysOrg != null){
                    sysUserForm.setRegionCode(sysOrg.getRegionLastCode());
                    sysUserForm.setRegionNames(sysOrg.getRegioncode());
                }
                // 设置用户角色
                Optional<UserHaveRoleVo> first = userHaveRoleVos.stream().filter(userHaveRoleVo -> userHaveRoleVo.getUserId().equals(item.getId())).findFirst();
                if(first.isPresent()){
                    UserHaveRoleVo userHaveRoleVo = first.get();
                    List<SysRole> sysRoles = userHaveRoleVo.getSysRoles();
                    if(!CollectionUtils.isEmpty(sysRoles)){
                        List<String> roleCodes = sysRoles.stream().map(SysRole::getRoleCode).collect(Collectors.toList());
                        sysUserForm.setRoleCodes(Sets.newHashSet(roleCodes));
                        List<String> roleIds = sysRoles.stream().map(SysRole::getId).collect(Collectors.toList());
                        sysUserForm.setRoleIds(IdUtil.getStrIdsByList(roleIds));
                    }
                }
                return sysUserForm;
            }).collect(Collectors.toList());
        }else {
            sysUserForms = sysUserList.stream().map(item->{
                SysUserForm sysUserForm = new SysUserForm(item);
                return sysUserForm;
            }).collect(Collectors.toList());
        }

        return sysUserForms;
    }

    @Override
  public boolean hasUserByOrgId(String orgId) {
    Integer count = sysUserMapper.selectCount(new QueryWrapper<SysUser>()
      .ne("DEL_FLAG", SysManageEnum.DEL_FLAG_Y.getCode())
      .eq("ORGANIZATION_ID", orgId)
    );

    return count>0;
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

    @Override
    public List<SysUserSystem> getUserUseSystemList(String userId){
        Map<String,Object> params=Maps.newHashMap();
        params.put("userId",userId);
        List<SysUserSystem> userSystemList=userSystemMapper.getListByConditions(params);
        return userSystemList;
    }
    @Override
    public String addUserSystem(String userId,String systemId){
        String returnString="";
        //判断是否之前存在用户和子系统的对应关系
        Map<String,Object> params=Maps.newHashMap();
        params.put("userId",userId);
        params.put("systemId",systemId);
        List<SysUserSystem> sysUserSystem=userSystemMapper.getListByConditions(params);
        SysSubsystem subSystem=sysSubsystemService.getSubsystemByIdOrAppId(systemId);
        if(sysUserSystem.size()>0){//存在就更新时间
            sysUserSystem.get(0).setUpdateTime(new Date());
            sysUserSystem.get(0).setViewUrl(subSystem.getViewUrl());
            sysUserSystem.get(0).setSystemName(subSystem.getSubsystemName());
            userSystemMapper.updateById(sysUserSystem.get(0));
        }else{//不存在就新增
            SysUserSystem userSystem=new SysUserSystem();
            userSystem.setId(IdUtil.getUUId());
            userSystem.setSystemId(systemId);
            userSystem.setUserId(userId);
            userSystem.setAppId(subSystem.getAppId());
            userSystem.setSystemName(subSystem.getSubsystemName());
            userSystem.setUpdateTime(new Date());
            userSystem.setViewUrl(subSystem.getViewUrl());

            userSystemMapper.insert(userSystem);
            //并判断该用户的常用子系统是否大于10条,大于10条就删除存在时间最久远的一条
            params.remove("systemId");
            List<SysUserSystem> userSystemList=userSystemMapper.getListByConditions(params);
            if(userSystemList.size()>10){
                userSystemMapper.deleteById(userSystemList.get(10).getId());
            }
        }
        //redis缓存用户常用子系统列表
        params.remove("systemId");
        List<SysUserSystem> userSystemList=userSystemMapper.getListByConditions(params);
        SysCacheUtils.cacheUserUseSystem(userId,userSystemList);
        return returnString;
    }
}
