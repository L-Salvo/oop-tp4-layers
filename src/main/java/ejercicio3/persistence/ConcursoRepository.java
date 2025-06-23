package ejercicio3.persistence;

import ejercicio3.model.Concurso;
import java.util.List;

public interface ConcursoRepository {
    List<Concurso> obtenerConcursosAbiertos();
}