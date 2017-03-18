package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Theater;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheaterRepository extends AbstractRepository<Theater> {

    private final static String SELECT_ALL = "select * from theaters";
    private final static String SELECT_BY_ID = "select * from theaters where theaters.id=?";
    private final static String SELECT_BY_NAME = "select * from theaters where theaters.name=?";
    private final static String DELETE_BY_ID = "deletePlace from theaters where theaters.id=?";
    private final static String ADD_USER = "insert into theaters(name, location, phone) values(?,?,?)";

    public TheaterRepository(Connection conn) {
        super(conn);
    }

    @Override
    Theater createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Theater getById(int id) throws SQLException {
        ResultSet rs =prepareRequest(SELECT_BY_ID,id);
        return rs.next() ? new Theater(rs) : null;
    }

    public List<Theater> getByIds(Integer... ids) throws SQLException {
        List<Theater> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public Theater getByName(String login) throws SQLException {
        ResultSet rs =prepareRequest(SELECT_BY_NAME,login);
        return rs.next() ? new Theater(rs) : null;
    }

    @Override
    public Theater add(Theater theater) throws SQLException {
        Theater theaterExist = getByName(theater.getName());
        if (theaterExist != null) return null;

        prepareUpdate(ADD_USER,
                theater.getName(),
                theater.getLocation(),
                theater.getPhone());

        return getByName(theater.getName());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id)==1;
    }

    @Override
    public List<Theater> getAll() throws SQLException {
        List<Theater> theaters = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            theaters.add(new Theater(rs));
        }
        return theaters;
    }
}
