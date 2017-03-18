package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cast {
    private int id;
    private int filmId;
    private int actorId;

    public Cast() {
    }

    public Cast(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
    }

    public Cast(int filmId, int actorId) {
        this(0, filmId, actorId);
    }

    public Cast(int id, int filmId, int actorId) {
        this.id = id;
        this.filmId = filmId;
        this.actorId = actorId;
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

    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", actorId=" + actorId +
                '}';
    }
}
