package com.sofn.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.enums.CommonEnum;
import com.sofn.common.exception.SofnException;
import com.sofn.common.model.BaseData;
import com.sofn.common.service.BaseService;
import com.sofn.common.utils.*;
import com.sofn.sys.enums.SysConstant;
import com.sofn.sys.enums.SysManageEnum;
import com.sofn.sys.mapper.SysRegionMapper;
import com.sofn.sys.model.SysDict;
import com.sofn.sys.model.SysOrganization;
import com.sofn.sys.model.SysRegion;
import com.sofn.sys.service.SysDictService;
import com.sofn.sys.service.SysRegionService;
import com.sofn.sys.service.SysSubsystemService;
import com.sofn.sys.util.RegionSortUtil;
import com.sofn.sys.util.RegionUtils;
import com.sofn.sys.util.SysCacheUtils;
import com.sofn.sys.vo.SysDictVo;
import com.sofn.sys.vo.SysRegionForm;
import com.sofn.sys.vo.SysRegionInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 行政区划CRUD 服务层
 *
 * @author heyongjie
 * @date 2019/5/29 11:15
 */
@Service
@Slf4j
public class SysRegionServiceImpl extends BaseService<SysRegionMapper, SysRegion>
        implements SysRegionService {
    //定义1000的常量，versionCode每年在18年最小的versionCode基础上加1000
    final static int increaseNumber = 1000;

    @Autowired
    private SysRegionMapper sysRegionMapper;

    @Autowired
    private SysDictService sysDictService;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysSubsystemService sysSubsystemService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addSysRegion(SysRegion sysRegion) {
        // 判断区域ID是否存在
        String areaName = getAreaName(sysRegion.getAreaId());
        if (StringUtils.isBlank(areaName)) {
            throw new SofnException("区域不存在");
        }

        // 如果父ID为空 那么就是一级菜单 在顶级菜单之下
        if (StringUtils.isBlank(sysRegion.getParentId())) {
            sysRegion.setParentId(SysManageEnum.ROOT_LEVEL.getCode());
        }
        Long maxVersionCodeByYear = getMaxVersionCodeByYear(sysRegion.getVersionYear());
        SysRegion parentRegion = sysRegionMapper.getParentRegion(sysRegion.getParentId(), maxVersionCodeByYear + "");

        if (parentRegion == null) {
            throw new SofnException("父节点不存在");
        } else {
            sysRegion.setParentId(parentRegion.getRegionCode());
        }
        // 2. 设置默认值
        sysRegion.preInsert();
        sysRegion.setDelFlag(SysManageEnum.DEL_FLAG_N.getCode());
        sysRegion.setStatus(SysManageEnum.STATUS_1.getCode());
        //如果未传年份，设置为最新年份
        if (sysRegion.getVersionYear() == null) {
            sysRegion.setVersionYear(Calendar.getInstance().get(Calendar.YEAR));
        }
        // 时间戳作为递增版本号
//        sysRegion.setVersionCode(Calendar.getInstance().getTimeInMillis());
        //查询18年最小的versionCode
        // 有可能现在加的已经存在了， 所以先查询之前是否存在过，
        // 如果存在过就直接版本号加1 如果不存在就是 （年度-2018） *1000 + 当前年度最小的版本号
        SysRegion selectSysRegion = sysRegionMapper.getSysRegionByRegionCode(sysRegion.getRegionCode(), sysRegion.getVersionYear() + "");
        if(selectSysRegion == null){
            String minVersioncode = sysRegionMapper.selectMinVersionCodeByyear("2018");
            Integer year = sysRegion.getVersionYear();
            sysRegion.setVersionCode(((long) (year - 2018) * increaseNumber) + Long.parseLong(minVersioncode));
        }else {
            sysRegion.setVersionCode(selectSysRegion.getVersionCode() + 1);
        }

        // 设置ParentIds和  parentNames
        setParentIdsAndParentNames(sysRegion);

        // 3. 保存
        sysRegionMapper.insert(sysRegion);

        // 4. 删除缓存
        List<String> appIds = sysSubsystemService.getAllAppidList();
        deleteRedis(appIds, sysRegion.getParentId());
    }

    /**
     * 删除redis缓存
     */
    @Override
    public void deleteRedis(List<String> appIds, String parentId) {
        try {
            SysCacheUtils.removeRegionTreeKey();
            SysCacheUtils.removeRegionTreeKeyByParentId(appIds, parentId);
            SysCacheUtils.removeRegionNameCodeCache();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("Redis异常");
        }
    }

    @Override
    public List<Integer> getVersionYearListForAvailable() {
        List<Integer> repoList = getVersionYearList();
        Calendar calendar = Calendar.getInstance();

        int minYear = calendar.get(Calendar.YEAR);
        calendar.add(Calendar.YEAR, 1);
        int maxYear = calendar.get(Calendar.YEAR);

        if (CollectionUtils.isEmpty(repoList)) {
            calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -5);
            minYear = calendar.get(Calendar.YEAR);
        } else {
            Optional<Integer> optional = repoList.stream().min((o1, o2) -> o1 >= o2 ? 1 : -1);
            if (optional.isPresent()) {
                minYear = optional.get();
            }
        }

        List<Integer> yearList = new ArrayList<>();
        for (int year = maxYear; year >= minYear; year--) {
            yearList.add(year);
        }

        return yearList;
    }

    @Override
    public List<Integer> getVersionYearList() {
        return sysRegionMapper.getVersionYearList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSysRegion(SysRegion sysRegion) {


        // 1. 校验内容是否存在
//        SysRegion selectSysRegion = getOneByCode(sysRegion.getRegionCode(), sysRegion.getVersionYear());
//        if (selectSysRegion == null) {
//            throw new SofnException("数据不存在");
//        }
        /**
         * 验证code是否存在
         */
        Map<String, Object> params = new HashMap<>();
        params.put("regionCode", sysRegion.getRegionCode());
        params.put("versionCode", getMaxVersionCodeByYear(sysRegion.getVersionYear()));
        List<SysRegion> regionList = sysRegionMapper.getSysRegionByContion(params);
        SysRegion selectSysRegion=sysRegionMapper.selectById(sysRegion.getId());
        if(regionList.size()!=0){
            /**
             * 已存在的code包含修改的数据
             */
           if(!regionList.stream().map(SysRegion::getId).collect(Collectors.toList()).contains(selectSysRegion.getId())) {
               throw new SofnException("该行政区划已存在");
           }
        }
        // 编辑也是新增一条记录，此处移除前端传递过来的ID
        sysRegion.setId(null);
        // 判断区域ID是否存在
        String areaName = getAreaName(sysRegion.getAreaId());
        if (StringUtils.isBlank(areaName)) {
            throw new SofnException("区域不存在");
        }

        SysRegion parentSysRegion = getOneByCode(sysRegion.getParentId(), sysRegion.getVersionYear());
        if (parentSysRegion == null) {
            throw new SofnException("父节点不存在");
        }
        Long maxVersionCodeByYear = getMaxVersionCodeByYear(sysRegion.getVersionYear());
        if (!sysRegion.getParentId().equals(selectSysRegion.getParentId())) {
            // 备份老信息
            SysRegion selectSysRegionBak = new SysRegion();
            BeanUtils.copyProperties(selectSysRegion, selectSysRegionBak);
            // 如果切换了根节点需要将下面的所有节点的根节点统一更改
            // 设置ParentIds和  parentNames
            setParentIdsAndParentNames(sysRegion);
            // 查找所有的子节点
            String parentId = selectSysRegionBak.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + selectSysRegionBak.getRegionCode();
            List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByParentIds(parentId, maxVersionCodeByYear);
            if (!CollectionUtils.isEmpty(sysRegionList)) {

                String newParentId = sysRegion.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + selectSysRegion.getRegionCode();
                List<String> collect = sysRegionList.stream().map(SysRegion::getId).collect(Collectors.toList());
                sysRegionMapper.updateParentIdsByIds(collect, newParentId, parentId);

                String oldParentNames = selectSysRegionBak.getParentNames() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + selectSysRegionBak.getRegionName();
                String newParentNames = sysRegion.getParentNames() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + selectSysRegion.getRegionName();
                sysRegionMapper.updateParentNamesByIds(collect, newParentNames, oldParentNames);

            }
            sysRegion.setVersionCode(selectSysRegion.getVersionCode());
        } else {
            // 3. 更改内容
            sysRegion.setVersionCode(selectSysRegion.getVersionCode());
            sysRegion.setParentIds(selectSysRegion.getParentIds());
            sysRegion.setParentNames(selectSysRegion.getParentNames());
        }
        BeanUtils.copyProperties(sysRegion, selectSysRegion);
        selectSysRegion.preInsert();
//        selectSysRegion.setVersionCode(Calendar.getInstance().getTimeInMillis());
        selectSysRegion.setVersionCode(selectSysRegion.getVersionCode() + 1);

        // 修改只是增加一条版本记录
        sysRegionMapper.insert(selectSysRegion);

        // 4. 删除缓存
        List<String> appIds = sysSubsystemService.getAllAppidList();
        deleteRedis(appIds, sysRegion.getParentId());
    }

    /**
     * 设置ParentIds和ParentNames
     *
     * @param sysRegion SysRegion
     */
    private void setParentIdsAndParentNames(SysRegion sysRegion) {
        if (!SysManageEnum.ROOT_LEVEL.getCode().equals(sysRegion.getParentId())) {
            Long maxVersionCodeByYear = getMaxVersionCodeByYear(sysRegion.getVersionYear());
            SysRegion parentSysRegion = sysRegionMapper.getParentRegion(sysRegion.getParentId(), maxVersionCodeByYear + "");
            if (parentSysRegion == null) {
                throw new SofnException("父节点不存在");
            }
            String parentIds = parentSysRegion.getParentIds() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + parentSysRegion.getRegionCode();
            String parentNames = parentSysRegion.getParentNames() + SysManageEnum.TREE_NODE_SPLIT_STR.getCode() + parentSysRegion.getRegionName();
            sysRegion.setParentIds(parentIds);
            sysRegion.setParentNames(parentNames);
        } else {
            // /100000
            // /行政区划
            sysRegion.setParentIds("/100000");
            sysRegion.setParentNames("/行政区划");
        }
    }

    @Override
    public void deleteSysRegion(String id) {
        // 1. 待删除资源是否存在
        SysRegion selectSysRegion = this.getOneById(id);
        if (selectSysRegion == null) {
            throw new SofnException("待删除内容不存在");
        }

        // 2. 如果下面有子节点的不能删除
        Map<String, Object> params = Maps.newHashMap();
//        params.put("parentId", selectSysRegion.getId());
        params.put("parentId", selectSysRegion.getRegionCode());
        params.put("versionCode", getMaxVersionCodeByYear(selectSysRegion.getVersionYear()));

        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
        if (sysRegionList != null && sysRegionList.size() > 0) {
            throw new SofnException("当前行政区划下有子行政区划，删除失败");
        }

        // 删除和编辑都是新增一条记录，需要移除ID重新生成
        selectSysRegion.setId(null);

        // 3. 删除逻辑为：新增一条del_flag=Y的版本记录
        selectSysRegion.preInsert();
//        selectSysRegion.setVersionCode(Calendar.getInstance().getTimeInMillis());
        selectSysRegion.setVersionCode(selectSysRegion.getVersionCode() + 1);
        selectSysRegion.setDelFlag(BoolUtils.Y);
        sysRegionMapper.insert(selectSysRegion);

        // 4. 删除缓存
        List<String> appIds = sysSubsystemService.getAllAppidList();
        deleteRedis(appIds, selectSysRegion.getParentId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchDeleteSysRegion(List<String> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            // 父ID 用于清理缓存
            Set<String> parentIds = Sets.newHashSet();

            // 如果有子节点不让删除
            ids.forEach((id) -> {
                SysRegion selectSysRegion = getOneById(id);
                if (selectSysRegion == null) {
                    return;
                }

                Map<String, Object> params = Maps.newHashMap();
//                params.put("parentId", id);
                params.put("parentId", selectSysRegion.getRegionCode());
                params.put("versionCode", getMaxVersionCodeByYear(selectSysRegion.getVersionYear()));

                List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
                if (sysRegionList != null && sysRegionList.size() > 0) {
                    throw new SofnException("行政区划[" + id + "]下有子行政区划，删除失败");
                }
            });

            // 删除
            ids.forEach(id -> {
                SysRegion sysRegion = getOneById(id);
                if (sysRegion != null) {
                    parentIds.add(sysRegion.getParentId());

                    // 删除实际是新增一条记录
                    sysRegion.setId(null);
                    sysRegion.preInsert();
//                    sysRegion.setVersionCode(Calendar.getInstance().getTimeInMillis());
                    //VersionCode新增数据在原基础上加1
                    sysRegion.setVersionCode(sysRegion.getVersionCode() + 1);
                    sysRegion.setDelFlag(BoolUtils.Y);
                    sysRegionMapper.insert(sysRegion);
                }
            });

            // 删除缓存
            try {
                SysCacheUtils.removeRegionTreeKey();
                SysCacheUtils.removeRegionNameCodeCache();
                // 删除父ID的缓存
                if (!CollectionUtils.isEmpty(parentIds)) {
                    List<String> appIds = sysSubsystemService.getAllAppidList();
                    parentIds.forEach(parentId -> SysCacheUtils.removeRegionTreeKeyByParentId(appIds, parentId));
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("Redis异常");
            }
        }
    }

    /**
     * 根据区域ID获取区域名称
     *
     * @param areaId 区域ID
     * @return areaId 对应的名称
     */
    @Override
    public String getAreaName(String areaId) {
        if (StringUtils.isNotBlank(areaId)) {
            List<SysDict> sysDicts = sysDictService.getDictNameByValueAndType(SysManageEnum.SYS_AREA_DICT_TYPE_KEY.getCode(), areaId);
            if (!CollectionUtils.isEmpty(sysDicts)) {
                return sysDicts.get(0).getDictname();
            }
        }
        return "";
    }

    @Override
    public List<SysDictVo> getRegionAreaList(String typeCode) {
        List<SysDict> sysDicts = sysDictService.getDictListByType(typeCode);
        List<SysDictVo> sysDictVos = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sysDicts)) {
            for (SysDict sysDict : sysDicts) {
                if (sysDict != null) {
                    SysDictVo sysDictVo = new SysDictVo();
                    sysDictVo.setDictcode(sysDict.getDictcode());
                    sysDictVo.setDictname(sysDict.getDictname());
                    sysDictVos.add(sysDictVo);
                }
            }
        }
        return sysDictVos;
    }


    @Override
    public PageUtils<SysRegionForm> getSysRegionByContionPage(Map<String, Object> prams, Integer pageNo, Integer pageSize) {
        Integer versionYear = (Integer) prams.get("versionYear");
        Long versionCode = (Long) prams.get("versionCode");

        if (versionCode == null || versionCode <= 0) {
            prams.put("versionCode", getMaxVersionCodeByYear(versionYear));
        }

        PageHelper.offsetPage(pageNo, pageSize);
        // 查询的时候查询未删除的
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(prams);
        PageInfo<SysRegion> pageInfo = new PageInfo<>(sysRegionList);

        // 转为VO
        PageInfo<SysRegionForm> sysRegionFormPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, sysRegionFormPageInfo);

        // 设置区域值
        if (!CollectionUtils.isEmpty(sysRegionList)) {
            List<SysRegionForm> sysRegionForms = Lists.newArrayList();
            sysRegionList.forEach((sysRegion -> {
                SysRegionForm sysRegionForm = SysRegionForm.getSysRegionForm(sysRegion);
                sysRegionForm.setAreaName(getAreaName(sysRegion.getAreaId()));
                sysRegionForms.add(sysRegionForm);
            }));
            sysRegionFormPageInfo.setList(sysRegionForms);
        }

        return PageUtils.getPageUtils(sysRegionFormPageInfo);
    }

    @Override
    public PageUtils<SysRegionForm> getSysRegionByContionsPage(Map<String, Object> prams, Integer pageNo, Integer pageSize) {
        Integer versionYear = (Integer) prams.get("versionYear");
        Long versionCode = (Long) prams.get("versionCode");

        if (versionCode == null || versionCode <= 0) {
            prams.put("versionCode", getMaxVersionCodeByYear(versionYear));
        }
        if (prams.get("regionCode") != null){
            String regionCode = (String) prams.get("regionCode");
            if (regionCode.contains(",")){
                List<String> regionCodeList = Arrays.asList(regionCode.split(","));
                prams.put("ids", regionCodeList);
                prams.put("regionCode", null);
            }
        }
        PageHelper.startPage(pageNo, pageSize);
        // 查询的时候查询未删除的
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContionBy(prams);
        PageInfo<SysRegion> pageInfo = new PageInfo<>(sysRegionList);

        // 转为VO
        PageInfo<SysRegionForm> sysRegionFormPageInfo = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, sysRegionFormPageInfo);

        // 设置区域值
        if (!CollectionUtils.isEmpty(sysRegionList)) {
            List<SysRegionForm> sysRegionForms = Lists.newArrayList();
            sysRegionList.forEach((sysRegion -> {
                SysRegionForm sysRegionForm = SysRegionForm.getSysRegionForm(sysRegion);
                sysRegionForm.setAreaName(sysRegion.getParentNames());
                sysRegionForms.add(sysRegionForm);
            }));
            sysRegionFormPageInfo.setList(sysRegionForms);
        }

        return PageUtils.getPageUtils(sysRegionFormPageInfo);
    }


    @Override
    public SysRegion getOneById(String id) {
        // 1. 校验内容是否存在
        List<SysRegion> sysRegionList = sysRegionMapper.selectList(new QueryWrapper<SysRegion>()
                .eq("del_Flag", SysManageEnum.DEL_FLAG_N.getCode())
                .eq("id", id));

        if (sysRegionList == null || sysRegionList.size() == 0) {
            return null;
        }

        if (sysRegionList.size() != 1) {
            log.info("当前ID{}对应了多条数据，请检查数据", id);
            throw new SofnException("该条数据异常");
        }

        return sysRegionList.get(0);
    }

    @Override
    public SysRegion getOneByCode(String code, Integer versionYear) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("regionCode", code);
        params.put("versionCode", getMaxVersionCodeByYear(versionYear));

        // 1. 校验内容是否存在
        List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);

        // 如果没有查询到或者查询到了不止一个就抛出数据异常
        if (sysRegionList == null || sysRegionList.size() == 0) {
            return null;
        }

        return sysRegionList.stream().findFirst().get();
    }

    @Override
    public List<SysRegionForm> getListByRegionCode(String appId, String parentId, String isAuth, Integer versionYear) {
        // 1. 从缓存中获取数据   取消缓存2020031816:54   hyj
        List<SysRegionForm> sysRegionForms = Lists.newArrayList();
        if (CollectionUtils.isEmpty(sysRegionForms)) {
            // 2. 从数据库查询
            Map<String, Object> paramas = Maps.newHashMap();
            if (StringUtils.isBlank(parentId)) {
                parentId = SysManageEnum.ROOT_LEVEL.getCode();
            }
            paramas.put("parentId", parentId);
            paramas.put("delFlag", SysManageEnum.DEL_FLAG_N.getCode());
            paramas.put("versionCode", getMaxVersionCodeByYear(versionYear));
            List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(paramas);

            if (!CollectionUtils.isEmpty(sysRegionList)) {
                sysRegionForms = Lists.newArrayList();

                List<BaseData> baseDataList = RegionUtils.getBaseDataList(appId);

                // 3. 转换为VO
                for (SysRegion sysRegion : sysRegionList) {
                    if (RegionUtils.isContainRegion(sysRegion.getRegionCode(), baseDataList)) {
                        continue;
                    }

                    SysRegionForm sysRegionForm = SysRegionForm.getSysRegionForm(sysRegion);
                    sysRegionForm.setAreaName(getAreaName(sysRegion.getAreaId()));
                    sysRegionForms.add(sysRegionForm);
                }

                if ("Y".equals(isAuth)) {
                    return getSysRegionByLoginUserId(sysRegionForms);
                }
            }
        }
        sysRegionForms.sort(RegionSortUtil.formComparator);
        return sysRegionForms;
    }

    /**
     * 根据当前登录用户获取列表
     *
     * @param sysRegionForms
     * @return
     */
    private List<SysRegionForm> getSysRegionByLoginUserId(List<SysRegionForm> sysRegionForms) {
        if (!CollectionUtils.isEmpty(sysRegionForms)) {
            String loginUserOrganizationInfo = UserUtil.getLoginUserOrganizationInfo();
            if (StringUtils.isBlank(loginUserOrganizationInfo)) {
                log.warn("当前登录用户机构信息为空，不做鉴权处理");
                return sysRegionForms;
            }
            SysOrganization sysOrganization = JsonUtils.json2obj(loginUserOrganizationInfo, SysOrganization.class);
            // 找出用户的真实区划级别 如是四川省就找四川省的CODE   如果是四川省成都市获取的就是([四川CODE，成都CODE])
            List<String> userOrg = Lists.newArrayList();
            if (sysOrganization != null) {
                // 获取机构级别
                String regionCode = sysOrganization.getRegioncode();
                if (!StringUtils.isBlank(regionCode)) {
                    String regionLastCode = sysOrganization.getRegionLastCode();
                    if (!StringUtils.isBlank(regionLastCode)) {
                        if (regionCode.contains(regionLastCode)) {
                            // 如果存在找出本级和本级之前的
                            List<String> regionCodes = JsonUtils.json2obj(regionCode, List.class);
                            if (!CollectionUtils.isEmpty(regionCodes)) {
                                for (String item : regionCodes) {
                                    userOrg.add(item);
                                    if (regionLastCode.equals(item)) {
                                        break;
                                    }
                                }
                            }

                        }
                    }
                }
            }
            if (!CollectionUtils.isEmpty(userOrg)) {
                for (SysRegionForm sysRegionForm : sysRegionForms) {
                    if (userOrg.contains(sysRegionForm.getRegionCode())) {
                        return Lists.newArrayList(sysRegionForm);
                    }
                }
            }

        }
        return sysRegionForms;
    }


    @Override
    public Map<String, Object> getProvinceAndCityAndCountryList() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("province", sysRegionMapper.getProvinceList());
        map.put("city", sysRegionMapper.getCityList());
        map.put("country", sysRegionMapper.getCountryList());

        return map;
    }

    @Override
    public String getRegionNames(String regionCodes, Integer versionYear) {
        if (!StringUtils.isBlank(regionCodes)) {
            List<String> idsByStr = IdUtil.getIdsByStr(regionCodes);
            Map<String, String> regionCodeMapping = SysCacheUtils.getRegionCodeMapping(versionYear);
            if (CollectionUtils.isEmpty(regionCodeMapping)) {
                Map<String, Object> params = new HashMap<>();
                params.put("versionCode", getMaxVersionCodeByYear(versionYear));

                // 当使用的时候查询数据组装成Map
                List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
                if (!CollectionUtils.isEmpty(sysRegionList)) {
                    regionCodeMapping = sysRegionList.stream().collect(Collectors.toMap(SysRegion::getRegionCode, SysRegion::getRegionName, (k1, k2) -> k1));
                    SysCacheUtils.setRegionCodeMappingCache(regionCodeMapping, versionYear);
                }
            }
            Map<String, String> maps = regionCodeMapping;
            List<String> names = idsByStr.stream().map(s -> {
                String name = maps.get(s);
                if (!StringUtils.isBlank(name)) {
                    return name;
                }
                return "";
            }).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(names)) {
                return "";
            }
            try {
                return IdUtil.getStrIdsByList(names);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("行政区划代码存在空置");
            }

        }
        return "";
    }

    @Override
    public SysRegion getRegionByName(String regionName) {
        List<SysRegion> sysRegionList = sysRegionMapper.selectList(new QueryWrapper<SysRegion>().eq("REGION_NAME", regionName));
        if (CollectionUtils.isEmpty(sysRegionList)) {
            return null;
        }
        List<SysRegion> collect = sysRegionList.stream().filter(item -> !SysManageEnum.DEL_FLAG_Y.getCode().equals(item.getDelFlag())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(collect)) {
            return null;
        }

        return collect.get(0);
    }

    @Override
    public boolean checkRegionLevel(String regionCode, String level) {
        SysRegion sysRegion = getOneByCode(regionCode, 0);
        if (sysRegion == null) {
            throw new SofnException("regionLastCode not found");
        }

        StringBuilder regex = new StringBuilder("^");
        switch (level) {
            case SysConstant.MINISTRY:
                regex.append("/");
                break;
            case SysConstant.PROVINCE:
                regex.append("/\\w+");
                break;
            case SysConstant.CITY:
                regex.append("/\\w+/\\w+");
                break;
            case SysConstant.COUNTY:
                regex.append("/\\w+/\\w+/\\w+");
                break;
            case SysConstant.COUNTRY:
                regex.append("/\\w+/\\w+/\\w+/\\w+");
                break;
            default:
        }
        regex.append("$");
        return RegexUtils.isMatch(regex.toString(), sysRegion.getParentIds());
    }

    /**
     * 更新表中的区划名称路由字段
     */
    public void updateAllRegionNames() {
        List<SysRegion> list = sysRegionMapper.selectList(new QueryWrapper<SysRegion>());
        for (int i = 0; i < list.size(); i++) {
            SysRegion sysRegion = list.get(i);
            if (sysRegion.getParentId().equals("0")) {
                sysRegion.setParentNames("/");
                sysRegionMapper.updateById(sysRegion);
            } else if (sysRegion.getParentId().equals(CommonEnum.SYS_REGION_ROOT_NODE.getCode())) {
                sysRegion.setParentNames("/" + CommonEnum.SYS_REGION_ROOT_NODE.getDescription());
                sysRegionMapper.updateById(sysRegion);
            } else {
                System.out.println(sysRegion.getRegionName());
                StringBuilder parentIds = new StringBuilder();
                String parentId = sysRegion.getParentId();
                while (true) {
                    SysRegion parentRegion = sysRegionMapper.selectById(parentId);
                    parentId = parentRegion.getParentId();
                    parentIds.insert(0, parentRegion.getRegionName());
                    parentIds.insert(0, "/");
                    if (parentRegion.getId().equals(CommonEnum.SYS_REGION_ROOT_NODE) || parentRegion.getId().equals("0")) {
                        break;
                    }
                }
                System.out.println(parentIds.toString());
                sysRegion.setParentNames(parentIds.toString());
                sysRegionMapper.updateById(sysRegion);
            }
        }
    }

    /***
     * 更新redis缓存中的一下两个key的数据：
     *  CommonEnum.SYS_REGION_NAME_CODE_CACHE_KEY
     *  CommonEnum.SYS_REGION_CODE_NAME_CACHE_KEY
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRegionRedisCache() {
//        if (!redisHelper.hasKey(CommonEnum.SYS_REGION_NAME_CODE_CACHE_KEY.getCode())
//                || !redisHelper.hasKey(CommonEnum.SYS_REGION_CODE_NAME_CACHE_KEY.getCode())) {

//            int maxPage = 100;
//            int pageNo = 1;
//            int pageSize = 500;
//
//            while (pageNo <= maxPage) {
//                PageHelper.startPage(pageNo, pageSize);
        List<SysRegion> list = sysRegionMapper.selectList(new QueryWrapper<SysRegion>());
//                PageInfo<SysRegion> pageInfo = new PageInfo<>(list);
        Map<String, Object> nameCodeMaps = Maps.newHashMap();
        Map<String, Object> codeNameMaps = Maps.newHashMap();
        list.forEach(sysRegion -> {
            String regionNamePath = sysRegion.getParentNames() + "/" + sysRegion.getRegionName();
            String regionCodePath = sysRegion.getParentIds() + "/" + sysRegion.getRegionCode();
            String nameReplace = regionNamePath.replaceAll("/", "_");
            String codeReplaces = regionCodePath.replaceAll("/", "_");
            nameCodeMaps.put(nameReplace, sysRegion.getRegionCode());
            codeNameMaps.put(codeReplaces, sysRegion.getRegionName());
        });

// 将区划对象列表转换成[区划名称路由-区划代码]Map
//                Map<String, Object> nameCodeMaps = list.stream().collect(Collectors.toMap(sysRegion -> {
//                    String regionNamePath = sysRegion.getParentNames() + "/" + sysRegion.getRegionName();
//                    return regionNamePath.replaceAll("/", "_");
//                }, SysRegion::getRegionCode, (k, v) -> k));

//                // 将区划对象列表转换成[区划代码路由-区划名称]Map
//                Map<String, Object> codeNameMaps = list.stream().collect(Collectors.toMap(sysRegion -> {
//                    String regionCodePath = sysRegion.getParentIds() + "/" + sysRegion.getId();
//                    return regionCodePath.replaceAll("/", "_");
//                }, SysRegion::getRegionName, (k, v) -> k));
        try {
            redisHelper.hmset(CommonEnum.SYS_REGION_NAME_CODE_CACHE_KEY.getCode(), nameCodeMaps);
            redisHelper.hmset(CommonEnum.SYS_REGION_CODE_NAME_CACHE_KEY.getCode(), codeNameMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }

//                if (pageInfo.getSize() < pageSize) {
//                    break;
//                }
//                pageNo++;
//            }
//        }
    }

    @Override
    public SysRegionInfoVo getSysRegionInfoById(String code) {
        SysRegionInfoVo vo = new SysRegionInfoVo();
        SysRegion sysRegion = getOneByCode(code, 0);
        if (sysRegion == null) {
            return vo;
        }

        List<String> codes = getFormatIdsOrNames(sysRegion.getParentIds(), sysRegion.getRegionCode(), "ID");
        List<String> names = getFormatIdsOrNames(sysRegion.getParentNames(), sysRegion.getRegionName(), "NAME");

        if (codes.size() > 0) {
            vo.setProvince(codes.get(0));
        }

        if (codes.size() > 1) {
            vo.setCity(codes.get(1));
        }

        if (codes.size() > 2) {
            vo.setArea(codes.get(2));
        }

        if (names.size() > 0) {
            vo.setProvinceName(names.get(0));
        }

        if (names.size() > 1) {
            vo.setCityName(names.get(1));
        }

        if (names.size() > 2) {
            vo.setAreaName(names.get(2));
        }
        return vo;
    }

    @Override
    public List<String> getFormatIdsOrNames(String joins, String lastStr, String idOrName) {
        joins = joins == null ? "" : joins;
        lastStr = lastStr == null ? "" : lastStr;
        String rootLabel = "";
        if (StringUtils.equalsIgnoreCase("ID", idOrName)) {
            rootLabel = "/" + SysManageEnum.ROOT_LEVEL.getCode();
        } else if (StringUtils.equalsIgnoreCase("NAME", idOrName)) {
            rootLabel = "/" + SysManageEnum.ROOT_LEVEL.getDescription();
        }

        String full = joins + CommonEnum.SLASH.getCode() + lastStr;
        if (StringUtils.isBlank(full)) {
            return new ArrayList<>();
        }

        if (full.contains(rootLabel)) {
            full = full.replace(rootLabel, "");
        }

        String[] codes = full.split("/");

        List<String> list = new ArrayList<>(Arrays.asList(codes));
        list.remove("");
        return list;
    }

    @Override
    public List<String> getFormatIdsOrNamesByCode(String code, String idOrName) {
        if (StringUtils.isBlank(code)) {
            throw new SofnException("code is blank");
        }

        if (StringUtils.equalsIgnoreCase(code, SysManageEnum.ROOT_LEVEL.getCode())) {
            List<String> root = new ArrayList<>();
            root.add(SysManageEnum.ROOT_LEVEL.getDescription());
            return root;
        }

        SysRegion sysRegion = getOneByCode(code, 0);
        if (sysRegion == null) {
            return null;
        }

        if (StringUtils.equalsIgnoreCase("ID", idOrName)) {
            return getFormatIdsOrNames(sysRegion.getParentIds(), sysRegion.getRegionCode(), "ID");
        } else {
            return getFormatIdsOrNames(sysRegion.getParentNames(), sysRegion.getRegionName(), "NAME");
        }
    }

    @Override
    public List<SysRegion> getSysRegionByContion(Map<String, Object> params) {
        GetAllInfoByPageUtil<SysRegion> getAllInfoByPageUtil = new GetAllInfoByPageUtil<>(800);
        List<SysRegion> sysOrgList = getAllInfoByPageUtil.getAllInfo((params2) -> sysRegionMapper.getSysRegionByContion((Map<String, Object>) params2[0]),
                0, true, "ids", params);

        return sysOrgList;
    }

    @Override
    public Integer getMaxVersionYear(Integer versionYear) {
        return sysRegionMapper.getMaxVersionYear(versionYear);
    }

    @Override
    public Long getMaxVersionCodeByYear(Integer versionYear) {
        int maxVersionYear = getMaxVersionYear(versionYear);
        if (versionYear == null || versionYear <= 0 || versionYear > maxVersionYear) {
            versionYear = maxVersionYear;
        }

        return sysRegionMapper.getMaxVersionCodeByYear(versionYear);
    }

    @Override
    public Map<String, String> getRegionNameMaps(Set<String> ids, Integer year) {
        Map<String, String> map = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(ids)) {
            if (year == null || year == 0) {
                String strYear = DateUtils.format(new Date(), "yyyy");
                year = Integer.parseInt(strYear);

            }
            Map<String, Object> params = new HashMap<>();
            Long maxVersionCodeByYear = getMaxVersionCodeByYear(year);
            if (maxVersionCodeByYear == null) {
                maxVersionCodeByYear = Long.parseLong("1");
            }
            params.put("versionCode", maxVersionCodeByYear);
            params.put("ids", ids);

            // 当使用的时候查询数据组装成Map
            List<SysRegion> sysRegionList = sysRegionMapper.getSysRegionByContion(params);
            if (!CollectionUtils.isEmpty(sysRegionList)) {
                sysRegionList.forEach(item -> map.put(item.getRegionCode(), item.getRegionName()));
            }
            return map;
        }
        return map;
    }

}
