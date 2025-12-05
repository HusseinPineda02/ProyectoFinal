package hospital.modelo;

import java.time.LocalDate;

public class RegistroClinico {

    private int idRegistro;
    private LocalDate fecha;
    private Doctor doctor;
    private Tipo tipo;
    private String descripcion; 

    public enum Tipo {
        CONSULTA,
        CIRUGIA,
        CONTROL,
        URGENCIA,
        LABORATORIO,
        OTRO
    }

    public RegistroClinico(int idRegistro, LocalDate fecha, Doctor doctor,
                           Tipo tipo, String descripcion) {
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.doctor = doctor;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "[" + idRegistro + "] " + fecha + " - " + tipo +
               " - Dr. " + doctor.getNombreCompleto() +
               " - " + descripcion;
    }
}
