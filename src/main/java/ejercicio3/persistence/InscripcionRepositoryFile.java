package ejercicio3.persistence;

import ejercicio3.model.Inscripcion;

import java.io.FileWriter;
import java.io.IOException;

public class InscripcionRepositoryFile implements InscripcionRepository {
    private String pathArchivo;

    public InscripcionRepositoryFile(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    @Override
    public void guardarInscripcion(Inscripcion i) {
        try (FileWriter writer = new FileWriter(pathArchivo, true)) {
            writer.write(formatoArchivo(i) + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String formatoArchivo(Inscripcion i) {
        return String.format("%s, %s, %s, %s, %d", i.getApellido(), i.getNombre(), i.getTelefono(),
                i.getEmail(), i.getIdConcurso());
    }

}
