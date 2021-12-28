package com.sofn.common.utils;

import com.sofn.common.enums.CommonEnum;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 先调用支撑平台SysRegionController.updateRegionRedisCache接口才会有缓存，下面的方法才有效
 * 区划缓存查询工具类
 * @author quzhijie
 */
public class RegionCacheUtils {

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    /**
     * 根据区划名称从缓存中获取区划代码
     * @param names 区划名称，如：[四川省，成都市，武侯区]
     * @return 区划代码数组,如：["510000","510100","510106"]
     */
    public static String[] getRegionCodesByNames(String... names){
      if (names == null || names.length==0) {
        return new String[]{};
      }

      List<String> nameList = Arrays.asList(names);
      String[] codes = new String[nameList.size()];
      for (int i=0; i<names.length; i++) {
        // 拼接缓存key
        List<String> subNames = nameList.subList(0,i+1);
        String cacheItemKey = String.format("_%s_%s",CommonEnum.SYS_REGION_ROOT_NODE.getDescription(),StringUtils.join(subNames,"_"));
        Object subCode = redisHelper.hget(CommonEnum.SYS_REGION_NAME_CODE_CACHE_KEY.getCode(),cacheItemKey);
        codes[i] = subCode==null?"":subCode.toString();
      }

      return codes;
    }

    /**
     * 根据区划代码从缓存中获取区划名称
     * @param codes 区划代码，如：["510000","510100","510106"]
     * @return 区划名称数组,如：[四川省，成都市，武侯区]
     */
    public static String[] getRegionNamesByCodes(String... codes){
      if (codes == null || codes.length==0) {
        return new String[]{};
      }

      List<String> codeList = Arrays.asList(codes);
      String[] names = new String[codeList.size()];
      for (int i=0; i<codes.length; i++) {
        // 拼接缓存key
        List<String> subCodes = codeList.subList(0,i+1);
        String cacheItemKey = String.format("_%s_%s",CommonEnum.SYS_REGION_ROOT_NODE.getCode(),StringUtils.join(subCodes,"_"));
        Object subCode = redisHelper.hget(CommonEnum.SYS_REGION_CODE_NAME_CACHE_KEY.getCode(),cacheItemKey);
        names[i] = subCode==null?"":subCode.toString();
      }

      return names;
    }
}
