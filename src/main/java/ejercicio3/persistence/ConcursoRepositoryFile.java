package ejercicio3.persistence;

import ejercicio3.model.Concurso;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ConcursoRepositoryFile implements ConcursoRepository {
    private String pathArchivo;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public ConcursoRepositoryFile(String pathArchivo) {
        this.pathArchivo = pathArchivo;
    }

    @Override
    public List<Concurso> obtenerConcursosAbiertos() {
        LocalDate hoy = LocalDate.now();
        try {
            return Files.lines(Paths.get(pathArchivo))
                    .map(String::trim)
                    .filter(linea -> !linea.isEmpty())
                    .map(linea -> linea.split(","))
                    .filter(p -> p.length == 4)
                    .map(p -> new Concurso(
                            Integer.parseInt(p[0].trim()),
                            p[1].trim(),
                            LocalDate.parse(p[2].trim(), FORMATO_FECHA),
                            LocalDate.parse(p[3].trim(), FORMATO_FECHA)))
                    .filter(c -> c.estaAbierto(hoy))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}