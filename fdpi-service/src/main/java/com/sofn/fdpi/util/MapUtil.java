package com.sofn.fdpi.util;

import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;

import java.util.Collections;
import java.util.Map;

public class MapUtil {

    /**
     * @param keys
     * @param vals
     * @return
     */
    public static Map<String, Object> getParams(String[] keys, Object[] vals) {
        Integer keyLength = keys.length;
        Integer valLength = vals.length;
        if (keyLength == 0 || valLength == 0)
            return Collections.EMPTY_MAP;
        if (!keyLength.equals(valLength))
            throw new SofnException("参数拼装有误，请检查！");
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(keyLength);
        for (int i = 0; i < keyLength; i++) {
            params.put(keys[i], vals[i]);
        }
        return params;
    }
}
