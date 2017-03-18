package by.gsu.epamlab.dao.entitys;

import by.gsu.epamlab.dao.models.Role;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends AbstractRepository<Role> {

    private final static String SELECT_ALL = "select * from roles";
    private final static String SELECT_BY_ID = "select * from roles where roles.id=?";
    private final static String SELECT_BY_NAME = "select * from roles where roles.name=?";
    private final static String DELETE_BY_ID = "deletePlace from roles where roles.id=?";
    private final static String ADD_USER = "insert into roles(name) values(?)";

    public RoleRepository(Connection conn) {
        super(conn);
    }

    @Override
    Role createByResultSet(ResultSet rs) throws SQLException {
        throw new NotImplementedException();
    }

    @Override
    public Role getById(int id) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_ID, id);
        return rs.next() ? new Role(rs) : null;
    }

    public List<Role> getByIds(Integer... ids) throws SQLException {
        List<Role> listFilms = new ArrayList<>();
        for (int id: ids) {
            listFilms.add(getById(id));
        }
        return listFilms;
    }

    public Role getByName(String name) throws SQLException {
        ResultSet rs = prepareRequest(SELECT_BY_NAME, name);
        return rs.next() ? new Role(rs) : null;
    }

    @Override
    public Role add(Role role) throws SQLException {
        Role roleExist = getByName(role.getName());
        if (roleExist != null) return null;
        prepareUpdate(ADD_USER, role.getName());
        return getByName(role.getName());
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return prepareUpdate(DELETE_BY_ID, id) == 1;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        List<Role> roles = new ArrayList<>();
        ResultSet rs = request(SELECT_ALL);
        while (rs.next()) {
            roles.add(new Role(rs));
        }
        return roles;
    }
}
