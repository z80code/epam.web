package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Actor extends Man {

    public Actor() {
    }

    public Actor(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public Actor(String name, String info) {
        this(0, name, info);
    }

    public Actor(int id, String name, String info) {
        super(id, name, info);
    }

}
