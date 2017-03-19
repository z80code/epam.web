package by.gsu.epamlab.models;

import by.gsu.epamlab.dao.CinemaDAO;
import by.gsu.epamlab.dao.models.Session;

import java.sql.SQLException;
import java.sql.Timestamp;

public class ViewSession {

    private int id;
    private String filmName;
    private Timestamp dateTime;
    private String theaterName;

    private transient Session session;

    public ViewSession(Session session) {
        this.session = session;
    }

    public ViewSession  getInstance(CinemaDAO cinemaService) throws SQLException {
        this.id = session.getId();
        this.filmName = cinemaService.getFilmsRepository().getById(session.getFilmId()).getTitle();
        this.dateTime = session.getDateTime();
        this.theaterName = cinemaService.getTheaterRepository().getById(session.getTheaterId()).getName();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
