package com.bee.devops.admin.common.utils;

import java.io.IOException;

import com.bee.devops.admin.core.common.entity.common.DingTalkMessage;
import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.MarkdownMessage;


public class DingTalkUtil {
	private static DingtalkChatbotClient client = new DingtalkChatbotClient();

	private static final String TITLE = "DevOps平台发来一条信息";
	private String webHook;

	public DingTalkUtil(String webHook) {
		this.webHook = webHook;
	}

	/**
	 * 简单的文本消息
	 * @author Yang Chunhai	 
	 * @param message 文本消息
	 * @return
	 * @throws IOException
	 */
	public String dingTalkSend(String message) {
		MarkdownMessage markdownMessage = new MarkdownMessage();
		markdownMessage.setTitle(TITLE);
		markdownMessage.add(message);
		int errorCode = send(markdownMessage);
		String info = errorInfo(errorCode);
		return info;
	}

	/**
	 * 带有链接提示的文本消息
	 * @author Yang Chunhai	 
	 * @param message 文本消息
	 * @param url 链接地址
	 * @param motif 链接主题
	 * @return
	 * @throws IOException
	 */
	public String dingTalkSend(String text, String url, String motif, String message) {
		MarkdownMessage markdownMessage = new MarkdownMessage();
		markdownMessage.setTitle(TITLE);
		markdownMessage.add(text);
		markdownMessage.add(MarkdownMessage.getReferenceText(message));
		markdownMessage.add(MarkdownMessage.getLinkText(motif, url));
		int errorCode = send(markdownMessage);
		String info = errorInfo(errorCode);
		return info;
	}
	
	/**
	 * 无链接提示的文本消息
	 * @author Yang Chunhai	 
	 * @param message 文本消息
	 * @param url 链接地址
	 * @param motif 链接主题
	 * @return
	 * @throws IOException
	 */
	public String dingTalkSend(String text, String message) {
		MarkdownMessage markdownMessage = new MarkdownMessage();
		markdownMessage.setTitle(TITLE);
		markdownMessage.add(text);
		markdownMessage.add(MarkdownMessage.getReferenceText(message));
		int errorCode = send(markdownMessage);
		String info = errorInfo(errorCode);
		return info;
	}
	
	/**
	 * 修改配置消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String editConfigDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
    	message.add(MarkdownMessage.getHeaderText(2, dingTalkMessage.getOperation()));
    	message.add(MarkdownMessage.getReferenceText("环境: "+dingTalkMessage.getEnvName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("分支: "+dingTalkMessage.getBranchName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("修改人: "+dingTalkMessage.getUserName()));
     	message.add("\n\n");
     	message.add("请管理员尽快审核！");
    	message.add(MarkdownMessage.getLinkText("点击传送", dingTalkMessage.getUrl()));
    	
    	int errorCode = send(message);
		String info = errorInfo(errorCode);
		return info;
    }
    
    /**
	 * 审核配置消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String auditConfigDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
    	message.add(MarkdownMessage.getHeaderText(2, dingTalkMessage.getOperation()));
    	message.add(MarkdownMessage.getReferenceText("环境: "+dingTalkMessage.getEnvName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("分支: "+dingTalkMessage.getBranchName()));
    	message.add("\n\n");
    	if(dingTalkMessage.getOperation().equals(DingTalkMessage.CONFIG_SUSSESS)){
        	message.add(MarkdownMessage.getReferenceText("生成版本号: "+MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()) ));
        	message.add("\n\n");
    	}
    	message.add(MarkdownMessage.getReferenceText("审核人: "+dingTalkMessage.getUserName()));
    	if(dingTalkMessage.getOperation().equals(DingTalkMessage.CONFIG_FAIL)) {
    		message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    	}
    	int errorCode = send(message);
		String info = errorInfo(errorCode);
		return info;
    }
    
    /**
	 * 应用版本消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String appVersionDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
    	if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getHeaderText(2,"应用版本制作成功！"));
        	message.add("\n\n");
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getHeaderText(2,"应用版本制作失败！"));
       		message.add("\n\n");
    	}
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
    	message.add("\n\n");
       	message.add(MarkdownMessage.getReferenceText("操作人: "+dingTalkMessage.getUserName()));
       	message.add("\n\n");
    	if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
    		message.add(MarkdownMessage.getReferenceText("生成版本号: "+MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl())));
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())) {
    		message.add(MarkdownMessage.getReferenceText("失败原因: "+dingTalkMessage.getFailMessage()));
    		message.add("\n\n");
    		message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    	}
    	
    	int errorCode = send(message);
		String info = errorInfo(errorCode);
		return info;
    }
    
    /**
	 * 配置版本消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String configVersionDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
   		if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
   			message.add(MarkdownMessage.getHeaderText(2, "配置版本制作成功！"));
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getHeaderText(2, "配置版本制作失败！"));
    	}
    	message.add(MarkdownMessage.getReferenceText("环境: "+dingTalkMessage.getEnvName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("操作人: "+dingTalkMessage.getUserName()));
    	message.add("\n\n");
   		if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
   			message.add(MarkdownMessage.getReferenceText("生成版本号: "+MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl())));
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getReferenceText("失败原因: "+dingTalkMessage.getFailMessage()));
    		message.add("\n\n");
    		message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    	}
    	int errorCode = send(message);
		String info = errorInfo(errorCode);
		return info;
    }
    
    /**
	 * 发布版本消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String depVersionDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
    	if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getHeaderText(2, "发布版本制作成功！"));
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getHeaderText(2, "发布版本制作失败！"));
    	}
    	message.add(MarkdownMessage.getReferenceText("环境: "+dingTalkMessage.getEnvName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
   		message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("操作人: "+dingTalkMessage.getUserName()));
    	message.add("\n\n");
    	if(DingTalkMessage.VER_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getReferenceText("生成版本号: "+MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl())));
    	} else if(DingTalkMessage.VER_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getReferenceText("失败原因: "+dingTalkMessage.getFailMessage()));
    		message.add("\n\n");
    	  	message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    	}
    	int errorCode = send(message);
		String info = errorInfo(errorCode);
		return info;
    }
    
    /**
	 * 发布应用消息
	 * @author Yang Chunhai	 
	 * @param dingTalkMessage
	 * @return
	 * @throws Exception
	 */
    public String depAppDingTalkSend(DingTalkMessage dingTalkMessage) {
    	MarkdownMessage message = new MarkdownMessage();
    	message.setTitle(TITLE);
    	if(DingTalkMessage.DEP_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getHeaderText(2,"应用发布完毕"));
    	} else if (DingTalkMessage.DEP_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getHeaderText(2,"应用部署失败"));
    	} else if(DingTalkMessage.RE_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getHeaderText(2,"应用发布完毕"));
    	} else if (DingTalkMessage.RE_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getHeaderText(2,"应用重启失败"));
    	} else {
    		message.add(MarkdownMessage.getHeaderText(2,dingTalkMessage.getOperation()));
		}
    	message.add(MarkdownMessage.getReferenceText("环境: "+dingTalkMessage.getEnvName()));
    	message.add("\n\n");
    	message.add(MarkdownMessage.getReferenceText("应用: "+dingTalkMessage.getAppName()));
   		message.add("\n\n");
   		message.add(MarkdownMessage.getReferenceText("服务器: "+dingTalkMessage.getServerName()));
   		message.add("\n\n");
   		message.add(MarkdownMessage.getReferenceText("发布版本: "+dingTalkMessage.getVersionCode()));
   		message.add("\n\n");
    	if(DingTalkMessage.DEP_SUCCESS.equals(dingTalkMessage.getOperation())) {
        	message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    	} else if (DingTalkMessage.DEP_FAIL.equals(dingTalkMessage.getOperation())){
    		message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
    		message.add("\n\n");
    		message.add(MarkdownMessage.getReferenceText(dingTalkMessage.getFailMessage()));
    	} else if(DingTalkMessage.RE_SUCCESS.equals(dingTalkMessage.getOperation())) {
    		message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
		} else if (DingTalkMessage.RE_FAIL.equals(dingTalkMessage.getOperation())) {
			message.add(MarkdownMessage.getReferenceText(dingTalkMessage.getFailMessage()));
			message.add("\n\n");
			message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
		} else {
			if (dingTalkMessage.getFailMessage() != null) {
				message.add(MarkdownMessage.getReferenceText(dingTalkMessage.getFailMessage()));
				message.add("\n\n");
			}
			message.add(MarkdownMessage.getLinkText(dingTalkMessage.getMotif(), dingTalkMessage.getUrl()));
		}
    	int errorCode = send(message);
		return errorInfo(errorCode);
    }
    
    public static void main(String[] args) throws Exception {
		new DingTalkUtil("https://oapi.dingtalk.com/robot/send?access_token=1e32def7abfc99fc4559610d9e093a76e23cdfb016f2e610d46ae1b7038762aa").auditConfigDingTalkSend(new DingTalkMessage().getConfigMessage("admin", "dev", "123", "123", "http://www.baidu.com", "xxxx", "修改配置"));
	}

	/**
	 * 全局错误码处理
	 * @author Yang Chunhai	 
	 * @return
	 * @throws IOException 
	 */
	private String errorInfo(int errorCode){
		switch (errorCode) {
		case 0: return "请求成功";
		case -1: return "系统繁忙:服务器暂不可用，请稍后重试";
		case 33001: return "无效的企业ID:请检查access_token的正确性";
		default:
			return "未知错误:请联系管理员";
		}
	}

	/**
	 * 消息过程
	 * @author Yang Chunhai	 
	 * @return
	 * @throws IOException
	 */
	private Integer send(MarkdownMessage message){
		try {
			SendResult result = client.send(this.webHook, message);
			return result.getErrorCode();
		} catch (Exception e) {
			return 9999;
		}
	}
}
