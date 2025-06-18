package database;

import model.Concurso;
import model.ConcursoRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileConcursoRepository implements ConcursoRepository {
    private final String rutaArchivo;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public FileConcursoRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Concurso> obtenerConcursosAbiertos() throws IOException {
        List<Concurso> concursos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0].trim());
                    String nombre = datos[1].trim();
                    LocalDate fechaInicio = LocalDate.parse(datos[2].trim(), formatter);
                    LocalDate fechaFin = LocalDate.parse(datos[3].trim(), formatter);

                    Concurso concurso = new Concurso(id, nombre, fechaInicio, fechaFin);
                    if (concurso.estaAbierto()) {
                        concursos.add(concurso);
                    }
                }
            }
        }
        return concursos;
    }
} 