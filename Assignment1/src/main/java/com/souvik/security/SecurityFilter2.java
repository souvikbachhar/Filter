package com.souvik.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter2 implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		
		try {
		
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				ServletRequest myrequest = new RequestWrapper(httpServletRequest);
				filterChain.doFilter(myrequest, response);
			
		} catch (Exception e) {
			filterChain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
