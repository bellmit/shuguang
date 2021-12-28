package com.sofn.agpjyz.util;

import com.sofn.agpjyz.vo.DropDownVo;
import com.sofn.agpjyz.vo.DropDownWithLatinVo;
import com.sofn.agpjyz.vo.DropDownWithOther;
import com.sofn.common.model.Result;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Deacription TODO
 * @Author yumao
 * @Date 2020/4/9 9:55
 **/
public class ApiUtil {

    public static Map<String, String> getResultMap(Result<List<DropDownVo>> result) {
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

    public static Map<String, String> getResultMap1(Result<List<DropDownWithLatinVo>> result) {
        try {
            List<DropDownWithLatinVo> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().collect(Collectors.toMap(DropDownWithLatinVo::getId, DropDownWithLatinVo::getName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }

    public static Map<String, String> getResultMap2(Result<List<DropDownWithOther>> result) {
        try {
            List<DropDownWithOther> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().collect(Collectors.toMap(DropDownWithOther::getId, DropDownWithOther::getName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_MAP;
    }
    public static Map<String, String> getResultMap3(Result<List<DropDownWithOther>> result) {
        try {
            List<DropDownWithOther> list = result.getData();
            if (!CollectionUtils.isEmpty(list)) {
                return list.stream().collect(Collectors.toMap(DropDownWithOther::getId, DropDownWithOther::getThirdColumn));
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
}
