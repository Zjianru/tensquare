package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import entity.TokenCode;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA
 *
 * @author Zjianru
 * @version 1.0
 * 2019/6/6
 * com.tensquare.web.filter
 */
public class WebFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String header = request.getHeader(TokenCode.REQUEST_HEADER);
		if (header != null && !"".equals(header)){
			currentContext.addZuulRequestHeader(TokenCode.REQUEST_HEADER,header);
		}
		return null;
	}
}
