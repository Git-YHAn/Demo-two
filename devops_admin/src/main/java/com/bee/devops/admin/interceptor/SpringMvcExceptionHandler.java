package com.bee.devops.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bee.devops.admin.common.constant.Constants;
import com.bee.devops.admin.common.request.ResultHandler;
import com.bee.devops.admin.exception.BusinessException;
import com.bee.devops.admin.exception.NotLoginException;

/**
 * Spring MVC统一异常拦截器
 * 
 * @author
 */
@Component
public class SpringMvcExceptionHandler implements HandlerExceptionResolver {
	private final Logger logger = LoggerFactory.getLogger(SpringMvcExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.error("异常内容为： ", ex);
		ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
		try {
			ResultHandler<?> result = null;
			if (ex instanceof NotLoginException) {
				response.setStatus(401);
				mv.setStatus(HttpStatus.UNAUTHORIZED);
				result = ResultHandler.error(Constants.RESP_CODE.TO_LOGIN, "未登录，跳转到登录页面", "");
			} else if (ex instanceof BusinessException) {
				result = ResultHandler.error(Constants.RESP_CODE.FAILED, ex.getMessage(), null);
			} else {
				result = ResultHandler.error(ex.getMessage(), null);
			}
			logger.info("result内容为： ", result.toString());
			mv.addObject("code", result.getCode());
			mv.addObject("msg", result.getMsg());
			mv.addObject("data", result.getData());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			response.getOutputStream().flush();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mv;
	}

}
