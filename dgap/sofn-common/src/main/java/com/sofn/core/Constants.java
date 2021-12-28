package com.sofn.core;

/**
 * 常量表
 * 
 * @author sofn
 * @version $Id: Constants.java, v 0.1 2014-2-28 上午11:18:28 sofn Exp $
 */
public interface Constants {
	/**
	 * 异常信息统一头信息<br>
	 * 非常遗憾的通知您,程序发生了异常
	 */
	public static final String Exception_Head = "SORRY! SOME ERRORS OCCURED! AS FOLLOWS :";
	/** 客户端语言 */
	public static final String USERLANGUAGE = "userLanguage";
	/** 客户端主题 */
	public static final String WEBTHEME = "webTheme";
	/** 当前用户 */
	public static final String CURRENT_USER = "CURRENT_USER";
	/** 在线用户数量 */
	public static final String ALLUSER_NUMBER = "ALLUSER_NUMBER";
	/** 登录用户数量 */
	public static final String USER_NUMBER = "USER_NUMBER";
	/** 上次请求地址 */
	public static final String PREREQUEST = "PREREQUEST";
	/** 上次请求时间 */
	public static final String PREREQUEST_TIME = "PREREQUEST_TIME";
	/** 非法请求次数 */
	public static final String MALICIOUS_REQUEST_TIMES = "MALICIOUS_REQUEST_TIMES";
	/** 缓存命名空间 */
	public static final String CACHE_NAMESPACE = "sofn:";

	/** 存储当前登录用户id的字段名 */
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

	/** token有效期（秒） */
	public static final int TOKEN_EXPIRES_HOUR = 72*24*3600;

	/** 存放Authorization的header字段 */
	public static final String AUTHORIZATION = "authorization";

	/** 客户端请求的token名 */
	public static final String TOKEN_HTTPHEADERNAME = "token";


	public static final String PARAM_DIGEST = "digest";
	public static final String PARAM_USERNAME = "username";

	public static final String SUPERADMIN_ACCOUNT = "superadmin";

	//工作调度名称
	public static final String ST_WORK_SCHEDULE = "ST_Work_Schedule";
	//是否完成
	public static final String FINISHED = "FINISHED";
	//工作调度 过期时间(单位秒)
	public static final int ST_WORK_SCHEDULE_EXPIRE = 20*60;
	// 同一账号下允许的token个数上限值
	public static final long TOKEN_NUMBER_UPPERLIMIT = 5;
}
