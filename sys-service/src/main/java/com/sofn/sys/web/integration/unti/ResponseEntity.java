package com.sofn.sys.web.integration.unti;

/**
 * 方法执行成功后的响应对象
 * 
 */
public class ResponseEntity {

	private String message;

	private String code;
	
	private String guid ;

	private boolean success;

	public ResponseEntity() {

	}

	public ResponseEntity(String message, String code,String guid, boolean success) {
		this.message = message;
		this.code = code;
		this.guid = guid ;
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ServiceResponse [message=" + message + ", code=" + code
				+ ", success=" + success + "]";
	}
}
