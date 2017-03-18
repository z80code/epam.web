package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Genre {
    private int id;
    private String name;
    private String info;

    public Genre() {
    }

    public Genre(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
             resultSet.getString(2),
             resultSet.getString(3));
    }


    public Genre(String name, String info) {
        this(0, name,  info);
    }

    public Genre(int id, String name, String info) {
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
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
