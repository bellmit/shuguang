package com.sofn.agzirdd.util;

import com.sofn.agzirdd.sysapi.SysRegionApi;
import com.sofn.agzirdd.sysapi.bean.SysRegionTreeVo;
import com.sofn.common.exception.SofnException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @Description 根据id将entiy赋值name
 * @Author wg
 * @Date 2021/8/17 10:32
 **/
@Component
public class SetValueUtil {
    @Resource
    private SysRegionApi sysRegionApi;

    public Object setNameById(Object obj) {
        try {
            Class<?> clasz = obj.getClass();
            Field countyId = null;
            try {
                countyId = clasz.getDeclaredField("countyId");
            } catch (NoSuchFieldException e) {
                //如果没有这个字段，那就是继承父类的
                Class<?> superclass = clasz.getSuperclass();
                countyId = superclass.getDeclaredField("countyId");
            }
            countyId.setAccessible(true);
            //获取countyId的值
            String countyIdVal = (String) countyId.get(obj);
            List<SysRegionTreeVo> datas = sysRegionApi.getParentNode(countyIdVal).getData();
            String provinceIdName = datas.get(0).getRegionName();//省name
            String cityNameVal = datas.get(1).getRegionName();//市name
            String countyNameVal = datas.get(2).getRegionName();//区name
            //一设置provinceIdName值
            Field provinceName = null;
            try {
                provinceName = clasz.getDeclaredField("provinceName");
            } catch (NoSuchFieldException e) {
                //如果没有这个字段，那就是继承父类的
                Class<?> superclass = clasz.getSuperclass();
                provinceName = superclass.getDeclaredField("provinceName");
            }
            provinceName.setAccessible(true);
            provinceName.set(obj, provinceIdName);
            //二、获取cityId
            Field cityName = null;
            try {
                cityName = clasz.getDeclaredField("cityName");
            } catch (NoSuchFieldException e) {
                //如果没有这个字段，那就是继承父类的
                Class<?> superclass = clasz.getSuperclass();
                cityName = superclass.getDeclaredField("cityName");
            }
            cityName.setAccessible(true);
            cityName.set(obj, cityNameVal);
            //三、设置countyName的值
            Field countyName = null;
            try {
                countyName = clasz.getDeclaredField("countyName");
            } catch (NoSuchFieldException e) {
                //如果没有这个字段，那就是继承父类的
                Class<?> superclass = clasz.getSuperclass();
                countyName = superclass.getDeclaredField("countyName");
            }
            countyName.setAccessible(true);
            countyName.set(obj, countyNameVal);
        } catch (Exception e) {
            throw new SofnException("反射赋值异常");
        }
        return obj;
    }
}
