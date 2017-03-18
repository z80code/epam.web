package by.gsu.epamlab.controllers;

import by.gsu.epamlab.helpers.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(LOGOUT)
public class LogoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute(USER) != null) {
			req.getSession().removeAttribute(USER);
		}
		Helper.deleteCookie(USER, resp);
		Helper.deleteCookie(SESSION_ID, resp);
		resp.sendRedirect(FORWARD_LOCATION);
	}
}
