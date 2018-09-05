package com.bee.devops.admin.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;

import com.bee.devops.admin.common.request.RequestDtoBase;
import com.bee.devops.admin.common.request.ResultHandler;
import com.google.common.collect.Maps;

/**
 * 
 * @author created by liyong on 2018年3月2日 上午 11:56:18
 */
@Aspect
@Order(0)
public class ControllerMethodAspect {

	private final Logger logger = LoggerFactory.getLogger(ControllerMethodAspect.class);
	
	private Validator validator;
	
	public ControllerMethodAspect(Validator validator) {
		this.validator = validator;
	}
	
	@Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
	public void restController() {
	}

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMethod() {
	}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
	public void postMappingMethod() {
	}
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	public void getMappingMethod() {
	}
	
	@Around(value = "restController() && (requestMethod() || postMappingMethod() || getMappingMethod()) && args(requestDtoBase,..)")
	public Object doValidate(ProceedingJoinPoint pjp, RequestDtoBase requestDtoBase) throws Throwable {
		// 输入参数校验
		BindingResult result = new MapBindingResult(Maps.newHashMap(), "restRequest");
		validator.validate(requestDtoBase, result);
		if (result.hasErrors()) {
			logger.error("接口【{}】参数校验异常【{}】", pjp.getSignature(), result.getAllErrors().get(0).getDefaultMessage());
			return ResultHandler.error(result.getFieldErrors().get(0).getDefaultMessage(), "");
		}
		// HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return pjp.proceed();
	}

}
