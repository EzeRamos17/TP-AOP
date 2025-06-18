package model;

public class Inscripto {
    private String apellido;
    private String nombre;
    private String telefono;
    private String email;
    private int idConcurso;

    public Inscripto(String apellido, String nombre, String telefono, String email, int idConcurso) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.idConcurso = idConcurso;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public String toCSV() {
        return String.format("%s, %s, %s, %s, %d",
                apellido, nombre, telefono, email, idConcurso);
    }
} 