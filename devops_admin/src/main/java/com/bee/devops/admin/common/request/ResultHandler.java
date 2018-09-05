package com.bee.devops.admin.common.request;

import com.bee.devops.admin.common.constant.Constants.RESP_CODE;

/**
 * 接口应答结果统一包装类
 * @author yangliang
 */
public class ResultHandler<T> {
	/** 当CODE为-1是表示未登录 */
	private String code;
	private String msg;
	private T data;

	public ResultHandler() {
	}

	public ResultHandler(T data) {
		this(RESP_CODE.SUCCESS.getCode(), data);
	}

	public ResultHandler(String code, T data) {
		this(code, "", data);
	}

	public ResultHandler(String code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static <T> ResultHandler<T> success(T data) {
		return new ResultHandler<>(data);
	}

	/**
	 * 重载成功方法,传入成功msg信息
	 */
	public static <T> ResultHandler<T> success(String msg, T data) {
		return new ResultHandler<>(RESP_CODE.SUCCESS.getCode(), msg, data);
	}

	public static <T> ResultHandler<T> error(T data) {
		return error("", data);
	}
	
	public static <T> ResultHandler<T> error(String msg) {
		return error(msg, null);
	}
	
	public static <T> ResultHandler<T> error(String msg, T data) {
		return error(RESP_CODE.FAILED, msg, data);
	}

	public static <T> ResultHandler<T> error(RESP_CODE code, String msg, T data) {
		return new ResultHandler<>(code.getCode(), msg, data);
	}
	public boolean isSuccess() {
		return RESP_CODE.SUCCESS.getCode().equals(code);
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}
}
