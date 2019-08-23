package com.souvik.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class SecurityFilter2 implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityFilter2.class);

	private String username = "a";

	private String password = "a";

	private String realm = "Protected";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		try {
			String authHeader = request.getHeader("Authorization");
			if (authHeader != null) {
				StringTokenizer st = new StringTokenizer(authHeader);
				if (st.hasMoreTokens()) {
					String basic = st.nextToken();

					if (basic.equalsIgnoreCase("Basic")) {
						try {
							String credentials = new String(Base64.decodeBase64(st.nextToken()), "UTF-8");
						//	LOG.debug("Credentials: " + credentials);
							int p = credentials.indexOf(":");
							if (p != -1) {
								String _username = credentials.substring(0, p).trim();
								String _password = credentials.substring(p + 1).trim();

								if (!username.equals(_username) || !password.equals(_password)) {
									unauthorized(response, "Unauthorized attempt,issue will be logged and sent to concerned team");
								}

								filterChain.doFilter(servletRequest, servletResponse);
							} else {
								unauthorized(response, "Invalid authentication token");
							}
						} catch (UnsupportedEncodingException e) {
							unauthorized(response);
							throw new Error("Couldn't retrieve authentication", e);
						}
					}
				}
			} else {
				unauthorized(response);
			}

			/*
			 * ServletRequest myrequest = new
			 * RequestWrapper(httpServletRequest);
			 * filterChain.doFilter(myrequest, response);
			 */

		} catch (Exception e) {
			// filterChain.doFilter(servletRequest, servletResponse);
			unauthorized(response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	private void unauthorized(HttpServletResponse response, String message) throws IOException {
		response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
		response.sendError(401, message);
	}

	private void unauthorized(HttpServletResponse response) throws IOException {
		unauthorized(response, "Unauthorized");
	}

}
