package com.bee.devops.admin.interceptor;

import com.bee.devops.admin.common.token.TokenPayLoad;
import com.bee.devops.admin.common.token.TokenUtil;
import com.bee.devops.admin.core.common.service.admin.AdminUriService;
import com.bee.devops.admin.exception.NoPermissionException;
import io.swagger.models.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 资源拦截器,对前端请求的接口做权限校验,其顺序在登录拦截器之后
 *
 * @author wanghuajie
 * @date 2018/8/31 11:59
 */
@Component
public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(ResourceInterceptor.class);

    @Autowired
    private AdminUriService adminUriService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 静态资源直接return true
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        // options请求跳过、登入登出跳过
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())
                || request.getRequestURI().matches("/login/.*")
                || request.getRequestURI().matches("/assets/download")
                || request.getRequestURI().matches("/order/publish")
                || request.getRequestURI().matches("/download/.*")) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers",
                    "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            return true;
        }
        // 根据用户token获取当前登录用户的权限(资源)
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 请求接口路径
        String requestURI = request.getRequestURI();
        TokenPayLoad payLoad = null;
        if (StringUtils.isNotBlank(token)) {
            payLoad = TokenUtil.parseToken(token);
            if (payLoad == null) {
                token = TokenUtil.refreshToken(token);
                payLoad = TokenUtil.parseToken(token);
            }
        }
        // 当前登录用户的用户id
        if (payLoad != null) {
            long userId = payLoad.getUserid();
            String username = payLoad.getUsername();
            // 获取当前登录用户允许访问的接口集合 缓存 TODO
            List<String> permissions = adminUriService.getPermissions(userId);

            // 如果集合中不包含请求的路径,那么有可能是集合中含有正则需要匹配
            boolean flag = false;
            if (!permissions.contains(requestURI)) {
                for (String permission : permissions) {
                    if (requestURI.matches(permission)) {
                        logger.info("请求路径【" + requestURI + "】与permission【" + permission + "】匹配成功!");
                        flag = true;
                        break;
                    }
                }

                // 没有匹配到
                if (!flag) {
                    throw new NoPermissionException("当前用户【" + username + "】没有接口【" + requestURI + "】的访问权限!");
                }
            }

            return true;
        }

        return false;
    }
}
