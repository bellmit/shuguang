package com.sofn.fyem.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.common.utils.UserUtil;
import com.sofn.fyem.enums.AuditStatueEnum;
import com.sofn.fyem.enums.RoleLevelEnum;
import com.sofn.fyem.model.BasicProliferationRelease;
import com.sofn.fyem.model.ProliferationReleaseStatistics;
import com.sofn.fyem.service.*;
import com.sofn.fyem.sysapi.bean.SysOrgVo;
import com.sofn.fyem.sysapi.bean.SysOrganization;
import com.sofn.fyem.vo.CityAuditVo;
import com.sofn.fyem.vo.MinisterAuditVo;
import com.sofn.fyem.vo.ProvinceAuditVo;
import com.sofn.fyem.vo.ReleaseStatisticsSpeciesVo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @Description: 用于获取用户在当前子系统中的用户角色
 * @Author: DJH
 * @Date: 2020/5/7 13:48
 */
public interface RoleCodeUtil {

    /**
     *  获取用户在fyem子系统中的用户角色
     * @param roleCodes
     * @return
     */
    static String getLoginUserFyemRoleCode(List<String> roleCodes){
        if (CollectionUtils.isEmpty(roleCodes)) {
            roleCodes = UserUtil.getLoginUserRoleCodeList();
        }
        for (String roleCode: roleCodes) {
            if (roleCode.contains("fyem")){
                return roleCode;
            }
        }
        return "";
    }

    /**
     * 获取用户在fyem子系统中的用户角色级别
     * @return
     */
    static String getLoginUserFyemRoleLevel(){
        List<String> roleCodes = UserUtil.getLoginUserRoleCodeList();
        String roleCode = getLoginUserFyemRoleCode(roleCodes);
        String level = "";
        switch (roleCode){
            case "fyem_county" : level = "county";break;
            case "fyem_city_add" :
            case "fyem_city" :
                level = "city";break;
            case "fyem_prov_add" :
            case "fyem_prov" :
                level = "province";break;
            case "fyem_master_add" :
            case "fyem_master" :
                level = "ministry";break;
        }
        return level;
    }

    /**
     * 填报员角色集合
     */
    Set<RoleLevelEnum> adds = Collections.unmodifiableSet(Sets.newHashSet(
            RoleLevelEnum.LEVEL_COUNTY,
            RoleLevelEnum.LEVEL_CITY_ADD,
            RoleLevelEnum.LEVEL_PROVINCE_ADD,
            RoleLevelEnum.LEVEL_MASTER_ADD
    ));

    /**
     * 判断处于 auditStatueEnum 的数据是否可以被 roleLevelEnum 的角色修改
     * @param auditStatueEnum 数据状态
     * @param roleLevelEnum 角色状态, 为空时表示最高权限
     * @param createUserLevel 创建人的角色状态
     * @return true 可以修改
     */
    @Deprecated
    @SuppressWarnings("all")
    static boolean modifiable(Optional<AuditStatueEnum> auditStatueEnum, Optional<RoleLevelEnum> roleLevelEnum, Optional<RoleLevelEnum> createUserLevel) {
        final RoleLevelEnum createLevel = createUserLevel.orElseThrow(() -> new IllegalArgumentException("非法的角色"));
        if (roleLevelEnum.isPresent() && !adds.contains(roleLevelEnum.get())) return false;
        switch (auditStatueEnum.get()) {
            case STATUS_RETURN_CITY:
                return !roleLevelEnum.isPresent() || roleLevelEnum.get() == RoleLevelEnum.LEVEL_COUNTY || roleLevelEnum.get() == RoleLevelEnum.LEVEL_CITY_ADD;
            case STATUS_RETURN_PROV:
                return !roleLevelEnum.isPresent() || roleLevelEnum.get() == RoleLevelEnum.LEVEL_PROVINCE_ADD;
            case STATUS_RETURN_MASTER:
                return !roleLevelEnum.isPresent() || roleLevelEnum.get() == RoleLevelEnum.LEVEL_MASTER_ADD;
            case STATUS_NOTREPORT:
                return true;
            case STATUS_APPROVE_CITY:
            case STATUS_REPORT_CITY:
                return !roleLevelEnum.isPresent() && createLevel == RoleLevelEnum.LEVEL_CITY_ADD;
            case STATUS_REPORT_PROV:
            case STATUS_APPROVE_PROV:
                return !roleLevelEnum.isPresent() && createLevel == RoleLevelEnum.LEVEL_PROVINCE_ADD;
            case STATUS_APPROVE_MASTER:
            case STATUS_REPORT_MASTER:
                return !roleLevelEnum.isPresent() && createLevel == RoleLevelEnum.LEVEL_MASTER_ADD;
            default:
                return false;
        }
    }

    /**
     * 是否可以修改数据状态
     * @param auditStatueEnum 当前数据状态
     * @param cur true if 当前数据就是待审数据
     * @param roleLevelEnum 当前角色状态
     * @param createUserLevelEnum 数据创建人角色
     * @return true 可以修改
     */
    @Deprecated
    static boolean able2Audit(AuditStatueEnum auditStatueEnum, final boolean cur, RoleLevelEnum roleLevelEnum, RoleLevelEnum createUserLevelEnum) {
        if (Objects.isNull(auditStatueEnum) || Objects.isNull(roleLevelEnum)) return false;

        if (adds.contains(roleLevelEnum)) {
            // 上报
            boolean flag = false;
            switch (auditStatueEnum) {
                case STATUS_RETURN_MASTER:
                    flag = roleLevelEnum == RoleLevelEnum.LEVEL_MASTER_ADD;
                case STATUS_RETURN_PROV:
                    flag = flag || roleLevelEnum == RoleLevelEnum.LEVEL_PROVINCE_ADD;
                case STATUS_RETURN_CITY:
                    flag = flag || roleLevelEnum == RoleLevelEnum.LEVEL_CITY_ADD || roleLevelEnum == RoleLevelEnum.LEVEL_COUNTY;
                    break;
                case STATUS_NOTREPORT:
                    return true;
                case STATUS_REPORT_MASTER:
                case STATUS_APPROVE_MASTER:
                    return !cur && createUserLevelEnum == RoleLevelEnum.LEVEL_MASTER_ADD;
                case STATUS_REPORT_PROV:
                case STATUS_APPROVE_PROV:
                    return !cur && createUserLevelEnum == RoleLevelEnum.LEVEL_PROVINCE_ADD;
                case STATUS_REPORT_CITY:
                case STATUS_APPROVE_CITY:
                    return !cur && (roleLevelEnum == RoleLevelEnum.LEVEL_COUNTY && createUserLevelEnum == RoleLevelEnum.LEVEL_CITY_ADD
                        || roleLevelEnum == RoleLevelEnum.LEVEL_CITY_ADD && createUserLevelEnum == RoleLevelEnum.LEVEL_COUNTY);
                default:
            }
            return flag;
        }

        if (cur) {
            switch (auditStatueEnum) {
                case STATUS_REPORT_CITY:
                case STATUS_RETURN_PROV:
                case STATUS_APPROVE_CITY:
                    return roleLevelEnum == RoleLevelEnum.LEVEL_CITY;
                case STATUS_REPORT_PROV:
                case STATUS_APPROVE_PROV:
                case STATUS_RETURN_MASTER:
                    return roleLevelEnum == RoleLevelEnum.LEVEL_PROVINCE;
                case STATUS_REPORT_MASTER:
                case STATUS_APPROVE_MASTER:
                    return roleLevelEnum == RoleLevelEnum.LEVEL_MASTER;
                case STATUS_RETURN_CITY:
                case STATUS_NOTREPORT:
                    return adds.contains(roleLevelEnum);
                default:
                    return false;
            }
        } else {
            switch (roleLevelEnum) {
                case LEVEL_COUNTY:
                    return auditStatueEnum != AuditStatueEnum.STATUS_REPORT_CITY &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_CITY &&
                            auditStatueEnum != AuditStatueEnum.STATUS_REPORT_PROV &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_PROV &&
                            auditStatueEnum != AuditStatueEnum.STATUS_REPORT_MASTER &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_MASTER;
                case LEVEL_CITY:
                    return auditStatueEnum != AuditStatueEnum.STATUS_REPORT_PROV &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_PROV &&
                            auditStatueEnum != AuditStatueEnum.STATUS_REPORT_MASTER &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_MASTER;
                case LEVEL_PROVINCE:
                    return auditStatueEnum != AuditStatueEnum.STATUS_REPORT_MASTER &&
                            auditStatueEnum != AuditStatueEnum.STATUS_APPROVE_MASTER;
                case LEVEL_MASTER:
                    return auditStatueEnum != AuditStatueEnum.STATUS_RETURN_MASTER;
                default:
                    return false;
            }
        }
    }

    /**
     * 检查 BasicProliferationRelease 是否可以进行填报
     * @param release
     * @param roleCodes
     */
    @Deprecated
    static void checkModifiable(final BasicProliferationRelease release, List<String> roleCodes) {
        final SysOrgVo orgVo = OrganizationUtil.getLoginUserSysOrgVo();
        BasicProliferationReleaseService basicProliferationReleaseService = SpringContextHolder.getBean(BasicProliferationReleaseService.class);
        List<BasicProliferationRelease> list = basicProliferationReleaseService.listBPRByBelongYearAndOrgId(release.getBelongYear(), orgVo.getId());

        final List<RoleLevelEnum> roles = Lists.newArrayListWithCapacity(roleCodes.size());
        for (String roleCode : roleCodes) {
            Optional<RoleLevelEnum> roleLevelEnum = RoleLevelEnum.resolve(roleCode);
            roleLevelEnum.ifPresent(roles::add);
        }

        if (!list.parallelStream().allMatch(vo -> roles.parallelStream().anyMatch(role -> modifiable(AuditStatueEnum.resolve(vo.getStatus()),
                Objects.equals(orgVo.getId(), vo.getOrganizationId()) ? Optional.of(role) : Optional.empty(),
                RoleLevelEnum.resolve(vo.getRoleCode()))))) {
            throw new SofnException("存在已上报未退回的数据");
        }
    }

    @Deprecated
    static void checkModifiable(final ReleaseStatisticsSpeciesVo release, List<String> roleCodes) {
        final SysOrgVo orgVo = OrganizationUtil.getLoginUserSysOrgVo();
        ProliferationReleaseStatisticsService proliferationReleaseStatisticsService = SpringContextHolder.getBean(ProliferationReleaseStatisticsService.class);
        List<ProliferationReleaseStatistics> list = proliferationReleaseStatisticsService.getProliferationReleaseStatisticsByBelongYearAndOrgId(release.getBelongYear(), orgVo.getId());

        final List<RoleLevelEnum> roles = Lists.newArrayListWithCapacity(roleCodes.size());
        for (String roleCode : roleCodes) {
            Optional<RoleLevelEnum> roleLevelEnum = RoleLevelEnum.resolve(roleCode);
            roleLevelEnum.ifPresent(roles::add);
        }

        if (!list.parallelStream().allMatch(vo -> roles.parallelStream().anyMatch(role -> modifiable(AuditStatueEnum.resolve(vo.getStatus()),
                Objects.equals(orgVo.getId(), vo.getOrganizationId()) ? Optional.of(role) : Optional.empty(),
                RoleLevelEnum.resolve(vo.getRoleCode()))))) {
            throw new SofnException("存在已上报未退回的数据");
        }

    }

    /**
     * 审核权限校验
     */
    @Deprecated
    static void checkAudit(String belongYear, String provinceId, String cityId, String countyId) {
        final SysOrganization org = OrganizationUtil.getLoginUserSysOrganization();
        Set<String> children = OrganizationUtil.getLogUserOrgAndChildren().parallelStream().map(SysOrgVo::getId).collect(Collectors.toSet());
        String[] parentIds = ArrayUtils.subarray(org.getParentIds().split("\\W+"), 2, Integer.MAX_VALUE);

        String level = getLoginUserFyemRoleLevel();
        int lastIndex = parentIds.length;
        RoleLevelEnum role = RoleLevelEnum.resolve(RoleCodeUtil.getLoginUserFyemRoleCode(null)).orElseThrow(() -> new UnsupportedOperationException("不符合的角色"));

        Map<String, Object> tmpParams = Maps.newHashMap();
        tmpParams.put("belongYear", belongYear);
        tmpParams.put("provinceId", Objects.equals(provinceId, "0") ? StringUtils.EMPTY : provinceId);
        tmpParams.put("cityId", Objects.equals(cityId, "0") ? StringUtils.EMPTY : cityId);
        tmpParams.put("countyId", Objects.equals(countyId, "0") ? StringUtils.EMPTY : countyId);
        final Map<String, Object> params = FyemAreaUtil.fillRegionCode(tmpParams);

        AtomicBoolean curLevel = new AtomicBoolean(Boolean.TRUE);
        String errMsg = "不符合前提条件";
        Set<String> all = new HashSet<>(children);
        switch (level) {
            case "county":
            case "city":
                CityAuditService cityAuditService = SpringContextHolder.getBean(CityAuditService.class);
                Collections.addAll(all, ArrayUtils.subarray(parentIds, 0, lastIndex--));
                params.put("organizationIdList", all);
                List<CityAuditVo> cityAuditVos = cityAuditService.listCityAuditsByBelongYear(params);
                if (!cityAuditVos.stream().allMatch(vo -> able2Audit(AuditStatueEnum.resolve(vo.getStatusCode()).orElseThrow(() -> new IllegalArgumentException("不合法的状态")),
                        Objects.equals(params.get("provinceId"), vo.getProvinceId()) && Objects.equals(params.get("cityId"), vo.getCityId())
                                && Objects.equals(params.get("countyId"), vo.getCountyId()) && curLevel.getAndSet(Boolean.FALSE), role,
                                RoleLevelEnum.resolve(vo.getRoleCode()).orElseThrow(() -> new IllegalArgumentException("不合法的角色"))))) {
                    throw new UnsupportedOperationException(errMsg);
                }
                all = new HashSet<>(children);
                errMsg = "上级存在已上报未回退的数据";
            case "province":
                ProvinceAuditService provinceAuditService = SpringContextHolder.getBean(ProvinceAuditService.class);
                Collections.addAll(all, ArrayUtils.subarray(parentIds, 0, lastIndex--));
                params.put("organizationIdList", all);
                List<ProvinceAuditVo> provinceAuditVos = provinceAuditService.listProvinceAuditsByBelongYear(params);
                if (!provinceAuditVos.stream().allMatch(vo -> able2Audit(AuditStatueEnum.resolve(vo.getStatusCode()).orElseThrow(() -> new IllegalArgumentException("不合法的状态")),
                        Objects.equals(params.get("provinceId"), vo.getProvinceId()) && Objects.equals(params.get("cityId"), vo.getCityId())
                                && Objects.equals(params.get("countyId"), vo.getCountyId()) && curLevel.getAndSet(Boolean.FALSE), role,
                        RoleLevelEnum.resolve(vo.getRoleCode()).orElseThrow(() -> new IllegalArgumentException("不合法的角色"))))) {
                    throw new UnsupportedOperationException(errMsg);
                }
                all = new HashSet<>(children);
                errMsg = "上级存在已上报未回退的数据";
            case "ministry":
                MinisterAuditService ministerAuditService = SpringContextHolder.getBean(MinisterAuditService.class);
                Collections.addAll(all, ArrayUtils.subarray(parentIds, 0, lastIndex--));
                params.put("organizationIdList", all);
                List<MinisterAuditVo> ministerAuditVos = ministerAuditService.listMinisterAuditsByBelongYear(params);
                if (!ministerAuditVos.stream().allMatch(vo -> able2Audit(AuditStatueEnum.resolve(vo.getStatusCode()).orElseThrow(() -> new IllegalArgumentException("不合法的状态")),
                        Objects.equals(params.get("provinceId"), vo.getProvinceId()) && Objects.equals(params.get("cityId"), vo.getCityId())
                                && Objects.equals(params.get("countyId"), vo.getCountyId()) && curLevel.getAndSet(Boolean.FALSE), role,
                        RoleLevelEnum.resolve(vo.getRoleCode()).orElseThrow(() -> new IllegalArgumentException("不合法的角色"))))) {
                    throw new UnsupportedOperationException(errMsg);
                }
                all = new HashSet<>(children);
                errMsg = "上级存在已上报未回退的数据";
            default:
        }


    }
}
