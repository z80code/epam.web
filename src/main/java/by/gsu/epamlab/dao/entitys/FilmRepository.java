package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Film;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmRepository extends AbstractRepository<Film> {

    private final static String SELECT_ALL = "select * from films";
    private final static String SELECT_BY_ID = "select * from films where films.id=?";
    private final static String SELECT_BY_TITLE_RELEASE = "select * from films where films.title=? and films.release=?";
    private final static String DELETE_BY_ID = "deletePlace from films where films.id=?";
    private final static String ADD_USER = "insert into users(title, release, directorId, description, image) values(?,?,?,?,?)";

    public FilmRepository(Connection conn) {
        super(conn);
    }

    @Override
    Film createByResultSet(ResultSet rs) throws SQLException {
        return new Film(rs);
    }

    @Override
    public Film getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Film(rs) : null;
    }

    public List<Film> getByIds(Integer... ids) throws SQLException {
        List<Film> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public Film getByTitleRelease(String title, Date release) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_TITLE_RELEASE, title, release);
        return rs.next() ? new Film(rs) : null;
    }

    @Override
    public Film add(Film film) throws SQLException {
        Film filmExist = getByTitleRelease(film.getTitle(), film.getRelease());
        if (filmExist != null) return null;

        prepareUpdate(ADD_USER,
                film.getTitle(),
                film.getRelease(),
                film.getDirectorId(),
                film.getDescription(),
                film.getImage());

        return getByTitleRelease(film.getTitle(), film.getRelease());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Film> getAll() throws SQLException {
        List<Film> films = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            films.add(new Film(rs));
        }
        return films;
    }
}
