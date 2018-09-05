package com.bee.devops.admin.common.enums;

public enum StatusType {
	/**发布中**/
	PUBLISHING(0,"发布中"),
	/**发布成功**/
	PUBLISHSUCCESS(1,"发布成功"), 
	/**发布失败**/
	PUBLISHERROR(2,"发布失败"),
	/**发布已取消**/
	PUBLISHCANCELLED(3,"发布已取消"),
	/**发布中**/
	ROLLBACK(4,"回滚中"),
	/**发布成功**/
	ROLLBACKSUCCESS(5,"回滚成功"), 
	/**发布失败**/
	ROLLBACKERROR(6,"回滚失败"),
	/**回滚已取消**/
	ROLLBACKCANCELLED(7,"回滚已取消"),
	/**轻发布中**/
	LIGTHPUBLISH(8,"轻发布中"),
	/**轻发布成功**/
	LIGTHPUBLISHSUCCESS(9,"轻发布成功"), 
	/**轻发布失败**/
	LIGTHPUBLISHERROR(10,"轻发布失败"),
	/**轻发布已取消**/
	LIGTHPUBLISHCANCELLED(11,"轻发布已取消"),
	/**重启中**/
	RESTARTING(12,"重启中"),
	/**重启成功**/
	RESTARTSUCCESS(13,"重启成功"), 
	/**重启失败**/
	RESTARTERROR(14,"重启失败"),
	/**重启已取消**/
	RESTARTCANCELLED(15,"重启已取消");
	
	private Integer code;
	private String msg;
	StatusType(Integer code,String msg){
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
