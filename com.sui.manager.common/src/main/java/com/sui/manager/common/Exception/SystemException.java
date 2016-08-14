package com.sui.manager.common.Exception;

@SuppressWarnings("serial")
public class SystemException extends RuntimeException {
	private String code;

	public SystemException() {
		super();
	}

	public SystemException(String code) {
		super(code);
		this.code = code;
	}

	public SystemException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public SystemException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
