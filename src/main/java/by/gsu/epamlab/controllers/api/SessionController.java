package by.gsu.epamlab.controllers.api;

import by.gsu.epamlab.enums.RequestStatus;
import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.exceptions.IllegalParameters;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.logic.SessionLogic;
import by.gsu.epamlab.models.ViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(API_SESSION_URL + ANY_SUB_PATH)
public class SessionController extends CinemaHttpController {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// set CharsetEncoding and type of returned data
		setOutputParameters(resp);
		// select string with parameters
		String argString = req.getRequestURI().replace(API_SESSION_URL, EMPTY_STRING).trim();
		// get output stream
		PrintWriter out = resp.getWriter();
		// create reference to sessionLogic object
		SessionLogic sessionLogic = null;
		// create reference and object of ViewModel
		ViewModel responseData = new ViewModel();

		try {	// protected code
			// initialize reference to sessionLogic object
			sessionLogic = new SessionLogic(getCinemaService());
			// has been parameters?
			if (argString.length() > 0) { // yes
				// get all sessions with Ids
				// and get array of session for the Ids
				Integer[] indexes = Helper.csvIndexesParser(argString);
				responseData.setData(sessionLogic.getActualSessionByFilmIds(indexes));
			} else { // no
				// get all actual session
				responseData.setData(sessionLogic.getActualSession());
			}
			// set status to success passed
			responseData.setStatus(RequestStatus.OK);
		} catch (ClassNotFoundException | SQLException e ) {
			responseData = new ViewModel(RequestStatus.ERROR, e.getMessage(), null);
			throw new DatabaseException();
		} catch (IllegalParameters | NumberFormatException e) {
			responseData = new ViewModel(RequestStatus.ERROR, BAD_PARAMETERS, null);
			throw new IllegalParameters();
		} finally {
			if(out!=null){
				out.print(gson.toJson(responseData));
				out.close();
			}
			if(sessionLogic!=null){
				try {
					sessionLogic.close();
				} catch (SQLException e) {
					throw new DatabaseException();
				}
			}
		}
	}
}
