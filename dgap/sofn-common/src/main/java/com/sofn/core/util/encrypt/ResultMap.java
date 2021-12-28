package com.sofn.core.util.encrypt;

/**
 * 私钥加密后数据对象
 * @author Administrator
 *
 */
public class ResultMap {
	//des的加密key
	private String keyDes;
	//密文
	private String text;
	//错误
	private String error;
	//签名
	private String sign;
	public String getKeyDes() {
		return keyDes;
	}
	public void setKeyDes(String keyDes) {
		this.keyDes = keyDes;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
}
