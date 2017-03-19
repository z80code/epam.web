package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Session;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SessionRepository extends AbstractRepository<Session> {

    private final static String SELECT_ALL = "select * from sessions";
    private final static String SELECT_BY_ID = "select * from sessions where sessions.id=?";
    private final static String SELECT_BY_PATH = "select * from sessions where 1 limit ?, ?";
    private final static String SELECT_ALL_BY_FILM_ID = "select * from sessions where sessions.filmId=?";
    private final static String SELECT_BY_SESSION = "select * from sessions where sessions.filmId=? and sessions.dateTime=? and sessions.theaterId=?";
    private final static String DELETE_BY_ID = "delete * from sessions where sessions.id=?";
    private final static String ADD_USER = "insert into sessions(filmId, dateTime, theaterId) values(?,?,?)";

    public SessionRepository(Connection conn) {
        super(conn);
    }

    @Override
    Session createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Session getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Session(rs) : null;
    }

    public List<Session> getByIds(Integer... ids) throws SQLException {
        List<Session> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public List<Session> getByFilmIds(Integer... ids) throws SQLException {
        List<Session> listFilms = new ArrayList<>();

        for (Integer id: ids) {
            ResultSet rs = prepareRequest(SELECT_ALL_BY_FILM_ID, id);
            while (rs.next()) {
                listFilms.add(new Session(rs));
            }
        }
        return listFilms;
    }

    public Session getByUserSession(int filmId, Timestamp dateTime, int theaterId) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_SESSION, filmId, dateTime, theaterId);
        return rs.next() ? new Session(rs) : null;
    }

    @Override
    public Session add(Session session) throws SQLException {
        Session sessionExist = getByUserSession(session.getFilmId(), session.getDateTime(), session.getTheaterId());
        if (sessionExist != null) return null;

        prepareUpdate(ADD_USER,
                session.getFilmId(),
                session.getDateTime(),
                session.getTheaterId());

        return getByUserSession(session.getFilmId(), session.getDateTime(), session.getTheaterId());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Session> getAll() throws SQLException {
        List<Session> sessions = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            sessions.add(new Session(rs));
        }
        return sessions;
    }

    public List<Session> getAll(int firstSession, int numberSession) throws SQLException {
        List<Session> sessions = new ArrayList<>();
        ResultSet rs = prepareRequest(SELECT_BY_PATH, firstSession, numberSession);
        while (rs.next()) {
            sessions.add(new Session(rs));
        }
        return sessions;
    }
}
