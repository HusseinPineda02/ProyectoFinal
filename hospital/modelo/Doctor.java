package hospital.modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Trabajador {

    private String especialidad;
    private List<Cita> citas;
    private List<Cirugia> cirugias;
    private List<Horario> horarios = new ArrayList<>();

    public Doctor(int idTrabajador, String dni, String nombre, String apellidoP,
                  String apellidoM, double salario, String especialidad) {
        super(idTrabajador, dni, nombre, apellidoP, apellidoM, salario, "Doctor");
        this.especialidad = especialidad;
        this.citas = new ArrayList<>();
        this.cirugias = new ArrayList<>();
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public List<Cirugia> getCirugias() {
        return cirugias;
    }

    public void agregarCita(Cita cita) {
        this.citas.add(cita);
    }

    public void agregarCirugia(Cirugia cirugia) {
        this.cirugias.add(cirugia);
    }

    public boolean estaDisponible(LocalDate fecha, LocalTime hora) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return horarios.stream()
            .anyMatch(h -> h.getDia() == dia && h.cubre(hora));
    }

    @Override
    public String toString() {
        return super.toString() + " - Especialidad: " + especialidad;
    }
}