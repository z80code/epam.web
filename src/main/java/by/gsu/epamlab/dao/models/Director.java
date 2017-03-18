package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Director extends Man {
    public Director() {
    }

    public Director(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public Director(String name, String info) {
        this(0, name, info);
    }

    public Director(int id, String name, String info) {
        super(id, name, info);
    }

}
