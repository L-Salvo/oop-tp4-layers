package ejercicio1.model;

import java.util.List;

public interface ParticipantesDatabaseService {

    void almacenarParticipante(String nombre, String telefono, String region);

    List<Participante> obtenerParticipantes();
}
