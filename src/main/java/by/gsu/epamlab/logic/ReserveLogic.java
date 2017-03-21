package by.gsu.epamlab.logic;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.*;
import by.gsu.epamlab.enums.SeatState;
import by.gsu.epamlab.exceptions.WebFilmsException;
import by.gsu.epamlab.models.ViewSessionInfo;

import java.sql.SQLException;
import java.util.List;

import static by.gsu.epamlab.contants.Constants.SOME_PLACES_ARE_BUSY_JET;

public class ReserveLogic extends AbstractLogic {

    public ReserveLogic(CinemaDAO cinemaService) throws ClassNotFoundException, SQLException {
        super(cinemaService);
    }

    private List<Place> theaterPlacesActuator(Session userSelectedSession, List<SoldPlace> userSelectedPlaces, Integer userId, Integer sessionId) throws SQLException {

        List<Place> theaterPlaces = cinemaService.getPlaceRepository().getByTheaterId(userSelectedSession.getTheaterId());
        List<SoldPlace> usersSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession(sessionId);

        for (SoldPlace soldPlace : usersSelectedPlaces) {
            Place tempPlace = cinemaService.getPlaceRepository().getById(soldPlace.getPlaceId());
            int index = theaterPlaces.indexOf(tempPlace);
            if (userSelectedPlaces.contains(soldPlace)) {

                theaterPlaces.get(index).setState(SeatState.FREE.name().toLowerCase());
            } else {
                theaterPlaces.get(index).setState(SeatState.RESERVED.name().toLowerCase());

            }
        }
        return theaterPlaces;
    }

    public ViewSessionInfo getSessionData(Integer userId, Integer sessionId) throws SQLException {
        Session userSelectedSession = cinemaService.getSessionRepository().getById(sessionId);
        Film userSelectedFilm = cinemaService.getFilmsRepository().getById(userSelectedSession.getFilmId());
        Theater userSelectedTheater = cinemaService.getTheaterRepository().getById(userSelectedSession.getTheaterId());
        List<SoldPlace> userSelectedPlaces = cinemaService.getSoldPlaceRepository().getUserSession(userId, sessionId);
        List<Place> theaterPlaces = theaterPlacesActuator(userSelectedSession, userSelectedPlaces, userId, sessionId);
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
            if (cinemaService.getSoldPlaceRepository().add(new SoldPlace(userId, place.getId(), sessionId)) == null) {
                throw new WebFilmsException(SOME_PLACES_ARE_BUSY_JET);
            }
        }

        return getSessionData(userId, sessionId);
    }
}
