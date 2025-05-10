package ejercicio1.main;

import ejercicio1.ui.AgregarParticipante;

import java.awt.EventQueue;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AgregarParticipante();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }
}
