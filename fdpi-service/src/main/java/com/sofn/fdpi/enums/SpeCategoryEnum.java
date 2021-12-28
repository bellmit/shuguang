package com.sofn.fdpi.enums;

import com.sofn.fdpi.vo.SelectVo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum SpeCategoryEnum {


    THALATTOSAURIA("fdpi_thalattosauria_unit", "海龙类"),
    WATER_SNAKE("fdpi_water_snake_unit", "水蛇类"),
    MUSSELS("fdpi_mussels_unit", "蚌类"),
    GINSENG("fdpi_ginseng_unit", "参类"),
    TRIDACTYLA("fdpi_tridactyla_unit", "砗磲类"),
    SNAILS("fdpi_snails_unit", "螺类"),
    HYDRA("fdpi_hydra_unit", "螅类"),
    ALLIGATORS("fdpi_alligators_unit", "鳄鼍类"),
    FRESHWATER_TURTLES("fdpi_freshwater_turtles_unit", "淡水龟"),
    SALAMANDERS1("fdpi_salamanders1_unit", "螈类"),
    FISH("fdpi_fish_unit", "鱼类"),
    TORTOISE("fdpi_tortoise_unit", "鳖类"),
    MAMMALS("fdpi_mammals_unit", "哺乳类"),
    CORALS("fdpi_corals_unit", "珊瑚"),
    SHELLFISH("fdpi_shellfish_unit", "贝类"),
    SALAMANDERS2("fdpi_salamanders2_unit", "鲵类"),
    PRODUCTS("fdpi_products_unit", "制品类"),
    CAVIAR("fdpi_caviar_unit", "鱼子酱"),
    TURTLES("fdpi_turtles_unit", "海龟类"),
    FROGS("fdpi_frogs_unit", "蛙类");


    private String key;
    private String val;

    SpeCategoryEnum(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public static List<SelectVo> getSelect() {
        SpeCategoryEnum[] values = SpeCategoryEnum.values();
        ArrayList<SelectVo> list = new ArrayList<>(values.length);
        for (SpeCategoryEnum obj : values) {
            SelectVo sv = new SelectVo();
            sv.setKey(obj.getKey());
            sv.setVal(obj.getVal());
            list.add(sv);
        }
        return list;
    }

    public static String getVal(String key) {
        SpeCategoryEnum[] arr = values();
        for (SpeCategoryEnum obj : arr) {
            if (obj.key.equals(key)) {
                return obj.val;
            }
        }
        return null;
    }
}
