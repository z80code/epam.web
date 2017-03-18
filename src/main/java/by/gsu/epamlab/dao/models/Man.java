package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Man {
    private int id;
    private String name;
    private String info;

    Man() {
    }

    Man(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
    }
    public Man(String name, String info) {
        this(0, name, info);
    }

    Man(int id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Man{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
