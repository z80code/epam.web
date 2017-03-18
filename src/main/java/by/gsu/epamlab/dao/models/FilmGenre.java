package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmGenre {
    private int id;
    private int filmId;
    private int genreId;

    public FilmGenre() {
    }

    public FilmGenre(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
    }


    public FilmGenre(int filmId, int genreId) {
        this(0, filmId, genreId);
    }

    public FilmGenre(int id, int filmId, int genreId) {
        this.id = id;
        this.filmId = filmId;
        this.genreId = genreId;
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

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "FilmGenres{" +
                "id=" + id +
                ", filmId=" + filmId +
                ", genreId=" + genreId +
                '}';
    }
}
