package by.gsu.epamlab.controllers;

import by.gsu.epamlab.enums.RequestStatus;
import by.gsu.epamlab.models.ViewModel;
import by.gsu.epamlab.models.ViewUser;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

import static by.gsu.epamlab.contants.Constants.*;

@WebFilter(API_ANY_URL)
public class ApiFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		if (req.getRequestURI().contains(API_FILM_URL)) {
			chain.doFilter(request, response);
			return;
		}
		ViewUser user = (ViewUser)req.getSession().getAttribute(USER);
		if(user==null){
			PrintWriter out = response.getWriter();
			out.println(new Gson().toJson(new ViewModel(RequestStatus.AUTH_ERROR, NOT_AUTHORIZED_USER, null)));
		} else {
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