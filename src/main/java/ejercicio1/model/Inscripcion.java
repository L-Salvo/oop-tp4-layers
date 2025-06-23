package ejercicio1.model;

import java.util.List;

public interface Inscripcion {
    void nuevoParticipante(String nombre, String telefono, String region);

    List<Participante> participantes();
}