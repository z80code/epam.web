package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.FilmGenre;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmGenreRepository extends AbstractRepository<FilmGenre> {

    private final static String SELECT_ALL = "select * from filmGenres";
    private final static String SELECT_BY_ID = "select * from filmGenres where filmGenres.id=?";
    private final static String SELECT_BY_FILM_ID = "select * from filmGenres where filmGenres.filmId=?";
    private final static String SELECT_BY_FILM_GENRE = "select * from filmGenres where filmGenres.filmId=? and filmGenres.genreId=?";
    private final static String DELETE_BY_ID = "deletePlace from filmGenres where filmGenres.id=?";
    private final static String ADD_CAST = "insert into filmGenres(filmId, genreId) values(?,?)";

    public FilmGenreRepository(Connection conn) {
        super(conn);
    }

    @Override
    FilmGenre createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public FilmGenre getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new FilmGenre(rs) : null;
    }

    public List<FilmGenre> getByIds(Integer... ids) throws SQLException {
        List<FilmGenre> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }


    public List<FilmGenre> getByFilmId(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_FILM_ID, id);
        List<FilmGenre> casts = new ArrayList<>();
        while (rs.next()){
            casts.add(new FilmGenre(rs));
        }
        return casts;
    }

    public FilmGenre getByFilmAndGenre(int film, int actor) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_FILM_GENRE, film, actor);
        return rs.next() ? new FilmGenre(rs) : null;
    }

    @Override
    public FilmGenre add(FilmGenre filmGenre) throws SQLException {
        FilmGenre filmGenreExist = getByFilmAndGenre(filmGenre.getFilmId(), filmGenre.getGenreId());
        if (filmGenreExist != null) return null;

        prepareUpdate(ADD_CAST,
                filmGenre.getFilmId(),
                filmGenre.getGenreId());

        return getByFilmAndGenre(filmGenre.getFilmId(), filmGenre.getGenreId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<FilmGenre> getAll() throws SQLException {
        List<FilmGenre> filmGenres = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            filmGenres.add(new FilmGenre(rs));
        }
        return filmGenres;
    }
}
