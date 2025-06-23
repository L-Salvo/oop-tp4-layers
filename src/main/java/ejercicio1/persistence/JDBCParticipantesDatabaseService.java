package ejercicio1.persistence;

import ejercicio1.model.Participante;
import ejercicio1.model.ParticipantesDatabaseService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  JDBCParticipantesDatabaseService implements ParticipantesDatabaseService {

    private final ConnectionManager conn;

    public JDBCParticipantesDatabaseService(String url, String user, String password) {
        this.conn = new ConnectionManager(url, user, password);
    }


    @Override
    public void almacenarParticipante(String nombre, String telefono, String region) {

        String sql = "INSERT INTO participantes (nombre, telefono, region) VALUES (?, ?, ?)";

        try(var conexion = conn.connect();
            var st = conexion.prepareStatement(sql)){

            st.setString(1, nombre);
            st.setString(2, telefono);
            st.setString(3, region);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Participante> obtenerParticipantes() {
        String sql = "SELECT * FROM participantes";
        try (var conn = this.conn.connect();
             var stmt = conn.prepareStatement(sql);
             var rs = stmt.executeQuery()) {
            List<Participante> participantes = new ArrayList<>();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String region = rs.getString("region");
                participantes.add(new Participante(nombre, telefono, region));
            }
            return participantes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
