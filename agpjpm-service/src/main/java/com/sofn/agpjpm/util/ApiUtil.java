package com.sofn.agpjpm.util;


import com.sofn.agpjpm.vo.DropDownWithLatinVo;
import com.sofn.common.model.Result;
import com.sofn.agpjpm.vo.DropDownVo;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiUtil {

    public static Map<String, String> getResultStrMap(Result<List<DropDownVo>> result) {
        try {
            List<DropDownVo> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().collect(Collectors.toMap(DropDownVo::getId, DropDownVo::getName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

    public static Map<String, DropDownWithLatinVo> getResultObjMap(Result<List<DropDownWithLatinVo>> result) {
        try {
            List<DropDownWithLatinVo> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                Map<String, DropDownWithLatinVo> map = new HashMap<>(list.size());
                list.forEach(dropDownWithLatinVo -> {
                    map.put(dropDownWithLatinVo.getId(), dropDownWithLatinVo);
                });
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

    public static String[] getResultStrArr(Result<String> result) {
        try {
            String str = result.getData();
            if (StringUtils.hasText(str)) {
                return str.split(",");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean existIdFromAgpjzb(Result<List<DropDownVo>> result, String id) {
        try {
            List<DropDownVo> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().map(DropDownVo::getId).collect(Collectors.toList()).contains(id);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
