package com.sofn.sys.service.permission.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.treeutil.basic.TreeGenerate;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.UserUtil;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysSystemOrgMapper;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysSystemOrg;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.permission.PermissionOrganizationService;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.service.permission.PermissionSuperAdminService;
import com.sofn.sys.vo.SysOrgVo;
import com.sofn.sys.vo.SysOrganizationTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 当前的接口中有属于SYS_ORGANIZATION表中的接口，已经全部没用了
 *
 * @author heyongjie
 * @date 2019/12/17 15:04
 */
@Slf4j
@Service("organizationService")
public class PermissionOrganizationServiceImpl implements PermissionOrganizationService {

    @Autowired
    private PermissionSuperAdminService permissionSuperAdminService;

    @Autowired
    private PermissionSubSystemService permissionSubSystemService;

    @Autowired
    private SysOrgService sysOrgService;

    @Autowired
    private SysSystemOrgMapper sysSystemOrgMapper;

    /**
     * 树工具  因为每棵树根节点、分隔符、排序方式（倒序、正序）有可能不一样！所以需要单独指定
     */
    private TreeGenerate<SysOrganizationTreeVo> treeGenerate =
            new TreeGenerate<>(SysManageEnum.TREE_NODE_SPLIT_STR.getCode(),
                    SysManageEnum.ROOT_SYS_ORG.getCode());

    @Override
//    @RedisCache(key = PermissionConstant.PERMISSION_ORG_KEY + "getOrgIdsByLoginUser", isCarryLoginUserId = true)
    public List<String> getOrgIdsByLoginUser() {
        // 1. 获取当前登录用户的机构
        boolean isSuperman = permissionSuperAdminService.isSuperman();
        if (isSuperman) {
            return treeGenerate.getAllNodeByParentId(SysManageEnum.ROOT_SYS_ORG.getCode(), this);
        } else {
            String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
            if (StringUtils.isBlank(loginUserOrganizationId)) {
                log.error("当前登录用户：{}", UserUtil.getLoginUserId());
                throw new SofnException("当前用户的机构信息为空");
            }
            return treeGenerate.getAllNodeByParentId(loginUserOrganizationId, this);
        }
    }

    @Override
//    @RedisCache(key = PermissionConstant.PERMISSION_ORG_KEY + "getPermissionOrganizationTree", isCarryLoginUserId = true, isCarryParams = true)
    public SysOrganizationTreeVo getPermissionOrganizationTree(String orgId, String regionId, String orgName) {
//        Map<String, Object> params = Maps.newHashMap();
//        if (!StringUtils.isBlank(regionId)) {
//            params.put("regionId", regionId);
//        }
//        if (!StringUtils.isBlank(orgName)) {
//            params.put("orgName", orgName);
//        }
//        if (!StringUtils.isBlank(orgId)) {
//            // 如果是超级管理员直接什么条件都不加
//            boolean isSuperman = permissionSuperAdminService.isSuperman();
//            if (isSuperman) {
//                // 直接返回以组织机构跟节点为根节点的一棵树
//                return treeGenerate.toTreeByCondition(params, this, SysManageEnum.ROOT_SYS_ORG.getCode(), BoolUtils.Y);
//            } else {
//                params.put("loginUserId", UserUtil.getLoginUserId());
//                // 返回以当前登录用户为根节点的一棵树
//                SysOrganization sysOrganization = sysOrganizationMapper.selectById(orgId);
//                if (sysOrganization != null) {
//                    if (!permissionSuperAdminService.isSuperman()) {
//                        params.put("ids", Lists.newArrayList(sysOrganization.getId()));
//                        return treeGenerate.toTreeByCondition(params, this, sysOrganization.getId(), BoolUtils.Y);
//                    }
//                }
//            }
//        }
        return null;
    }

    @Override
//    @RedisCache(key = PermissionConstant.PERMISSION_ORG_KEY + "getOrgIdByLoginUserAndAgent", isCarryLoginUserId = true)
    public List<String> getOrgIdByLoginUserAndAgent() {
//        List<String> orgIdsByLoginUser = getOrgIdsByLoginUser();
//        if (!permissionSuperAdminService.isSuperman()) {
//            // 如果不是超级管理员加上代理机构的ID
//            String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
//            SysOrganization sysOrganization = sysOrganizationMapper.selectById(loginUserOrganizationId);
//            if (sysOrganization == null || SysManageEnum.DEL_FLAG_Y.getCode().equals(sysOrganization.getDelFlag())) {
//                log.error("获取到的当前登录用户机构{}", loginUserOrganizationId);
//                throw new SofnException("当前登录用户的机构不存在");
//            }
//            if ("N".equals(sysOrganization.getThirdOrg())) {
//                // 如果是行政机构查询是否有被代理
//                List<String> userHasSubsystemIds = permissionSubSystemService.getUserHasSubsystemIds();
//                List<String> proxyOrg = Lists.newArrayList();
//                if (!CollectionUtils.isEmpty(userHasSubsystemIds)) {
//                    userHasSubsystemIds.forEach(subsystem -> {
//                        List<SysOrganization> sysOrganizations = sysOrganizationAgentService.selectOrgAgentByOrgId(subsystem, loginUserOrganizationId, "");
//                        if (!CollectionUtils.isEmpty(sysOrganizations)) {
//                            proxyOrg.addAll(sysOrganizations.stream().map(SysOrganization::getId).collect(Collectors.toList()));
//                        }
//                    });
//                }
//                if (!CollectionUtils.isEmpty(proxyOrg)) {
//                    orgIdsByLoginUser.addAll(proxyOrg);
//                }
//            }
//        }
//        return orgIdsByLoginUser;
        return null;
    }

    @Override
    public PageUtils<SysOrgVo> getUserHaveOrgByParentIdAndOtherParam(Map<String, Object> params, Integer pageNo, Integer pageSize) {
        // 如果需要搜索需要在当前节点    到底以parentId为准还是以查询条件为准

        // 1. 校验是否可以访问系统
        String appId = params.get("appId") == null ? null : params.get("appId").toString();
        if(StringUtils.isBlank(appId)){
            throw new SofnException("请传入AppId");
        }
        // 2. 获取用户的机构
        String parentId =  params.get("parentId") == null ? "" : params.get("parentId").toString();
        String loginUserOrgId = UserUtil.getLoginUserOrganizationId();
        if(StringUtils.isBlank(loginUserOrgId)){
            throw new SofnException("未获取到登录用户");
        }
        SysOrg sysOrg = sysOrgService.getById(loginUserOrgId);
        if(sysOrg == null){
            throw new SofnException("当前用户所在机构不存在");
        }

        PageInfo<SysOrg> pageInfo;

        if(params.size() > 2){
            // 如果是按照条件查询就从当前节点和当前节点下面的节点中找
            String parentIdStrs;
            if(SysManageEnum.ROOT_ORG.getCode().equals(loginUserOrgId)){
                parentIdStrs = SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + SysManageEnum.ROOT_ORG.getCode();
            }else{
                parentIdStrs = sysOrg.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + sysOrg.getId();
            }
            params.put("parentIdStrs",parentIdStrs);
            params.put("id",loginUserOrgId);
            if (pageSize != null && pageSize > 0) {
                PageHelper.offsetPage(pageNo, pageSize);
            }
            List<SysOrg> orgInfoByAppIdAndParentIdAndOtherParam = sysOrgService.getOrgInfoByAppIdAndParentIdAndOtherParam(appId, parentId, params);
            pageInfo = new PageInfo<>(orgInfoByAppIdAndParentIdAndOtherParam);

        }else{
            // 如果不是按照条件查询就以parentId为准   获取自己
            // parentId不传入那么根节点超级管理员为root 非超级管理员直接返回自己的机构
            if(StringUtils.isBlank(parentId)){
                if(!SysManageEnum.ROOT_ORG.getCode().equals(loginUserOrgId)){
                    pageInfo =  getSelfByPage(pageNo,pageSize,loginUserOrgId);
                }else{
                    if (pageSize != null && pageSize > 0) {
                        PageHelper.offsetPage(pageNo, pageSize);
                    }
                    // 根据根节点和AppId查询节点
                    List<SysOrg> sysOrgs = sysOrgService.selectByAppId(appId, SysManageEnum.ROOT_ORG.getCode(), null);
                    pageInfo = new PageInfo<>(sysOrgs);
                }
            }else{
                if(SysManageEnum.ROOT_ORG.getCode().equals(loginUserOrgId)){
                    // 如果传入了直接根据parentId查询结果
                    if (pageSize != null && pageSize > 0) {
                        PageHelper.offsetPage(pageNo, pageSize);
                    }
                    // 根据根节点和AppId查询节点
                    List<SysOrg> sysOrgs = sysOrgService.selectByAppId(appId, parentId, null);
                    pageInfo = new PageInfo<>(sysOrgs);
                }else {
                    // 如果传入的是根节点，直接返回自己
                    if (SysManageEnum.ROOT_ORG.getCode().equals(parentId)) {
                        pageInfo =  getSelfByPage(pageNo,pageSize,loginUserOrgId);
                    } else {
                        // 如果不是就判断权限是否符合  传入的parentId如果是自己节点的上一级，就返回自己，如果是自己的下属机构就直接查询
                        SysOrg byId = sysOrgService.getById(parentId);
                        if (byId == null) {
                            throw new SofnException("父机构不存在");
                        }

                        String parentIds1 = byId.getParentIds();
                        if (byId.getId().equals(sysOrg.getParentId())) {
                            pageInfo = getSelfByPage(pageNo,pageSize,loginUserOrgId);
                        } else if (parentId.equals(loginUserOrgId) || parentIds1.contains(loginUserOrgId)) {
                            // 如果是自己或者自己的下级机构就直接返回
                            if (pageSize != null && pageSize > 0) {
                                PageHelper.offsetPage(pageNo, pageSize);
                            }
                            List<SysOrg> id = sysOrgService.selectByAppId(appId, parentId, null);
                            pageInfo = new PageInfo<>(id);
                        } else {
                            throw new SofnException("暂无权限");
                        }
                    }
                }
            }
        }

        PageInfo<SysOrgVo> pageInfo2 = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfo2);
        pageInfo2.setList(sysOrgService.getVoList(pageInfo.getList()));
        return PageUtils.getPageUtils(pageInfo2);
    }

    /**
     * 根据ID分页获取信息
     * @param pageNo   从哪开始
     * @param pageSize  每页显示条数
     * @param loginUserOrgId  登录用户ID
     * @return PageInfo<SysOrg>
     */
    private PageInfo<SysOrg>  getSelfByPage(Integer pageNo,Integer pageSize,String loginUserOrgId){
        if (pageSize != null && pageSize > 0) {
            PageHelper.offsetPage(pageNo, pageSize);
        }
        List<SysOrg> id = sysOrgService.list(new QueryWrapper<SysOrg>().eq("ID", loginUserOrgId));
       return  new PageInfo<>(id);
    }

    @Override
    public SysOrganizationTreeVo getUserHaveOrgBaseInfoToTree() {
        // 1. 获取当前用户的机构信息
        SysOrg sysOrg = null;
        String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
        if (StringUtils.isNotBlank(loginUserOrganizationId)) {
            List<SysOrg> sysOrgs = (List<SysOrg>) sysOrgService.listByIds(Lists.newArrayList(loginUserOrganizationId));
            if (CollectionUtils.isEmpty(sysOrgs)) {
                throw new SofnException("当前用户机构信息不存在");
            }
            List<SysOrg> collect = sysOrgs.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect) || collect.size() > 1) {
                throw new SofnException("当前登录用户【" + UserUtil.getLoginUserId() + "】的机构【" + loginUserOrganizationId + "】异常");
            }
            sysOrg = collect.get(0);
        }
        if(sysOrg == null){
            throw new SofnException("未获取当前用户的机构信息");
        }

        // 2. 获取当前用户有的子系统
        List<String> userAppIds = permissionSubSystemService.getUserAppIds();
        if(CollectionUtils.isEmpty(userAppIds)){
            throw new SofnException("当前用户没有系统权限");
        }
        // 3. 根据机构ID和系统ID筛选出机构 不做值的翻译操作
        String parentIdStr;
        if(!SysManageEnum.ROOT_ORG.getCode().equals(loginUserOrganizationId)){
            parentIdStr = sysOrg.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + sysOrg.getId() ;
        }else{
            parentIdStr =  SysManageEnum.TREE_NODE_SPLIT_STR.getCode()   + SysManageEnum.ROOT_ORG.getCode() ;
        }

        List<SysOrgVo> orgInfoByParentIdAndAppId = sysOrgService.getOrgInfoByParentIdAndAppId(parentIdStr, userAppIds);
        // 添加用户有的机构信息 的AppId
        List<SysSystemOrg> infoByOrgId = sysSystemOrgMapper.getInfoByOrgId(sysOrg.getId());
        SysOrgVo sysOrganizationTreeVo = new SysOrgVo();
        sysOrganizationTreeVo.setId(sysOrg.getId());
        sysOrganizationTreeVo.setOrganizationName(sysOrg.getOrganizationName());
        sysOrganizationTreeVo.setParentId(sysOrg.getParentId());
        sysOrganizationTreeVo.setThirdOrg(sysOrg.getThirdOrg());
        if(!CollectionUtils.isEmpty(infoByOrgId)){
            Set<String> appIds= infoByOrgId.stream().map(SysSystemOrg::getAppId).collect(Collectors.toSet());
            sysOrganizationTreeVo.setAppIds(Lists.newArrayList(appIds));
        }
        orgInfoByParentIdAndAppId.add(sysOrganizationTreeVo);
        if(!CollectionUtils.isEmpty(orgInfoByParentIdAndAppId)){
            // 将数据做成树结构
            List<SysOrganizationTreeVo> collect =
                    orgInfoByParentIdAndAppId.stream().map(SysOrganizationTreeVo::getSysOrganizationTreeVo).collect(Collectors.toList());
            return treeGenerate.toTree(collect, sysOrg.getId());
        }
        return null;
    }

    @Override
    public List<SysOrganizationTreeVo> getInfoByIds(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            Map<String, Object> params = Maps.newHashMap();
            params.put("ids", ids);
            return getInfoByCondition(params);
        }
        return null;
    }

    @Override
    public List<SysOrganizationTreeVo> getInfoByCondition(Map<String, Object> params) {
//        List<SysOrganizationTreeVo> treeVoInfoByCondition = sysOrganizationMapper.getTreeVoInfoByCondition(params);
//        if (!CollectionUtils.isEmpty(treeVoInfoByCondition)) {
//            // 1. 查询所有的行政区划信息
//            Set<String> regionIds = Sets.newHashSet();
//            Set<String> addressIds = Sets.newHashSet();
//            treeVoInfoByCondition.forEach((tree) -> {
//                if (tree != null) {
//                    List<String> regionCodeStrGetList = getListByStr(tree.getRegioncode(), tree.getId());
//                    if (!CollectionUtils.isEmpty(regionCodeStrGetList)) {
//                        regionIds.addAll(regionCodeStrGetList);
//                    }
//                    List<String> addressStrGetList = getListByStr(tree.getAddress(), tree.getId());
//                    if (!CollectionUtils.isEmpty(addressStrGetList)) {
//                        addressIds.addAll(addressStrGetList);
//                    }
//                }
//            });
//            Set<String> all = Sets.newHashSet();
//            all.addAll(regionIds);
//            all.addAll(addressIds);
//            if (!CollectionUtils.isEmpty(all)) {
//                List<SysRegion> list = sysRegionService.list(new QueryWrapper<SysRegion>().in("ID", all).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
//                Map<String, String> collect = list.stream().collect(Collectors.toMap(SysRegion::getId, SysRegion::getRegionName, (old, newValue) -> old + newValue));
//                // 2. 构造返回信息   重新设置区划名称和地址名称
//                if (!CollectionUtils.isEmpty(list)) {
//                    for (SysOrganizationTreeVo tree : treeVoInfoByCondition) {
//                        List<String> regionCodeStrGetList = getListByStr(tree.getRegioncode(), tree.getId());
//                        if (!CollectionUtils.isEmpty(regionCodeStrGetList)) {
//                            String regionName = regionCodeStrGetList.stream().map((e) -> collect.get(e) == null ? "" : collect.get(e)).collect(Collectors.joining(","));
//                            tree.setRegioncodeName(regionName);
//                        }
//                        List<String> addressStrGetList = getListByStr(tree.getAddress(), tree.getId());
//                        if (!CollectionUtils.isEmpty(addressStrGetList)) {
//                            String addressName = addressStrGetList.stream().map((e) -> collect.get(e) == null ? "" : collect.get(e)).collect(Collectors.joining(","));
//                            tree.setAddressName(addressName);
//                        }
//                    }
//                }
//            }
//
//        }
//        return treeVoInfoByCondition;
        return null;
    }

    @Override
    public void deleteByIds(List<String> ids) {
        log.info("删除接口未实现");
    }

    @Override
    public List<SysOrganizationTreeVo> getInfoByParentId(List<String> parentIds) {
//        if (!CollectionUtils.isEmpty(parentIds)) {
//            Map<String, Object> params = Maps.newHashMap();
//            params.put("parentIdList", parentIds);
//            return sysOrganizationMapper.getBasicTreeVoInfoByCondition(params);
//        }
        return null;
    }

    @Override
    public List<SysOrganizationTreeVo> getInfoByLikeParentIds(String parentIds) {
//        if (!StringUtils.isBlank(parentIds)) {
//            Map<String, Object> params = Maps.newHashMap();
//            params.put("parentIds", parentIds);
//            return sysOrganizationMapper.getBasicTreeVoInfoByCondition(params);
//        }
        return null;
    }

    @Override
    public void updateParentId(String parentIds, List<String> ids) {
//        if (!CollectionUtils.isEmpty(ids)) {
//            sysOrganizationMapper.updateParentIdsByIds(parentIds, ids);
//        }
    }
}
