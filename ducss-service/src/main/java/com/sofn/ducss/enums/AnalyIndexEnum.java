package com.sofn.ducss.enums;


 /**
 * @desc 数据分析指标枚举类
 * @author xl
  * @date 2021-05-21 16:40
 **/
public enum AnalyIndexEnum {
    CODE_1("1","粮食产量(吨)","grainYield", "grain_yield"),
    CODE_2("2","草谷比","grassValleyRatio","grass_valley_ratio"),
    CODE_3("3","可收集系数","collectionRatio","collection_ratio"),
    CODE_4("4","播种面积(亩)","seedArea","seed_area"),
    CODE_5("5","还田面积(亩)","returnArea","return_area"),
    CODE_6("6","调出量(吨)","exportYield","export_yield"),
    CODE_7("7","产生量(吨)","theoryResource", "theory_resource"),
    CODE_8("8","可收集量(吨)","collectResource", "collect_resource"),
    CODE_9("9","市场主体利用量(吨)","marketEnt","market_ent"),
    CODE_10("10","市场主体肥料化(吨)","fertilising","fertilizes"),
    CODE_11("11","市场主体饲料化(吨)","forage","feeds"),
    CODE_12("12","市场主体燃料化(吨)","fuel","fuelleds"),
    CODE_13("13","市场主体基料化(吨)","base","baseMats"),
    CODE_14("14","市场主体原料化(吨)","material","materializations"),
    CODE_15("15","分散利用量(吨)","reuse","reuse"),
    CODE_16("16","分散肥料化(吨)","fertilisings","fertilisingd"),
    CODE_17("17","分散饲料化(吨)","forages","foraged"),
    CODE_18("18","分散燃料化(吨)","fuels","fueld"),
    CODE_19("19","分散基料化(吨)","bases","based"),
    CODE_20("20","分散原料化(吨)","materials","materiald"),
    CODE_21("21","直接还田量(吨)","returnResource","return_resource"),
    CODE_22("22","市场主体调入量(吨)","other","other"),
    CODE_23("23","肥料化利用量(吨)","fertilize","fertilize"),
    CODE_24("24","饲料化利用量(吨)","feed","feed"),
    CODE_25("25","燃料化利用量(吨)","fuelled","fuelled"),
    CODE_26("26","基料化利用量(吨)","baseMat","base_mat"),
    CODE_27("27","原料化利用量(吨)","materialization","materialization"),
    CODE_28("28","秸秆利用量(吨)","totol","straw_utilization"),
    CODE_29("29","综合利用率(%)","totolRate","totol_rate"),
    CODE_30("30","综合利用能力指数","comprUtilIndex","compr_util_index"),
    CODE_31("31","产业化利用能力指数","induUtilIndex","indu_util_index"),

    ;

    private String code;
    private String value;
    private String dataValue;
    private String sqlVaule;

    AnalyIndexEnum(String code, String value,String dataValue,String sqlVaule) {
        this.code = code;
        this.value = value;
        this.dataValue = dataValue;
        this.sqlVaule = sqlVaule;
    }

    public static String getValue(String val) {
        for (AnalyIndexEnum ele : values()) {
            if (ele.getCode().equals(val)) {
                return ele.getValue();
            }
        }
        return null;
    }
     public static String getDataValue(String val) {
         for (AnalyIndexEnum ele : values()) {
             if (ele.getCode().equals(val)) {
                 return ele.getDataValue();
             }
         }
         return null;
     }

     public static String getSqlValue(String val) {
         for (AnalyIndexEnum ele : values()) {
             if (ele.getDataValue().equals(val)) {
                 return ele.getSqlVaule();
             }
         }
         return null;
     }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

     public String getSqlVaule() {
         return sqlVaule;
     }

     public void setSqlVaule(String sqlVaule) {
         this.sqlVaule = sqlVaule;
     }

     public void setValue(String value) {
        this.value = value;
    }


     public String getDataValue() {
         return dataValue;
     }

     public void setDataValue(String dataValue) {
         this.dataValue = dataValue;
     }

     public static void main(String[] args) {
        System.out.println(getValue("1"));
    }

}

