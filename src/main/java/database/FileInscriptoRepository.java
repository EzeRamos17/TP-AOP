package database;

import model.Inscripto;
import model.InscriptoRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileInscriptoRepository implements InscriptoRepository {
    private final String rutaArchivo;

    public FileInscriptoRepository(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardarInscripto(Inscripto inscripto) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.println(inscripto.toCSV());
        }
    }
} 