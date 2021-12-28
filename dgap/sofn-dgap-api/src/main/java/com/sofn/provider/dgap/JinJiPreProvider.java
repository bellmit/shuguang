package com.sofn.provider.dgap;

import java.util.List;
import java.util.Map;

/**
 * @author hyl
 * @date 2018/9/29 15:03
 */
public interface JinJiPreProvider {
    List<Map<String,Object>> getDatas(String table);
}
