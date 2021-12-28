package com.sofn.ducss.util;

import com.sofn.ducss.enums.AuditStatusEnum;
import com.sofn.ducss.enums.RegionLevel;
import com.sofn.ducss.vo.AreaRegionCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据汇总 搜索状态统一获取
 */
public class AggregateSearchStatusUtil {


    /**
     * 获取不同区域下的查询状态
     *
     * @param currentLevel
     * @param areaId
     * @return
     */
    public static List<String> getStatusList(String currentLevel, String areaId) {
        // 默认查询下级都是查询已上报 + 已读 +已通过
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add(AuditStatusEnum.PASSED.getCode());
        statusList.add(AuditStatusEnum.READ.getCode());
        statusList.add(AuditStatusEnum.REPORTED.getCode());
        // 特殊情况： 跨级查询只查询已通过的
        // 查询区划
        AreaRegionCode targetRegionCode = SysRegionUtil.getRegionCodeByLastCode2(areaId);
        if (targetRegionCode == null) {
            return statusList;
        }
        // 省级查询
        boolean isProvinceSearch = RegionLevel.PROVINCE.getCode().equals(currentLevel)
                && (RegionLevel.CITY.getCode().equals(targetRegionCode.getRegionLevel()) || RegionLevel.COUNTY.getCode().equals(targetRegionCode.getRegionLevel()));
        if (isProvinceSearch) {
            statusList.remove(AuditStatusEnum.READ.getCode());
            statusList.remove(AuditStatusEnum.REPORTED.getCode());
        }
        // 部级查询
        boolean isMinistrySearch = RegionLevel.MINISTRY.getCode().equals(currentLevel)
                && (RegionLevel.PROVINCE.getCode().equals(targetRegionCode.getRegionLevel())
                || RegionLevel.CITY.getCode().equals(targetRegionCode.getRegionLevel())
                || RegionLevel.COUNTY.getCode().equals(targetRegionCode.getRegionLevel()));
        if (isMinistrySearch) {
            statusList.remove(AuditStatusEnum.READ.getCode());
            statusList.remove(AuditStatusEnum.REPORTED.getCode());
        }
        return statusList;
    }


    /**
     * 获取单个区域的查询状态
     *
     * @param areaId
     * @return
     */
    public static List<String> getStatusList(String areaId) {
        // 默认查询下级都是查询已上报 + 已通过
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add(AuditStatusEnum.PASSED.getCode());
        statusList.add(AuditStatusEnum.READ.getCode());
        statusList.add(AuditStatusEnum.REPORTED.getCode());
        // 特殊情况： 跨级查询只查询已通过的
        // 当前账号
        AreaRegionCode currentRegionCode = SysRegionUtil.getRegionCode();
        // 查询区划
        AreaRegionCode targetRegionCode = SysRegionUtil.getRegionCodeByLastCode2(areaId);
        if (targetRegionCode == null) {
            return statusList;
        }
        // 省级查询
        boolean isProvinceSearch = RegionLevel.PROVINCE.getCode().equals(currentRegionCode.getRegionLevel())
                && (RegionLevel.CITY.getCode().equals(targetRegionCode.getRegionLevel()) || RegionLevel.COUNTY.getCode().equals(targetRegionCode.getRegionLevel()));
        if (isProvinceSearch) {
            statusList.remove(AuditStatusEnum.READ.getCode());
            statusList.remove(AuditStatusEnum.REPORTED.getCode());
        }
        // 部级查询
        boolean isMinistrySearch = RegionLevel.MINISTRY.getCode().equals(currentRegionCode.getRegionLevel())
                && (RegionLevel.PROVINCE.getCode().equals(targetRegionCode.getRegionLevel())
                || RegionLevel.CITY.getCode().equals(targetRegionCode.getRegionLevel())
                || RegionLevel.COUNTY.getCode().equals(targetRegionCode.getRegionLevel()));
        if (isMinistrySearch) {
            statusList.remove(AuditStatusEnum.READ.getCode());
            statusList.remove(AuditStatusEnum.REPORTED.getCode());
        }
        return statusList;
    }


    public static List<String> getCheckInfoStatus(Byte collectFlowStatus) {
        // 默认查询下级都是查询已上报 + 已通过
        ArrayList<String> statusList = new ArrayList<>();
        statusList.add(AuditStatusEnum.PASSED.getCode());
        statusList.add(AuditStatusEnum.REPORTED.getCode());
        // 审核通过查看的是通过的数据
        if (AuditStatusEnum.PASSED.getCode().equals(collectFlowStatus.toString())) {
            statusList.remove(AuditStatusEnum.READ.getCode());
            statusList.remove(AuditStatusEnum.REPORTED.getCode());
        }
        return statusList;
    }
}
