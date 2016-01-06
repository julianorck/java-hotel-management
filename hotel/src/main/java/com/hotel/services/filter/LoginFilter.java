package com.hotel.services.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hotel.model.Employee;

public class LoginFilter implements Filter {
	@Override
	public void destroy() { // TODO Auto-generated method stub
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		Employee user = null;
		final HttpSession sess = ((HttpServletRequest) request).getSession(false);
		if (sess != null) {
			user = (Employee) sess.getAttribute("user");
		}
		if (user == null) {
			final String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/pages/login.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException { // TODO Auto-generated method stub

	}

}
