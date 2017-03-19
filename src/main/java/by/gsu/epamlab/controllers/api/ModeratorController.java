package by.gsu.epamlab.controllers.api;

import by.gsu.epamlab.enums.Entity;
import by.gsu.epamlab.enums.RequestStatus;
import by.gsu.epamlab.exceptions.DatabaseException;
import by.gsu.epamlab.exceptions.IllegalParameters;
import by.gsu.epamlab.helpers.Helper;
import by.gsu.epamlab.logic.ModeratorLogic;
import by.gsu.epamlab.models.ViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static by.gsu.epamlab.contants.Constants.*;

@WebServlet(API_MODERATOR_URL + ANY_SUB_PATH)
public class ModeratorController extends CinemaHttpController {

    private Entity extractEntity(String methodAndArgString) {
        return Enum.valueOf(Entity.class, methodAndArgString.split(CURRENT_URI)[0].trim().toUpperCase());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // set CharsetEncoding and type of returned data
        setOutputParameters(resp);
        // select string with parameters
        String entityAndArgString = req.getRequestURI().replace(API_MODERATOR_URL, EMPTY_STRING).trim();
        // get output stream
        PrintWriter out = resp.getWriter();
        // create reference to moderatorLogic object
        ModeratorLogic moderatorLogic = null;
        // create reference and object of ViewModel
        ViewModel responseData = new ViewModel();

        try {	// protected code
            // initialize reference to moderatorLogic object
            moderatorLogic = new ModeratorLogic(getCinemaService());

            Entity entitySource = extractEntity(entityAndArgString);

            String argString = entityAndArgString.replace(entitySource.name().toLowerCase()+ CURRENT_URI, EMPTY_STRING);

            // has been parameters?
            if (argString.length() > 0) { // yes
                // get all sessions with Ids
                // and get array of session for the Ids

              Integer[] indexes = Helper.csvIndexesParser(argString);

                switch (entitySource) {
                    case FILMS: {
                        responseData.setData(moderatorLogic.getFilms(indexes));
                        break;
                    }
                    case SESSIONS: {
                        responseData.setData(moderatorLogic.getSessions(indexes));
                        break;
                    }
                }
            } else { // no

                switch (entitySource) {
                    case FILMS: {
                        responseData.setData(moderatorLogic.getFilmsCount());
                        break;
                    }
                    case SESSIONS: {
                        responseData.setData(moderatorLogic.getSessionsCount());
                        break;
                    }
                }
            }
            // set status to success passed
            responseData.setStatus(RequestStatus.OK);
        } catch (ClassNotFoundException | SQLException e ) {
            responseData = new ViewModel(RequestStatus.ERROR, e.getMessage(), null);
            throw new DatabaseException();
        } catch (IllegalParameters | IllegalArgumentException e) {
            responseData = new ViewModel(RequestStatus.ERROR, BAD_PARAMETERS, null);
            throw new IllegalParameters();
        } finally {
            if(out!=null){
                out.print(gson.toJson(responseData));
                out.close();
            }
            if(moderatorLogic!=null){
                try {
                    moderatorLogic.close();
                } catch (SQLException e) {
                    throw new DatabaseException();
                }
            }
        }
    }
}
