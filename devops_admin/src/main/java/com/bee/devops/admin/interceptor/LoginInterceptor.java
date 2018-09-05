package com.bee.devops.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.bee.devops.admin.common.request.BaseController;
import com.bee.devops.admin.common.token.TokenPayLoad;
import com.bee.devops.admin.common.token.TokenUtil;
import com.bee.devops.admin.config.LogAopAspect;
import com.bee.devops.admin.core.common.entity.admin.AdminUser;
import com.bee.devops.admin.core.common.service.admin.AdminUserService;
import com.bee.devops.admin.exception.NotLoginException;

import io.swagger.models.HttpMethod;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	AdminUserService adminUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		// 静态资源直接return true
		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}
		// options请求跳过、登入登出跳过
		if (HttpMethod.OPTIONS.name().equals(request.getMethod()) || request.getRequestURI().matches("/login/.*")) {
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
			response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin, X-Requested-With, Content-Type, Accept, Authorization");
			return true;
		}
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		TokenPayLoad payLoad = null;
		if (StringUtils.isNotBlank(token)) {
			payLoad = TokenUtil.parseToken(token);
			if (payLoad == null) {
				token = TokenUtil.refreshToken(token);
				payLoad = TokenUtil.parseToken(token);
			}
		} else { // gitlab钩子触发时没有token
			if (StringUtils.equals("hook", request.getParameter("source"))) {
				payLoad = new TokenPayLoad();
				payLoad.setUserid(1L); // 暂时用admin
				payLoad.setIp(request.getRemoteHost());
			}
		}
		// 打印登录日志
		printReqLog(request, payLoad);

		if (payLoad == null || !request.getRemoteHost().equals(payLoad.getIp())) {
			throw new NotLoginException("用户未登录");
		} else {
			response.setHeader("Access-Control-Expose-Headers", HttpHeaders.AUTHORIZATION);
			response.setHeader(HttpHeaders.AUTHORIZATION, token);
			Long userid = payLoad.getUserid();
			AdminUser adminUser = adminUserService.getUserById(userid);
			BaseController.setAdminUser(adminUser);
			LogAopAspect.setAdminUser(adminUser);
		}
		return true;
	}

	private void printReqLog(HttpServletRequest request, TokenPayLoad payLoad) {
		StringBuilder sb = new StringBuilder();
		String name = payLoad != null ? payLoad.getUserid() + "-" + payLoad.getUsername() : "游客";
		sb.append("用户[" + name + "]");
		String ip = getIpAddr(request);
		sb.append(",ip[" + ip + "]");
		String querString = request.getQueryString();
		sb.append(",url[" + request.getRequestURL());
		if (querString != null) {
			sb.append("?" + querString);
		}
		sb.append("]");

		StringBuffer parame = new StringBuffer();

		if (request.getMethod().equalsIgnoreCase("Post")) {
			sb.append(", submitway: post and parameter[" + parame + "]");
		} else {
			sb.append(", submitway: " + request.getMethod());
		}
		logger.info(sb.toString());
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

	}

	private String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
