package com.sofn.core.constant;

import com.sofn.core.config.Global;

public class ApiConstants {
	public static final String CODE = "code";
	public static final String MSG = "msg";
	public static final String STATUS = "status";
	public final static String TYPE_PC = "pc";
	public final static String TYPE_APP = "app";
	// 默认pc端token后缀
	public static final String PCTOKEN = "pc--token";
	public static final String APPTOKEN = "pc--token";
	// app 端默认失效时间为永久
	public static final int APP_TOKEN_TIMEOUT = Integer.parseInt(Global.getConfig("app.token.timeout"));
	// PC 端失效时间为30分钟。
	public static final int PC_TOKEN_TIMEOUT = Integer.parseInt(Global.getConfig("pc.token.timeout"));
	// 默认分页大小
	public static final int DEFUALUT_PAGESIZE = Integer.parseInt(Global.getConfig("default.pagesize"));
	// 验证码长度
	public static final int VALIDATE_CODE_LEN = Integer.parseInt(Global.getConfig("validate.code.len"));
	// 验证码保存时间
	public static final int VALIDATE_CODE_SAVE_TIME= Integer.parseInt(Global.getConfig("validate.code.save.time"));
	// 注册失败信息
	public static final String REGISTER_FAIL = "账号重复";
	// 注销用户失败信息
	public static final String DEL_USER_FAIL = "账号错误";
	// 默认账户
	public static final String DEFAULT_USER = "admin";
	public static final String SOFN_API_CLIENTID = Global.getConfig("sofn.api.clientId");
	public static final String SOFN_API_NAME = Global.getConfig("sofn.api.name");
	public static final long SOFN_API_EXPIRESSECOND = Long.parseLong(Global.getConfig("sofn.api.expiresSecond")) * 1000;
	public static final String SOFN_API_BASE64SECRET = Global.getConfig("sofn.api.base64Secret");
}
