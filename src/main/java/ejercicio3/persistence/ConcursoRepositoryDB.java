package ejercicio3.persistence;

import ejercicio3.model.Concurso;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConcursoRepositoryDB implements ConcursoRepository {

    private final ConnectionManager connectionManager;

    public ConcursoRepositoryDB(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Concurso> obtenerConcursosAbiertos() {
        List<Concurso> concursos = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        try (Connection conn = connectionManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM concursos")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                LocalDate inicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate fin = rs.getDate("fecha_fin").toLocalDate();

                Concurso c = new Concurso(id, nombre, inicio, fin);
                if (c.estaAbierto(hoy)) {
                    concursos.add(c);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los concursos abiertos", e);
        }

        return concursos;
    }
}