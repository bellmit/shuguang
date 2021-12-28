package com.sofn.ducss.enums;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum CropsEnum
{
	//农作物               strawType中文名    strawType英文名          亩产量最小值               亩产量最大值               最大草谷比系数
	EARLY_RICE		    ("早稻","early_rice",  new BigDecimal(0), 	   new BigDecimal(1500),  new BigDecimal(2.5)),
	MIDDLE_RICE		    ("中稻和一季晚稻","middle_rice", new BigDecimal(0), 	   new BigDecimal(1800),  new BigDecimal(2.5)),
	DOUBLE_LATE_RICE	("双季晚稻","double_late_rice",new BigDecimal(0), new BigDecimal(1800),  new BigDecimal(2.5)),
	WHEAT			    ("小麦","wheat",       new BigDecimal(0),   new BigDecimal(1200),  new BigDecimal(2.5)),
	CORN			    ("玉米","corn",        new BigDecimal(0),   new BigDecimal(2300),  new BigDecimal(3)),
	POTATO	            ("马铃薯","potato",      new BigDecimal(0),   new BigDecimal(11000), new BigDecimal(2.5)),
	SWEET_POTATO		("甘薯","sweet_potato",new BigDecimal(0),  new BigDecimal(7500),  new BigDecimal(2.5)),
	CASSAVA	            ("木薯","cassava",     new BigDecimal(0),  new BigDecimal(14000), new BigDecimal(2.5)),
	PEANUT		        ("花生","peanut",      new BigDecimal(0),   new BigDecimal(1200),  new BigDecimal(3)),
	RAPE			    ("油菜","rape",        new BigDecimal(0),    new BigDecimal(500),   new BigDecimal(3)),
	SOYBEAN	            ("大豆","soybean",		new BigDecimal(0),    new BigDecimal(700),   new BigDecimal(3)),
	COTTON		        ("棉花","cotton",		new BigDecimal(0),   new BigDecimal(600),   new BigDecimal(6)),
	SUGAR_CANE	        ("甘蔗","sugar_cane",	new BigDecimal(0),  new BigDecimal(21000), new BigDecimal(0.5)),
	OTHER	            ("其他","other",	new BigDecimal(0),  new BigDecimal(21000), new BigDecimal(6)),
	;

	private String chineseName;
	private String name;
	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal grassValleyRatio;
	private static Map<String,CropsEnum> dataMap;

	CropsEnum(String chineseName,String name, BigDecimal min, BigDecimal max, BigDecimal grassValleyRatio)
	{
		this.chineseName = chineseName;
		this.name = name;
		this.min = min;
		this.max = max;
		this.grassValleyRatio = grassValleyRatio;
	}

	/**
	 * 验证取值，并返回验证结果
	 * @param resultMap
	 * @param
	 * @param name
	 * @param strawStr
	 * @return
	 */
	public static Map<String, Object> checkValue(Map<String, Object> resultMap,BigDecimal grainYield,BigDecimal grassValleyRatio,String strawStr,String name) {
		if (resultMap == null) {
			resultMap = new HashMap();
		}

		if (grainYield != null && null != grassValleyRatio && name != null) {
			Map<String, CropsEnum> maps = getDataMap();
			if (maps.containsKey(name) || null!=getByChineseName(strawStr)) {
				CropsEnum ce = maps.get(name);
				if(null==ce){
					ce = getByChineseName(strawStr);
				}
//				if ((grainYield.compareTo(ce.getMin()) == -1 || grainYield.compareTo(ce.getMax()) == 1)) {
//					resultMap.put("strawStr", strawStr);
//					String propertyName = "粮食产量";
//					String unit = "吨";
//					resultMap.put("threshold", propertyName+"范围应该【"+ce.getMin()+"~"+ce.getMax()+"】"+unit);
//				}else
					if((grassValleyRatio.compareTo(ce.getMin()) == -1 || grassValleyRatio.compareTo(ce.getGrassValleyRatio()) == 1)) {
					resultMap.put("strawStr", strawStr);
					String propertyName = "草谷比";
					resultMap.put("threshold", propertyName+"范围应该【"+ce.getMin()+"~"+ce.getGrassValleyRatio()+"】");
				}
			}
		}

		return resultMap;
	}

	/**
	 * 验证取值，并返回验证结果
	 * @param resultMap
	 * @param yieldPerMu
	 * @param name
	 * @param strawStr
	 * @return
	 */
	public static Map<String, Object> checkValue(Map<String, Object> resultMap,BigDecimal yieldPerMu,String name,String strawStr) {
		if (resultMap == null) {
			resultMap = new HashMap();
		}
		if (yieldPerMu != null && name != null) {
			Map<String, CropsEnum> maps = getDataMap();
			if (maps.containsKey(name)) {
				CropsEnum ce = maps.get(name);
				if (yieldPerMu.compareTo(ce.getMin()) == -1 || yieldPerMu.compareTo(ce.getMax()) == 1) {
					resultMap.put("strId", strawStr);
					resultMap.put("threshold", "【"+ce.getMin()+"~"+ce.getMax()+"】");
				}
			}
		}

		return resultMap;
	}

	private static Map<String, CropsEnum> getDataMap() {
		if (dataMap == null) {
			initialDataMap();
		}
		return dataMap;
	}

	private static synchronized Map<String, CropsEnum> initialDataMap() {
		if (dataMap == null) {
			synchronized (CropsEnum.class) {
				if (dataMap == null) {
					dataMap = new HashMap();
					for (CropsEnum ce : CropsEnum.values()) {
						dataMap.put(ce.getName(), ce);
					}
				}
			}
		}
		return dataMap;
	}

	//根据中文名获取枚举类
	public static CropsEnum getByChineseName(String ChineseName){
		if(StringUtils.isEmpty(ChineseName)) return null;

		for (CropsEnum ce : CropsEnum.values()) {
			if(ChineseName.equals(ce.chineseName)){
				return ce;
			}
		}

		return null;
	}

	//根据英文名获取枚举类
	public static CropsEnum getByStrawType(String strawType){
		if(StringUtils.isEmpty(strawType)) return null;

		for (CropsEnum ce : CropsEnum.values()) {
			if(strawType.equals(ce.name)){
				return ce;
			}
		}

		return null;
	}

	//根据英文名获取中文名
	public static String getByStrawTypeEnglish(String strawType){
		if(StringUtils.isEmpty(strawType)) return null;

		for (CropsEnum ce : CropsEnum.values()) {
			if(strawType.equals(ce.name)){
				return ce.chineseName;
			}
		}

		return null;
	}
}
