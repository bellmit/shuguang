package com.sofn.core.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 系统类别枚举
 * <p>
 * 用于区分项目中的不同子系统
 * </p>
 */
public enum SysCategoryEnum {
    SYS("系统管理", "sys"),
    ADS("监测系统", "ads"),
    ALES("执法系统", "ales"),
    ASMS("监管系统", "asms"),
    DGAP("数据共享", "dgap"),
    QRY("查询系统", "qry"),
    TTS("追溯系统", "tts"),
    PORTAL("门户网站", "portal"),
    XXCJ("信息采集", "xxcj");

    private String name;
    private String value;
    private static Map<String, String> map = new LinkedHashMap<>();
    private static Map<String, String> appSysCategoryMap = Collections.synchronizedMap(new LinkedHashMap<>());

    static {
        SysCategoryEnum[] categories = SysCategoryEnum.values();
        for (SysCategoryEnum category : categories) {
            map.put(category.getValue(), category.getName());
        }
        map = Collections.unmodifiableMap(map); // 返回只读的map
    }

    /**
     * 枚举构造函数
     *
     * @param name  枚举名称
     * @param value 枚举值
     */
    SysCategoryEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * 获取枚举的名称
     *
     * @return 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 获取枚举的值
     *
     * @return 枚举值
     */
    public String getValue() {
        return value;
    }

    /**
     * 获取包含对应枚举名称和枚举值的map
     *
     * @return map
     */
    public static Map<String, String> toMap() {
        return map;
    }

    /**
     * 获取包含对应枚举名称和枚举值的map
     * @param c 定义从枚举转换到map的函数式接口
     * @return
     */
    public static Map<String, String> toMap(Converter c) {
        final Map<String, String> orderlyMap = new LinkedHashMap<>();
        SysCategoryEnum[] categories = SysCategoryEnum.values();
        for (SysCategoryEnum category : categories) {
            c.process(orderlyMap, category);
        }

        return Collections.unmodifiableMap(orderlyMap); // 返回只读的map
    }

    /**
     * 获取包含对应枚举名称和枚举值的map（仅包含app端支持的系统）
     * @return
     */
    public static Map<String, String> toMapForApp() {
        if (appSysCategoryMap.isEmpty()) {
            SysCategoryEnum[] categoryEnums = {SysCategoryEnum.TTS, SysCategoryEnum.ADS, SysCategoryEnum.ASMS, SysCategoryEnum.ALES};
            Arrays.stream(categoryEnums).forEach(categoryEnum -> appSysCategoryMap.put(categoryEnum.getValue(), categoryEnum.getName()));
        }

        return appSysCategoryMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysCategories{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * 从枚举转换到map的转换接口
     * <p>
     * 可以在实现类的方法中指定枚举名称或者枚举值作为key存入map,
     * 本接口是函数式接口，使用时可以采用lambdas表达式写法
     * </p>
     */
    @FunctionalInterface
    public interface Converter {
        /**
         * 将枚举值和枚举名称放入map中
         *
         * @param map Map
         * @param e   系统类别枚举
         */
        void process(Map<String, String> map, SysCategoryEnum e);
    }
}
