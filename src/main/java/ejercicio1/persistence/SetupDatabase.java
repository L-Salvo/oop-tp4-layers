package ejercicio1.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDatabase {
    private final String username;
    private final String pwd;
    private final String conn;

    public SetupDatabase(String conn, String username, String pwd) {
        this.conn = conn;
        this.username = username;
        this.pwd = pwd;
    }

    public void setup() {
        try (var connection = DriverManager.getConnection(conn, username, pwd)) {
            var stmt = connection.createStatement();
            createTableParticipantes(stmt); // Primero crea la tabla si no existe
            truncateTableParticipantes(stmt); // Luego la limpia si existe
            restartIdentity(stmt); // Opcional: reinicia el contador IDENTITY
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void truncateTableParticipantes(Statement stmt) {
        try {
            stmt.executeUpdate("TRUNCATE TABLE participantes");
        } catch (Exception e) {
            System.out.println("[INFO] No se pudo truncar la tabla 'participantes'. ¿Existe?");
        }
    }

    private void restartIdentity(Statement stmt) {
        try {
            stmt.executeUpdate("ALTER TABLE participantes ALTER COLUMN id_participante RESTART WITH 1");
        } catch (Exception e) {
            System.out.println("[INFO] No se pudo reiniciar el contador IDENTITY.");
        }
    }

    private void createTableParticipantes(Statement stmt) throws SQLException {
        // Derby no soporta "CREATE TABLE IF NOT EXISTS", así que atrapamos la excepción si ya existe
        try {
            stmt.executeUpdate("CREATE TABLE participantes ("
                    + "id_participante INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "nombre VARCHAR(100), "
                    + "telefono VARCHAR(20), "
                    + "region VARCHAR(50))");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) { // X0Y32 = tabla ya existe en Derby
                throw e;
            }
        }
    }
}
