package com.bee.devops.admin.core.openapi.ops;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.core.common.entity.common.OpsComGitHook;
import com.bee.devops.admin.core.common.entity.ops.OpsAssembleApp;
import com.bee.devops.admin.core.common.service.common.OpsComGitHookService;
import com.bee.devops.admin.core.common.service.ops.OpsAssembleAppService;
import com.bee.devops.admin.core.vo.request.GitlabCommitsRequest;
import com.bee.devops.admin.core.vo.request.GitlabMessageRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Gitlab发送消息Controller")
@RestController
@RequestMapping(value="/gitlab_message")
public class OpsComGitHookController extends BaseController{
	final static Logger log = Logger.getLogger(OpsComGitHookController.class) ;
	
	@Autowired
	OpsComGitHookService opsComGitHookService;
	@Autowired
	OpsAssembleAppService opsAssembleAppService;
    /**
	 * gitlab消息
	 * @param 
	 * @return
	 */
    @ApiOperation(value="gitlab发送消息",notes="接受gitlab发送的消息")
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResultHandler<String> send(@RequestBody GitlabMessageRequest gitlabMessageRequest) {
    	if(gitlabMessageRequest != null){
    		OpsAssembleApp opsAssembleApp = opsAssembleAppService.getBygiturl(gitlabMessageRequest.getRepository().getGit_http_url());
    		if(opsAssembleApp != null){
    			OpsComGitHook opsComGitHook = new OpsComGitHook();
    			opsComGitHook.setAppId(opsAssembleApp.getAppId());
    			opsComGitHook.setEnvId(opsAssembleApp.getEnvId());
    			opsComGitHook.setMsgType(gitlabMessageRequest.getEvent_name());
    			opsComGitHook.setMsgStatus(0);
    			opsComGitHook.setCommitsCount(gitlabMessageRequest.getTotal_commits_count());
    			StringBuilder filestr=new StringBuilder();
    			GitlabCommitsRequest[] gitlabCommits = gitlabMessageRequest.getCommits();
    			for(GitlabCommitsRequest gitlabCommit:gitlabCommits){
    				String[] addeds = gitlabCommit.getAdded();
    				String[] modifieds = gitlabCommit.getModified();
    				String[] removeds = gitlabCommit.getRemoved();
    				for(String added:addeds){
    					filestr.append("added:"+added);
    				}
    				for(String modified:modifieds){
    					filestr.append("modified:"+modified);
    				}
    				for(String removed:removeds){
    					filestr.append("removed:"+removed);
    				}	
    			}
    			opsComGitHook.setCommitsContent(filestr.toString());
    			opsComGitHookService.saveGitlabMessage(opsComGitHook);
    		}
    		return ResultHandler.success("ok");
    	}    	   	
    	return ResultHandler.error("error");
    }
    
}
