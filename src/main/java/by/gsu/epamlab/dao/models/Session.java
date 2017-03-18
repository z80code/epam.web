package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Session {

    private int id;
    private int filmId;
    private Timestamp dateTime;
    private int theaterId;

    public Session() {
    }

    public Session(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
             resultSet.getInt(2),
             resultSet.getTimestamp(3),
             resultSet.getInt(4));
    }

    public Session(int filmId, Timestamp dateTime,int theaterId) {
        this(0, filmId, dateTime, theaterId);
    }

    public Session(int id, int filmId, Timestamp dateTime, int theaterId) {
        this.id = id;
        this.filmId = filmId;
        this.dateTime = dateTime;
        this.theaterId = theaterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", dateTime=" + dateTime +
                ", theaterId=" + theaterId +
                '}';
    }
}
