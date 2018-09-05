package com.bee.devops.admin.common.enums;

public enum OperateType {
	/**发布**/
	PUBLISH(1,"发布"),
	/**回滚**/
	ROLLBACK(2,"回滚"), 
	/**重启**/
	RESTART(3,"重启"),
	/**轻发布**/
	LIGHT_RELEASE(4,"轻发布"),
	/**停止**/
	STOP(5,"停止"),
	/**审核**/
	ADUIT(6,"审核"),
	/**创建镜像**/
	CREATE_IMAGE(7,"制作镜像");
	
	private Integer code;
	private String msg;
	OperateType(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode(){
        return this.code;
    }
	
	public String getMsg(){
		return this.msg;
	}
}
