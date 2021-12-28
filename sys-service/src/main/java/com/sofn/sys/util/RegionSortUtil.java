package com.sofn.sys.util;

import com.sofn.sys.vo.SysRegionForm;
import com.sofn.sys.vo.SysRegionTreeVo;

import java.util.Comparator;

/**
 * @author heyongjie
 * @date 2020/6/9 9:57
 */
public class RegionSortUtil {

    /**
     * 行政区划排序
     */
    public static Comparator comparator = (Comparator<SysRegionTreeVo>) (o1, o2) -> {
        if (o1 == null || o2 == null) {
            return 0;
        }
        if (o1.getRegionCode() == null || o2.getRegionCode() == null) {
            return 0;
        }
        try{
            return Integer.parseInt(o1.getRegionCode()) - Integer.parseInt(o2.getRegionCode());
        }catch (Exception e){
            try{
                return Integer.parseInt(Long.parseLong(o1.getRegionCode()) -  Long.parseLong(o2.getRegionCode()) + "");
            }catch (Exception temp){
                temp.printStackTrace();
                return 0;
            }
        }
    };

    /**
     * 行政区划排序
     */
    public static Comparator formComparator = (Comparator<SysRegionForm>) (o1, o2) -> {
        if (o1 == null || o2 == null) {
            return 0;
        }
        if (o1.getRegionCode() == null || o2.getRegionCode() == null) {
            return 0;
        }
        try{
            return Integer.parseInt(o1.getRegionCode()) - Integer.parseInt(o2.getRegionCode());
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    };

}
