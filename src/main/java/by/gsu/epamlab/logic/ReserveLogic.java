package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.*;
import by.gsu.epamlab.exceptions.WebFilmsException;
import by.gsu.epamlab.models.ViewSessionInfo;

import java.sql.SQLException;
import java.util.List;

import static by.gsu.epamlab.contants.Constants.*;

public class ReserveLogic extends AbstractLogic {

    public ReserveLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
        super(cinemaService);
    }

    public ViewSessionInfo getSessionData(Integer userId, Integer sessionId) throws SQLException {
        Session userSelectedSession = cinemaService.getSessionRepository().getById(sessionId);
        Film userSelectedFilm = cinemaService.getFilmsRepository().getById(userSelectedSession.getFilmId());
        List<Place> theaterPlaces = cinemaService.getPlaceRepository().getByTheaterId(userSelectedSession.getTheaterId());
        Theater userSelectedTheater = cinemaService.getTheaterRepository().getById(userSelectedSession.getTheaterId());
        List<SoldPlace> userSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession(userId, sessionId);
        return new ViewSessionInfo(
                userSelectedSession,
                userSelectedTheater,
                userSelectedFilm,
                theaterPlaces,
                getIdSeats(userSelectedPlaces)
        );
    }

    private Integer[] getIdSeats(List<SoldPlace> places) {
        Integer[] result = new Integer[places.size()];
        for (int i = 0; i < places.size(); i++) {
            result[i] = places.get(i).getPlaceId();
        }
        return result;
    }

    public ViewSessionInfo setSessionData(Integer userId, Integer sessionId, List<Place> places) throws SQLException {
        // todo

        List<SoldPlace> userSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession(userId, sessionId);

        for (SoldPlace soldPlace : userSelectedPlaces) {
            cinemaService.getSoldPlaceRepository().deleteById(soldPlace.getId());
        }
        for (Place place : places) {
            if(cinemaService.getSoldPlaceRepository().add(new SoldPlace(userId, place.getId(), sessionId))==null){
                throw new WebFilmsException(SOME_PLACES_ARE_BUSY_JET);
            }
        }

        Session userSelectedSession = cinemaService.getSessionRepository().getById(sessionId);
        Film userSelectedFilm = cinemaService.getFilmsRepository().getById(userSelectedSession.getFilmId());
        List<Place> theaterPlaces = cinemaService.getPlaceRepository().getByTheaterId(userSelectedSession.getTheaterId());
        Theater userSelectedTheater = cinemaService.getTheaterRepository().getById(userSelectedSession.getTheaterId());
        userSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession(userId, sessionId);
        return new ViewSessionInfo(
                userSelectedSession,
                userSelectedTheater,
                userSelectedFilm,
                theaterPlaces,
                getIdSeats(userSelectedPlaces)
        );
    }
}
