package com.sofn.gatewayservice.utils;

import com.sofn.common.config.UriConfig;
import com.sofn.common.utils.RegexUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 *@Description TODO
 *@Author quzhijie
 *@Date 2020/4/28 14:09
 *@Version 1.0
 **/
public class FilterUtil {

  /**
   * 是否匹配正则表达式
   * @param uri
   * @return
   */
  public static boolean isMatch(String uri, List<String> filterRegexList) {
    if (!CollectionUtils.isEmpty(filterRegexList)){
      for (String regex : filterRegexList) {
        if (StringUtils.isBlank(regex)){
          continue;
        }

        if (RegexUtils.isMatch(regex,uri)){
          return true;
        }
      }
    }

    return false;
  }

  /**
   * 过滤地址转换成正则表达式
   */
  public static void uriToRegex(UriConfig uriConfig, List<String> filterRegexList){
    Map<String,List<String>> appUris = uriConfig.getUris();
    if (!CollectionUtils.isEmpty(appUris)) {
      for (Map.Entry<String,List<String>> entry : appUris.entrySet()) {
        String appId = entry.getKey();
        List<String> urls = entry.getValue();
        if (!CollectionUtils.isEmpty(urls)){
          urls.forEach(url -> {
            if (url.contains("/**")){
              url = url.replace("/**", "/.*");
            }

            if (url.contains("/*")){
              url = url.replace("/*", "/[\\w]*");
            }

            filterRegexList.add(String.format("^%s%s$",StringUtils.equalsIgnoreCase("comm",appId)?"":"/"+appId,url));
          });
        }
      }
    }
  }

}
