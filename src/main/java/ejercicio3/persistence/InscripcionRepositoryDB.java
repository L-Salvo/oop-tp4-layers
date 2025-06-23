package ejercicio3.persistence;

import ejercicio3.model.Inscripcion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscripcionRepositoryDB implements InscripcionRepository {

    private final ConnectionManager connectionManager;

    public InscripcionRepositoryDB(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void guardarInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscriptos (apellido, nombre, telefono, email, idconcurso) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, inscripcion.getApellido());
            ps.setString(2, inscripcion.getNombre());
            ps.setString(3, inscripcion.getTelefono());
            ps.setString(4, inscripcion.getEmail());
            ps.setInt(5, inscripcion.getIdConcurso());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la inscripción", e);
        }
    }
}