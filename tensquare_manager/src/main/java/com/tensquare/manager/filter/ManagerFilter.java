package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import entity.MagicCode;
import entity.TokenCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/6
 * com.tensquare.manager.filter
 */
@Component
public class ManagerFilter extends ZuulFilter {
	@Autowired
	private JwtUtil jwtUtil;
	/**
	 * 过滤器类型  pre or post
	 * @return String
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过滤器优先级
	 * @return int
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 是否启用
	 * @return boolean
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器工作内容
	 * 只有 setsendzuulResponse(false)表示不再继续执行
	 * @return Object
	 * @throws ZuulException Exception
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		if (request.getMethod().equals(MagicCode.OPTIONS)){
			return null;
		}
		if (request.getRequestURL().toString().indexOf(MagicCode.JUMP_URL)>0){
			return null;
		}

		String header = request.getHeader(TokenCode.REQUEST_HEADER);
		if (header != null && !"".equals(header)){
			if (header.startsWith(TokenCode.TOKEN_CODE)){
				String token = header.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get(TokenCode.ROLES);
					if (roles.equals(TokenCode.ROLE_ADMIN)){
						currentContext.addZuulRequestHeader(TokenCode.REQUEST_HEADER,header);
						return null;
					}
				}catch (Exception e){
					e.printStackTrace();
					currentContext.setSendZuulResponse(false);
				}
			}
		}
		currentContext.setSendZuulResponse(false);
		currentContext.setResponseStatusCode(403);
		currentContext.setResponseBody("权限不足");
		currentContext.getResponse().setContentType("text/html;charset=utf-8");
		return null;
	}
}
