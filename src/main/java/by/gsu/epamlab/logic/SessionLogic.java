package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.Session;
import by.gsu.epamlab.helpers.Helper;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SessionLogic  extends AbstractLogic {

	public SessionLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
		super(cinemaService);
	}

	public List<Session> getActualSession() throws SQLException {
		List<Session> allSessions = cinemaService.getSessionRepository().getAll();
		Date dateNow = Helper.getCurrentDameTime();
		return SessionLogic.getDateFilter(dateNow, allSessions);
	}

	public List<Session> getActualSessionByFilmIds(Integer... ids) throws SQLException {
		List<Session> actualSessionForFilms = new ArrayList<>();
		List<Session> actualSession = getActualSession();
		for (Integer id : ids) {
			actualSessionForFilms.addAll(getFilmIdFilter(id, actualSession));
		}
		return actualSessionForFilms;
	}

	private static List<Session> getFilmIdFilter(int filmId, Collection<Session> collection) {
		List<Session> filteredByFilmId = new ArrayList<>();
		Session tempSession;
		for (Session aCollection : collection) {
			tempSession = aCollection;
			if (tempSession.getFilmId() == filmId) {
				filteredByFilmId.add(tempSession);
			}
		}
		return filteredByFilmId;
	}

	private static List<Session> getDateFilter(Date date, Collection<Session> collection) {
		List<Session> filteredByDate = new ArrayList<>();
		Session tempSession;
		for (Session aCollection : collection) {
			tempSession = aCollection;
			if (tempSession.getDateTime().getTime() >= date.getTime()) {
				filteredByDate.add(tempSession);
			}
		}
		return filteredByDate;
	}
}
