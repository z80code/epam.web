package by.gsu.epamlab.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
            String dbUrl = "jdbc:mysql://localhost/webfilms?useSSL=false";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(dbUrl, user, password);
    }
}
