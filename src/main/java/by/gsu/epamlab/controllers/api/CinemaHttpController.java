package by.gsu.epamlab.controllers.api;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.exceptions.DatabaseException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

public class CinemaHttpController extends HttpServlet {

	Gson gson = new GsonBuilder().setDateFormat(VIEW_DATETIME_FORMAT).create();

	CinemaDAO getCinemaService() {
		try {
			return CinemaDAO.getInstance();
		} catch (ClassNotFoundException | SQLException e) {
			throw new DatabaseException(e);
		}
	}

	void setOutputParameters(HttpServletResponse resp) {
		resp.setCharacterEncoding(RESPONSE_ENCODING_UTF_8);
		resp.setContentType(RESPONSE_APPLICATION_JSON);
	}
}
