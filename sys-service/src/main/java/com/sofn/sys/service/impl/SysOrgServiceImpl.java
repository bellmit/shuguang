package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.exception.SofnException;
import com.sofn.common.mq.MqMessage;
import com.sofn.common.mq.MqTopics;
import com.sofn.common.mq.rabbit.RabbitMqSendService;
import com.sofn.common.service.BaseService;
import com.sofn.common.tree.TreeUtil;
import com.sofn.common.utils.*;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysOrgMapper;
import com.sofn.sys.mapper.SysSubsystemMapper;
import com.sofn.sys.mapper.SysSystemOrgMapper;
import com.sofn.sys.mapper.SysUserMapper;
import com.sofn.sys.model.*;
import com.sofn.sys.service.SysOrgAgentService;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.permission.PermissionConstant;
import com.sofn.sys.service.permission.PermissionSubSystemService;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@SuppressWarnings("ALL")
public class SysOrgServiceImpl extends BaseService<SysOrgMapper, SysOrg>
        implements SysOrgService {

    @Autowired
    SysOrgMapper sysOrgMapper;

    @Autowired
    SysSystemOrgMapper sysSystemOrgMapper;

    @Autowired
    SysRegionService sysRegionService;

    @Autowired
    private SysSubsystemMapper sysSubsystemMapper;


    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    PermissionSubSystemService permissionSubSystemService;

    @Autowired
    private RabbitMqSendService<SysOrg> rabbitMqSendService;

    @Autowired
    private SysOrgAgentService sysOrgAgentService;

    /**
     * 机构缓存
     */
    public static final String SYS_ORG_CACHE_KEY = PermissionConstant.PERMISSION_ORG_KEY;


    @Override
    public SysOrg getOrgById(String id) {
        if (StringUtils.isBlank(id)){
            throw new NullPointerException("机构id为空");
        }

        SysOrg sysOrg = SysCacheUtils.getCacheOrgById(id);
        if (sysOrg == null) {
            sysOrg = sysOrgMapper.selectById(id);
            if (sysOrg != null) {
                SysCacheUtils.cacheOrgById(sysOrg);
            }
        }

        return sysOrg;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveOrg(SysOrg sysOrg, List<String> appIds) {
        boolean isUpdate = StringUtils.isNotBlank(sysOrg.getId());
        if (checkOrgNameIsExist(sysOrg.getOrganizationName(), sysOrg.getId())) {
            throw new SofnException("组织机构名称重复");
        }

        // 如果不是超级管理员只能在自己的机构下创建机构。
        String loginOrgId = UserUtil.getLoginUserOrganizationId();
        SysOrg parentOrgInfo = sysOrgMapper.selectById(sysOrg.getParentId());
        if(!SysManageEnum.ROOT_ORG.getCode().equals(loginOrgId)){
            if (parentOrgInfo == null) {
                throw new SofnException("父节点信息不存在");
            }
        }

        if (isUpdate) {
            // 如果是更新需要判断parentId是否更新，如果更新了子节点也要跟着更新
            SysOrg oldOrgInfo = sysOrgMapper.selectById(sysOrg.getId());
            if (!oldOrgInfo.getParentId().equals(sysOrg.getParentId())) {
                // 需要更新子节点的parentIds信息
                if (sysOrg.getParentId().equals(sysOrg.getId())) {
                    throw new SofnException("父节点不能是自己");
                }
                // /sysorgroot/fb3d3f140e7c45b1b2363c7936c346f5/a7ce9f4676d94172b2c16d110ddde177
                String oldParentIdsPrefix = oldOrgInfo.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + oldOrgInfo.getId();
                String newParentIdsPrefix = parentOrgInfo.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + parentOrgInfo.getId()
                        + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + sysOrg.getId();
                List<SysOrg> sysOrgs = sysOrgMapper.selectList(new QueryWrapper<SysOrg>().eq("PARENT_ID", sysOrg.getId()).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                if (!CollectionUtils.isEmpty(sysOrgs)) {
                    List<String> sysOrgIds = sysOrgs.stream().map(SysOrg::getId).collect(Collectors.toList());
                    this.replaceParentIds(sysOrgIds, newParentIdsPrefix, oldParentIdsPrefix);
                }
            }
            SysCacheUtils.delCacheOrgByIds(sysOrg.getId());
        }
        String orgType = getOrgType(sysOrg);
        if (BoolUtils.isTrue(orgType)) {
            // 行政机构必须设置区划
            if (StringUtils.isBlank(sysOrg.getRegionLastCode())) {
                throw new SofnException("区划代码不能为空");
            }
            // 行政区划和级别校验
            checkRegionMatchLevel(sysOrg);
        }else {
            // 非行政区划不需要接收区划代码和行政级别
            sysOrg.setRegionLastCode(null);
            sysOrg.setOrganizationLevel(null);
        }

        if (!isUpdate) {
            // 如果是新增，删除已经删除了的同名的机构
            List<SysOrg> sysOrgs = sysOrgMapper.selectList(new QueryWrapper<SysOrg>().eq("organization_name", sysOrg.getOrganizationName()));
            if (!CollectionUtils.isEmpty(sysOrgs)) {
                List<String> orgIdList = sysOrgs.stream().map(SysOrg::getId).collect(Collectors.toList());
                String[] orgIds = new String[orgIdList.size()];
                orgIdList.toArray(orgIds);
                deleteOrgByIds(orgIds);
            }
        }

        sysOrg.setThirdOrg(orgType);
        sysOrg.setParentIds(buildPrantIds(sysOrg.getParentId()));
        setRegionChain(sysOrg);
        if (saveOrUpdate(sysOrg)) {
            // 默认把该机构归属到支撑平台，否则支撑平台无权限查看该机构
            // 如果是修改 需要判断子系统是否减少了，如果减少了，后面的子节点也要跟着减少，否则会出现显示混乱的情况
            if (isUpdate) {
                // 旧的子系统
                List<String> appIdsByOrgId = this.getAppIdsByOrgId(sysOrg.getId());
                if (!CollectionUtils.isEmpty(appIdsByOrgId)) {
                    // 找出新得AppId中不存在了的系统
                    List<String> notExistsAppId = appIdsByOrgId.stream().filter(item -> !appIds.contains(item)).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(notExistsAppId)) {
                        // 找出子机构 然后组装删除数据   这里不区分子系统
                        Set<String> childrenOrgByIds = this.getChildrenOrgByIds(Lists.newArrayList(sysOrg.getId()), null);
                        List<Map<String, Object>> deleteParams = Lists.newArrayList();
                        if (!CollectionUtils.isEmpty(childrenOrgByIds)) {
                            // 组装需要删除的数据结构   ORG_ID  -> List<AppId>
                            childrenOrgByIds.forEach(item -> {
                                Map<String, Object> maps = Maps.newHashMap();
                                maps.put("orgId", item);
                                maps.put("appIds", notExistsAppId);
                                deleteParams.add(maps);
                            });
                            // 删除数据
                            sysSystemOrgMapper.deleteByOrgIdAndAppIds(deleteParams);
                        }
                    }
                }
            }
            sysSystemOrgMapper.delete(new UpdateWrapper<SysSystemOrg>()
                    .eq("ORG_ID", sysOrg.getId()));
            List<SysSystemOrg> systemOrgList = Lists.newArrayList();
            // APPID去重
            Set<String> appIdSets = Sets.newHashSet(appIds);
            appIdSets.forEach(appId -> {
                if (!StringUtils.isBlank(appId)) {
                    systemOrgList.add(new SysSystemOrg(IdUtil.getUUId(), appId, sysOrg.getId()));
                }
            });
            Optional<SysSystemOrg> first = systemOrgList.stream().filter(item -> "sys".equals(item.getAppId())).findFirst();
            if (!first.isPresent()) {
                systemOrgList.add(new SysSystemOrg(IdUtil.getUUId(), "sys", sysOrg.getId()));
            }
            sysSystemOrgMapper.saveBatch(systemOrgList);
        } else {
            throw new SofnException("保存获取修改机构失败");
        }

        try {
            if (!CollectionUtils.isEmpty(appIds)) {
                // 发送mq消息
                rabbitMqSendService.send(isUpdate ? MqTopics.SYS_ORG_UPDATE : MqTopics.SYS_ORG_ADD, appIds, MqMessage.ok(sysOrg));
            }
        } catch (Exception e) {
            log.error("给子系统发送消息失败");
        }

        return sysOrg.getId();
    }

    @Override
    public List<SysOrg> listOfAllByAppId(String appId) {
        return sysOrgMapper.selectAllByAppId(appId, null, null);
    }

    @Override
    public List<SysOrgVo> listOfUserByParentId(String parentId, String appId) {
        List<String> appIds = getUserAppIds();
        if (StringUtils.isNotBlank(appId)) {
            if (!appIds.contains(appId)) {
                return new ArrayList<>();
            }

            appIds = new ArrayList<>();
            appIds.add(appId);
        }

        List<SysOrg> list = null;
        // 根据APPID查询用户可见的组织机构,parentId空为一级目录
        if (StringUtils.isBlank(parentId)) {
            list = getRootOrgListByAppids(appIds, UserUtil.getLoginUserOrganizationId());
        } else {
            list = sysOrgMapper.selectByParentIdAndAppIds(parentId, appIds);
        }

        return getVoList(list);
    }

    @Override
    public PageInfo<SysOrgVo> listOfUserByParentIdForPaging(String parentId, String appId, Integer pageNo, Integer pageSize) {
        boolean isPaging = pageNo != null && pageSize != null && pageSize > 0;
        if (!isPaging) {
            throw new SofnException("分页参数不正确");
        }

        List<String> appIds = getUserAppIds();
        if (StringUtils.isNotBlank(appId)) {
            if (!appIds.contains(appId)) {
                return new PageInfo<>();
            }

            appIds = new ArrayList<>();
            appIds.add(appId);
        }

        PageHelper.startPage(pageNo, pageSize);
        List<SysOrg> list = null;
        // 根据APPID查询用户可见的组织机构,parentId空为一级目录
        if (StringUtils.isBlank(parentId)) {
            list = getRootOrgListByAppids(appIds, UserUtil.getLoginUserOrganizationId());
        } else {
            list = sysOrgMapper.selectByParentIdAndAppIds(parentId, appIds);
        }

        List<SysOrgVo> volist = getVoList(list);
        PageInfo<SysOrgVo> pageInfo = new PageInfo(volist);
        return pageInfo;
    }

    @Override
    public List<SysOrgVo> listOfUserTree(Map<String, String> params) {
        String appId = params.get("appId");
        String isAuth = params.get("isAuth");
        String orgName = params.get("orgName");
        String regionCode = params.get("regionCode");
        String thirdOrg = params.get("thirdOrg");
        String orgLevel = params.get("orgLevel");
        String isChildren = params.get("isChildren");

        List<String> appIds = new ArrayList<>();
        if (StringUtils.isNotBlank(appId)) {
            appIds.add(appId);
        } else {
            appIds = getUserAppIds();
        }
        String loginUserOrgId = "";
        if (BoolUtils.Y.equals(isAuth)) {
            loginUserOrgId = UserUtil.getLoginUserOrganizationId();
        }
        // 按条件查询节点
        log.info("按条件查询");
        List<SysOrg> userOrgList = sysOrgMapper.selectByAppIdsOfUser(appIds, loginUserOrgId, orgName, regionCode, thirdOrg, orgLevel);
        if (CollectionUtils.isEmpty(userOrgList)) {
            return new ArrayList<>();
        }
        log.info("按条件查询结束");

        // 如果传入需要子节点，那么就携带子节点

        if (BoolUtils.Y.equals(isChildren)) {
            log.info("查询子机构");
            List<String> collect = userOrgList.stream()
                    .filter(sysOrg -> !SysManageEnum.ROOT_ORG.getCode().equals(sysOrg.getId()))
                    .map(SysOrg::getId).collect(Collectors.toList());
            Set<String> childrenOrgByIds = Sets.newHashSet();
            if (!CollectionUtils.isEmpty(collect)) {
                // 所有待寻找ParentIds
                List<String> parentIds = Lists.newArrayList();
                List<String> oldParentIds = userOrgList.stream().map(item -> {
                    return item.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + item.getId();
                }).collect(Collectors.toList());
                oldParentIds.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() - o2.length();
                    }
                });
                oldParentIds.forEach(item -> {
                    Optional<String> first = parentIds.stream().filter(childParentId -> item.contains(childParentId)).findFirst();
                    if (!first.isPresent()) {
                        parentIds.add(item);
                    }
                });
                if (!CollectionUtils.isEmpty(parentIds)) {
                    GetAllInfoByPageUtil<String> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>(500);
                    List<String> allChildId = getAllInfoByPageUtil.getAllInfo((param) -> {
                        return Lists.newArrayList(sysOrgMapper.getChildrenOrgByIds((List) param[0], (String) param[1]));
                    }, 0, false, "", parentIds, appId);
                    // 移除已有的
                    boolean b = allChildId.removeAll(collect);
                    if (b) {
                        childrenOrgByIds.addAll(allChildId);
                    }
                }
            }
            if (!CollectionUtils.isEmpty(childrenOrgByIds)) {
                // 分批次查询
                GetAllInfoByPageUtil<SysOrg> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
                List<SysOrg> sysOrgs = getAllInfoByPageUtil.getAllInfo((param) -> {
                    return sysOrgMapper.selectBatchIds((Collection<? extends Serializable>) param[0]);
                }, 0, false, "", childrenOrgByIds);
                userOrgList.addAll(sysOrgs);
            }
            log.info("查询子机构结束");
        }

        List<SysOrg> realSysOrg = userOrgList;
        // 如果传入行政区划就将当前用户的下级所有机构匹配区划
        if (!StringUtils.isBlank(regionCode)) {
            log.info("筛选行政机构");
            String finalLoginUserOrgId = loginUserOrgId;
            // 总是加上根节点
            Optional<SysOrg> first = userOrgList.stream().filter(item -> SysManageEnum.ROOT_ORG.getCode().equals(item.getId())).findFirst();
            realSysOrg = userOrgList.stream()
                    .filter(item -> item.getRegionLastCode().equals(regionCode))
                    .collect(Collectors.toList());
            if (first.isPresent()) {
                realSysOrg.add(first.get());
            }
            log.info("筛选行政机构结束");
        }
        TreeUtil<SysOrgVo> treeUtil = new TreeUtil<>();
        log.info("开始获取VO");
        List<SysOrgVo> voList = getVoList(realSysOrg);
        log.info("VO获取成功");

        List<SysOrgVo> nodeList = treeUtil.generate(voList);
        log.info("树结构生成成功");
        return nodeList;
    }

    @Override
    public List<SysOrgVo> getDirectOrgByRegionCode(String regionCode) {
        List<SysOrg> sysOrgList = sysOrgMapper.selectList(new QueryWrapper<SysOrg>()
                .eq("REGION_LAST_CODE", regionCode)
                .eq("THIRD_ORG", BoolUtils.Y)
                .eq("DEL_FLAG", BoolUtils.N));
        return getVoList(sysOrgList);
    }

    @Override
    public List<String> getAppIdsByOrgId(String orgId) {
        try {
            if (StringUtils.isBlank(orgId)) {
                return Lists.newArrayList();
            }
            List<SysSystemOrg> list = sysSystemOrgMapper.getInfoByOrgId(orgId);

            if (CollectionUtils.isEmpty(list)) {
                return new ArrayList<>();
            }

            Set<String> appIdList = new HashSet<>();
            list.forEach(new Consumer<SysSystemOrg>() {
                @Override
                public void accept(SysSystemOrg sysSystemOrg) {
                    appIdList.add(sysSystemOrg.getAppId());
                }
            });

            return new ArrayList<>(appIdList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SysRegionInfoVo getSysRegionInfoByOrgId(String orgId) {
        if (!StringUtils.isBlank(orgId)) {
            SysOrg sysOrg = getOneById(orgId);
            if (sysOrg != null && StringUtils.isNotBlank(sysOrg.getRegionLastCode())) {
                return sysRegionService.getSysRegionInfoById(sysOrg.getRegionLastCode());
            }
        }

        return null;
    }

    @Override
    public List<SysOrgVo> getAllByAppId(String appId, String orgname, String regionid) {
        List<SysOrg> list = sysOrgMapper.selectAllByAppId(appId, orgname, regionid);
        List<SysOrgVo> voList = getVoList(list);
        TreeUtil<SysOrgVo> treeUtil = new TreeUtil<>();
        return treeUtil.generate(voList);
    }

    @Override
    public PageUtils<SysOrg> getOrgByAddressRegionCode(String addressRegionCode, Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo, pageSize);
        List<SysOrg> sysOrganizationList = sysOrgMapper.selectList(new QueryWrapper<SysOrg>()
                .eq("ADDRESS_LAST_CODE", addressRegionCode)
                .eq("THIRD_ORG", BoolUtils.N)
                .eq("DEL_FLAG", BoolUtils.N));
        PageInfo<SysOrg> pageInfo = new PageInfo<SysOrg>(sysOrganizationList);
        return PageUtils.getPageUtils(pageInfo);
    }

    @Override
    public List<Map<String, String>> getOrgInfoByIdsAndName(String ids, String name, Integer pageNo, Integer pageSize) {
        List<Map<String, String>> list = Lists.newArrayList();
        List<String> idList = IdUtil.getIdsByStr(ids);
        if (pageSize == null || pageSize == 0) {
            // 不分页
            list = sysOrgMapper.getInfoByCondition(name, idList);
        } else {
            // 分页
            PageHelper.startPage(pageNo, pageSize);
            List<Map<String, String>> infoByCondition = sysOrgMapper.getInfoByCondition(name, idList);
            PageInfo<Map<String, String>> pageInfo = new PageInfo(infoByCondition);
            list = pageInfo.getList();
        }

        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(new Consumer<Map<String, String>>() {
                @Override
                public void accept(Map<String, String> map) {
                    String parentIds = map.get("parent_ids");
                    String parentNames = map.get("parent_names");
                    String regionCode = map.get("region_code");
                    String regionName = map.get("region_name");
                    List<String> ids = sysRegionService.getFormatIdsOrNames(parentIds, regionCode, "ID");
                    List<String> names = sysRegionService.getFormatIdsOrNames(parentNames, regionName, "NAME");
                    map.put("ADDRESS", JsonUtils.obj2json(ids));
                    map.put("ADDRESSNAME", StringUtils.join(names, ","));
                }
            });
        }

        return list;
    }

    @Override
    public SysOrganizationForm getInfoById(String id) {
        SysOrg org = sysOrgMapper.selectById(id);

        SysOrganizationForm form = new SysOrganizationForm();
        if (org != null) {
            BeanUtils.copyProperties(org, form);

            SysRegionInfoVo regionInfoVo = sysRegionService.getSysRegionInfoById(org.getRegionLastCode());
            SysRegionInfoVo addressRegionInfoVo = sysRegionService.getSysRegionInfoById(org.getAddressLastCode());
            if (form != null) {
                form.setRegionInfoVo(regionInfoVo);
                form.setAddressRegionInfoVo(addressRegionInfoVo);
            }
        }
        return form;
    }

    @Override
    public List<SysOrgVo> getAllThridOrgList(String appId) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("thridOrg", "N");
        params.put("appId", appId);
        return sysOrgMapper.selectJoin(params);
    }

    @Override
    public List<SysOrganizationTreeVo> getProxyOrgByRegionIdAndAppId(String regionId, String appId, String orgId) {
        if (!StringUtils.isBlank(regionId)) {
            return sysOrgMapper.getProxyOrgByRegionIdAndAppId(regionId, appId, orgId);
        }
        return null;
    }

    @Override
    public List<SysOrg> getOrgInfoByOrgFunction(String orgFunction, String appId) {
        if (!StringUtils.isBlank(orgFunction)) {
            List<SysOrg> orgFunctionList = sysOrgMapper.selectByAppIdAndFunctionCode(appId, orgFunction);
            if (CollectionUtils.isEmpty(orgFunctionList)) {
                return Lists.newArrayList();
            }

            return orgFunctionList;
        }

        return Lists.newArrayList();
    }

    @Override
    public List<SysOrg> getOrgByRegionAndLevel(String appId, String region, String level) {
        List<SysOrg> sysOrgs = sysOrgMapper.selectByRegionAndLevel(appId, region, level);
        System.out.println(sysOrgs);
        return sysOrgs;
    }

    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(30);

    @Override
    public List<SysOrgVo> getVoList(List<SysOrg> list) {

        List<List> lists = GetAllInfoByPageUtil.subList(list, 100);
        if (CollectionUtils.isEmpty(lists)) {
            return null;
        }
        CountDownLatch countDownLatch = new CountDownLatch(lists.size());
        List<SysOrgVo> allList = Lists.newArrayList();
        lists.forEach(item -> {
            cachedThreadPool.execute(() -> {
                List<SysOrgVo> sysOrgVos = getSysOrgVos(item);
                allList.addAll(sysOrgVos);
                countDownLatch.countDown();
            });

        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allList;
    }

    @NotNull
    private List<SysOrgVo> getSysOrgVos(List<SysOrg> list) {
        List<SysOrgVo> voList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            // 查出所有的行政区划
            Set<String> regionCode = list.stream().map(SysOrg::getRegionLastCode).collect(Collectors.toSet());
            // 查询所有的地址信息
            Set<String> addressSet = list.stream().map(SysOrg::getAddressLastCode).collect(Collectors.toSet());
            regionCode.addAll(addressSet);

            Collection<SysRegion> sysRegions = null;
            if (!CollectionUtils.isEmpty(regionCode)) {
                try {
                    Map<String, Object> params = Maps.newHashMap();
                    params.put("ids", regionCode);
                    Long maxVersionCodeByYear = sysRegionService.getMaxVersionCodeByYear(null);
                    params.put("versionCode", maxVersionCodeByYear);
                    sysRegions = sysRegionService.getSysRegionByContion(params);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Map<String, SysRegion> sysRegionMap = Maps.newHashMap();
            if (!CollectionUtils.isEmpty(sysRegions)) {
                sysRegions.forEach(item -> {
                    if (!sysRegionMap.containsKey(item.getId())) {
                        sysRegionMap.put(item.getRegionCode(), item);
                    }
                });
            }
            // 根据机构ID查询已有的系统信息
            List<String> collect = list.stream().filter(item -> !StringUtils.isBlank(item.getId())).map(SysOrg::getId).collect(Collectors.toList());
            List<SysSystemOrg> sysSystemOrgs = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(collect)) {
                GetAllInfoByPageUtil<SysSystemOrg> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
                sysSystemOrgs = getAllInfoByPageUtil.getAllInfo((param) -> {
                    return sysSystemOrgMapper.selectList(new QueryWrapper<SysSystemOrg>().in("ORG_ID", (List) param[0]));
                }, 0, false, "", collect);
            }

            Map<String, List<String>> orgAndAppIds = Maps.newHashMap();
            if (!CollectionUtils.isEmpty(sysSystemOrgs)) {
                List<SysSystemOrg> finalSysSystemOrgs = sysSystemOrgs;
                collect.forEach(item -> {
                    if (!StringUtils.isBlank(item)) {
                        orgAndAppIds.put(item, finalSysSystemOrgs.stream().filter(e -> item.equals(e.getOrgId())).map(SysSystemOrg::getAppId).collect(Collectors.toList()));
                    }
                });
            }

            list.forEach(sysOrg -> {
                SysOrgVo vo = new SysOrgVo();
                BeanUtils.copyProperties(sysOrg, vo);
                if (StringUtils.isNotBlank(sysOrg.getRegionLastCode())) {
                    // 是根节点直接显示根节点信息
                    if (SysManageEnum.ROOT_LEVEL.getCode().equals(sysOrg.getRegionLastCode())) {
                        vo.setRegionFullCode(Lists.newArrayList(SysManageEnum.ROOT_LEVEL.getCode()));
                        vo.setRegionFullName(Lists.newArrayList(SysManageEnum.ROOT_LEVEL.getDescription()));
                    } else {
                        SysRegion region = sysRegionMap.get(sysOrg.getRegionLastCode());
                        if (region != null) {
                            // 这里面的方法没有再次查询数据库，所以还是使用之前的逻辑
                            List<String> ids = sysRegionService.getFormatIdsOrNames(region.getParentIds(), region.getRegionCode(), "ID");
                            List<String> names = sysRegionService.getFormatIdsOrNames(region.getParentNames(), region.getRegionName(), "NAME");
                            vo.setRegionFullCode(ids);
                            vo.setRegionFullName(names);
                        }
                    }
                }
                if (StringUtils.isNotBlank(sysOrg.getAddressLastCode())) {
                    SysRegion region = sysRegionMap.get(sysOrg.getAddressLastCode());
                    if (region != null) {
                        List<String> ids = sysRegionService.getFormatIdsOrNames(region.getParentIds(), region.getRegionCode(), "ID");
                        List<String> names = sysRegionService.getFormatIdsOrNames(region.getParentNames(), region.getRegionName(), "NAME");
                        vo.setAddressFullCode(ids);
                        vo.setAddressFullName(names);
                    }
                }
                vo.setAppIds(orgAndAppIds.get(sysOrg.getId()));
                if (StringUtils.isNotBlank(sysOrg.getOrganizationLevel())) {
                    vo.setOrganizationLevelName(SysConstant.ORG_LEVEL.get(sysOrg.getOrganizationLevel()));
                }
                voList.add(vo);

            });
        }
        return voList;
    }

    @Override
    public List<SysOrg> getInfoByCondition(Map<String, Object> params) {
        QueryWrapper<SysOrg> sysOrgQueryWrapper = new QueryWrapper<>();
        try {
            if (!CollectionUtils.isEmpty(params)) {
                // 按照ID批量查询
                if (params.get("ids") != null) {
                    List<String> ids = (List<String>) params.get("ids");
                    if (!CollectionUtils.isEmpty(ids)) {
                        sysOrgQueryWrapper.in("ID", ids);
                    }
                }
                // 查询是否第三方
                if (params.get("thirdOrg") != null) {
                    String thirdOrg = params.get("thirdOrg").toString();
                    if (StringUtils.isNotBlank(thirdOrg)) {
                        sysOrgQueryWrapper.eq("third_Org", thirdOrg);
                    }
                }
                // 根据ID查询
                if (params.get("id") != null) {
                    String id = params.get("id").toString();
                    if (StringUtils.isNotBlank(id)) {
                        sysOrgQueryWrapper.eq("ID", id);
                    }
                }
                if (params.get("orgNames") != null) {
                    List<String> orgNames = (List<String>) params.get("orgNames");
                    if (!CollectionUtils.isEmpty(orgNames)) {
                        sysOrgQueryWrapper.in("ORGANIZATION_NAME", orgNames);
                    }
                }
                if (params.get("parentIdStrs") != null) {
                    String parentIdStrs = params.get("parentIdStrs").toString();
                    if (StringUtils.isNotBlank(parentIdStrs)) {
                        sysOrgQueryWrapper.eq("PARENT_IDS", parentIdStrs);
                    }
                }
            }
            List<SysOrg> sysOrgs = sysOrgMapper.selectList(sysOrgQueryWrapper);
            if (!CollectionUtils.isEmpty(sysOrgs)) {
                return sysOrgs.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("机构按条件查询异常");
        }
    }

    @Override
    public List<SysOrgVo> getOrgInfoByAppIdAndFunctionCode(String appId, String functionCode) {
        List<SysOrg> list = sysOrgMapper.getOrgInfoByAppIdAndFunctionCode(appId, functionCode);
        return getVoList(list);
    }

    @Override
    public List<SysOrg> getOrgInfoByAppIdAndParentIdAndOtherParam(String appId, String parentId, Map<String, Object> params) {
        return sysOrgMapper.getOrgInfoByAppIdAndParentIdAndOtherParam(appId, parentId, params);
    }

    @Override
    public List<SysOrgVo> getOrgInfoByParentIdAndAppId(String parentIdStr, List<String> appIds) {
        if (StringUtils.isBlank(parentIdStr) || CollectionUtils.isEmpty(appIds)) {
            throw new SofnException("parentIdStr或者appIds不能为空");
        }

        return sysOrgMapper.getOrgInfoByParentIdAndAppId(parentIdStr, appIds);
    }

    @Override
    public List<SysOrg> getOrgIdByRegionCodeAndAppId(String appId, List<String> regionCodes, String orgLevel) {
        if (StringUtils.isBlank(appId)) {
            throw new SofnException("系统代码不能为空");
        }

        final List<SysOrg> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(regionCodes)) {
            List<SysOrg> list = sysOrgMapper.getOrgIdByRegionCodeAndAppId(appId, null, orgLevel);
            if (!CollectionUtils.isEmpty(list)) {
                resultList.addAll(list);
            }
        }else {
            regionCodes.forEach(new Consumer<String>() {
                @Override
                public void accept(String code) {
                    List<SysOrg> list = sysOrgMapper.getOrgIdByRegionCodeAndAppId(appId, code, orgLevel);
                    if (!CollectionUtils.isEmpty(list)) {
                        resultList.addAll(list);
                    }
                }
            });
        }

        return resultList;
    }

    @Override
    public void replaceParentIds(List<String> ids, String newParentIdsPrefix, String oldParentIdsPrefix) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        if (StringUtils.isBlank(newParentIdsPrefix) || StringUtils.isBlank(oldParentIdsPrefix)) {
            return;
        }
        sysOrgMapper.replaceParentIds(ids, newParentIdsPrefix, oldParentIdsPrefix);
    }

    @Override
    public List<SysOrg> selectByAppId(String appId, String parentId, String level) {
        return sysOrgMapper.selectByAppId(appId, parentId, level);
    }

    @Override
    public Map<String, String> getOrgLocation(String id) {
      Map<String, String> map = new HashMap<>();
      SysOrg org = sysOrgMapper.selectById(id);
      if (org != null) {
        String lotd = null;
        String latd = null;
        if (StringUtils.isBlank(org.getLongitude()) || StringUtils.isBlank(org.getLatitude())){
          lotd = org.getLongitude();
          latd = org.getLatitude();
        }else {
          SysRegion region = sysRegionService.getOneByCode(StringUtils.isNotBlank(org.getRegionLastCode())?org.getRegionLastCode():org.getAddressLastCode(), 0);
          if (region != null) {
            lotd = region.getLongitude();
            latd = region.getLatitude();
          }
        }

        map.put("longitude",lotd);
        map.put("latitude",latd);
      }

      return map;
    }

    @Override
    public void deleteOrgByIds(String... ids) {
        sysOrgMapper.deleteBatchIds(Arrays.asList(ids));
        SysCacheUtils.delCacheOrgByIds(ids);
    }


    /**
     * 获取登录用户所属系统的appid集合
     *
     * @return APPID集合
     */
    public List<String> getUserAppIds() {
        // 获取用户所属系统
        return permissionSubSystemService.getUserAppIds();
    }

    /**
     * 根据及用户组织机构id，appids获取根节点
     *
     * @param appIds    用户所属系统APPID集合
     * @param userOrgId 用户所属机构id
     * @return
     */
    private List<SysOrg> getRootOrgListByAppids(List<String> appIds, String userOrgId) {
        if (StringUtils.isBlank(userOrgId)) {
            throw new SofnException("用户机构ID为空");
        }

        List<SysOrg> list = sysOrgMapper.selectByAppIdsOfUser(appIds, userOrgId, null, null, null, null);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        Set<String> ids = new HashSet<>();
        for (SysOrg so : list) {
            ids.add(so.getId());
        }

        List<SysOrg> rootOrgList = new ArrayList<>();
        for (SysOrg so : list) {
            if (!ids.contains(so.getParentId())) {
                rootOrgList.contains(so);
            }
        }

        return rootOrgList;
    }

    /**
     * 检查组织机构名称是否重复
     *
     * @param name
     * @param id
     */
    @Override
    public boolean checkOrgNameIsExist(String name, String id) {
        Integer count = 0;
        if (StringUtils.isNotBlank(id)) {
            // 修改
            count = sysOrgMapper.selectCount(new QueryWrapper<SysOrg>()
                    .ne("DEL_FLAG", BoolUtils.Y)
                    .eq("ORGANIZATION_NAME", name)
                    .ne("ID", id));
        } else {
            // 添加
            count = sysOrgMapper.selectCount(new QueryWrapper<SysOrg>()
                    .ne("DEL_FLAG", BoolUtils.Y)
                    .eq("ORGANIZATION_NAME", name));
        }

        return count > 0;
    }

    @Override
    public List<SysOrgVo> getAllOrgListAndLoginUserId(List<String> appIds, boolean carryChildrenOrg, boolean carryProxyOrg) {
        List<SysOrg> userHaveSysOrgInfo = getUserHaveSysOrgInfo(appIds, carryChildrenOrg, carryProxyOrg);
        return getVoList(userHaveSysOrgInfo);

    }

    @Override
    public List<String> getUserHaveOrgIds(List<String> appIds, boolean carryChildrenOrg, boolean carryProxyOrg) {

        // getUserHaveSysOrgInfo 方法会判断appIds是否为空
        String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
        if(StringUtils.isBlank(loginUserOrganizationId)){
            throw new SofnException("当前用户异常，不属于任何机构");
        }
        String carryChildrenOrgStr = carryChildrenOrg ? BoolUtils.Y : BoolUtils.N;
        String carryProxyOrgStr =  carryProxyOrg ? BoolUtils.Y : BoolUtils.N;


        return sysOrgMapper.getOrgCanShowOrgIds(loginUserOrganizationId, appIds,carryChildrenOrgStr,carryProxyOrgStr );
    }

    /**
     * 根据系统代码获取用户有的机构
     * @param appIds  系统代码
     * @param carryChildrenOrg   是否携带子节点
     * @param carryProxyOrg  是否携带代理机构
     * @return List<SysOrg>
     */
    private List<SysOrg> getUserHaveSysOrgInfo(List<String> appIds, boolean carryChildrenOrg, boolean carryProxyOrg) {
        // 1. 获取当前登录用户有的子系统
        List<String> userAppIds = permissionSubSystemService.getUserAppIds();
        if (CollectionUtils.isEmpty(userAppIds)) {
            log.warn("用户不属于任何系统");
            return Lists.newArrayList();
        }
        if(!CollectionUtils.isEmpty(appIds)){
            // 2. 筛选appId，
            // 找出传入的但是用户没有的
            List<String> userDontHaveAppId = Lists.newArrayList(appIds);
            userDontHaveAppId.removeAll(userAppIds);
            if (!CollectionUtils.isEmpty(userDontHaveAppId)) {
                log.info("用户没有这些系统的权限：{}", userDontHaveAppId);
                appIds.removeAll(userDontHaveAppId);
            }
            if (CollectionUtils.isEmpty(appIds)) {
                throw new SofnException("传入的系统用户都没有权限");
            }
        }else{
            appIds = userAppIds;
        }
        // 根据系统和当前用户有的系统筛选出子系统
        String loginUserOrganizationId = UserUtil.getLoginUserOrganizationId();
        SysOrg sysOrg = sysOrgMapper.selectById(loginUserOrganizationId);
        if (sysOrg == null) {
            throw new SofnException("当前用户不属于任何机构！");
        }
        List<SysOrg> orgAndChildrenOrg = Lists.newArrayList();


        if (SysManageEnum.ROOT_ORG.getCode().equals(sysOrg.getId())) {
            // 如果是超级管理员  直接返回- 表示所有
            List<SysOrg> sysOrgs = sysOrgMapper.selectByParentIdAndAppIds(null, appIds);
            if (!CollectionUtils.isEmpty(sysOrgs)) {
                orgAndChildrenOrg.addAll(sysOrgs);
            }
        } else {
            orgAndChildrenOrg.add(sysOrg);
            // 查询子节点
            if (carryChildrenOrg) {
                String parentIdStrings = null;
                if (!SysManageEnum.ROOT_ORG.getCode().equals(sysOrg.getId())) {
                    // 如果不是根节点，带上parentIdStrings
                    parentIdStrings = sysOrg.getParentIds() +  SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + sysOrg.getId() ;
                }
                List<SysOrg> sysOrgList = sysOrgMapper.getChildInfoByParentIdsAndAppIds(parentIdStrings,appIds);
                if(!CollectionUtils.isEmpty(sysOrgList)){
                    orgAndChildrenOrg.addAll(sysOrgList);
                }
            }
            if (carryProxyOrg) {
                List<String> orgIds = orgAndChildrenOrg.stream().map(SysOrg::getId).collect(Collectors.toList());
                // 查询代理机构信息
                List<SysSubsystem> sysSubsystems = sysSubsystemMapper.selectList(new QueryWrapper<SysSubsystem>()
                        .eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode())
                        .in("APP_ID", appIds)
                );
                List<String> subSystemIds = sysSubsystems.stream().map(SysSubsystem::getId).collect(Collectors.toList());
                List<String> proxyOrgIds = sysOrgAgentService.selectOrgAgentByOrgId(subSystemIds, orgIds, null);
                if (CollectionUtils.isEmpty(proxyOrgIds)) {
                    proxyOrgIds = Lists.newArrayList();
                }
                List<String> proxyOrgIds2 = sysOrgAgentService.selectOrgAgentByOrgId(appIds, orgIds, null);
                if (!CollectionUtils.isEmpty(proxyOrgIds2)) {
                    proxyOrgIds.addAll(proxyOrgIds2);
                }
                if (!CollectionUtils.isEmpty(proxyOrgIds)) {
                    // 去掉已查询出来了的
                    proxyOrgIds.removeAll(orgIds);
                    // 根据ID查询出机构信息
                    if(!CollectionUtils.isEmpty(proxyOrgIds)){
                        List<SysOrg> sysOrgs = sysOrgMapper.selectList(new QueryWrapper<SysOrg>().in("ID", proxyOrgIds).eq("DEL_FLAG", SysManageEnum.DEL_FLAG_N.getCode()));
                        if(!CollectionUtils.isEmpty(sysOrgs)){
                            orgAndChildrenOrg.addAll(sysOrgs);
                        }
                    }

                }
            }
        }
        return orgAndChildrenOrg;
    }

    @Override
    public List<SysOrgVo> listByAppIdAndIds(String appId, List<String> ids) {
        List<SysOrg> list = sysOrgMapper.selectListByAppIdAndIds(appId, ids);
        return getVoList(list);
    }

    @Override
    public Set<String> getChildrenOrgByIds(List<String> orgIds, String appId) {
        if (!CollectionUtils.isEmpty(orgIds)) {
            // 1. 获取节点
            GetAllInfoByPageUtil<String> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>();
            List<String> allInfo = getAllInfoByPageUtil.getAllInfo((param) -> {
                Set<String> parentIdsByOrgIds = sysOrgMapper.getParentIdsByOrgIds((List) param[0]);
                return Lists.newArrayList(parentIdsByOrgIds);
            }, 0, false, "", orgIds);
            Set<String> parentIdsByOrgIds = Sets.newHashSet(allInfo);


            if (!CollectionUtils.isEmpty(parentIdsByOrgIds)) {
                GetAllInfoByPageUtil<String> getAllInfoByPageUtil2 = new GetAllInfoByPageUtil<>(500);
                List allInfo1 = getAllInfoByPageUtil2.getAllInfo((param) -> {
                    return Lists.newArrayList(sysOrgMapper.getChildrenOrgByIds((List) param[0], (String) param[1]));
                }, 0, false, "", Lists.newArrayList(parentIdsByOrgIds), appId);
                return Sets.newHashSet(allInfo1);
            }
        }
        return null;
    }

    @Override
    public List<SysOrgVo> listTreeByAppId(String appId, String parentId, String level) {
        if (StringUtils.isBlank(appId)) {
            throw new SofnException("APPID不能为空");
        }

        List<SysOrg> list = sysOrgMapper.selectByAppId(appId, parentId, level);
        return getVoList(list);
    }

    @Override
    public List<SysOrgVo> listByAppId(String appId, String parentId) {
        if (StringUtils.isBlank(appId)) {
            throw new SofnException("APPID不能为空");
        }

        List<SysOrg> list = sysOrgMapper.selectDrillByAppId(appId, parentId);
        return getVoList(list);
    }


    /**
     * 检查是否和父级机构匹配
     *
     * @param sysOrg
     */
    private String getOrgType(SysOrg sysOrg) {
        if (!StringUtils.equals(SysManageEnum.ROOT_ORG.getCode(), sysOrg.getParentId())) {
            SysOrg parentOrg = sysOrgMapper.selectById(sysOrg.getParentId());
            if (parentOrg == null) {
                throw new SofnException("父级机构不存在");
            }

            if (StringUtils.isBlank(parentOrg.getThirdOrg())) {
                throw new SofnException("父级机构类型不存在");
            }

            return parentOrg.getThirdOrg();
        } else {
            if (StringUtils.isBlank(sysOrg.getThirdOrg())) {
                throw new SofnException("机构类型不能为空");
            }
            return sysOrg.getThirdOrg();
        }
    }

    /**
     * 设置区划和地址的完整省市县json串
     *
     * @param sysOrg
     */
    private void setRegionChain(SysOrg sysOrg) {
        if (StringUtils.equalsIgnoreCase(sysOrg.getThirdOrg(), BoolUtils.Y)) {
            if (StringUtils.isBlank(sysOrg.getRegionLastCode())) {
                throw new SofnException("last region code is null");
            }
            SysRegion sysRegion = sysRegionService.getOneByCode(sysOrg.getRegionLastCode(),0);
            if (sysRegion == null) {
                throw new SofnException("not found region");
            }

            // 行政区划根节点不需要构建省市区串
            if (!StringUtils.equalsIgnoreCase(sysRegion.getRegionCode(), SysManageEnum.ROOT_LEVEL.getCode())) {
                List<String> codes = sysRegionService.getFormatIdsOrNames(sysRegion.getParentIds(), sysRegion.getRegionCode(), "ID");
                if (CollectionUtils.isEmpty(codes)) {
                    throw new SofnException("parentIds is not exits");
                }

                sysOrg.setRegioncode(JsonUtils.obj2json(codes));
            }
        }

        if (StringUtils.isBlank(sysOrg.getAddressLastCode())) {
            throw new SofnException("last address code is null");
        }
        SysRegion sysRegion = sysRegionService.getOneByCode(sysOrg.getAddressLastCode(),0);
        if (sysRegion == null) {
            throw new SofnException("not found region");
        }

        List<String> codes = sysRegionService.getFormatIdsOrNames(sysRegion.getParentIds(), sysRegion.getRegionCode(), "ID");
        if (CollectionUtils.isEmpty(codes)) {
            throw new SofnException("parentIds is not exits");
        }

        sysOrg.setAddress(JsonUtils.obj2json(codes));
    }

    /**
     * 校验机构是否和级别匹配
     *
     * @param sysOrg
     */
    private void checkRegionMatchLevel(SysOrg sysOrg) {
        if (!sysRegionService.checkRegionLevel(sysOrg.getRegionLastCode(), sysOrg.getOrganizationLevel())) {
            throw new SofnException("regionLastCode and level do not match");
        }
    }

    /**
     * 构建父级路由
     *
     * @param parentId 父ID
     * @return 路由
     */
    private String buildPrantIds(String parentId) {
        if (StringUtils.isBlank(parentId)
                || StringUtils.equalsIgnoreCase(parentId, SysManageEnum.ROOT_ORG.getCode())) {
            return CommonEnum.SLASH.getCode() + SysManageEnum.ROOT_ORG.getCode();
        } else {
            SysOrg parentOrg = sysOrgMapper.selectById(parentId);
            if (parentOrg == null || StringUtils.isBlank(parentOrg.getParentIds())) {
                throw new SofnException("parentId or parntIds is not exist");
            }

            return String.format("%s" + CommonEnum.SLASH.getCode() + "%s", parentOrg.getParentIds(), parentId);
        }
    }

    @Override
    public List<SysOrgVo> getAllSysOgrList(String appId, String dictCode, List<String> regionCode) {
        List<SysOrg> list = sysOrgMapper.getAllSysOgrList(appId, dictCode, regionCode);
        List<SysOrgVo> voList = getVoList(list);
        TreeUtil<SysOrgVo> treeUtil = new TreeUtil<>();
        return treeUtil.generate(voList);
    }

    @Override
    public List<SysUserForm> getUserUsingApp(String appId, List<String> regionCodeList) {
        List<SysUserForm> userList  = new ArrayList<SysUserForm>();
        List<SysUser> userUsingApp = sysUserMapper.getUserUsingApp(appId, regionCodeList);
        for (SysUser sysUser : userUsingApp){
            SysUserForm sysUserVo = new SysUserForm();
            BeanUtils.copyProperties(sysUser, sysUserVo);
            userList.add(sysUserVo);
        }
        return userList;
    }

}
