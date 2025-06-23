package ejercicio3.persistence;

import java.sql.*;


public class SetupDatabase {

    private final ConnectionManager connectionManager;

    public SetupDatabase(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void setup() {
        try (Connection connection = connectionManager.connect()) {
            Statement stmt = connection.createStatement();
            createTableConcursos(stmt);
            createTableInscriptos(stmt);
            insertarConcursosPorDefecto(connection);
        } catch (Exception e) {
            throw new RuntimeException("Error al inicializar la base de datos", e);
        }
    }

    private void createTableConcursos(Statement stmt) throws SQLException {
        try {
            stmt.executeUpdate("""
                CREATE TABLE concursos (
                    id INT PRIMARY KEY,
                    nombre VARCHAR(100),
                    fecha_inicio DATE,
                    fecha_fin DATE
                )
            """);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) { // tabla ya existe
                throw e;
            }
        }
    }

    private void createTableInscriptos(Statement stmt) throws SQLException {
        try {
            stmt.executeUpdate("""
                CREATE TABLE inscriptos (
                    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    apellido VARCHAR(50),
                    nombre VARCHAR(50),
                    telefono VARCHAR(20),
                    email VARCHAR(100),
                    idconcurso INT,
                    FOREIGN KEY (idconcurso) REFERENCES concursos(id)
                )
            """);
        } catch (SQLException e) {
            if (!"X0Y32".equals(e.getSQLState())) { // tabla ya existe
                throw e;
            }
        }
    }

    private void insertarConcursosPorDefecto(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM concursos")) {

            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                PreparedStatement ps = conn.prepareStatement("""
                    INSERT INTO concursos (id, nombre, fecha_inicio, fecha_fin)
                    VALUES (?, ?, ?, ?)
                """);

                ps.setInt(1, 1);
                ps.setString(2, "Concurso Rock Nacional");
                ps.setDate(3, Date.valueOf("2025-06-01"));
                ps.setDate(4, Date.valueOf("2025-07-01"));
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setString(2, "Concurso Jazz Patagónico");
                ps.setDate(3, Date.valueOf("2025-06-15"));
                ps.setDate(4, Date.valueOf("2025-07-15"));
                ps.executeUpdate();

                ps.setInt(1, 3);
                ps.setString(2, "Concurso Techno Andino");
                ps.setDate(3, Date.valueOf("2025-05-01"));
                ps.setDate(4, Date.valueOf("2025-05-31"));
                ps.executeUpdate();

                ps.close();
                System.out.println("[INFO] Concursos de prueba insertados.");
            }

        }
    }
}