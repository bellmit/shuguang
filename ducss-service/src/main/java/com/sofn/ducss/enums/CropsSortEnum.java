/**
 * Copyright (c) 1998-2021 SOFN Corporation
 * All rights reserved.
 * <p>
 * <p>
 * Created On 2021-01-13 15:21
 */
package com.sofn.ducss.enums;

import lombok.Getter;

import java.math.BigDecimal;

/**
 * 秸秆类型排序枚举
 *
 * @author jiangtao
 * @version 1.0
 **/
@Getter
public enum CropsSortEnum {

    //农作物               strawType中文名    strawType英文名          亩产量最小值               亩产量最大值               最大草谷比系数
    TOTAL	            ("全部作物","total",0),
    EARLY_RICE		    ("早稻","early_rice",1),
    MIDDLE_RICE		    ("中稻和一季晚稻","middle_rice",2),
    DOUBLE_LATE_RICE	("双季晚稻","double_late_rice",3),
    WHEAT			    ("小麦","wheat",4),
    CORN			    ("玉米","corn",  5),
    POTATO	            ("马铃薯","potato", 6),
    SWEET_POTATO		("甘薯","sweet_potato",7),
    CASSAVA	            ("木薯","cassava",8),
    PEANUT		        ("花生","peanut", 9),
    RAPE			    ("油菜","rape", 10),
    SOYBEAN	            ("大豆","soybean",11),
    COTTON		        ("棉花","cotton",12),
    SUGAR_CANE	        ("甘蔗","sugar_cane",13),
    OTHER	            ("其他","other",14)
    ;

    private String chineseName;
    private String name;
    private int index;

    CropsSortEnum(String chineseName, String name, int index) {
        this.chineseName = chineseName;
        this.name = name;
        this.index = index;
    }
}
