/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.sofn.common.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义数组
 * @author ThinkGem
 * @version 2016-11-9
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelFields {

	/**
	 * 对象类型  这里只能传入单个对象不能传入集合，
	 * 这里只考虑简单的Excel,即一行只代表一条数据
	 * @return
	 */
	Class clazz();
	
}
