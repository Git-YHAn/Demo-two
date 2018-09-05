package com.bee.devops.admin.exception;

/**
 * 没有权限异常
 *
 * @author wanghuajie
 * @date 2018/9/4 10:03
 */
public class NoPermissionException extends BusinessException {
    public NoPermissionException() {
        super();
    }

    public NoPermissionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NoPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPermissionException(String message) {
        super(message);
    }

    public NoPermissionException(Throwable cause) {
        super(cause);
    }
}
