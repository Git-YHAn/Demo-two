package com.bee.devops.admin.core.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.common.token.TokenPayLoad;
import com.bee.devops.admin.common.token.TokenUtil;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.service.admin.AdminUserService;
import com.bee.devops.admin.core.vo.request.LoginRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("登陆模块")
@RestController
@RequestMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class LoginController extends BaseController {
	final static Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private AdminUserService adminUserService;
	// @Autowired
	// private ManagerAdminSession managerAdminSession;

	private final static String PASS_PARAM = "adminuser";

	/***
	 * 登陆查询管理员
	 * 
	 * @param loginRequest
	 * @return
	 */
	@ApiOperation(value = "登陆查询管理员", notes = "登陆查询管理员")
	@PostMapping(value = "/dologin")
	public ResultHandler<String> queryAdminUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
		try {
			String password = new Md5PasswordEncoder().encodePassword(loginRequest.getPwd(),
					loginRequest.getUsername() + PASS_PARAM);
			Integer result = adminUserService.userLoginLimit(loginRequest.getUsername(), password, request);
			if(result == -1) {
				return ResultHandler.error("IP已被禁止访问", "0");
			} else if (result == -2) {
				return ResultHandler.error("帐号被禁用", "0");
			} else if (result == 1) {
				return ResultHandler.error("用户名或密码不正确", "0");
			}
			AdminUser adminUser = adminUserService.queryAdminUser(loginRequest.getUsername(), password);
	        //获取是否保存Cookie
	        boolean rember = loginRequest.isRember();
			if(rember) {
				//创建Cookie
		        Cookie nameCookie = new Cookie("name",URLEncoder.encode(loginRequest.getUsername(),"utf-8"));
		        Cookie pwCookie = new Cookie("psw",loginRequest.getPwd());

		        //设置Cookie的路径
		        nameCookie.setPath("/");
		        pwCookie.setPath("/");

	            nameCookie.setMaxAge(7*24*60*60);
	            pwCookie.setMaxAge(7*24*60*60);

		        //加入Cookie到响应头
		        response.addCookie(nameCookie);
		        response.addCookie(pwCookie);
			} else {
				// 未勾选记住密码则清除cookie
				Cookie nameCookie = new Cookie("name", null);
		        Cookie pwCookie = new Cookie("psw", null);

		        //设置Cookie的路径
		        nameCookie.setPath("/");
		        pwCookie.setPath("/");

	            nameCookie.setMaxAge(0);
	            pwCookie.setMaxAge(0);

		        //加入Cookie到响应头
		        response.addCookie(nameCookie);
		        response.addCookie(pwCookie);
			}

			String payload = JSONObject.toJSONString(new TokenPayLoad(adminUser.getAdminUserId(),
					adminUser.getUsername(), adminUser.getPassword(), request.getRemoteHost()));
			String token = TokenUtil.getToken(payload);
			return ResultHandler.success(token);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return ResultHandler.error("登陆失败", null);
	}

	@ApiOperation(value = "注销", notes = "注销")
	@PostMapping(value = "/logout")
	public ResultHandler<?> logout(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader(HttpHeaders.AUTHORIZATION, "");
		return ResultHandler.success(null);
	}

}
