package model;

import java.util.List;

public class ConcursoManager {
    private final ConcursoRepository concursoRepository;
    private final InscriptoRepository inscriptoRepository;

    public ConcursoManager(ConcursoRepository concursoRepository, InscriptoRepository inscriptoRepository) {
        this.concursoRepository = concursoRepository;
        this.inscriptoRepository = inscriptoRepository;
    }

    public List<Concurso> obtenerConcursosAbiertos() throws Exception {
        return concursoRepository.obtenerConcursosAbiertos();
    }

    public void inscribirParticipante(String apellido, String nombre, String telefono, String email, int idConcurso) throws Exception {
        Inscripto inscripto = new Inscripto(apellido, nombre, telefono, email, idConcurso);
        inscriptoRepository.guardarInscripto(inscripto);
    }

    public boolean validarEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean validarTelefono(String telefono) {
        String regex = "\\d{4}-\\d{6}";
        return telefono.matches(regex);
    }
} 