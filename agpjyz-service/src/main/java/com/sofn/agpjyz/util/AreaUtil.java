package com.sofn.agpjyz.util;

import com.sofn.common.exception.SofnException;
import org.apache.commons.lang.StringUtils;

/**
 * @Description: 验证区域信息必须传省市区
 * @Auther: xiaobo
 * @Date: 2020-08-24 15:24
 */
public class AreaUtil {
    public static void checkArea(String province,String city,String area){
            if (StringUtils.isEmpty(province)){
                throw  new SofnException("省级区域不能为空");
            }
            if (StringUtils.isEmpty(city)){
                throw  new SofnException("市级区域不能为空");
             }
            if (StringUtils.isEmpty(area)){
                throw  new SofnException("县级区域不能为空");
            }
    }
}
