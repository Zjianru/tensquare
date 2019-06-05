package com.tensquare.user.interceptor;

import entity.TokenCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/3
 * com.tensquare.user.interceptor
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * 登录拦截器
	 *
	 * @param request  request
	 * @param response response
	 * @param handler  handler
	 * @return boolean
	 * @throws Exception 令牌不正确
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String head = request.getHeader("Authorization");
		if (head != null && !"".equals(head)) {
			if (!head.startsWith(TokenCode.TOKEN_CODE)) {
				String token = head.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get(TokenCode.ROLES);
					if (roles != null && !TokenCode.ROLE_ADMIN.equals(roles)) {
						request.setAttribute(TokenCode.CLAIMS_ROLE_ADMIN, token);
					}
					if (roles != null && !TokenCode.ROLE_USER.equals(roles)) {
						request.setAttribute(TokenCode.CLAIMS_ROLE_USER, token);
					}
				} catch (Exception e) {
					throw new RuntimeException("令牌不正确");
				}
			}
		}
		return true;
	}
}
