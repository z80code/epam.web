package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.Film;
import by.gsu.epamlab.dao.models.Session;
import by.gsu.epamlab.enums.IndexPosition;
import by.gsu.epamlab.models.ViewFilm;
import by.gsu.epamlab.models.ViewSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.gsu.epamlab.enums.IndexPosition.FIRST;
import static by.gsu.epamlab.enums.IndexPosition.ZERO;

public class ModeratorLogic extends AbstractLogic {

    public ModeratorLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
        super(cinemaService);
    }


    public Object getFilms(Integer[] indexes) throws SQLException {
        int firstFilm = indexes[ZERO.getIndex()];
        int numberFilms = indexes[FIRST.getIndex()];
        List<Film> filmList = cinemaService.getFilmsRepository().getAll(firstFilm, numberFilms);
        List<ViewFilm> viewFilmList = new ArrayList<>(filmList.size());

        for (Film film: filmList) {
            viewFilmList.add(new ViewFilm(film).getInstance(cinemaService));
        }
        return viewFilmList;
    }

    public Object getSessions(Integer[] indexes) throws SQLException {
        int firstSession = indexes[ZERO.getIndex()];
        int numberSession = indexes[FIRST.getIndex()];
        List<Session> sessionList = cinemaService.getSessionRepository().getAll(firstSession, numberSession);
        List<ViewSession> listViewSession =  new ArrayList<>(sessionList.size());

        for (Session session: sessionList) {
            listViewSession.add(new ViewSession(session).getInstance(cinemaService));
        }
       return listViewSession;
    }

    public Object getSessionsCount() throws SQLException {
        return cinemaService.getSessionRepository().getAll().size();
    }

    public Object getFilmsCount() throws SQLException {
        return cinemaService.getFilmsRepository().getAll().size();
    }
}
