package com.sofn.ducss.enums;

import com.sofn.common.exception.SofnException;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * 六大区枚举
 *
 * 这里面请不要随意的添加枚举， 否则会影响程序计算
 *
 * @author jiangtao
 * @date 2020/11/9
 */
@Getter
public enum SixRegionEnum {

    /**
     * 六大区枚举
     *
     */
    NORTH_REGION("110000,120000,370000,410000,130000,140000","华北区"),
    CHANG_JIANG_RIVER_REGION("420000,430000,360000,330000,310000,320000,340000","长江中下游区"),
    NORTHEAST_REGION("210000,220000,230000,150000","东北区"),
    NORTHWEST_REGION("610000,620000,630000,640000,650000,660000","西北区"),
    SOUTHWEST_REGION("510000,520000,530000,540000,500000","西南区"),
    SOUTH_REGION("350000,440000,450000,460000","华南地区"),
    ;


    private String code;
    private String description;


    SixRegionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }


    /**
     * 根据名称获取枚举
     * @param code   枚举名称
     * @return  SysManageEnum
     */
    public static SixRegionEnum getSixRegionEnum(String code){
        for (SixRegionEnum c : SixRegionEnum.values()) {
            if(c.name().equals(code)){
                return c;
            }
        }
        throw new SofnException("未找到该枚举");

    }
    //根据区域code获取中文名
    public static String getByStrawTypeEnglish(String codes){
        if(StringUtils.isEmpty(codes)) return null;

        for (SixRegionEnum ce : SixRegionEnum.values()) {
            if(ce.code.contains(codes)){
                return ce.description;
            }
        }

        return null;
    }
    //根据中文名获取code
    public static String getCode(String description){
        if(StringUtils.isEmpty(description)) return "";
        for (SixRegionEnum ce : SixRegionEnum.values()) {
            if (ce.description.equals(description))return ce.code;
        }
        return "";
    }

    public static void main(String[] args) {
    }
}
