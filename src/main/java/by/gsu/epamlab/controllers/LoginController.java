package by.gsu.epamlab.controllers;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.User;
import by.gsu.epamlab.enums.UserRole;
import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.models.ViewUser;
import by.gsu.epamlab.services.RoleService;
import by.gsu.epamlab.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(LOGIN)
public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userLogin = req.getParameter(USER_LOGIN);
		String userPassword = req.getParameter(PASSWORD);

		if (userLogin == null || userLogin.length() == 0 || userPassword == null || userPassword.length() == 0) {
			resp.sendRedirect(FORWARD_LOCATION);
		}
		CinemaDAO cinemaDAO = null;
		try {
			cinemaDAO = CinemaDAO.getInstance();
			UserService userService = new UserService(cinemaDAO);
			RoleService roleService = new RoleService(cinemaDAO);

			if (userService.checkUserPassword(userLogin, userPassword)) {
				User user = userService.getUserByLogin(userLogin);
				UserRole role =  Enum.valueOf(UserRole.class, (roleService.getRoleById(user.getRole())).getName().toUpperCase());
				ViewUser viewUser = new ViewUser(
						user.getId(),
						user.getName(),
						user.getLogin(),
						role
				);

				Helper.setCookie(USER, viewUser.getName(), resp);
				req.getSession().setAttribute(USER, viewUser);

				// create mapping by user roles
				switch (role) {

					case USER: {
						resp.sendRedirect(FORWARD_LOCATION + USER);
						break;
					}
					case MODERATOR: {
						resp.sendRedirect(FORWARD_LOCATION + MODERATOR);
						break;
					}
					case ADMIN: {
						resp.sendRedirect(FORWARD_LOCATION + USER);
						break;
					}
				}
			} else {
				resp.sendRedirect(FORWARD_LOCATION);
			}
		} catch (SQLException | ClassNotFoundException e) {
			 throw new DatabaseException(e);
		} finally {
			if (cinemaDAO != null) {
				try {
					cinemaDAO.close();
				} catch (SQLException e) {
					throw new DatabaseException(e);
				}
			}
		}
	}
}
