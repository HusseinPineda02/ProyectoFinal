package hospital.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {

    private int idCita;
    private Paciente paciente;
    private Doctor doctor;
    private LocalDate fecha;
    private LocalTime hora;
    private String motivo;
    private Estado estado;

    public enum Estado {
        PROGRAMADA,
        ATENDIDA,
        CANCELADA
    }

    public Cita(int idCita, Paciente paciente, Doctor doctor,
                LocalDate fecha, LocalTime hora, String motivo, Estado estado) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.doctor = doctor;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.estado = estado;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    @Override
    public String toString() {
        return "Cita #" + idCita + " - " + fecha + " " + hora +
               " - Paciente: " + paciente.getNombreCompleto() +
               ", Doctor: " + doctor.getNombreCompleto() +
               " (" + estado + ")";
    }
}
