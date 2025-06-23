package ejercicio3.main;

import ejercicio3.persistence.*;
import ejercicio3.ui.RadioCompetition;

import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Main().start();
                } catch (Exception e) {
                    // log exception...
                    System.out.println(e);
                }
            }
        });
    }

    private void start() {
        var cm = new ConnectionManager("jdbc:derby:mydb;create=true", "", "");
        new SetupDatabase(cm).setup();

        // Repositorios que usan Derby
        ConcursoRepository concursoRepo = new ConcursoRepositoryDB(cm);
        InscripcionRepository inscripcionRepo = new InscripcionRepositoryDB(cm);

        //new RadioCompetition(concursoRepo, inscripcionRepo);

        new RadioCompetition(new ConcursoRepositoryFile("C:/Users/1lau_/Desktop/concursos.txt"),
                new InscripcionRepositoryFile("C:/Users/1lau_/Desktop/inscriptos.txt"));
    }
}
