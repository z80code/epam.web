package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Director;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorRepository extends AbstractRepository<Director> {

    private final static String SELECT_ALL = "select * from directors";
    private final static String SELECT_BY_ID = "select * from directors where directors.id=?";
    private final static String SELECT_BY_NAME = "select * from directors where directors.name=?";
    private final static String DELETE_BY_ID = "deletePlace from directors where directors.id=?";
    private final static String ADD_USER = "insert into directors(name, info) values(?,?)";

    public DirectorRepository(Connection conn) {
        super(conn);
    }

    @Override
    Director createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Director getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Director(rs) : null;
    }

    public List<Director> getByIds(Integer... ids) throws SQLException {
        List<Director> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public Director getByName(String name) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_NAME, name);
        return rs.next() ? new Director(rs) : null;
    }

    @Override
    public Director add(Director director) throws SQLException {
        Director directorExist = getByName(director.getName());
        if (directorExist != null) return null;

        return prepareUpdate(ADD_USER, director.getName(), director.getInfo()) == 1 ?
                getByName(director.getName()) : null;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Director> getAll() throws SQLException {
        List<Director> directors = new ArrayList<>();
        ResultSet rs =request(SELECT_ALL);
        while (rs.next()) {
            directors.add(new Director(rs));
        }
        return directors;
    }
}
