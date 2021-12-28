package com.sofn.fdpi.util;


import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.fdpi.sysapi.SysRegionApi;
import com.sofn.fdpi.sysapi.bean.SysDict;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 申请单号,标识编码工具类
 */
public class CodeUtil {

    private static SysRegionApi sysRegionApi = SpringContextHolder.getBean(SysRegionApi.class);

    /**
     * @param provinceCode 省代码
     * @param type         单号类型 通过CodeTypeEnum获取
     * @param sequenceNum  顺序号
     * @return
     */
    public static String getApplyCode(String provinceCode, String type, String sequenceNum) {
        StringBuilder sb = new StringBuilder(DateUtils.format(new Date(), "yyyyMMdd"));
        if (StringUtils.hasText(provinceCode)) {
            sb.append("0").append(provinceCode);
        } else {
            throw new SofnException("未找到对应的省份编号！");
        }
        sb.append(type).append(StringUtils.hasText(sequenceNum) ? sequenceNum : "000001");
        return sb.toString();
    }

    public static String getProvinceCode(String provinceName) {
        Map<String, String> paperTypeMap = sysRegionApi.getDictListByType("province_code").getData().
                stream().collect(Collectors.toMap(SysDict::getDictname, SysDict::getDictcode));
        String provinceCode = null;
        for (String key : paperTypeMap.keySet()) {
            if (provinceName.startsWith(key)) {
                provinceCode = paperTypeMap.get(key);
                break;
            }
        }
        if (StringUtils.isEmpty(provinceCode)) {
            throw new SofnException("未找到对应的省份编号！");
        }
        return provinceCode;
    }
}
