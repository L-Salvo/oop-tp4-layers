package ejercicio1.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class  JDBCParticipantesDatabaseService implements ParticipantesDatabaseService{

    private final ConnectionManager conn;

    public JDBCParticipantesDatabaseService(String url, String user, String password) {
        this.conn = new ConnectionManager(url, user, password);
    }


    @Override
    public void almacenarParticipante(String nombre, String telefono, String region) {

        try(var conexion = conn.connect()){
            PreparedStatement st = dbConn
                    .prepareStatement("insert
                            into
                            values(?,?,?)");
            try {
                st.setString(1, nombre.getText());
                st.setString(2, telefono.getText());
                st.setString(3, region.getText());
                st.executeUpdate();
            } finally {
                st.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
