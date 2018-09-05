package com.bee.devops.admin.component.sp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.utils.OpsSysParameterUtil;
import com.bee.devops.admin.component.sp.ksc.iam.KSCIAMClient;
import com.bee.devops.admin.component.sp.ksc.iam.request.GetAccountAllProjectListRequest;
import com.bee.devops.admin.component.sp.ksc.iam.request.GetUserRequest;
import com.bee.devops.admin.component.sp.ksc.iam.request.ListUserRequest;
import com.bee.devops.admin.component.sp.ksc.iam.response.GetAccountAllProjectListResponse;
import com.bee.devops.admin.component.sp.ksc.iam.response.GetUserResponse;
import com.bee.devops.admin.component.sp.ksc.iam.response.ListUsersResponse;
import com.bee.devops.admin.component.sp.ksc.iam.transform.ListProjectResult;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.BasicAWSCredentials;

/**
 * 服务器供应商用户权限接口代理类
 * 
 * @description IAMServerProviderAPIProxy
 * @author TurnerXi
 * @date 2018年7月19日
 */
public class IAMServerProviderAPIProxy {
	private AWSCredentials credentials;
	private KSCIAMClient iamClient;

	public IAMServerProviderAPIProxy() {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.iamClient = new KSCIAMClient(credentials);
		this.iamClient.setEndpoint(iamClient.getEndpointPrefix() + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public IAMServerProviderAPIProxy(String region) {
		super();
		this.credentials = new BasicAWSCredentials(OpsSysParameterUtil.getKscAccessKey(), OpsSysParameterUtil.getKscPrivateKey());
		this.iamClient = new KSCIAMClient(credentials);
		this.iamClient.setEndpoint(iamClient.getEndpointPrefix() + "." + region + "." + OpsSysParameterUtil.getKscEndpoint());
	}

	public static IAMServerProviderAPIProxy getInstance(String region) {
		return new IAMServerProviderAPIProxy(region);
	}

	public static IAMServerProviderAPIProxy getInstance() {
		return new IAMServerProviderAPIProxy();
	}

	/**
	 * 查询单个用户详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONObject getUser(String userName) throws Exception {
		GetUserRequest userInfoRequest = new GetUserRequest();
		GetUserResponse userInfo = iamClient.getUser(userInfoRequest);
		return (JSONObject) JSON.toJSON(userInfo.getGetUserResult().getUser());
	}

	/**
	 * 查询用户列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public JSONArray queryUsers() throws Exception {
		ListUserRequest request = new ListUserRequest();
		ListUsersResponse response = iamClient.ListUsers(request);
		return (JSONArray) JSON.toJSON(response.getListUserResult().getUsers());
	}

	/**
	 * 查询账号下所有项目
	 * 
	 * @return
	 * @throws Exception
	 */
	public ListProjectResult queryAllProjectList() throws Exception {
		GetAccountAllProjectListRequest request = new GetAccountAllProjectListRequest();
		GetAccountAllProjectListResponse response = iamClient.getAccountAllProjectList(request);
		return response.getListProjectResult();
	}

}
