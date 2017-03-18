package by.gsu.epamlab.controllers;

import by.gsu.epamlab.contants.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.gsu.epamlab.contants.Constants.USER_INDEX_HTML;

@WebServlet(Constants.USER_PAGE_URL)
public class UserPageController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			getServletContext().getRequestDispatcher(USER_INDEX_HTML).forward(req, resp);
	}
}
