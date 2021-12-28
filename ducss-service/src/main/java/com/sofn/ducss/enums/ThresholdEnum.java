package com.sofn.ducss.enums;

import com.google.common.collect.Maps;
import com.sofn.common.excel.exception.ExcelException;
import com.sofn.common.exception.SofnException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * 阈值相关的枚举
 */
@AllArgsConstructor
@Getter
public enum ThresholdEnum {

    YEAR_MANAGER_IS_ADD_Y("1","新增"),
    YEAR_MANAGER_IS_ADD_N("2","不是新增"),

    VALUE_MANAGER_COMPUTER_TYPE_1("1", "数值"),
    VALUE_MANAGER_COMPUTER_TYPE_2("2", "比例"),

    VALUE_OPERATE_1(">=","大于等于"),
    VALUE_OPERATE_2("<=","小于等于"),

    VALUE_MANAGER_TABLE_TYPE_SJSH("1","数据审核"),
    VALUE_MANAGER_TABLE_TYPE_CSQKHZ("2","产生情况汇总"),
    VALUE_MANAGER_TABLE_TYPE_LYQKHZ("3","利用情况汇总"),
    VALUE_MANAGER_TABLE_TYPE_HTLTQK("4","还田离田情况"),

    VALUE_MANAGER_TARGET_TYPE_1_CSL("1-1","产生量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_1_KSJL("1-2","可收集量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_1_ZHLYL("1-3","综合利用量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_1_ZHLYL2("1-4","综合利用率（%）"),
    VALUE_MANAGER_TARGET_TYPE_1_ZJHTL("1-5","直接还田量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_1_LTLYL("1-6","离田利用量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_1_CYFSHS("1-7","抽样分散户数（个）"),
    VALUE_MANAGER_TARGET_TYPE_1_SCZTGMHSL("1-8","市场主体规模化数量（个）"),

    VALUE_MANAGER_TARGET_TYPE_2_CSL("2-1","产生量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_2_CSLZB("2-2","产生量占比（%）"),
    VALUE_MANAGER_TARGET_TYPE_2_KSJL("2-3","可收集量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_2_KSJLZB("2-4","可收集量比例（%）"),
    VALUE_MANAGER_TARGET_TYPE_2_LSCL("2-5","粮食产量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_2_BZMJ("2-6","播种面积（千公顷）"),


    VALUE_MANAGER_TARGET_TYPE_3_JGLYL("3-1","秸秆利用量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_ZGLYL("3-2","综合利用率（%）"),
    VALUE_MANAGER_TARGET_TYPE_3_HJ("3-3","合计（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_FLH("3-4","肥料化（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_SLH("3-5","饲料化（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_RLH("3-6","燃料化（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_JLH("3-7","基料化（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_YLH("3-8","原料化（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_3_ZHLYZS("3-9","综合利用能力指数"),
    VALUE_MANAGER_TARGET_TYPE_3_CYHLYNLZS("3-10","产业化利用能力指数"),


    VALUE_MANAGER_TARGET_TYPE_4_ZJHTL("4-1","直接还田量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_4_ZJHTL2("4-2","直接还田率（%）"),
    VALUE_MANAGER_TARGET_TYPE_4_HJ("4-3","合计（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_4_NHFSLYL("4-4","农户分散利用量（万吨）"),
    VALUE_MANAGER_TARGET_TYPE_4_SCZTGMHLYL("4-5","市场主体规模化利用量（万吨）"),

    ;
    private String code;
    private String description;


    /**
     * 模糊匹配KEY  获取描述信息
     *
     * @param enumValue 枚举值  模糊匹配
     * @param key       code
     * @return desc
     */
    public static String getEnumDes(String enumValue, String key) {
        for (ThresholdEnum c : ThresholdEnum.values()) {
            if (c.name().contains(enumValue)) {
                if (c.getCode().equals(key)) {
                    return c.getDescription();
                }
            }
        }
        return "";
    }

    /**
     * 模糊匹配KEY  获取描述信息
     *
     * @param enumValue 枚举值  模糊匹配
     * @param des       中文描述
     * @return desc
     */
    public static String getEnumCode(String enumValue, String des) {
        for (ThresholdEnum c : ThresholdEnum.values()) {
            if (c.name().contains(enumValue)) {
                if (c.getDescription().equals(des)) {
                    return c.getCode();
                }
            }
        }
        return "";
    }



    public static Map<String,String>  getCodeList(String enumValue){
        Map<String,String> maps = Maps.newHashMap();
        for (ThresholdEnum c : ThresholdEnum.values()) {
            if (c.name().contains(enumValue)) {
                maps.put(c.getCode(), c.getDescription());
            }
        }
        if(CollectionUtils.isEmpty(maps)){
            throw new SofnException("获取的代码列表为空,需要增加该表类型的泛型");
        }
        return maps;

    }


    /**
     * 获取单位
     * @param type
     * @return
     */
    public static String getUnit(String type){
        if(StringUtils.isBlank(type)){
            return "";
        }
        for (ThresholdEnum c : ThresholdEnum.values()) {
            if(c.getCode().equals(type)){
                String name = c.getDescription();
                if(name.contains("（")){
                    try{
                        String unit = name.split("（")[1].split("）")[0];
                        return unit;
                    }catch (ExcelException e){
                        e.printStackTrace();
                        System.out.println("获取指标单位失败：指标的描述信息：" + name);
                    }
                }

            }
        }
        return "";
    }


}
