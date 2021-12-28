package com.sofn.ducss.util;

/**
 * @Author Zhang Yi
 * @Date 2020/11/4 10:00
 * @Version 1.0
 * bean集合拷贝
 */



import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * bean工具类
 */
@Slf4j
public class BeanUtil {

	/**
	 * 复制 2个bean的属性值
	 * @param source
	 * @param target
	 * @param propertiesNames
	 */
	public static void copyProperties(Object source, Object target, List<String> propertiesNames){

		// 如果属性列表不存在，将把 source的所有属性设置为属性列表
		if (propertiesNames==null || propertiesNames.size() == 0){
			Arrays.stream(source.getClass().getDeclaredFields()).forEach(it->{
				try {
					target.getClass().getDeclaredField(it.getName()).set(target,it.get(source));
				}catch (Exception e){
					log.warn(e.getMessage());
				}
			});
		}else {

			propertiesNames.forEach(it->{
				try {
					Field fd = source.getClass().getDeclaredField(it);
					target.getClass().getDeclaredField(it).set(target,fd.get(source));
				}catch (Exception e){
					log.warn(e.getMessage());
				}
			});
		}
	}

	/**
	 * 判断 当前属性是否存在 此 对象中
	 * @param obj
	 * @param propName
	 * @return
	 */
	public static Boolean checkProperties(Object obj,String propName){
		try {
			obj.getClass().getDeclaredField(propName);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	/**
	 * 设置 对象中 属性的值
	 * @param obj
	 * @param propName
	 * @param val
	 */
	public  static void setProperties(Object obj,String propName,Object val){
		try {
			Field filed=obj.getClass().getDeclaredField(propName);
			filed.setAccessible(true);
			if(filed.getGenericType() instanceof List||filed.getGenericType() instanceof ParameterizedType){
				filed.set(obj, JSON.parseObject(val.toString(),filed.getType()));
			}else {
				filed.set(obj,val);
			}

		}catch (Exception e){
			log.warn(e.getMessage());
		}
	}

	/**
	 * 转换 对象名称，去掉第一个"T" 字符
	 * 例如 convertBeanName(TUserBasic.class,":") TUserBasic -> user:basic
	 * @param obj
	 * @param separator
	 * @return
	 */
	public static String convertBeanName(Object obj,String separator){
		String className = obj.getClass().getSimpleName();
		// 获取第一个字符，判断是否是"T"
		String firstChar = className.substring(0,1);
		if (firstChar.equals("T")){
			className = className.substring(1,className.length());
		}
		StringBuilder resString = new StringBuilder();

		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(className);
		while (matcher.find()) {
			String word = matcher.group();
			resString.append(word.toUpperCase());
			resString.append(matcher.end() == className.length() ? "" : separator);
		}

		return resString.toString();
	}

	/**
	 * 对象转换
	 */
	public static <T> T ObjectToObject(Object source,Class<T> target){
		try {
			return JSON.parseObject(JSON.toJSONString(source),target);

		}catch (Exception e){
			return null;
		}

	}

	/*
	 *列表对象转换
	 */

	public static <T> List<T> convertListToList(List bList,TypeReference  typeRef) {
		if (bList == null) {
			return null;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		List<T> list = null;
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			list = (List<T>) objectMapper.readValue(objectMapper.writeValueAsString(bList), typeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public static Field getDeclaredField(Object object, String fieldName){
		Field field = null ;

		Class<?> clazz = object.getClass() ;

		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				field = clazz.getDeclaredField(fieldName) ;
				return field ;
			} catch (Exception e) {
				//这里甚么都不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会进入
			}
		}

		return null;
	}

	/**
	 * 直接读的属性值, 忽略 private/protected 修饰符, 也     * @param object : 子类对象
	 * @param fieldName : 父类中     * @return : 父类中     */

	public static Object getFieldValue(Object object, String fieldName){

		//根据 对象和属性名通过取 Field对象
		Field field = getDeclaredField(object, fieldName) ;

		//抑制Java对其的检查
		field.setAccessible(true) ;

		try {
			//获的属性值
			return field.get(object) ;

		} catch(Exception e) {
			e.printStackTrace() ;
		}

		return null;
	}
}
