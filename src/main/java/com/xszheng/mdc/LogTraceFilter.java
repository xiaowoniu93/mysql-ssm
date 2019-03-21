package com.xszheng.mdc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogTraceFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			LogTrace.enterTrace();
			chain.doFilter(request, response);
		} catch (Exception e) {
		} finally {
			LogTrace.exitTrace();
		}
		
	}

	@Override
	public void destroy() {
		
	}

}
