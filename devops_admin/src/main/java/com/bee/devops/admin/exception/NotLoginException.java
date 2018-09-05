package com.bee.devops.admin.exception;

public class NotLoginException extends BusinessException {

	private static final long serialVersionUID = -6341069988972991741L;

	public NotLoginException() {
		super();
	}

	public NotLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotLoginException(String message) {
		super(message);
	}

	public NotLoginException(Throwable cause) {
		super(cause);
	}
	
}
