/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.sofn.common.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel注解定义
 * @author ThinkGem
 * @version 2013-03-10
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 * 导出字段标题
	 */
	String title();

	/**
	 * 字段排序
	 * @return
	 */
	int sort() default 0;
	
	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	Type type() default Type.ALL;
	public enum Type {
		ALL(0),
		EXPORT(1),
		IMPORT(2);
		private final int value;
		Type(int value) { this.value = value; }
		public int value() { return this.value; }
	}

	/**
	 * 导出字段对齐方式（0：自动；1：靠左；2：居中；3：靠右）
	 */
	Align align() default Align.CENTER;
	public enum Align {
		AUTO(0),
		LEFT(1),
		CENTER(2),
		RIGHT(3);
		private final int value;
		Align(int value) { this.value = value; }
		public int value() { return this.value; }
	}

	/**
	 * 指定导出列宽（以字符宽度的1/256为单位，假如你想显示5个字符的话，就可以设置5*256，1个汉字占2个字符）
	 */
	int width() default -1;

	
	/**
	 * 导入时指定列索引（从0开始）在指定读取excel中的指定的列时使用
	 */
	int column() default -1;

	/**
	 * 如果是字典类型，请设置字典的type值
	 */
	String dictType() default "";

	/**
	 * 自定义查询字典的Sql， 需要保证当前Sql能在支撑平台数据库执行，并且只能有一个返回字段
	 * 如果有查询条件请使用字段=?格式
	 * 只是用于填报的时候生成下拉列表，在导入的时候不会进行值得翻译，请自己翻译
	 * @return String
	 */
	String dictSql() default  "";

	/**
	 * 自定义Sql的参数值，参数顺序需要和上面的Sql中的?数量一样 并且顺序相同
	 */
	String[] dictSqlParam() default  {};

	/**
	 * 自定义下拉列表字典值
	 * @return   CustomDictValue[]
	 */
	String[] customDictValue() default {};

	/**
	 * 数值格式（例如：0.00，yyyy-MM-dd）
	 * 导出模板时设置单元格格式，值请参考： Excel中选中某个单元格右键->设置单元格格式->自定义
	 * 日期时如果只传入yyyy会出现错误，请注意
	 * 默认为字符串
	 * 如果是数值的话尽量传入格式：整数0，小数0.00
	 */
	String dataFormat() default "@";

	/**
	 * 通过这个注解来控制是否显示在模板中（动态导出模板中会使用）
	 * add by wuXY 2019-11-26 14:54:33
	 * 先调用ExcelReflectionUtil.doChangeExcelFieldIsShowVaule(CheckManageExcel.class, isShowHeader, true) 去修改注解isShow的值来控制是否导出显示表头
	 */
	boolean isShow() default true;
	/**
	 * 导出标题合并的标题
	 * 目前仅支持两行标题中有部分合并
	 * 2021-6-22 19:45:35
	 * add by L
	 */
	String merge() default  "";

}
