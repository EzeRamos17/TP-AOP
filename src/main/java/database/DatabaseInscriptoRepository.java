package database;

import model.Inscripto;
import model.InscriptoRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInscriptoRepository implements InscriptoRepository {
    private final String url;
    private final String user;
    private final String password;

    public DatabaseInscriptoRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void guardarInscripto(Inscripto inscripto) throws SQLException {
        String sql = "INSERT INTO inscripto (apellido, nombre, telefono, email, id_concurso) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inscripto.getApellido());
            stmt.setString(2, inscripto.getNombre());
            stmt.setString(3, inscripto.getTelefono());
            stmt.setString(4, inscripto.getEmail());
            stmt.setInt(5, inscripto.getIdConcurso());

            stmt.executeUpdate();
        }
    }
} 