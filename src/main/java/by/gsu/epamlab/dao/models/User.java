package by.gsu.epamlab.dao.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User extends Man {

    private String login;
    private String password;
    private int role;

    public User() {
    }

    public User(ResultSet resultSet) throws SQLException {
        this(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6));
    }

    public User(String login, String password, int role, String name, String info) {
        this(0, login, password, role, name, info);

    }

    public User(int id, String login, String password, int role, String name, String info) {
        super(id, name, info);
        this.login = login;
        this.password = password;
        this.role = role;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                super.toString() +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
