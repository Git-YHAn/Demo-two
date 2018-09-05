package com.bee.devops.admin.common.constant;
/**
 * 公共常量类
 * @author yangliang
 */
public class Constants {
		
	/**
	 * 请求应答状态码
	 */
	public enum RESP_CODE {
		/** 资源不存在 */
		NOT_FOUND("404", "资源不存在"),
		/** 失败 */
		FAILED("0", "失败"),
		/** 成功 */
		SUCCESS("1", "成功"),
		/** 未登录 */
		TO_LOGIN("401", "未登录"),
		/** 无权限 */
		NOT_PERMISSION("-2", "无权限");
		private String code;
		private String info;
		RESP_CODE(String code, String info) {
			this.code = code;
			this.info = info;
		}
		public String getCode() {
			return code;
		}
		public String getInfo() {
			return info;
		}
	}
	
}
