package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.*;
import com.sofn.common.constants.Constants;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.BaseData;
import com.sofn.common.utils.*;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysRegionMapper;
import com.sofn.sys.model.SysOrg;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.model.SysRegion;
import com.sofn.sys.service.SysOrgAgentService;
import com.sofn.sys.service.SysOrgService;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysRegionToTreeService;
import com.sofn.sys.util.RegionSortUtil;
import com.sofn.sys.util.RegionUtils;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.OrgAndProOrgInfoVo;
import com.sofn.sys.vo.SysRegionTreeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 生成行政区划树Service
 *
 * @author heyongjie
 */
@SuppressWarnings({"ALL"})
@Service
@Slf4j
public class SysRegionToTreeServiceImpl extends ServiceImpl<SysRegionMapper, SysRegion> implements SysRegionToTreeService {

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysRegionService sysRegionService;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysOrgAgentService sysOrgAgentService;

    @Autowired
    private SysOrgService sysOrgService;

    @Override
    public SysRegionTreeVo getSysRegionTree(String appId, Integer versionYear) {
        Map<String, Object> params = new HashMap<>();
//        sql查询该年最大版本号即可
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));
        params.put("versionYear", versionYear);
        // 1. 查询出所有的结构
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
        return getTree(sysRegionList, appId);
    }

    @Override
    public SysRegionTreeVo getSysRegionTreeByLoginUser(String token, String level, String orgId, String appId, Integer versionYear) {
        Object obj = redisHelper.hget(token, Constants.UserSession.userOrgInfo);
        if (obj != null) {
            SysOrganization sysOrganization = JsonUtils.json2obj(obj.toString(), SysOrganization.class);

            if (sysOrganization != null) {
                // 获取机构的行政区划ID
                String regionLastCode = sysOrganization.getRegionLastCode();
                // 根节点没有是否第三方字段
                if (!SysManageEnum.ROOT_ORG.getCode().equals(sysOrganization.getId())) {
                    // 如果是第三方需要找出代理机构
                    if (!BoolUtils.Y.equals(sysOrganization.getThirdOrg())) {
                        String realOrgId = getRealOrgId(orgId, sysOrganization);
                        SysOrg infoById = sysOrgService.getById(realOrgId);
                        if (infoById == null) {
                            log.error("当前登录用户的机构{}代理的行政机构不存在", realOrgId);
                            throw new SofnException("当前登录用户代理的行政机构不存在");
                        }
                        regionLastCode = infoById.getRegionLastCode();
                    }
                }

                // 如果是部级机构不进行数据筛选
                if (!SysManageEnum.ROOT_LEVEL.getCode().equals(regionLastCode)) {
                    if (!StringUtils.isBlank(regionLastCode)) {
                        // 将用户完整的行政区划列表获取出来   省 市 县
                        List<SysRegionTreeVo> parentNodeTemp = getParentNode(regionLastCode, versionYear);
                        List<SysRegionTreeVo> parentNode = Lists.newArrayList();
                        if (!CollectionUtils.isEmpty(parentNodeTemp)) {
                            if (SysConstant.PROVINCE.equals(level)) {
                                parentNode.add(parentNodeTemp.get(0));
                            } else if (SysConstant.CITY.equals(level)) {
                                parentNode.add(parentNodeTemp.get(0));
                                if (parentNodeTemp.size() > 1) {
                                    parentNode.add(parentNodeTemp.get(1));
                                }
                            } else {
                                parentNode.addAll(parentNodeTemp);
                            }
                            // 获取整棵树列表
                            SysRegionTreeVo sysRegionTreeByCache = this.getSysRegionTreeByCache(appId, versionYear);
                            // 获取所有的省
                            List<SysRegionTreeVo> children = sysRegionTreeByCache.getChildren();
                            List<SysRegionTreeVo> newTreeVo = Lists.newArrayList();
                            getTree(children, parentNode, 0, newTreeVo);
                            if (!CollectionUtils.isEmpty(newTreeVo)) {
                                // 代表上一级机构
                                for (int i = 0; i < newTreeVo.size(); i++) {
                                    // 上级节点的子节点为下一个
                                    int k = i;
                                    k++;
                                    if (k <= newTreeVo.size() - 1) {
                                        newTreeVo.get(i).setChildren(Lists.newArrayList(newTreeVo.get(k)));
                                    }
                                }
                                // 添加跟节点
                                SysRegion sysRegion = sysRegionMapper.selectById(SysManageEnum.ROOT_LEVEL.getCode());
                                SysRegionTreeVo sysRegionTreeVo = SysRegionTreeVo.getSysRegionTreeVo(sysRegion);
                                sysRegionTreeVo.setChildren(Lists.newArrayList(newTreeVo.get(0)));
                                return sysRegionTreeVo;
                            } else {
                                // 一个都没有找到直接返回全部
                                return sysRegionTreeByCache;
                            }
                        }
                    }
                }
            }
        }

        return this.getSysRegionTreeByCache(appId, versionYear);
    }

    /**
     * 获取真实的机构ID ，如果是行政机构就返回行政机构ID ，如果是代理机构就返回代理机构所代理的行政机构ID，如果代理了多个，通过orgId明确返回一个。
     *
     * @param orgId           具体的行政机构ID
     * @param sysOrganization 当前用户的机构信息
     * @return 第三方机构使用的行政机构
     */
    private String getRealOrgId(String orgId, SysOrganization sysOrganization) {
        // 如果是第三方机构就获取代理的行政机构   根据代理的行政机构
        if (sysOrganization == null) {
            throw new SofnException("当前用户机构为空");
        }
        if (!BoolUtils.Y.equals(sysOrganization.getThirdOrg())) {
            Map<String, String> params = Maps.newHashMap();
            params.put("proxyId", sysOrganization.getId());
            List<OrgAndProOrgInfoVo> orgAndProOrgInfo = sysOrgAgentService.getOrgAndProOrgInfo(params);
            if (CollectionUtils.isEmpty(orgAndProOrgInfo)) {
                throw new SofnException("当前用户为第三方机构，并且没有代理任何行政机构，无法使用权限限制！");
            }
            List<String> allOrgIds = orgAndProOrgInfo.stream().filter(Objects::nonNull).map(OrgAndProOrgInfoVo::getOrgId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(allOrgIds)) {
                // 如果不仅仅代理了一个机构，就需要传入固定的机构ID
                String regionOrgId;
                if (allOrgIds.size() > 1) {
                    if (StringUtils.isBlank(orgId)) {
                        throw new SofnException("当前用户机构不仅仅代理了一个行政机构，请明确需要使用哪个行政机构的级别");
                    } else {
                        // 判断机构是否在已代理机构中
                        Optional<String> first = allOrgIds.stream().filter(orgId::equals).findFirst();
                        if (first.isPresent()) {
                            regionOrgId = orgId;
                        } else {
                            throw new SofnException("该机构未被当前登录用户机构所代理！");
                        }
                    }
                } else {
                    regionOrgId = allOrgIds.get(0);
                }
                return regionOrgId;
            } else {
                throw new SofnException("当前用户为第三方机构，并且没有代理任何行政机构，无法使用权限限制！");
            }
        } else {
            return sysOrganization.getId();
        }
    }


    /**
     * 从整颗树中筛选出符合条件的数据
     *
     * @param children   整颗树
     * @param parentNode 当前用户的行政区划   如 省市县 或者 省市
     * @param num        用于还要循环多少次，从0开始传入
     * @param newTreeVo  将值放入哪里
     */
    private void getTree(List<SysRegionTreeVo> children, List<SysRegionTreeVo> parentNode, int num, List<SysRegionTreeVo> newTreeVo) {
        if (!CollectionUtils.isEmpty(parentNode) && !CollectionUtils.isEmpty(children)) {
            // 遍历一次循环一层
            for (SysRegionTreeVo sysRegionTreeVo : children) {
                if (sysRegionTreeVo != null) {
                    if (sysRegionTreeVo.getId().equals(parentNode.get(num).getId())) {
                        // 找到匹配的数据
                        newTreeVo.add(sysRegionTreeVo);
                        num++;
                        if (num > parentNode.size() - 1) {
                            break;
                        }
                        if (!CollectionUtils.isEmpty(sysRegionTreeVo.getChildren())) {
                            getTree(sysRegionTreeVo.getChildren(), parentNode, num, newTreeVo);
                        } else {
                            break;
                        }

                    }
                }
            }
        }
    }


    /**
     * 将指定的行政区划数据做成树结构
     *
     * @param sysRegionList 行政区划数据
     * @return 树结构
     */
    private SysRegionTreeVo getTree(List<SysRegion> sysRegionList, String appId) {
        SysRegionTreeVo sysRegionTreeVo = null;
        // 2. 找到所有的一级节点
        List<SysRegionTreeVo> rootSysRegion = Lists.newArrayList();
        // 存储的是String -> List结构  方便后面使用递归查找子节点
        Multimap<String, SysRegionTreeVo> multimap = ArrayListMultimap.create();
        if (sysRegionList != null && sysRegionList.size() > 0) {
            List<BaseData> baseDataList = RegionUtils.getBaseDataList(appId);
            for (SysRegion sysRegion : sysRegionList) {
                // 如果已删除  当前数据不处理,2021/7/14 sql中排除
//                if (SysManageEnum.DEL_FLAG_Y.getCode().equals(sysRegion.getDelFlag())) {
//                    continue;
//                }

                if (RegionUtils.isContainRegion(sysRegion.getRegionCode(), baseDataList)){
                  continue;
                }

                SysRegionTreeVo tempSysRegionTreeVo = SysRegionTreeVo.getSysRegionTreeVo(sysRegion);
                tempSysRegionTreeVo.setAreaName(sysRegionService.getAreaName(sysRegion.getAreaId()));
//                if (SysManageEnum.ROOT_LEVEL.getCode().equals(sysRegion.getId())) {
              if (SysManageEnum.ROOT_LEVEL.getCode().equals(sysRegion.getRegionCode())) {
                    // 根节点
                    sysRegionTreeVo = tempSysRegionTreeVo;
                } else if (SysManageEnum.ROOT_LEVEL.getCode().equals(sysRegion.getParentId())) {
                    // 一级节点
                    rootSysRegion.add(tempSysRegionTreeVo);
                } else {
                    // 一级以上节点
                    multimap.put(sysRegion.getParentId(), tempSysRegionTreeVo);
                }
            }
        }
        if (sysRegionTreeVo == null) {
            log.warn("根节点数据为空，请检查");
            return null;
        }
        // 3. 排序
        if (rootSysRegion.size() > 0) {
            rootSysRegion.sort(RegionSortUtil.comparator);
        }

        rootSysRegion = toTree(rootSysRegion, multimap);
        sysRegionTreeVo.setChildren(rootSysRegion);
        return sysRegionTreeVo;

    }

    @Override
    public SysRegionTreeVo getSysRegionTreeByCache(String appId, Integer versionYear) {
        // 1. 从缓存中获取数据
       SysRegionTreeVo sysRegionTreeVo =null;
       /*
       取消从缓存哪数据，缓存数据未安年区分数据
        */
        sysRegionTreeVo = SysCacheUtils.getSysRegionTreeByCache(appId, versionYear);
        //  缓存中没有值
        if (sysRegionTreeVo == null) {
            sysRegionTreeVo = this.getSysRegionTree(appId, versionYear);
            if (sysRegionTreeVo != null) {
                // 将对象存入缓存中
                SysCacheUtils.setSysRegionTreeByCache(appId, versionYear, sysRegionTreeVo);
            }
        }
        return sysRegionTreeVo;
    }


    @Override
    public List<SysRegionTreeVo> getSysRegionTreeById(String appId, String id, Integer versionYear) {
        // 查找数据
        Map<String, Object> params = Maps.newHashMap();
        params.put("parentId", id);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));

        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
        // 转换为VO
        List<SysRegionTreeVo> sysRegionTreeVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sysRegionList)) {
            List<BaseData> bdList = RegionUtils.getBaseDataList(appId);
            final List<BaseData> baseDataList = bdList;
            sysRegionList.forEach((sysRegion) -> {
                if (RegionUtils.isContainRegion(sysRegion.getRegionCode(), baseDataList)){
                  return;
                }

                SysRegionTreeVo sysRegionTreeVo = SysRegionTreeVo.getSysRegionTreeVo(sysRegion);
                sysRegionTreeVo.setAreaName(sysRegionService.getAreaName(sysRegion.getAreaId()));
                sysRegionTreeVos.add(sysRegionTreeVo);
            });
            // 排序
            sysRegionTreeVos.sort(RegionSortUtil.comparator);
        }
        return sysRegionTreeVos;
    }

    @Override
    public List<SysRegionTreeVo> getSysRegionTreeByIdAndCache(String appId, String id, Integer versionYear) {
        List<SysRegionTreeVo> sysRegionTreeVos;
        sysRegionTreeVos = SysCacheUtils.getSysRegionTreeByIdAndCache(appId, id, versionYear);
        if (CollectionUtils.isEmpty(sysRegionTreeVos)) {
            // 1.从数据库中查找数据
            sysRegionTreeVos = getSysRegionTreeById(appId, id, versionYear);
            // 2.将数据放入缓存中
            if (sysRegionTreeVos != null && sysRegionTreeVos.size() > 0) {
                SysCacheUtils.setSysRegionTreeByIdAndCache(appId, id, versionYear, sysRegionTreeVos);
            }
        }
        return sysRegionTreeVos;
    }

    @Override
    public List<SysRegionTreeVo> getParentNode(String regionCode, Integer versionYear) {
        if (StringUtils.isBlank(regionCode)) {
            throw new SofnException("行政区划代码必传");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("regionCode", regionCode);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));

        // 获取年份下最新区划
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);

        if (!CollectionUtils.isEmpty(sysRegionList)) {
            sysRegionList = sysRegionList.stream().filter(item -> !BoolUtils.Y.equals(item.getDelFlag())).collect(Collectors.toList());
        }

        if (!CollectionUtils.isEmpty(sysRegionList)) {
            SysRegion sysRegion = sysRegionList.get(0);
            String parentIds = sysRegion.getParentIds();
            // 添加null 判断
            if (!StringUtils.isBlank(parentIds)) {
                // 将当前节点的parentIds查询出来，查出来后根据ID查询出所有节点信息，然后如果ID不为空和不为根节点时，加入返回值中。
                List<String> parentIdList = Arrays.asList(parentIds.split("/"));
                if(!CollectionUtils.isEmpty(parentIdList)){
//                    List<SysRegion> sysRegionList1 = sysRegionMapper.selectBatchIds(parentIdList);
                    List<SysRegion> sysRegionList1 = sysRegionMapper.selectByRegioncode(parentIdList, String.valueOf(sysRegionService.getMaxVersionCodeByYear(versionYear)));
                    // 构造返回值
                    List<SysRegionTreeVo> sysRegionTreeVoList = Lists.newArrayList();
                    if (!CollectionUtils.isEmpty(sysRegionList1)) {
                        for (String item : parentIdList) {
                            if (!StringUtils.isBlank(item) && !SysManageEnum.ROOT_LEVEL.getCode().equals(item)) {
                                Optional<SysRegion> first = sysRegionList1.stream().filter(element -> item.equals(element.getRegionCode())).findFirst();
                                if (first.isPresent()) {
                                    SysRegion sysRegion1 = first.get();
                                    SysRegionTreeVo sysRegionTreeVo = SysRegionTreeVo.getSysRegionTreeVo(sysRegion1);
                                    sysRegionTreeVoList.add(sysRegionTreeVo);
                                }
                            }
                        }
                    }
                    // 添加本节点信息
                    sysRegionTreeVoList.add(SysRegionTreeVo.getSysRegionTreeVo(sysRegion));
                    return sysRegionTreeVoList;
                }
            }
        }
        return Lists.newArrayList();
    }

    @Override
    public List<SysRegionTreeVo> getParentNodeByRegionId(String regionId, Integer versionYear) {
        if (StringUtils.isBlank(regionId)) {
            throw new SofnException("行政区划代码id必传");
        }

        Map<String,Object> params = new HashMap<>();
        params.put("regionId", regionId);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));

        // 获取年份下最新区划
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);

        if (!CollectionUtils.isEmpty(sysRegionList)) {
            sysRegionList = sysRegionList.stream().filter(item -> !BoolUtils.Y.equals(item.getDelFlag())).collect(Collectors.toList());
        }

        if (!CollectionUtils.isEmpty(sysRegionList)) {
            SysRegion sysRegion = sysRegionList.get(0);
            String parentIds = sysRegion.getParentIds();
            // 添加null 判断
            if (!StringUtils.isBlank(parentIds)) {
                // 将当前节点的parentIds查询出来，查出来后根据ID查询出所有节点信息，然后如果ID不为空和不为根节点时，加入返回值中。
                List<String> parentIdList = Arrays.asList(parentIds.split("/"));
                if(!CollectionUtils.isEmpty(parentIdList)){
//                    List<SysRegion> sysRegionList1 = sysRegionMapper.selectBatchIds(parentIdList);
                    List<SysRegion> sysRegionList1 = sysRegionMapper.selectByRegioncode(parentIdList, String.valueOf(sysRegionService.getMaxVersionCodeByYear(versionYear)));
                    // 构造返回值
                    List<SysRegionTreeVo> sysRegionTreeVoList = Lists.newArrayList();
                    if (!CollectionUtils.isEmpty(sysRegionList1)) {
                        for (String item : parentIdList) {
                            if (!StringUtils.isBlank(item) && !SysManageEnum.ROOT_LEVEL.getCode().equals(item)) {
                                Optional<SysRegion> first = sysRegionList1.stream().filter(element -> item.equals(element.getRegionCode())).findFirst();
                                if (first.isPresent()) {
                                    SysRegion sysRegion1 = first.get();
                                    SysRegionTreeVo sysRegionTreeVo = SysRegionTreeVo.getSysRegionTreeVo(sysRegion1);
                                    sysRegionTreeVoList.add(sysRegionTreeVo);
                                }
                            }
                        }
                    }
                    // 添加本节点信息
                    sysRegionTreeVoList.add(SysRegionTreeVo.getSysRegionTreeVo(sysRegion));
                    return sysRegionTreeVoList;
                }
            }
        }
        return Lists.newArrayList();
    }


    /**
     * 获取父节点
     *
     * @param sysRegionTree    查询出来的完整树结构
     * @param sysRegionTreeVos List<SysRegionTreeVo>
     * @param regionCode       行政区划代码
     */
    private void getParentNode(SysRegionTreeVo sysRegionTree, List<SysRegionTreeVo> sysRegionTreeVos, String regionCode) {
        if (sysRegionTree != null) {
            List<SysRegionTreeVo> children = sysRegionTree.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                children.forEach((s) -> {
                    // 这里移除children属性
                    SysRegionTreeVo sysRegionTreeVo = new SysRegionTreeVo();
                    BeanUtils.copyProperties(s, sysRegionTreeVo);
                    sysRegionTreeVo.setChildren(null);
                    sysRegionTreeVos.add(sysRegionTreeVo);
                    if (!s.getRegionCode().equals(regionCode)) {
                        getParentNode(s, sysRegionTreeVos, regionCode);
                    }
                });
            }
        }
    }


    /**
     * 递归查询子节点
     *
     * @param rootSysRegion 根节点内容
     * @param multimap      根据parentId分好的数据
     * @return List<SysRegionTreeVo>
     */
    private List<SysRegionTreeVo> toTree(List<SysRegionTreeVo> rootSysRegion, Multimap<String, SysRegionTreeVo> multimap) {
        if (rootSysRegion != null && rootSysRegion.size() > 0) {
            for (SysRegionTreeVo sysRegionTreeVo : rootSysRegion) {
                // 1. 找到所有的子节点
                List<SysRegionTreeVo> sonSysRegionList = (List<SysRegionTreeVo>) multimap.get(sysRegionTreeVo.getRegionCode());
                // 2. 排序
                if (sonSysRegionList != null && sonSysRegionList.size() > 0) {
                    sonSysRegionList.sort(RegionSortUtil.comparator);
                }
                sysRegionTreeVo.setChildren(sonSysRegionList);
                // 递归查找子节点
                toTree(sonSysRegionList, multimap);
            }

        }
        return rootSysRegion;
    }


    /**
     * 递归查找父节点
     *
     * @param sysRegion     子节点数据
     * @param sysRegionList 所有节点数据
     */
    private void getSuperSysRegion(SysRegion sysRegion, Set<SysRegion> sysRegionList) {
        if (sysRegion != null) {
            // 这是父节点
            SysRegion parentSysRegion = sysRegionMapper.selectById(sysRegion.getParentId());
            if (parentSysRegion != null) {
                // 在sysRegionList 不存在的情况   任何一个条件成功返回true
                boolean flag = sysRegionList.stream().anyMatch(s -> s.getId().equals(parentSysRegion.getId()));
                if (!flag) {
                    sysRegionList.add(parentSysRegion);
                    // 代表当前节点已经查询过了
                    getSuperSysRegion(parentSysRegion, sysRegionList);
                }

            }

            boolean flag = sysRegionList.stream().anyMatch(s -> s.getId().equals(sysRegion.getId()));
            if (!flag) {
                sysRegionList.add(sysRegion);
            }

        }
    }


    @Override
    public SysRegionTreeVo getSysRegionTree(String appId,String regionName, String parentId, String regionCode, Integer versionYear) {
        Map<String, Object> params = new HashMap<>();
        params.put("regionName", regionName);
        params.put("parentId", parentId);
        params.put("regionCode", regionCode);
        params.put("versionCode", sysRegionService.getMaxVersionCodeByYear(versionYear));
        params.put("versionYear", versionYear);


        // 查找符合条件的节点
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);

        // 根据子节点将所有父节点全部找出来
        Set<SysRegion> sysRegionList2 = Sets.newHashSet();
        if (!CollectionUtils.isEmpty(sysRegionList)) {
            // 查询所有的父节点
            Set<String> parentIds = Sets.newHashSet();

            Set<String> childrenParentIdStrs = Sets.newHashSet();
            sysRegionList.forEach(item -> {
                String tempParentId = item.getParentIds();
                if(!StringUtils.isBlank(tempParentId)){
                    parentIds.addAll(Arrays.asList(tempParentId.split(SysManageEnum.TREE_NODE_SPLIT_STR.getCode())));
                    childrenParentIdStrs.add(item.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + item.getRegionCode());
//                    childrenParentIdStrs.add(item.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode());
                }

            });
            if(!CollectionUtils.isEmpty(parentIds)){
//                List<SysRegion> sysRegionList1 = sysRegionMapper.selectList(new QueryWrapper<SysRegion>().in("region_code", parentIds).eq("del_flag", SysManageEnum.DEL_FLAG_N.getCode()));
              //封装请求参数
              HashMap<String, Object> paramsF = new HashMap<>();
              paramsF.put("parentIds",parentIds);
              paramsF.put("versionCode",sysRegionService.getMaxVersionCodeByYear(versionYear));
              paramsF.put("regionCode",regionCode);
              paramsF.put("versionYear", versionYear);
              List<SysRegion> sysRegionList1= sysRegionMapper.selectFList(paramsF);
                sysRegionList2.addAll(sysRegionList1);
            }

            // 查询所有的子节点  TODO  目前是查询高县的时候 筠连县也会返回，同级别还是会返回，检查
            if(!CollectionUtils.isEmpty(childrenParentIdStrs)){
                sysRegionList2.addAll(sysRegionMapper.getInfoByParentIdStrs(Lists.newArrayList(childrenParentIdStrs),
                        String.valueOf(sysRegionService.getMaxVersionCodeByYear(versionYear)), versionYear));
            }
            sysRegionList2.addAll(sysRegionList);
        }

        // 如果没有根节点  添加根节点
        boolean flag = sysRegionList2.stream().anyMatch(s -> SysManageEnum.ROOT_LEVEL.getCode().equals(s.getRegionCode()));
        if (!flag) {
            SysRegion sysRegion = new SysRegion();
            sysRegion.setRegionCode(SysManageEnum.ROOT_LEVEL.getCode());
//            sysRegion.setId(SysManageEnum.ROOT_LEVEL.getCode());
            sysRegion.setRegionName("行政区划");
            sysRegion.setSortid(0);
            sysRegionList2.add(sysRegion);
        }
        if (!CollectionUtils.isEmpty(sysRegionList2)) {
            // 根据节点将树做出来
            SysRegionTreeVo tree = getTree(Lists.newArrayList(sysRegionList2), appId);
            return tree;
        }
        return null;
    }

    /**
     * 获取子节点内容
     *
     * @param parentId 父节点内容
     */
    private void getSonRegion(String parentId, Set<SysRegion> sysRegionList) {
        if (StringUtils.isNotBlank(parentId)) {
            List<SysRegion> sysRegionList1 = sysRegionMapper.selectList(new QueryWrapper<SysRegion>().like("PARENT_ID", parentId));
            for (SysRegion sysRegion : sysRegionList1) {
                // 如果不存在再添加
                boolean flag = sysRegionList.stream().anyMatch(region -> region.getId().equals(sysRegion.getId()));
                if (!flag) {
                    sysRegionList.add(sysRegion);
                }
                // 继续递归查找
                getSonRegion(sysRegion.getId(), sysRegionList);
            }
        }


    }


    @Override
    public PageUtils<SysRegionTreeVo> getSysRegionFormByParentId(Map<String, String> params, Integer versionYear, Integer pageNo, Integer pageSize) {
        String parentId = params.get("parentId");
        String keyword = params.get("keyword");
        String regionCode = params.get("regionCode");
        if(null==versionYear){
            versionYear= Calendar.getInstance().get(Calendar.YEAR);
        }
        if (StringUtils.isBlank(keyword) && StringUtils.isBlank(regionCode) && StringUtils.isBlank(parentId)) {
            parentId = SysManageEnum.ROOT_LEVEL.getCode();
//            throw new SofnException("不带任何条件时，parentId不能为空");
        }

        Map<String,Object> map = new HashMap<>();
        map.putAll(params);
        map.put("versionCode", sysRegionService.getMaxVersionCodeByYear(Integer.valueOf(versionYear)));
        map.put("versionYear",versionYear);
        if (pageNo != null && pageSize != null && pageSize > 0) {
            PageHelper.offsetPage(pageNo, pageSize);
        }

        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(map);
        PageInfo<SysRegion> pageInfo = new PageInfo<>(sysRegionList);
        PageInfo<SysRegionTreeVo> sysRegionFormPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, sysRegionFormPageInfo);
        List<SysRegion> list = pageInfo.getList();
        if (!CollectionUtils.isEmpty(list)) {
            sysRegionFormPageInfo.setList(list.stream().map(SysRegionTreeVo::getSysRegionTreeVo).collect(Collectors.toList()));
        }
        return PageUtils.getPageUtils(sysRegionFormPageInfo);
    }

}
