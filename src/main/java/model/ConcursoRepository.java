package model;

import java.util.List;

public interface ConcursoRepository {
    List<Concurso> obtenerConcursosAbiertos() throws Exception;
} 