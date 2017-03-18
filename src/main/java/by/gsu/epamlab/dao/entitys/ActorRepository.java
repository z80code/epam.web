package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Actor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorRepository extends AbstractRepository<Actor> {

    private final static String SELECT_ALL = "select * from actors";
    private final static String SELECT_BY_ID = "select * from actors where actors.id=?";
    private final static String SELECT_BY_NAME = "select * from actors where actors.name=?";
    private final static String DELETE_BY_ID = "deletePlace from actors where actors.id=?";
    private final static String ADD_USER = "insert into actors(name, info) values(?,?)";

    public ActorRepository(Connection conn) {
        super(conn);
    }

    @Override
    Actor createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }


    @Override
    public Actor getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Actor(rs) : null;
    }

    public List<Actor> getByIds(Integer... ids) throws SQLException {
        List<Actor> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public Actor getByName(String name) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_NAME, name);
        return rs.next() ? new Actor(rs) : null;
    }

    @Override
    public Actor add(Actor actor) throws SQLException {
        Actor actorExist = getByName(actor.getName());
        if (actorExist != null) return null;

        return prepareUpdate(ADD_USER, actor.getName(), actor.getInfo()) == 1 ?
                getByName(actor.getName()) : null;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Actor> getAll() throws SQLException {
        List<Actor> actors = new ArrayList<>();
        ResultSet rs =request(SELECT_ALL);
        while (rs.next()) {
            actors.add(new Actor(rs));
        }
        return actors;
    }
}
