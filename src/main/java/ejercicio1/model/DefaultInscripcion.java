package ejercicio1.model;

import java.util.List;

public class DefaultInscripcion implements Inscripcion {

    private final ParticipantesDatabaseService participantesDataBaseService;

    public DefaultInscripcion(ParticipantesDatabaseService participantesDataBaseService) {
        this.participantesDataBaseService = participantesDataBaseService;
    }

    @Override
    public void nuevoParticipante(String nombre, String telefono, String region) {//dudas

        Participante participante = new Participante(nombre, telefono, region);
        participantesDataBaseService.almacenarParticipante(
                participante.getNombre(),
                participante.getTelefono(),
                participante.getRegion()
        );
    }

    @Override
    public List<Participante> participantes() {
        List<Participante> participantes = participantesDataBaseService.obtenerParticipantes();
        return participantes;
    }
}