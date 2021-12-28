package com.sofn.sys.web.integration.IntegrationTokenImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.sofn.common.exception.ExceptionType;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.BoolUtils;
import com.sofn.common.utils.JwtUtils;
import com.sofn.common.utils.shiro.ShiroUtils;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysUserMapper;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.service.permission.PermissionRoleService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.LoginVo;
import com.sofn.sys.vo.SysRoleVo;
import com.sofn.sys.vo.SysUserForm;
import com.sofn.sys.web.integration.IntegrationTokenService.IntegrationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service()
public class IntegrationTokenServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements IntegrationTokenService {

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysRegionService sysRegionService;
    @Autowired
    private PermissionRoleService permissionRoleService;
    @Autowired
    private SysSubsystemService sysSubsystemService;

    @Override
    public Auth execuLogin(LoginVo loginVo, boolean isApp, String type) {

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


    /**
     * 根据用户ID查询用户角色
     * @param userId 用户id
     * @return 角色列表
     */
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


    /**
     * 根据用户名查询用
     * @param username 用户名
     * @return SysUser
     */
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



}
