package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Role {
    private int id;
    private String name;

    public Role() {
    }
    public Role(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
             resultSet.getString(2));
    }

    public Role(String name) {
        this(0, name);
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
