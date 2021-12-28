package com.sofn.ahhrm.util;

import com.sofn.ahhrm.vo.DropDownVo;
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
