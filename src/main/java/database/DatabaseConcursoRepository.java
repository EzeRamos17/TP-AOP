package database;

import model.Concurso;
import model.ConcursoRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConcursoRepository implements ConcursoRepository {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseConcursoRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public List<Concurso> obtenerConcursosAbiertos() throws SQLException {
        List<Concurso> concursos = new ArrayList<>();
        String sql = "SELECT id, nombre, fecha_inicio, fecha_fin FROM concurso WHERE fecha_inicio <= ? AND fecha_fin >= ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            LocalDate hoy = LocalDate.now();
            stmt.setDate(1, Date.valueOf(hoy));
            stmt.setDate(2, Date.valueOf(hoy));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Concurso concurso = new Concurso(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDate("fecha_inicio").toLocalDate(),
                            rs.getDate("fecha_fin").toLocalDate()
                    );
                    concursos.add(concurso);
                }
            }
        }
        return concursos;
    }
} 