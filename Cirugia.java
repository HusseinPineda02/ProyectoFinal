package hospital.modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Cirugia {

    private int idCirugia;
    private Paciente paciente;
    private Doctor cirujano;
    private List<Enfermero> equipoEnfermeria;
    private Habitacion quirofano;
    private LocalDate fecha;
    private LocalTime hora;
    private String descripcion;
    private Estado estado;

    public enum Estado {
        PROGRAMADA,
        REALIZADA,
        CANCELADA
    }

    public Cirugia(int idCirugia, Paciente paciente, Doctor cirujano,
                   Habitacion quirofano, LocalDate fecha, LocalTime hora,
                   String descripcion, Estado estado) {
        this.idCirugia = idCirugia;
        this.paciente = paciente;
        this.cirujano = cirujano;
        this.quirofano = quirofano;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.estado = estado;
        this.equipoEnfermeria = new ArrayList<>();
    }

    public int getIdCirugia() {
        return idCirugia;
    }

    public void setIdCirugia(int idCirugia) {
        this.idCirugia = idCirugia;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getCirujano() {
        return cirujano;
    }

    public void setCirujano(Doctor cirujano) {
        this.cirujano = cirujano;
    }

    public List<Enfermero> getEquipoEnfermeria() {
        return equipoEnfermeria;
    }

    public void agregarEnfermero(Enfermero e) {
        this.equipoEnfermeria.add(e);
    }

    public Habitacion getQuirofano() {
        return quirofano;
    }

    public void setQuirofano(Habitacion quirofano) {
        this.quirofano = quirofano;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cirugía #" + idCirugia + " - Paciente: " + paciente.getNombreCompleto() +
               " - Cirujano: " + cirujano.getNombreCompleto() +
               " - Fecha: " + fecha + " " + hora +
               " - Estado: " + estado;
    }
}
