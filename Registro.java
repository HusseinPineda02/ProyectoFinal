import java.time.LocalDate;

public class Registro {
    private LocalDate fecha;
    private String descripcion;
    private Doctor doctor;

    public Registro(LocalDate fecha, String descripcion, Doctor doctor) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.doctor = doctor;
    }

    public LocalDate getFecha() {
        return fecha; 
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}