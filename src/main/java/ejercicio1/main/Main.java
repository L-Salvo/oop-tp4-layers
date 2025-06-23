package ejercicio1.main;

import ejercicio1.model.DefaultInscripcion;
import ejercicio1.model.Inscripcion;
import ejercicio1.persistence.JDBCParticipantesDatabaseService;
import ejercicio1.model.ParticipantesDatabaseService;
import ejercicio1.persistence.SetupDatabase;
import ejercicio1.ui.AgregarParticipante;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        final String url = "jdbc:derby:memory:inscripcion;create=true";
        final String user = "app";
        final String password = "app";

        var jdbc = new SetupDatabase(url, user, password);
        jdbc.setup();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Inscripcion inscriptos = new DefaultInscripcion(new JDBCParticipantesDatabaseService(url, user, password));
                    new AgregarParticipante(inscriptos).setVisible(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
