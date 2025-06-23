package ejercicio3.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private final String url;
    private final String user;
    private final String password;

    public ConnectionManager(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    Connection connect() throws SQLException {

        try {
            return DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            throw new RuntimeException("Error, vuelva a intentarlo mas tarde..", e);
        }

    }

}
