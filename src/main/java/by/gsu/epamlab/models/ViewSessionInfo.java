package by.gsu.epamlab.models;

import by.gsu.epamlab.dao.models.Film;
import by.gsu.epamlab.dao.models.Place;
import by.gsu.epamlab.dao.models.Session;
import by.gsu.epamlab.dao.models.Theater;

import java.util.List;

public class ViewSessionInfo {

    private int id;
    private Session session;
    private Theater theater;
    private Film film;
    private List<Place> placeMap;
    private Integer[] userBookingIds;

    public ViewSessionInfo(int id, Session session, Theater theater, Film film, List<Place> placeMap, Integer[] userBookingIds) {
        this.id = id;
        this.session = session;
        this.theater = theater;
        this.film = film;
        this.placeMap = placeMap;
        this.userBookingIds = userBookingIds;
    }

    public ViewSessionInfo(Session session, Theater theater, Film film, List<Place> placeMap, Integer[] userBookingIds) {
        this(0, session, theater, film, placeMap, userBookingIds);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public List<Place> getPlaceMap() {
        return placeMap;
    }

    public void setPlaceMap(List<Place> placeMap) {
        this.placeMap = placeMap;
    }

    public Integer[] getUserBookingIds() {
        return userBookingIds;
    }

    public void setUserBookingIds(Integer[] userBookingIds) {
        this.userBookingIds = userBookingIds;
    }
}
