package by.gsu.epamlab.controllers;

import by.gsu.epamlab.contants.Constants;
import by.gsu.epamlab.models.ViewUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.contants.Constants.*;

@WebFilter(ANY_URL)
public class MainFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		ViewUser user = (ViewUser) req.getSession().getAttribute(Constants.USER);
		if (user!=null) {
			chain.doFilter(request, response);
		} else {
			//resp.sendRedirect(FORWARD_LOCATION);
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}