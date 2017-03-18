package by.gsu.epamlab.controllers.api;

import by.gsu.epamlab.enums.RequestStatus;
import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.exceptions.IllegalParameters;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.logic.CinemaLogic;
import by.gsu.epamlab.models.ViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(API_FILM_URL + ANY_SUB_PATH)
public class FilmController extends CinemaHttpController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// set CharsetEncoding and type of returned data
		setOutputParameters(resp);
		// select string with parameters
		String argString = req.getRequestURI().replace(API_FILM_URL, EMPTY_STRING).trim();
		// get output stream
		PrintWriter out = resp.getWriter();
		// create reference to CinemaLogic object
		CinemaLogic cinemaLogic = null;
		// create reference and object of ViewModel
		ViewModel responseData = new ViewModel();
		try {  // protected code
			// initialize reference to sessionLogic object
			cinemaLogic = new CinemaLogic(getCinemaService());
			// has been parameters?
			if (argString.length() > 0) { // yes
				// and get array of films for the Ids
				Integer[] indexes = Helper.csvIndexesParser(argString);
				responseData.setData(cinemaLogic.getActualFilms(indexes));
			} else { // no
				// get all actual films
				responseData.setData(cinemaLogic.getActualFilms());
			}
			// set status to success passed
			responseData.setStatus(RequestStatus.OK);
		} catch (ClassNotFoundException | SQLException e) {
			responseData = new ViewModel(RequestStatus.ERROR, e.getMessage(), null);
			throw new DatabaseException();
		} catch (IllegalParameters | NumberFormatException e) {
			responseData = new ViewModel(RequestStatus.ERROR, BAD_PARAMETERS, null);
			throw new IllegalParameters();
		} finally {
			if (out != null) {
				out.print(gson.toJson(responseData));
				out.close();
			}
			if(cinemaLogic!=null){
				try {
					cinemaLogic.close();
				} catch (SQLException e) {
					throw new DatabaseException(e);
				}
			}
		}
	}
}